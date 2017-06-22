package com.aglhz.yicommunity.common.push;

/**
 * Author：leguang on 2017/5/5 0009 10:49
 * Email：langmanleguang@qq.com
 * <p>
 * 推送管理类
 */

public class PushManager {
    private static final String TAG = PushManager.class.getSimpleName();
    private static PushManager mPushManager;
    private final String ALIAS_TYPE = "userType";

    private PushManager() {
    }

    //获取单例
    public static PushManager getInstance() {
        if (mPushManager == null) {
            synchronized (PushManager.class) {
                if (mPushManager == null) {
                    mPushManager = new PushManager();
                }
            }
        }
        return mPushManager;
    }


    public void init() {

    }

    public void exit() {


    }

}
