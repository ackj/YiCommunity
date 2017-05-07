package com.aglhz.yicommunity.bean;

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

    //Banner
    private List<String> banners;
    //titile & community service
    private String title;
    private String desc;
    private String notice;
    private int bgRes;
    private int textColor;
    private int type;
    private boolean hasMore;
    private List<ServiceBean> services;
    private List<ServiceBean> qualityLifes;

    public List<ServiceBean> getServices() {
        return services;
    }

    public void setServices(List<ServiceBean> services) {
        this.services = services;
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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public List<String> getBanners() {
        return banners;
    }

    public void setBanners(List<String> banners) {
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
