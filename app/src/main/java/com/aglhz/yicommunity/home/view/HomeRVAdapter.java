package com.aglhz.yicommunity.home.view;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BannerBean;
import com.aglhz.yicommunity.bean.HomeBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: LiuJia on 2017/4/26 0026 16:25.
 * Email: liujia95me@126.com
 */

public class HomeRVAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

    HomeFragment fragment;

    public void setFragment(HomeFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeRVAdapter(List<HomeBean> data) {
        super(data);
        addItemType(HomeBean.TYPE_COMMUNITY_BANNER, R.layout.item_home_fragment_rv_banner);
        addItemType(HomeBean.TYPE_COMMUNITY_NOTICE, R.layout.item_home_fragment_rv_notice);
        addItemType(HomeBean.TYPE_COMMUNITY_FUNCTION, R.layout.item_home_fragment_rv_function);
        addItemType(HomeBean.TYPE_COMMUNITY_SERVICE, R.layout.item_home_fragment_rv_service);
        addItemType(HomeBean.TYPE_COMMUNITY_QUALITY_LIFE, R.layout.item_home_fragment_rv_life);
    }

    @Override
    protected void convert(final BaseViewHolder helper, HomeBean item) {
        switch (helper.getItemViewType()) {
            case HomeBean.TYPE_COMMUNITY_BANNER:
                helper.addOnClickListener(R.id.fl_item_banner);
                List<BannerBean.DataBean.AdvsBean> banners = item.getBanners();
                if (banners != null && banners.size() > 0) {
                    ViewPager viewpager = helper.getView(R.id.viewpager_item_banner);
                    BannerVPAdapter adapter = new BannerVPAdapter(banners);
                    viewpager.setAdapter(adapter);
                }
                break;
            case HomeBean.TYPE_COMMUNITY_NOTICE:
                helper.setText(R.id.tv_notice, item.getNotice());
                helper.addOnClickListener(R.id.ll_item_notice);
                break;
            case HomeBean.TYPE_COMMUNITY_FUNCTION:
                helper.addOnClickListener(R.id.ll_quick_open_door)
                        .addOnClickListener(R.id.ll_property_payment)
                        .addOnClickListener(R.id.ll_temporary_parking)
                        .addOnClickListener(R.id.ll_life_supermarket);
                break;
            case HomeBean.TYPE_COMMUNITY_SERVICE:
                ServiceVPAdapter adapter = new ServiceVPAdapter(item.getServices());
                ViewPager viewpager = helper.getView(R.id.viewpager);
                viewpager.setOffscreenPageLimit(3);
                viewpager.setPageTransformer(true, new ZoomOutPageTransformer());
                viewpager.setPageMargin(DensityUtils.dp2px(BaseApplication.mContext, 5));
                viewpager.setAdapter(adapter);
                viewpager.setCurrentItem(1);
                adapter.setOnItemClickListener(position -> {
                    ToastUtils.showToast(BaseApplication.mContext, "社区服务::" + position);
                    switch (position) {
                        case 0:
                            fragment.go2Web("家政服务", "http://www.aglhz.com/sub_property_ysq/m/html/jiazheng.html");
                            break;
                        case 1:
                            fragment.go2Web("家居维修", "http://www.aglhz.com/sub_property_ysq/m/html/weixiu.html");
                            break;
                        case 2:
                            fragment.go2Web("送水上门", "http://www.aglhz.com/sub_property_ysq/m/html/songshui.html");
                            break;
                    }
                });
                break;
            case HomeBean.TYPE_COMMUNITY_QUALITY_LIFE:
                LifeRVAdapter qualityLifeAdapter = new LifeRVAdapter(item.getQualityLifes());
                RecyclerView recyclerView = helper.getView(R.id.recyclerView);
                recyclerView.setLayoutManager(new GridLayoutManager(BaseApplication.mContext, 3, GridLayoutManager.VERTICAL, false) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                recyclerView.setAdapter(qualityLifeAdapter);
                qualityLifeAdapter.setOnItemClickListener((adapter1, view, position) -> {
                    ToastUtils.showToast(BaseApplication.mContext, "品质生活:" + position);
                    switch (position) {
                        case 0:
                            break;
                        case 1:
                            fragment.go2Web("快递查询", "http://www.aglhz.com/sub_property_ysq/m/mall_zyg/html/wuliuSearch.html?token=%@&appType=2");
                            break;
                        case 2:
                            break;
                    }
                });
                break;
        }
    }

}
