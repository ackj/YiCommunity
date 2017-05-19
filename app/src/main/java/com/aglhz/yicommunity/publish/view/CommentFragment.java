package com.aglhz.yicommunity.publish.view;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.abase.utils.ScreenUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.CommentBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.event.KeyboardChangeListener;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.publish.contract.CommentContract;
import com.aglhz.yicommunity.publish.presenter.CommentPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

import static com.aglhz.yicommunity.neighbour.view.MessageFragment.TYPE_CARPOOL_OWNER;
import static com.aglhz.yicommunity.neighbour.view.MessageFragment.TYPE_CARPOOL_passenger;
import static com.aglhz.yicommunity.neighbour.view.MessageFragment.TYPE_EXCHANGE;
import static com.aglhz.yicommunity.neighbour.view.MessageFragment.TYPE_MY_CARPOOL;
import static com.aglhz.yicommunity.neighbour.view.MessageFragment.TYPE_MY_EXCHANGE;
import static com.aglhz.yicommunity.neighbour.view.MessageFragment.TYPE_MY_NEIGHBOUR;
import static com.aglhz.yicommunity.neighbour.view.MessageFragment.TYPE_NEIGHBOUR;

/**
 * Author: LiuJia on 2017/5/11 0011 15:52.
 * Email: liujia95me@126.com
 */

public class CommentFragment extends BaseFragment<CommentPresenter> implements CommentContract.View {

    private static final String TAG = CommentFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.et_input_fragment_comment)
    EditText etInputFragmentComment;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.view_bottom_space)
    View viewBottomSpace;


    private Unbinder unbinder;
    private CommentRVAdapter adapter;
    private Params commentListParams = Params.getInstance();
    private Params commentPostParams = Params.getInstance();

    private String fid;
    private int type;
    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;

    public static CommentFragment newInstance(String fid, int type) {
        ALog.e(TAG, "newInstance type:" + type);
        CommentFragment fragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fid", fid);
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected CommentPresenter createPresenter() {
        return new CommentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ALog.e(TAG, "onCreateView type:" + type);
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ALog.e(TAG, "onViewCreated type:" + type);
        Bundle bundle = getArguments();
        fid = bundle.getString("fid");
        type = bundle.getInt("type");
        initToolbar();
        initData();
        initPtrFrameLayout();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("评论留言");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initPtrFrameLayout() {
        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(BaseApplication.mContext, 15F), 0, DensityUtils.dp2px(BaseApplication.mContext, 10F));
        header.setPtrFrameLayout(ptrFrameLayout);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.postDelayed(() -> ptrFrameLayout.autoRefresh(true), 100);

        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //判断是否滑动到顶部。
                return ScrollingHelper.isRecyclerViewToTop(recyclerView);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
                commentListParams.fid = fid;
                commentListParams.page = 1;
                commentListParams.pageSize = Constants.PAGE_SIZE;
                requestComments();
            }
        });
    }

    private KeyboardChangeListener mKeyboardChangeListener;

    private boolean isVisiableForLast;

    private void initListener() {
        mKeyboardChangeListener = new KeyboardChangeListener(_mActivity);
        mKeyboardChangeListener.setKeyBoardListener((isShow, keyboardHeight) -> ALog.e(TAG, "isShow = [" + isShow + "], keyboardHeight = [" + keyboardHeight + "]"));

        final View decorView = _mActivity.getWindow().getDecorView();
        //计算出可见屏幕的高度
        //获得屏幕整体的高度
        //获得键盘高度
        globalLayoutListener = () -> {
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            //计算出可见屏幕的高度
            int displayHight = rect.bottom - rect.top;
            //获得屏幕整体的高度
            int hight = decorView.getHeight();
            //获得键盘高度
            int keyboardHeight = hight - displayHight;
            boolean visible = (double) displayHight / hight < 0.8;
            if (visible != isVisiableForLast) {
                listener.onSoftKeyBoardVisible(visible, keyboardHeight);
            }
            isVisiableForLast = visible;
        };
        //注册布局变化监听
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
    }

    IKeyBoardVisibleListener listener = (visible, windowBottom) -> {
        windowBottom -= ScreenUtils.getStatusBarHeight(_mActivity);
        if (visible) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, windowBottom);
            viewBottomSpace.setLayoutParams(lp);
        } else {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            viewBottomSpace.setLayoutParams(lp);
        }
        ALog.d("onSoftKeyBoardVisible ---- visible:" + visible + " windowBottom:" + windowBottom);
    };

    interface IKeyBoardVisibleListener {
        void onSoftKeyBoardVisible(boolean visible, int windowBottom);
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new CommentRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            commentListParams.page++;
            ALog.e("加载更多………………………………");
            requestComments();
        }, recyclerView);

        recyclerView.setAdapter(adapter);
    }

    private void requestComments() {
        ALog.e(" commentListParams.page++" + commentListParams.page);
        switch (type) {
            case TYPE_EXCHANGE:
            case TYPE_MY_EXCHANGE:
                mPresenter.requestExchangeCommentList(commentListParams);
                break;
            case TYPE_CARPOOL_OWNER:
            case TYPE_CARPOOL_passenger:
            case TYPE_MY_CARPOOL:
                mPresenter.requestCarpoolCommentList(commentListParams);
                break;
            case TYPE_NEIGHBOUR:
            case TYPE_MY_NEIGHBOUR:
                mPresenter.requestNeighbourCommentList(commentListParams);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        KeyBoardUtils.hideKeybord(getView(), _mActivity);
        //移除布局变化监听
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            _mActivity.getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(globalLayoutListener);
        } else {
            _mActivity.getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(globalLayoutListener);
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        if (commentListParams.page == 1) {
            //为后面的pageState做准备
        } else if (commentListParams.page > 1) {
            adapter.loadMoreFail();
            commentListParams.page--;
        }
        DialogHelper.warningSnackbar(getView(), errorMessage);//后面换成pagerstate的提示，不需要这种了
    }

    @Override
    public void responseCommentList(List<CommentBean> datas) {
        ptrFrameLayout.refreshComplete();

        if (datas == null || datas.isEmpty()) {
            adapter.loadMoreEnd();
            return;
        }

        ALog.e("datas::" + datas.size());
        if (commentListParams.page == 1) {
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void responseCommentSuccess(BaseBean bean) {
        etInputFragmentComment.setText("");
        ptrFrameLayout.autoRefresh();
    }

    @OnClick(R.id.tv_send_fragment_comment)
    public void onViewClicked() {
        sendComment();
    }

    private void sendComment() {
        String comment = etInputFragmentComment.getText().toString().trim();
        if (TextUtils.isEmpty(comment)) {
            DialogHelper.warningSnackbar(getView(), "请输入评论内容！不能为空");
            return;
        }
        commentPostParams.fid = fid;
        commentPostParams.content = comment;
        switch (type) {
            case TYPE_EXCHANGE:
            case TYPE_MY_EXCHANGE:
                mPresenter.postExchangeComment(commentPostParams);
                break;
            case TYPE_CARPOOL_OWNER:
            case TYPE_CARPOOL_passenger:
            case TYPE_MY_CARPOOL:
                mPresenter.postCarpoolComment(commentPostParams);
                break;
            case TYPE_NEIGHBOUR:
            case TYPE_MY_NEIGHBOUR:
                mPresenter.postNeighbourComment(commentPostParams);
                break;
        }
    }
}
