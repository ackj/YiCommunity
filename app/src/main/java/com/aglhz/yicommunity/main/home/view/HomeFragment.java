package com.aglhz.yicommunity.main.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.common.AudioPlayer;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.BannerBean;
import com.aglhz.yicommunity.entity.bean.HomeBean;
import com.aglhz.yicommunity.entity.bean.OneKeyDoorBean;
import com.aglhz.yicommunity.entity.bean.ServiceBean;
import com.aglhz.yicommunity.entity.bean.ServicesTypesBean;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.main.home.contract.HomeContract;
import com.aglhz.yicommunity.main.home.presenter.HomePresenter;
import com.aglhz.yicommunity.main.home.view.header.RentalsSunHeaderView;
import com.aglhz.yicommunity.main.park.view.TemporaryParkPayFragment;
import com.aglhz.yicommunity.main.picker.PickerActivity;
import com.aglhz.yicommunity.main.propery.view.NoticeListFragment;
import com.aglhz.yicommunity.main.propery.view.PropertyPayFragment;
import com.aglhz.yicommunity.web.WebActivity;
import com.aglhz.yicommunity.widget.OpenDoorDialog;

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

/**
 * Created by leguang on 2017/4/19 9:15.
 * Email：langmanleguang@qq.com
 * <p>
 * [社区]首页的View层。
 * 打开方式：Start App-->社区
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
    private String normalNotice = "欢迎来到亿社区！";
    private Params params = Params.getInstance();
    private OpenDoorDialog openDoorialog;
    private List<OneKeyDoorBean.DataBean.ItemListBean> oneKeyDoorList = new ArrayList<>();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
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
        noticeBean.notice = normalNotice;
        data.add(noticeBean);

        //CommunityService
        HomeBean functioneBean = new HomeBean();
        functioneBean.setItemType(HomeBean.TYPE_COMMUNITY_FUNCTION);
        data.add(functioneBean);

        //
        HomeBean serviceBeans = new HomeBean();
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
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                AudioPlayer.getInstance(_mActivity).play(1);
                ALog.e(TAG,"request all -- cmnt_c"+params.cmnt_c+" ");
                mPresenter.requestBanners();
                mPresenter.requestHomeNotices(params);
                mPresenter.requestServiceTypes(params);
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
                            //请求列表
//                            MultiSelectorDialog dialog = MultiSelectorDialog.builder(_mActivity)
//                                    .setTitle("请选择开哪扇门")
//                                    .setTabVisible(false)
//                                    .setLevel(1)
//                                    .setOnItemClickListener((pagerPosition, optionPosition, option) -> ToastUtils.showToast(_mActivity, "pagerPosition-->" + pagerPosition + "\r\noptionPosition-->" + optionPosition + "\r\noption-->" + option))
//                                    .build();

//                            dialog.show();
//                            List<String> devicesList = new ArrayList<>();
//                            for (int i = 0; i < 100; i++) {
//                                devicesList.add("dfasdfa");
//                            }
//                            view.postDelayed(() -> dialog.notifyDataSetChanged(devicesList),500);
                            showLoadingDialog();
                            mPresenter.requestOneKeyOpenDoorDeviceList(params);
                            break;
                        case R.id.ll_property_payment:
                            _mActivity.start(PropertyPayFragment.newInstance());
                            break;
                        case R.id.ll_temporary_parking:
//                            go2Web("临时停车", ApiService.TEMP_PARKING);
                            _mActivity.start(TemporaryParkPayFragment.newInstance());
                            break;
                        case R.id.ll_life_supermarket:
                            go2Web("生活超市", ApiService.SUPERMARKET
                                    .replace("%1", UserHelper.token)
                                    .replace("%2", UserHelper.longitude)
                                    .replace("%3", UserHelper.latitude));
                            break;
                    }
                    break;
            }
        });
    }

    private void openDoor(String dir) {
        UserHelper.dir = dir;
        showQuickOpenDoorDialog();
        recyclerView.postDelayed(() -> {
            mPresenter.requestOpenDoor();
        }, 1000);
    }

    public void go2Web(String title, String link) {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_LINK, link);
        _mActivity.startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        AudioPlayer.getInstance(_mActivity).clear();
        if (adapter != null) {
            adapter = null;
        }
    }

    @Override
    public void start(Object response) {
    }

    @Override
    public void error(String errorMessage) {
        ALog.e(TAG,"error:"+errorMessage);
        if (openDoorialog != null) {
            openDoorialog.setError();
        }
        dismissLoadingDialog();
        ptrFrameLayout.refreshComplete();
        adapter.notifyItemChanged(0);
        adapter.notifyItemChanged(1);
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseBanners(List<BannerBean.DataBean.AdvsBean> banners) {
        ALog.e(TAG,"responseBanners:"+banners.size());
        ptrFrameLayout.refreshComplete();
        adapter.getData().get(0).setBanners(banners);
        adapter.notifyItemChanged(0);
    }

    @Override
    public void responseHomeNotices(List<String> notices) {
        ALog.e(TAG,"responseHomeNotices:"+notices.size());
        ptrFrameLayout.refreshComplete();
        if (notices.size() > 0) {
            adapter.getData().get(1).notice = notices.get(0);
            adapter.notifyItemChanged(1);
        } else {
            adapter.getData().get(1).notice = normalNotice;
            adapter.notifyItemChanged(1);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        adapter.getData().get(0).community = UserHelper.city + UserHelper.communityName;
        ptrFrameLayout.autoRefresh();
    }

    @Override
    public void responseOpenDoor() {
        if (openDoorialog != null) {
            openDoorialog.setSuccess();
        }
        DialogHelper.successSnackbar(getView(), "开门成功，欢迎回家，我的主人！");
    }

    @Override
    public void responseServiceClassifyList(List<ServicesTypesBean.DataBean.ClassifyListBean> classifys) {
        ptrFrameLayout.refreshComplete();
        ALog.e(TAG, "responseServiceClassifyList size:" + classifys.size());
        adapter.getData().get(3).setServicesClassifyList(classifys);
        adapter.notifyItemChanged(3);
    }

    @Override
    public void responseOneKeyOpenDoorDeviceList(List<OneKeyDoorBean.DataBean.ItemListBean> doorList) {
        this.oneKeyDoorList = doorList;
        dismissLoadingDialog();
        if (oneKeyDoorList.size() == 0) {
            ToastUtils.showToast(_mActivity,"该社区没有指定开门");
        } else if (oneKeyDoorList.size() == 1) {
            openDoor(oneKeyDoorList.get(0).getDir());
        } else {
            String[] selectedArr = new String[oneKeyDoorList.size()];
            for (int i = 0; i < oneKeyDoorList.size(); i++) {
                selectedArr[i] = oneKeyDoorList.get(i).getName();
            }
            new AlertDialog.Builder(_mActivity)
                    .setTitle("请选择开门")
                    .setItems(selectedArr, (dialog, which) -> openDoor(oneKeyDoorList.get(which).getDir())).show();
        }
    }

    public void go2TopAndRefresh() {
        if (recyclerView == null || ptrFrameLayout == null) {
            return;
        }
        recyclerView.scrollToPosition(0);
        ptrFrameLayout.autoRefresh();
    }

    public void showQuickOpenDoorDialog() {
        if (openDoorialog == null) {
            openDoorialog = new OpenDoorDialog(_mActivity);
        }
        openDoorialog.setOpenDoor();
        openDoorialog.show();
    }
}
