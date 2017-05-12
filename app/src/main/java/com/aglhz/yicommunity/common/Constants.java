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
    public static final int PAGE_SIZE = 20;

    public static final String COMMUNITY_CODE = "community_code";
    public static final String COMMUNITY_NAME = "community_name";


    //网络部分
    public static final int RESPONSE_CODE_NOMAL = 200;
    public static final int RESPONSE_CODE_LOGOUT = 123;


    //web模块
    public static final String WEB_LINK = "link";
    public static final String WEB_TITLE = "title";
    public static final String FROM_TO = "from_to";

    //停车模块
    public static final int MY_CARD = 0;
    public static final int PARKING_RECORD = 1;
    //报修模块
    public static final int CARD_TRANSACT = 2;
    public static final int PROPERTY_REPAIR = 0;

    //智能门禁模块
    public static final int COMPLAIN = 2;
    public static final int SET_OPEN_DOOR = 0;
    public static final int APPOINT_OPEN_DOOR = 1;
    public static final int PASSWORD_OPEN_DOOR = 2;
    public static final int CALL_DOOR = 3;
    public static final int OPEN_RECORD = 4;
    public static final String DOOR_DIR = "door_dir";

    //房屋模块
    public static final int ADD_HOUSE = 1;
    public static final int HOUSE_RIGHTS = 0;
    public static final String HOUSE_FID = "house_fid";

    //Picker模块
    public static final String SP_KEY_USED_CITYS = "sp_key_used_citys";

    //EventBus系列,值是随便取，只要不相同即可。**********************************************************************
    //登录成功
    public static final int login = 11;

    //登出，或者token失效
    public static final int loginout = 10;


    //EventBus系列,值是随便取，只要不相同即可。**********************************************************************

    public static final String CITY = "city";


}
