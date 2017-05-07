package com.aglhz.yicommunity.bean;

/**
 * Created by Administrator on 2017/4/19 10:30.
 */
public class CarCardTransactBean {

    public CarCardTransactBean(int icon,String title,String desc,int bgRes,int textColor){
        this.textColor = textColor;
        this.bgRes = bgRes;
        this.icon = icon;
        this.title = title;
        this.desc = desc;
    }

    public String desc;
    public String title;
    public int icon;
    public int bgRes;
    public int textColor;

}
