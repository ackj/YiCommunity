package com.aglhz.yicommunity.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.aglhz.abase.log.ALog;
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
    private static final String CITY = "city";
    public static String token = "";
    public static String communityName = "";
    public static String city = "";
    public static String communityCode = "";
    public static String account = "";
    public static String password = "";
    public static String dir = "";//默认设置的一键开门的设备路径。
    public static UserBean.DataBean.MemberInfoBean userInfo;

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
        userInfo = memberInfo;
        SharedPreferences.Editor editor = getEditor();
        Gson gson = new Gson();
        String info = gson.toJson(memberInfo);
        editor.putString(USER_INFO, info);
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

    //更新社区名称和社区代码
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
        getUserInfo();
    }

    //更新社区名称和社区代码
    public static boolean setDir(String dir) {
        UserHelper.dir = dir;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(DIR, dir);
        return editor.commit();
    }

    //更新社区名称和社区代码
    public static boolean setCity(String city) {
        UserHelper.city = city;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(CITY, city);
        return editor.commit();
    }

    private static SharedPreferences getSp() {
        SharedPreferences sp = BaseApplication.mContext.getSharedPreferences(USER, Context.MODE_PRIVATE);
        return sp;
    }

    private static SharedPreferences.Editor getEditor() {
        return getSp().edit();
    }
}
