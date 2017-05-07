package com.aglhz.yicommunity.common;


import java.util.HashMap;
import java.util.Map;

import static com.aglhz.yicommunity.common.UserHelper.token;

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
//
//        Map<String, Object> map = new HashMap<>();
//        if (UserInfoHelper.getUserInfo() == null) return map;
//        String token = UserInfoHelper.getUserInfo().getToken();
//        KLog.e("token：：" + token);
//        map.put("token", token);
        return new HashMap<>();
    }

    public static Map<String, Object> getPamras() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
