package com.aglhz.yicommunity.propery.view;

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
import com.aglhz.yicommunity.bean.RepairApplyBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.message.view.RepairDetailFragment;
import com.aglhz.yicommunity.propery.contract.RepairApplyContract;
import com.aglhz.yicommunity.propery.presenter.RepairApplyPresenter;
import com.aglhz.yicommunity.publish.view.RepairFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2017/4/19 14:27.
 * <p>
 * 物业保修列表
 */
public class RepairRecordFragment extends BaseFragment<RepairApplyContract.Presenter> implements RepairApplyContract.View {
    private final String TAG = RepairRecordFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;


    private Unbinder unbinder;
    private RepairRecordRVAdapter adapter;
    private Params params = Params.getInstance();
    public static final int REQUEST_CODE = 100;

    public static RepairRecordFragment newInstance() {
        return new RepairRecordFragment();
    }

    @NonNull
    @Override
    protected RepairApplyContract.Presenter createPresenter() {
        return new RepairApplyPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initPtrFrameLayout(ptrFrameLayout, recyclerview);
    }

    @Override
    public void onRefresh() {
        params.page = 1;
        params.pageSize = Constants.PAGE_SIZE;
        params.cmnt_c = UserHelper.communityCode;
        mPresenter.requestRepairApplyList(params);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("物业报修");
        toolbar.inflateMenu(R.menu.menu_repair_record);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    public void initData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new RepairRecordRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.requestRepairApplyList(params);

        }, recyclerview);
        recyclerview.setAdapter(adapter);
    }

    private void initListener() {
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.btn_public_repairs) {
                startForResult(RepairFragment.newInstance(false), REQUEST_CODE);
            } else if (item.getItemId() == R.id.btn_private_repairs) {
                startForResult(RepairFragment.newInstance(true), REQUEST_CODE);
            }
            return true;
        });

        adapter.setOnItemClickListener((adapter, view, position) -> {
            RepairApplyBean.DataBean.RepairApplysBean bean = (RepairApplyBean.DataBean.RepairApplysBean) adapter.getData().get(position);
            start(RepairDetailFragment.newInstance(bean.getFid()));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
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

    @Override
    public void responseRepairApplyList(List<RepairApplyBean.DataBean.RepairApplysBean> datas) {

        ptrFrameLayout.refreshComplete();
        if (datas == null || datas.isEmpty()) {
            adapter.loadMoreEnd();
            return;
        }

        if (params.page == 1) {
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerview);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            ptrFrameLayout.autoRefresh();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }
}
