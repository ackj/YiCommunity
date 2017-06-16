package com.aglhz.yicommunity.common;

import android.content.Intent;
import android.text.TextUtils;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.main.door.call.CallActivity;
import com.sipphone.sdk.SipCoreManager;
import com.sipphone.sdk.SipCorePreferences;
import com.sipphone.sdk.SipService;
import com.sipphone.sdk.access.WebApiConstants;
import com.sipphone.sdk.access.WebUserApi;

import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreListenerBase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.Intent.ACTION_MAIN;

/**
 * Author：leguang on 2017/5/5 0009 10:49
 * Email：langmanleguang@qq.com
 * <p>
 * 门禁管理类
 */

public class DoorManager {
    private static final String TAG = DoorManager.class.getSimpleName();
    private static final String URL = "http://member.planidea.cn";
    private static DoorManager mDoorManager;
    private WebUserApi mWebUserApi;
    private LinphoneCallBack mListener;

    private DoorManager() {
        WebApiConstants.setHttpServer(URL);
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


    public DoorManager startService() {
        ALog.e(TAG, "startService");

        BaseApplication.mContext.startService(new Intent(ACTION_MAIN)
                .setClass(BaseApplication.mContext, SipService.class));

        Observable.create(o -> {
            try {
                while (!SipService.isReady()) {
                    try {
                        ALog.e(TAG, "while");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.onNext(o);
            } catch (Exception e) {
                o.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    SipService.instance()
                            .setActivityToLaunchOnIncomingReceived(CallActivity.class);
                    ALog.e(TAG, "setActivityToLaunchOnIncomingReceived");
                });
        return this;
    }

    public void exit() {
        // 停止SipService，用户明确的退出
        removeCallListener();
        BaseApplication.mContext.stopService(new Intent(ACTION_MAIN)
                .setClass(BaseApplication.mContext, SipService.class));
    }


    public DoorManager initWebUserApi(String userName, AccessCallBack accessCallBack) {

        mWebUserApi = new WebUserApi(BaseApplication.mContext);
        mWebUserApi.setOnAccessTokenListener(accessCallBack);
        mWebUserApi.accessToken(Device.UUID, userName);

        return this;
    }


    public DoorManager setCallListener(LinphoneCallBack callBack) {
        mListener = callBack;

        // 添加监听器对象到Native层的LinhoneCore监听器对象接口
        LinphoneCore lc = SipCoreManager.getLcIfManagerNotDestroyedOrNull();

        if (lc != null) {
            lc.addListener(mListener);

            try {
                // 这里总是报空指针，干脆让这个代码运行多次注册，同时捕获异常。
                SipCorePreferences.instance().setAccountOutboundProxyEnabled(0, true);
                ALog.e(TAG, "11111成功注册代理服务器…………………………………………");
            } catch (Exception e) {
                ALog.e(TAG, "11111异常出错了");
                e.printStackTrace();
            }
        }
        return this;
    }


    public DoorManager removeCallListener() {
        LinphoneCore lc = SipCoreManager.getLcIfManagerNotDestroyedOrNull();
        if (lc != null) {
            lc.removeListener(mListener);
        }

        return this;
    }

    public void callOut(String to) {
        if (TextUtils.isEmpty(to)) {
            return;
        }

        try {
            if (!SipCoreManager.getInstance().acceptCallIfIncomingPending()) {
                ALog.e(TAG, "sip:D" + to + "@member");
                SipCoreManager.getInstance().newOutgoingCall("sip:D" + to + "@member");
            }
        } catch (LinphoneCoreException e) {    // 呼叫发生异常，终止当前的Call
            SipCoreManager.getInstance().terminateCall();
        }
    }

    public static class LinphoneCallBack extends LinphoneCoreListenerBase {

    }

    public interface AccessCallBack extends WebUserApi.onAccessTokenListener {

    }

    public static class Device {
        public final static String UUID = "b4ec659b-af86-4333-b753-98839a2d4dbb";
        public final static String UserName = "da";
        public final static String tenantCode = "T0001";
    }
}
