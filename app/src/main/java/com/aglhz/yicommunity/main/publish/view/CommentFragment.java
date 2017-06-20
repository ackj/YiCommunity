package com.aglhz.yicommunity.main.publish.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.abase.utils.ScreenUtils;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.abase.widget.statemanager.StateManager;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.CommentBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.event.KeyboardChangeListener;
import com.aglhz.yicommunity.main.publish.contract.CommentContract;
import com.aglhz.yicommunity.main.publish.presenter.CommentPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.aglhz.yicommunity.main.sociality.view.SocialityListFragment.TYPE_CARPOOL_OWNER;
import static com.aglhz.yicommunity.main.sociality.view.SocialityListFragment.TYPE_CARPOOL_PASSENGER;
import static com.aglhz.yicommunity.main.sociality.view.SocialityListFragment.TYPE_EXCHANGE;
import static com.aglhz.yicommunity.main.sociality.view.SocialityListFragment.TYPE_MY_CARPOOL;
import static com.aglhz.yicommunity.main.sociality.view.SocialityListFragment.TYPE_MY_EXCHANGE;
import static com.aglhz.yicommunity.main.sociality.view.SocialityListFragment.TYPE_MY_NEIGHBOUR;
import static com.aglhz.yicommunity.main.sociality.view.SocialityListFragment.TYPE_NEIGHBOUR;

/**
 * Author: LiuJia on 2017/5/11 0011 15:52.
 * Email: liujia95me@126.com
 * [评论]的View层
 */

public class CommentFragment extends BaseFragment<CommentContract.Presenter> implements CommentContract.View {
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
    String[] arr = {"回复", "复制"};
    private String replyName = "";
    private StateManager mStateManager;

    public static CommentFragment newInstance(String fid, int type) {
        ALog.e(TAG, "newInstance type:" + type);
        CommentFragment fragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_FID, fid);
        bundle.putInt(Constants.KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected CommentContract.Presenter createPresenter() {
        return new CommentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        fid = bundle.getString(Constants.KEY_FID);
        type = bundle.getInt(Constants.KEY_TYPE);
        initToolbar();
        initData();
        initStateManager();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
        initListener();
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyImage(R.drawable.ic_comment_empty_state_gray_200px)
                .setEmptyText("快来抢沙发哦！")
                .setErrorOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setEmptyOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .build();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("评论留言");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onRefresh() {
        commentListParams.fid = fid;
        commentListParams.page = 1;
        commentListParams.pageSize = Constants.PAGE_SIZE;
        requestComments();//请求评论列表
    }

    private KeyboardChangeListener mKeyboardChangeListener;

    private boolean isVisiableForLast;

    @SuppressLint("SetTextI18n")
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

        adapter.setOnItemClickListener((adapter, view, position) -> {
            CommentBean bean = (CommentBean) adapter.getData().get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(_mActivity);
            builder.setItems(arr, (dialog, which) -> {
                switch (which) {
                    case 0:
                        //
                        String inputContent = etInputFragmentComment.getText().toString();
                        if (!TextUtils.isEmpty(inputContent) && inputContent.contains(replyName)) {
                            inputContent = inputContent.substring(replyName.length());
                        }
                        replyName = "@" + bean.getMember().getMemberNickName() + " ";
                        if (!inputContent.startsWith(replyName)) {
                            etInputFragmentComment.setText(replyName + inputContent);
                        }
                        break;
                    case 1:
                        ClipboardManager cm = (ClipboardManager) _mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setPrimaryClip(ClipData.newPlainText(null, bean.getContent()));
                        ToastUtils.showToast(_mActivity, "复制成功");
                        break;
                }
            });
            builder.show();
        });
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
            requestComments();//请求评论列表
        }, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 请求评论列表
     */
    private void requestComments() {
        switch (type) {
            case TYPE_EXCHANGE:
            case TYPE_MY_EXCHANGE:
                mPresenter.requestExchangeCommentList(commentListParams);//请求闲置交换的评论列表
                break;
            case TYPE_CARPOOL_OWNER:
            case TYPE_CARPOOL_PASSENGER:
            case TYPE_MY_CARPOOL:
                mPresenter.requestCarpoolCommentList(commentListParams);//请求拼车服务的评论列表
                break;
            case TYPE_NEIGHBOUR:
            case TYPE_MY_NEIGHBOUR:
                mPresenter.requestNeighbourCommentList(commentListParams);//请求左邻右里的评论列表
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
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
            mStateManager.showError();
        } else if (commentListParams.page > 1) {
            adapter.loadMoreFail();
            commentListParams.page--;
        }
        DialogHelper.warningSnackbar(getView(), errorMessage);//后面换成pagerstate的提示，不需要这种了
    }

    /**
     * 响应请求评论列表
     *
     * @param datas
     */
    @Override
    public void responseCommentList(List<CommentBean> datas) {
        ptrFrameLayout.refreshComplete();
        if (datas == null || datas.isEmpty()) {
            if (commentListParams.page == 1) {
                mStateManager.showEmpty();
            }
            adapter.loadMoreEnd();
            return;
        }

        if (commentListParams.page == 1) {
            mStateManager.showContent();
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    /**
     * 响应请求发送评论成功
     *
     * @param bean
     */
    @Override
    public void responseCommentSuccess(BaseBean bean) {
        etInputFragmentComment.setText("");
        ptrFrameLayout.autoRefresh();
    }

    @OnClick(R.id.tv_send_fragment_comment)
    public void onViewClicked() {
        sendComment();
    }

    /**
     * 发送评论
     */
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
                mPresenter.requestSubmitExchangeComment(commentPostParams);//请求提交闲置交换评论
                break;
            case TYPE_CARPOOL_OWNER:
            case TYPE_CARPOOL_PASSENGER:
            case TYPE_MY_CARPOOL:
                mPresenter.requestSubmitCarpoolComment(commentPostParams);//请求提交拼车服务评论
                break;
            case TYPE_NEIGHBOUR:
            case TYPE_MY_NEIGHBOUR:
                mPresenter.requestSubmitNeighbourComment(commentPostParams);//请求提交左邻右里评论
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }
}
