package com.aglhz.yicommunity.common;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class Constants {
    private final String TAG = this.getClass().getSimpleName();

    //不允许new
    private Constants() {
        throw new Error("Do not need instantiate!");
    }

    public static final String PRESS_AGAIN = "再按一次退出";
    public static final String WEB_LINK = "link";
    public static final String WEB_TITLE = "title";
    public static final String FROM_TO = "from_to";
    public static final int MY_CARD = 0;
    public static final int PARKING_RECORD = 1;
    public static final int CARD_TRANSACT = 2;

    public static final int PROPERTY_REPAIR = 0;
    public static final int COMPLAIN = 2;

}
