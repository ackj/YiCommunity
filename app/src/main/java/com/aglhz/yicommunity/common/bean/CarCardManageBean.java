package com.aglhz.yicommunity.common.bean;

/**
 * Created by Administrator on 2017/4/19 10:32.
 */
public class CarCardManageBean {

    public CarCardManageBean(String plateNum, int surplusDays, String name, String phone,String parking, String timeStart, String timeEnd, int type) {
        this.plateNum = plateNum;
        this.surplusDays = surplusDays;
        this.name = name;
        this.phone = phone;
        this.parking = parking;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.type = type;
    }

    public String plateNum;
    public int surplusDays;
    public String name;
    public String phone;
    public String parking;
    public String timeStart;
    public String timeEnd;
    public int type;

}
