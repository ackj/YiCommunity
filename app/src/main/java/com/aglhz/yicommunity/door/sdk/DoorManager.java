package com.aglhz.yicommunity.door.sdk;

import android.app.Activity;

import com.sipphone.sdk.SipService;
import com.sipphone.sdk.access.WebReponse;
import com.sipphone.sdk.access.WebUserApi;

/**
 * Author：leguang on 2017/5/5 0009 10:49
 * Email：langmanleguang@qq.com
 * <p>
 * 门禁管理类
 */

public class DoorManager {
    private WebUserApi mWebUserApi;
    private static DoorManager mDoorManager;

    private DoorManager() {
    }

    //获取单例
    public static DoorManager getInstance() {
        if (mDoorManager == null) {
            synchronized (DoorManager.class) {
                if (mDoorManager == null) {
                    mDoorManager = new DoorManager();
                }
            }
        }
        return mDoorManager;
    }


    public void hardwareInit(Activity mActivity) {
        mWebUserApi = new WebUserApi(mActivity);
        mWebUserApi.setOnAccessTokenListener(new WebUserApi.onAccessTokenListener() {

            @Override
            public void onPreAccessToken() {

            }

            @Override
            public void onPostAccessToken(WebReponse webReponse) {

                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        while (true) {
                            if (SipService.isReady()) {
                                // 启动SipService
                                SipService.instance().setActivityToLaunchOnIncomingReceived(mActivity.getClass());
                                break;
                            }

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();

            }

        });

        mWebUserApi.accessToken(DeviceInfo.UUID, DeviceInfo.UserName);
    }
}
