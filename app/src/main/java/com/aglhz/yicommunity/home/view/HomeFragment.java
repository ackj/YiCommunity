package com.aglhz.yicommunity.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.HomeBean;
import com.aglhz.yicommunity.bean.ServiceBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2017/4/19 9:15.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    Unbinder unbinder;
    private HomeRVAdapter adapter;
    private LinearLayoutManager layoutManager;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initStateBar(view);
        initData();
        initListener();
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);

        List<HomeBean> data = new ArrayList<>();
        //Banner
        HomeBean bannerBean = new HomeBean();
        List<String> bannersRes = new ArrayList<>();
        bannerBean.setItemType(HomeBean.TYPE_COMMUNITY_BANNER);
        bannerBean.setBanners(bannersRes);
        data.add(bannerBean);

        //Notice
        String notice = "哈哈哈哈哈哈哈";
        HomeBean noticeBean = new HomeBean();
        noticeBean.setItemType(HomeBean.TYPE_COMMUNITY_NOTICE);
        noticeBean.setNotice(notice);
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
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            int viewType = adapter.getItemViewType(position);
            switch (viewType) {
                case HomeBean.TYPE_COMMUNITY_BANNER:
                    switch (view.getId()) {
                        case R.id.convenient_banner_item_banner:
                            ToastUtils.showToast(_mActivity, "beanner");
                            break;
                        case R.id.fl_item_banner:
                            ToastUtils.showToast(_mActivity, "切换地址");
                            break;
                    }

                    break;
                case HomeBean.TYPE_COMMUNITY_NOTICE:
                    ToastUtils.showToast(_mActivity, "notice");
                    break;
                case HomeBean.TYPE_COMMUNITY_FUNCTION:
                    switch (view.getId()) {
                        case R.id.ll_quick_open_door:
                            ToastUtils.showToast(_mActivity, "一键开门");
                            break;
                        case R.id.ll_property_payment:
                            ToastUtils.showToast(_mActivity, "物业缴费");
                            break;
                        case R.id.ll_temporary_parking:
                            ToastUtils.showToast(_mActivity, "临时停车");
                            break;
                        case R.id.ll_life_supermarket:
                            ToastUtils.showToast(_mActivity, "生活超市");
                            break;
                    }
                    break;
            }
            return false;
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (adapter != null) {
            adapter = null;
        }
    }
}
