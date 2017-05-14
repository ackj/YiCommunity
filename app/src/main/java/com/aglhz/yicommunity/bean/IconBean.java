package com.aglhz.yicommunity.bean;

/**
 * Created by YandZD on 2017/1/16.
 */

public class IconBean {
    public int icon;
    public String title;
    public String fid;

    public IconBean(int icon, String title, String fid) {
        this.icon = icon;
        this.title = title;
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "IconBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", fid='" + fid + '\'' +
                '}';
    }
}
