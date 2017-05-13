package com.aglhz.yicommunity.publish.view;

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
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.CommentBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.KeyboardChangeListener;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.publish.contract.CommentContract;
import com.aglhz.yicommunity.publish.presenter.CommentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

import static com.aglhz.yicommunity.neighbour.view.MessageFragment.TYPE_CARPOOL;
import static com.aglhz.yicommunity.neighbour.view.MessageFragment.TYPE_EXCHANGE;
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


    private Unbinder unbinder;
    private CommentRVAdapter adapter;
    private Params commentListParams = Params.getInstance();
    private Params commentPostParams = Params.getInstance();

    private String fid;
    private int type;

    public static CommentFragment newInstance(String fid, int type) {
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
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        ptrFrameLayout.autoRefresh(true);
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
                requestComments();
            }
        });
    }

    private KeyboardChangeListener mKeyboardChangeListener;

    private void initListener() {
        mKeyboardChangeListener = new KeyboardChangeListener(_mActivity);
        mKeyboardChangeListener.setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                ALog.d(TAG, "isShow = [" + isShow + "], keyboardHeight = [" + keyboardHeight + "]");
            }
        });
    }

    private void initData() {
        requestComments();

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new CommentRVAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void requestComments() {
        commentListParams.fid = this.fid;
        commentListParams.page = 1;
        commentListParams.pageSize = 10;
        switch (type) {
            case TYPE_EXCHANGE:
                mPresenter.requestExchangeCommentList(commentListParams);
                break;
            case TYPE_CARPOOL:
                mPresenter.requestCarpoolCommentList(commentListParams);
                break;
            case TYPE_NEIGHBOUR:
                mPresenter.requestNeighbourCommentList(commentListParams);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseCommentList(List<CommentBean> datas) {
        ptrFrameLayout.refreshComplete();
        adapter.setNewData(datas);
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
                mPresenter.postExchangeComment(commentPostParams);
                break;
            case TYPE_CARPOOL:
                mPresenter.postCarpoolComment(commentPostParams);
                break;
            case TYPE_NEIGHBOUR:
                mPresenter.postNeighbourComment(commentPostParams);
                break;
        }
    }
}