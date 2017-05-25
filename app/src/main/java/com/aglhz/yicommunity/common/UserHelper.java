package com.aglhz.yicommunity.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.bean.UserBean;
import com.google.gson.Gson;


/**
 * Author：leguang on 2016/5/4 0009 15:49
 * Email：langmanleguang@qq.com
 */

public class UserHelper {
    private static final String USER = "user";
    private static final String TOKEN = "token";
    private static final String COMMUNITY_NAME = "community_name";
    private static final String COMMUNITY_CODE = "community_code";
    private static final String ACCOUNT = "account";
    private static final String PASSWORD = "password";
    private static final String USER_INFO = "user_info";
    private static final String DIR = "dir";
    private static final String PROVINCE = "province";
    private static final String CITY = "city";
    private static final String COUNTY = "county";
    private static final String ADDRESS = "address";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String POSITION_ADDRESS = "position_address";
    private static final String SIP = "sip";

    public static String token = "";
    public static String communityName = "";
    public static String province = "";
    public static String city = "";
    public static String county = "";
    public static String address = "";
    public static String communityCode = "";
    public static String account = "";
    public static String password = "";
    public static String dir = "";//默认设置的一键开门的设备路径。
    public static String latitude = "";//纬度
    public static String longitude = "";//经度
    public static String sip = "";
    public static String WXAPPID = "";
    public static String positionAddress = "";//定位的位置地址

    public static UserBean.DataBean.MemberInfoBean userInfo;

    public static boolean setLatitude(String latitude) {
        UserHelper.latitude = latitude;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(LATITUDE, latitude);
        return editor.commit();
    }

    public static boolean setLongitude(String longitude) {
        UserHelper.longitude = longitude;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(LONGITUDE, longitude);
        return editor.commit();
    }

    public static boolean setLocationAddress(String address) {
        UserHelper.positionAddress = address;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(POSITION_ADDRESS, address);
        return editor.commit();
    }

    //判断是否登录
    public static boolean isLogined() {
        return !TextUtils.isEmpty(token);
    }

    //判断是否有选择小区
    public static boolean hasCommunity() {
        return !TextUtils.isEmpty(communityName);
    }

    //退出登录或者token失效清除信息
    public static boolean clear() {
        token = "";
        communityName = "";
        communityCode = "";
        account = "";
        password = "";
        dir = "";
        city = "";
        userInfo = null;
        latitude = "";
        longitude = "";
        sip = "";
        province = "";
        city = "";
        county = "";
        address = "";

        return getEditor().clear().commit();
    }

    //更新Token
    public static boolean setToken(String token) {
        UserHelper.token = token;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(TOKEN, token);
        return editor.commit();
    }

    //更新社区名称和社区代码
    public static boolean setCommunity(String communityName, String communityCode) {
        UserHelper.communityName = communityName;
        UserHelper.communityCode = communityCode;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(COMMUNITY_NAME, communityName);
        editor.putString(COMMUNITY_CODE, communityCode);
        return editor.commit();
    }

    //更新用户信息
    public static boolean setUserInfo(UserBean.DataBean.MemberInfoBean memberInfo) {
        setToken(memberInfo.getToken());
        UserHelper.userInfo = memberInfo;
        SharedPreferences.Editor editor = getEditor();
        Gson gson = new Gson();
        String info = gson.toJson(memberInfo);
        editor.putString(USER_INFO, info);
        return editor.commit();
    }

    //更新sip账号
    public static boolean setSip(String sip) {
        UserHelper.sip = sip;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(SIP, sip);
        return editor.commit();
    }

    //获取用户信息
    public static UserBean.DataBean.MemberInfoBean getUserInfo() {
        if (userInfo != null) {
            return userInfo;
        }
        String userInfo = getSp().getString(USER_INFO, "");
        if (TextUtils.isEmpty(userInfo)) {
            return null;
        }
        UserHelper.userInfo = new Gson().fromJson(userInfo, UserBean.DataBean.MemberInfoBean.class);
        return UserHelper.userInfo;
    }

    //更新账号密码
    public static boolean setAccount(String account, String password) {
        UserHelper.account = account;
        UserHelper.password = password;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(ACCOUNT, account);
        editor.putString(PASSWORD, password);
        return editor.commit();
    }

    public static void init() {
        token = getSp().getString(TOKEN, "");
        account = getSp().getString(ACCOUNT, "");
        password = getSp().getString(PASSWORD, "");
        communityName = getSp().getString(COMMUNITY_NAME, "");
        communityCode = getSp().getString(COMMUNITY_CODE, "");
        dir = getSp().getString(DIR, "");
        city = getSp().getString(CITY, "");
        latitude = getSp().getString(LATITUDE, "");
        longitude = getSp().getString(LONGITUDE, "");
        sip = getSp().getString(SIP, "");
        province = getSp().getString(PROVINCE, "");
        city = getSp().getString(CITY, "");
        county = getSp().getString(COUNTY, "");
        address = getSp().getString(ADDRESS, "");
        getUserInfo();
    }

    //更新门禁机的路径
    public static boolean setDir(String dir) {
        UserHelper.dir = dir;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(DIR, dir);
        return editor.commit();
    }

    //更新省份名称
    public static boolean setProvince(String province) {
        UserHelper.province = province;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(PROVINCE, province);
        return editor.commit();
    }

    //更新城市名称
    public static boolean setCity(String city) {
        UserHelper.city = city;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(CITY, city);
        return editor.commit();
    }

    //更新区名
    public static boolean setCounty(String county) {
        UserHelper.county = county;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(COUNTY, county);
        return editor.commit();
    }

    //更新完整地址
    public static boolean setAddress(String address) {
        UserHelper.address = address;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(ADDRESS, address);
        return editor.commit();
    }

    //更新位置信息
    public static boolean setPosition(String province, String city, String county, String address) {
        UserHelper.province = province;
        UserHelper.city = city;
        UserHelper.county = county;
        UserHelper.address = address;

        SharedPreferences.Editor editor = getEditor();
        editor.putString(PROVINCE, province);
        editor.putString(CITY, city);
        editor.putString(COUNTY, county);
        editor.putString(ADDRESS, address);

        return editor.commit();
    }

    private static SharedPreferences getSp() {
        SharedPreferences sp = BaseApplication.mContext.getSharedPreferences(USER, Context.MODE_PRIVATE);
        return sp;
    }

    private static SharedPreferences.Editor getEditor() {
        return getSp().edit();
    }

    public static String string() {
        return "UserHelper{" +
                "token=" + token +
                ", sip='" + sip + '\'' +
                ", dir='" + dir + '\'' +
                '}';
    }
}
