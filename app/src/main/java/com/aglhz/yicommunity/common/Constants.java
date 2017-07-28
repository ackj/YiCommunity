package com.aglhz.yicommunity.common;


import com.aglhz.yicommunity.BuildConfig;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class Constants {
    private final String TAG = Constants.class.getSimpleName();

    //不允许new
    private Constants() {
        throw new Error("Do not need instantiate!");
    }

    public static final String PRESS_AGAIN = "再按一次退出";
    public static final int PAGE_SIZE = 20;
    public static final String TITLE = "title";

    //上门服务
    public static final String SERVICE_TYPE = "service_type";
    public static final String SERVICE_FID = "service_fid";
    public static final String COMMODITY_FID = "commodity_fid";
    public static final String FIRM_NAME = "firm_name";
    public static final String SERVICE_NAME = "service_name";
    public static final String SERVICE_ID = "service_id";

    //微信
    public static final String WX_APP_ID = "wx160fff7b6ed86ef7";

    //网络部分
    public static final int RESPONSE_CODE_NOMAL = 200;
    public static final int RESPONSE_CODE_LOGOUT = 123;

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

    //**************以下是EventBus系列,值是随便取，只要不相同即可。**********************************************************************
    //登录成功
    public static final int login = 11;

    //刷新“我的”模块里的消息中心未读红点标记
    public static final int refresh_unread_mark = 22;

    //刷新社区服务模块里的点评
    public static final int refresh_remark = 33;

    //**************以上是EventBus系列,值是随便取，只要不相同即可。**********************************************************************

    public static final String CITY = "city";

    //********************以下为物业缴费模块************************
    public static final int TYPE_ALIPAY = 1;
    public static final int TYPE_WXPAY = 2;
    public static final String ALIPAY = "支付宝支付";
    public static final String WXPAY = "微信支付";

    //********************以上为物业缴费模块************************


    //******************** Bundle key ***********************
    public static final String KEY_TITLE = "title";
    public static final String KEY_FID = "fid";
    public static final String KEY_ID = "id";
    public static final String KEY_BEAN = "bean";
    public static final String KEY_LINK = "link";
    public static final String KEY_DES = "des";
    public static final String KEY_SHORTFROM = "shortfrom";
    public static final String KEY_CARNO = "carNo";
    public static final String KEY_TYPE = "type";
    public static final String KEY_CITY = "city";
    public static final String KEY_WHICH = "which";
    public static final String KEY_IS_PASS = "isPass";
    public static final String KEY_PAY_STATE = "pay_position";
    public static final String KEY_FROM_TO = "from_to";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_MEMBER = "member";

    //******************** SP key ***********************
    public static final String SP_KEY_USED_CITYS = "sp_key_used_citys";
    //\主界面
    public static final String SP_KEY_GUIDE_MAIN = "sp_key_guide_main";
    //月卡缴费
    public static final String SP_KEY_GUIDE_CARD_PAY = "sp_key_guide_card_pay";
    //我的车卡
    public static final String SP_KEY_GUIDE_MY_CARD = "sp_key_guide_my_card";
    //临停缴费
    public static final String SP_KEY_GUIDE_TEMPPORARY_PARK_PAY = "sp_key_guide_tempporary_park_pay";
    //********************以下权限名称***********************
    public static final String PERMISSION_REMOTEWATCH = "RemoteWatch";

    //********************以上权限名称***********************

    public static final int TYPE_REMARK = 107;//社区服务点评回复

    //——————————————以下是区分debug版和非debug版的baseurl——————————————————————

    public static String BASE_PROPERTY = "";
    public static String BASE_USER = "";
    public static String BASE_PROPERTYCFG_M = "";

    static {
        if (BuildConfig.DEBUG) {
            //调试可以改这里的地址。
            BASE_USER = "http://www.aglhz.com:8076/memberSYS-m";           //用户
            BASE_PROPERTYCFG_M = "http://www.aglhz.com:8096/propertyCFG-m";
//            BASE_PROPERTY = "http://119.23.129.133:8090/sub_property_ysq";//物业调试IP

//            BASE_USER = "http://192.168.7.116:8080/memberSYS-m";           //用户
//            BASE_PROPERTYCFG_M = "http://192.168.7.116:8080/propertyCFG-m";
            BASE_PROPERTY = "http://192.168.7.116:8080/sub_property_ysq";//物业调试IP

//            BASE_PROPERTY = "http://192.168.7.101:8080/sub_property_ysq";   //小张主机物业调试IP
        } else {
            //这里的是正式版的基础地址，永远不要动。
            BASE_USER = "http://www.aglhz.com:8076/memberSYS-m";           //用户
            BASE_PROPERTYCFG_M = "http://www.aglhz.com:8096/propertyCFG-m";
            BASE_PROPERTY = "http://www.aglhz.com:8090/sub_property_ysq";   //物业
        }
    }
    //——————————————以上是区分debug版和非debug版的baseurl——————————————————————

}
