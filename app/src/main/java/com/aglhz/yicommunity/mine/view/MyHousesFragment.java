package com.aglhz.yicommunity.mine.view;

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
import com.aglhz.yicommunity.bean.HouseRightsBean;
import com.aglhz.yicommunity.bean.MyHousesBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.mine.contract.MyHousesContract;
import com.aglhz.yicommunity.mine.presenter.MyHousesPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/5/17 0017 15:29.
 * Email: liujia95me@126.com
 */

public class MyHousesFragment extends BaseFragment<MyHousesContract.Presenter> implements MyHousesContract.View {
    public static final String TAG = MyHousesFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private MyHousesRVAdapter adapter;
    private Unbinder unbinder;
    private Params params = Params.getInstance();

    public static MyHousesFragment newInstance() {
        return new MyHousesFragment();
    }

    @NonNull
    @Override
    protected MyHousesContract.Presenter createPresenter() {
        return new MyHousesPresenter(this);
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
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    @Override
    public void onRefresh() {
        params.page = 1;
        params.pageSize = Constants.PAGE_SIZE;
        params.cmnt_c = UserHelper.communityCode;
        mPresenter.requsetMyHouse(params);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("我的房屋");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        adapter = new MyHousesRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            ALog.e("加载更多………………………………");
            params.page++;
            mPresenter.requsetMyHouse(params);
        }, recyclerView);

        adapter.setOnItemClickListener((adapter, view, position) -> {
            MyHousesBean.DataBean.AuthBuildingsBean bean = MyHousesFragment.this.adapter.getData().get(position);
            start(HouseMembersFragment.newInstance(bean.getFid(), bean.getAddress()));
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setAdapter(adapter);
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
        DialogHelper.warningSnackbar(getView(), errorMessage);//后面换成pagerstate的提示，不需要这种了
    }

    @Override
    public void responseHouses(List<MyHousesBean.DataBean.AuthBuildingsBean> datas) {
        ptrFrameLayout.refreshComplete();

        if (datas == null || datas.size() == 0) {
            adapter.loadMoreEnd();
            return;
        }

        if (params.page == 1) {
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void responseRights(HouseRightsBean mHouseRights) {
        //公用Presenter多出来的
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }
}
