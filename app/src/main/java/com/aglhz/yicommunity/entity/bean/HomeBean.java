package com.aglhz.yicommunity.entity.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26 0026.
 */
public class HomeBean implements MultiItemEntity {

    public static final int TYPE_COMMUNITY_BANNER = 100;
    public static final int TYPE_COMMUNITY_NOTICE = 101;
    public static final int TYPE_COMMUNITY_FUNCTION = 102;
    public static final int TYPE_COMMUNITY_SERVICE = 103;
    public static final int TYPE_COMMUNITY_QUALITY_LIFE = 104;
    public static final int TYPE_COMMUNITY_WISDOM_LIFE = 105;

    //Banner
    public List<BannerBean.DataBean.AdvsBean> banners;
    //titile & community service
    public String title;
    public String desc;
    public List<String> notices;
    public String notice;
    public String community;
    public int bgRes;
    public int textColor;
    public int type;
    public boolean hasMore;
    public List<ServiceBean> qualityLifes;
    public List<SubCategoryBean.DataBean> wisdomLife;
    public List<ServicesTypesBean.DataBean.ClassifyListBean> servicesClassifyList;

    public List<ServicesTypesBean.DataBean.ClassifyListBean> getServicesClassifyList() {
        return servicesClassifyList;
    }

    public void setServicesClassifyList(List<ServicesTypesBean.DataBean.ClassifyListBean> servicesClassifyList) {
        this.servicesClassifyList = servicesClassifyList;
    }

    public List<SubCategoryBean.DataBean> getWisdomLife() {
        return wisdomLife;
    }

    public void setWisdomLife(List<SubCategoryBean.DataBean> wisdomLife) {
        this.wisdomLife = wisdomLife;
    }

    public List<ServiceBean> getQualityLifes() {
        return qualityLifes;
    }

    public void setQualityLifes(List<ServiceBean> qualityLife) {
        this.qualityLifes = qualityLife;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<String> getNotice() {
        return notices;
    }

    public void setNotice(List<String> notice) {
        this.notices = notice;
    }

    public List<BannerBean.DataBean.AdvsBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerBean.DataBean.AdvsBean> banners) {
        this.banners = banners;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getBgRes() {
        return bgRes;
    }

    public void setBgRes(int bgRes) {
        this.bgRes = bgRes;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setItemType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

}
