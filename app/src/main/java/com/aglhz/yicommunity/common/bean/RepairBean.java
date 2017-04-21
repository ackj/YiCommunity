package com.aglhz.yicommunity.common.bean;

/**
 * Created by Administrator on 2017/4/19 15:00.
 */
public class RepairBean {

    public RepairBean(String title, String date, String desc, int status) {
        this.title = title;
        this.date = date;
        this.desc = desc;
        this.status = status;
    }

    public String title;
    public String date;
    public String desc;
    public int status;

}
