package com.aglhz.yicommunity.propery.view;

import android.content.Intent;
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

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.NoticeBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.propery.contract.NoticeListContract;
import com.aglhz.yicommunity.propery.presenter.NoticeListPresenter;
import com.aglhz.yicommunity.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/5/9 0009 22:33.
 * Email: liujia95me@126.com
 */

public class NoticeListFragment extends BaseFragment<NoticeListPresenter> implements NoticeListContract.View {
    public static final String TAG = NoticeListFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private Unbinder unbinder;
    private NoticeListRVAdapter adapter;

    public static NoticeListFragment newInstance() {
        return new NoticeListFragment();
    }

    @NonNull
    @Override
    protected NoticeListPresenter createPresenter() {
        return new NoticeListPresenter(this);
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
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("物业公告");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        Params params = Params.getInstance();
        params.cmnt_c = UserHelper.communityCode;
        params.summerable = true;
        params.timeable = true;
        mPresenter.requestNotices(params);

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        ArrayList<NoticeBean.DataBean.NoticeListBean> data = new ArrayList<>();
        adapter = new NoticeListRVAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            NoticeBean.DataBean.NoticeListBean bean = (NoticeBean.DataBean.NoticeListBean) adapter.getData().get(position);
            go2Web("物业公告", ApiService.requestNoticeDetail + bean.getFid());
            return false;
        });
    }

    private void go2Web(String title, String link) {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("link", link);
        _mActivity.startActivity(intent);
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
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseNotices(List<NoticeBean.DataBean.NoticeListBean> datas) {
        adapter.setNewData(datas);
    }
}
