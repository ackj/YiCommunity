package com.aglhz.yicommunity.main.message.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.ComplainReplyBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.abase.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.message.contract.ComplainReplyContract;
import com.aglhz.yicommunity.main.message.presenter.ComplainReplyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/5/18 0018 17:36.
 * Email: liujia95me@126.com
 * [物业投诉回复]的View层。
 */

public class ComplainReplyFragment extends BaseFragment<ComplainReplyContract.Presenter> implements ComplainReplyContract.View {
    private static final String TAG = ComplainReplyFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private Params params = Params.getInstance();
    private Unbinder unbinder;
    private ComlpainReplyRVAdapter adapter;

    /**
     * ComplainReplyFragment 的创建入口。
     * @param complaintFid 物业投诉的fid
     * @return
     */
    public static ComplainReplyFragment newInstance(String complaintFid) {
        ComplainReplyFragment fragment = new ComplainReplyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_FID, complaintFid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected ComplainReplyContract.Presenter createPresenter() {
        return new ComplainReplyPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            params.complaintFid = args.getString(Constants.KEY_FID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("物业投诉回复");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onRefresh() {
        mPresenter.start(params);
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new ComlpainReplyRVAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> ALog.e(position));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 响应请求投诉回复列表
     * @param response
     */
    @Override
    public void start(Object response) {
        ptrFrameLayout.refreshComplete();
        adapter.setNewData(((ComplainReplyBean) response).getData().getReplyList());
    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.errorSnackbar(getView(), errorMessage);
    }
}
