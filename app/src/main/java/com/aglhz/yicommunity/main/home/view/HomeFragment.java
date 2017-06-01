package com.aglhz.yicommunity.main.home.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BannerBean;
import com.aglhz.yicommunity.bean.HomeBean;
import com.aglhz.yicommunity.bean.ServiceBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.main.home.contract.HomeContract;
import com.aglhz.yicommunity.main.home.presenter.HomePresenter;
import com.aglhz.yicommunity.main.home.view.header.RentalsSunHeaderView;
import com.aglhz.yicommunity.main.park.view.TemporaryParkPayFragment;
import com.aglhz.yicommunity.main.picker.PickerActivity;
import com.aglhz.yicommunity.main.propery.view.NoticeListFragment;
import com.aglhz.yicommunity.main.propery.view.PropertyPayFragment;
import com.aglhz.yicommunity.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import static com.aglhz.yicommunity.R.layout.recyclerview;

/**
 * Created by Administrator on 2017/4/19 9:15.
 */
public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {
    private static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    Unbinder unbinder;
    private HomeRVAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Dialog loadingDialog;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @NonNull
    @Override
    protected HomeContract.Presenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
        initPtrFrameLayout();
    }


    private void initData() {
        layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);

        List<HomeBean> data = new ArrayList<>();
        //Banner
        HomeBean bannerBean = new HomeBean();
        bannerBean.community = UserHelper.city + UserHelper.communityName;
        bannerBean.setItemType(HomeBean.TYPE_COMMUNITY_BANNER);
        data.add(bannerBean);

        //Notice
        HomeBean noticeBean = new HomeBean();
        noticeBean.setItemType(HomeBean.TYPE_COMMUNITY_NOTICE);
        noticeBean.setNotice(new ArrayList<>());
        data.add(noticeBean);

        //CommunityService
        HomeBean functioneBean = new HomeBean();
        functioneBean.setItemType(HomeBean.TYPE_COMMUNITY_FUNCTION);
        data.add(functioneBean);

        //service
        ServiceBean serviceBean0 = new ServiceBean("家政服务", "您家里的一切，让我来搞定！", R.drawable.bg_housekeeping_900px_540px);
        ServiceBean serviceBean1 = new ServiceBean("家居维修", "专业品质服务，大可放心！", R.drawable.bg_homemaintenanc_900px_540px);
        ServiceBean serviceBean2 = new ServiceBean("送水上门", "社区周边送水上门服务！", R.drawable.bg_watersupply_900px_540px);


        HomeBean serviceBeans = new HomeBean();
        List<ServiceBean> serviceBeanList = new ArrayList<>();
        serviceBeanList.add(serviceBean0);
        serviceBeanList.add(serviceBean1);
        serviceBeanList.add(serviceBean2);

        serviceBeans.setServices(serviceBeanList);
        serviceBeans.setItemType(HomeBean.TYPE_COMMUNITY_SERVICE);
        data.add(serviceBeans);

        //品质服务
        ServiceBean qualityLifeBean0 = new ServiceBean("闲置交换", "社区闲置物品不得闲", R.drawable.bg_xianzhijiaohuan_346px_450px);
        ServiceBean qualityLifeBean1 = new ServiceBean("快递查询", "对接各物流快递公司", R.drawable.bg_expressdelivery_345px_450px);
        ServiceBean qualityLifeBean2 = new ServiceBean("拼车服务", "社区拼车方便快捷", R.drawable.bg_pinchefuwu_345px_450px);

        HomeBean lifes = new HomeBean();

        List<ServiceBean> lifeList = new ArrayList<>();
        lifeList.add(qualityLifeBean0);
        lifeList.add(qualityLifeBean1);
        lifeList.add(qualityLifeBean2);

        lifes.setQualityLifes(lifeList);
        lifes.setItemType(HomeBean.TYPE_COMMUNITY_QUALITY_LIFE);
        data.add(lifes);
        adapter = new HomeRVAdapter(data);
        adapter.setFragment(this);
        recyclerView.setAdapter(adapter);

        //add footer
        View footerView = LayoutInflater.from(_mActivity).inflate(R.layout.footer_no_anymore, null, false);
        adapter.addFooterView(footerView);


    }

    private void initPtrFrameLayout() {
        // header
        final RentalsSunHeaderView header = new RentalsSunHeaderView(getContext());
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(_mActivity, 15), 0, DensityUtils.dp2px(_mActivity, 10));
        header.setUp(ptrFrameLayout);

        ptrFrameLayout.setLoadingMinTime(1000);
        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.postDelayed(() -> ptrFrameLayout.autoRefresh(true), 100);

        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return ScrollingHelper.isRecyclerViewToTop(recyclerView);
                //判断RecyclerView是否在在顶部，在顶部则允许滑动下拉刷新
