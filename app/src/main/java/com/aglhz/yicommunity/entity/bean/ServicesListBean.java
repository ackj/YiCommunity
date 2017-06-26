package com.aglhz.yicommunity.entity.bean;

/**
 * Created by leguang on 2017/6/26 0026.
 * Email：langmanleguang@qq.com
 */

public class ServicesListBean {
    public ServicesListBean(String title, String des, String address, String cost) {
        this.title = title;
        this.des = des;
        this.address = address;
        this.cost = cost;
    }

    public String title;
    public String des;
    public String address;
    public String cost;
}
