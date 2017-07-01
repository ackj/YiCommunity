package com.aglhz.yicommunity.main.home.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.abase.widget.AutoScrollTextView;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.BannerBean;
import com.aglhz.yicommunity.entity.bean.HomeBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.entity.bean.ServicesClassifyListBean;
import com.aglhz.yicommunity.main.services.ServicesActivity;
import com.aglhz.yicommunity.main.sociality.view.CarpoolFragment;
import com.aglhz.yicommunity.main.sociality.view.SocialityListFragment;
import com.aglhz.yicommunity.main.sociality.view.NeighbourFragment;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

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
                helper.addOnClickListener(R.id.fl_item_banner)
                        .setText(R.id.tv_location_item_banner, item.community);
                Banner banner = helper.getView(R.id.viewpager_item_banner);
                List<BannerBean.DataBean.AdvsBean> banners = item.getBanners();
                if (banners != null && banners.size() > 0) {
                    banner.setImages(banners).setImageLoader(new GlideImageLoader()).start();
                }
                break;
            case HomeBean.TYPE_COMMUNITY_NOTICE:
                AutoScrollTextView tv = helper.getView(R.id.tv_notice);
                tv.setTextList(item.getNotice());
                if (!tv.isStart()) {
                    tv.startAutoScroll();
                }
                helper.addOnClickListener(R.id.ll_item_notice);
                break;
            case HomeBean.TYPE_COMMUNITY_FUNCTION:
                helper.addOnClickListener(R.id.ll_quick_open_door)
                        .addOnClickListener(R.id.ll_property_payment)
                        .addOnClickListener(R.id.ll_temporary_parking)
                        .addOnClickListener(R.id.ll_life_supermarket);
                break;
            case HomeBean.TYPE_COMMUNITY_SERVICE:
                ServiceVPAdapter adapter = new ServiceVPAdapter(item.getServicesClassifyList());
                ViewPager viewpager = helper.getView(R.id.viewpager);
                viewpager.setOffscreenPageLimit(3);
                viewpager.setPageTransformer(true, new ZoomOutPageTransformer());
                viewpager.setPageMargin(DensityUtils.dp2px(BaseApplication.mContext, 5));
                viewpager.setAdapter(adapter);
                viewpager.setCurrentItem(100);
                adapter.setOnItemClickListener(position -> {
                    if (item.getServicesClassifyList() == null || item.getServicesClassifyList().isEmpty()) {
//                        adapter.getServiceBeans().get(position)
                        switch (position) {
                            case 0:
                                fragment.go2Web("家政服务", ApiService.JIAZHENG);
                                break;
                            case 1:
                                fragment.go2Web("家居维修", ApiService.WEIXIU);
                                break;
                            case 2:
                                fragment.go2Web("送水上门", ApiService.SONGSHUI);
                                break;
                        }
                    } else {
                        Intent intent = new Intent(viewpager.getContext(), ServicesActivity.class);
                        ServicesClassifyListBean.DataBean.ClassifyListBean classifyListBean = item.getServicesClassifyList().get(position);
                        intent.putExtra(Constants.SERVICE_FID, classifyListBean.getFid());
                        intent.putExtra(Constants.SERVICE_NAME, classifyListBean.getName());
                        viewpager.getContext().startActivity(intent);
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
                    switch (position) {
                        case 0:
                            ((BaseActivity) fragment.getActivity()).start(NeighbourFragment.newInstance(SocialityListFragment.TYPE_EXCHANGE));
                            break;
                        case 1:
                            fragment.go2Web("快递查询", ApiService.WULIU_SEARCH + UserHelper.token);
                            break;
                        case 2:
                            ((BaseActivity) fragment.getActivity()).start(CarpoolFragment.newInstance());
                            break;
                    }
                });
                break;
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object advsBean, ImageView imageView) {
            BannerBean.DataBean.AdvsBean bean = (BannerBean.DataBean.AdvsBean) advsBean;
            Glide.with(context).load(bean.getCover()).into(imageView);
        }
    }
}