//
//                if (null != recyclerView && null != layoutManager) {
//                    int position = layoutManager.findFirstVisibleItemPosition();
//
//                    ALog.e("position::" + position);
//                    ALog.e("layoutManager.findViewByPosition(position).getTop()::" + layoutManager.findViewByPosition(position).getTop());
//
//
//                    if (position >= 0) {
//                        if (layoutManager.findViewByPosition(position).getTop() >= 0 && position == 0) {
//                            return true;
//                        }
//                    }
//                } else {
//                    return true;
//                }
//                return false;

            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                mPresenter.requestBanners();
                mPresenter.requestHomeNotices();
            }
        });
    }

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            int viewType = adapter1.getItemViewType(position);
            switch (viewType) {
                case HomeBean.TYPE_COMMUNITY_BANNER:
                    switch (view.getId()) {
                        case R.id.fl_item_banner:
                            _mActivity.startActivity(new Intent(_mActivity, PickerActivity.class));
                            break;
                    }
                    break;
                case HomeBean.TYPE_COMMUNITY_NOTICE:
                    _mActivity.start(NoticeListFragment.newInstance());
                    break;
                case HomeBean.TYPE_COMMUNITY_FUNCTION:
                    switch (view.getId()) {
                        case R.id.ll_quick_open_door:
                            showLoadingDialog();
                            mPresenter.requestOpenDoor();
                            break;
                        case R.id.ll_property_payment:
                            _mActivity.start(PropertyPayFragment.newInstance());
                            break;
                        case R.id.ll_temporary_parking:
//                            go2Web("临时停车", ApiService.TEMP_PARKING);
                            _mActivity.start(TemporaryParkPayFragment.newInstance());
                            break;
                        case R.id.ll_life_supermarket:
                            go2Web("生活超市", ApiService.SUPERMARKET + UserHelper.token);
                            break;
                    }
                    break;
            }
        });
    }

    public void go2Web(String title, String link) {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("link", link);
        _mActivity.startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        if (adapter != null) {
            adapter = null;
        }
    }

    @Override
    public void start(Object response) {
    }

    @Override
    public void error(String errorMessage) {
        dismissLoadingDialog();
        ptrFrameLayout.refreshComplete();
        adapter.notifyItemChanged(0);
        adapter.notifyItemChanged(1);
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseBanners(List<BannerBean.DataBean.AdvsBean> banners) {
        ptrFrameLayout.refreshComplete();
        adapter.getData().get(0).setBanners(banners);
        adapter.notifyItemChanged(0);
    }

    @Override
    public void responseHomeNotices(List<String> notices) {
        ptrFrameLayout.refreshComplete();
        if (notices.size() == 0) {
            notices.add("欢迎来到亿社区！");
        }
        adapter.getData().get(1).setNotice(notices);
        adapter.notifyItemChanged(1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        adapter.getData().get(0).community = UserHelper.city + UserHelper.communityName;
        ptrFrameLayout.autoRefresh();
    }

    @Override
    public void responseOpenDoor() {
        dismissLoadingDialog();
        DialogHelper.successSnackbar(getView(), "开门成功，欢迎回家，我的主人！");
    }
}