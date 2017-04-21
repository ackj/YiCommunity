package com.aglhz.yicommunity.common;


import java.util.HashMap;
import java.util.Map;

/**
 * 公共请求参数基类
 * <p>
 * Created by YandZD on 2017/1/17.
 */

public class BaseParams {

    public static Map<String, Object> getScMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("sc", "AglhzYsq");
        return map;
    }

    public static Map<String, Object> getTokenMap() {
//        if (UserInfoHelper.isLogin() == false) {
//            RxBus.get().post(new NoLoginEvent());
//        }

        Map<String, Object> map = new HashMap<>();
//        if (UserInfoHelper.getUserInfo() == null) return map;
//        String token = UserInfoHelper.getUserInfo().getToken();
        map.put("token", "tk_dbdc23df-87ec-4f8a-8956-22dd514e147a");
        return map;
    }

    public static Map<String, Object> getPamras() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
