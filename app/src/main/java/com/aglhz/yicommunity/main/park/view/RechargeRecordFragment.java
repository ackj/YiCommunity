package com.aglhz.yicommunity.main.park.view;

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
import com.aglhz.yicommunity.bean.MonthCardBillListBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.park.presenter.RechargeRecordPresenter;
import com.aglhz.yicommunity.main.park.contract.RechargeRecordContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/5/24 0024 17:48.
 * Email: liujia95me@126.com
 * [充值记录]的View层。
 * 打开方式：StartApp-->管家-->我的车卡-->充值记录
 */

public class RechargeRecordFragment extends BaseFragment<RechargeRecordContract.Presenter> implements RechargeRecordContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    private Unbinder unbinder;
    Params params = Params.getInstance();
    private RechargeReocrdRVAdapter adapter;

    public static RechargeRecordFragment newInstance() {
        return new RechargeRecordFragment();
    }

    @NonNull
    @Override
    protected RechargeRecordContract.Presenter createPresenter() {
        return new RechargeRecordPresenter(this);
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
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
        initData();
        initListener();
    }

    @Override
    public void onRefresh() {
        params.page = 1;
        params.pageSize = 10;
        mPresenter.requestMonthCardBillList(params);//请求月卡的充值记录列表
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("充值记录");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        onRefresh();
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new RechargeReocrdRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.requestMonthCardBillList(params);//请求月卡的充值记录列表
        }, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            pop();
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
        if (params.page == 1) {
            //为后面的pageState做准备
        } else if (params.page > 1) {
            adapter.loadMoreFail();
            params.page--;
        }
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    /**
     * 响应请求充值记录的列表
     * @param datas
     */
    @Override
    public void responseRechargeRecord(List<MonthCardBillListBean.DataBean.MonthCardBillBean> datas) {
        ptrFrameLayout.refreshComplete();
        if (datas == null || datas.isEmpty()) {
            adapter.loadMoreEnd();
            return;
        }
        ALog.e("datas::" + datas.size());
        if (params.page == 1) {
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }
}
