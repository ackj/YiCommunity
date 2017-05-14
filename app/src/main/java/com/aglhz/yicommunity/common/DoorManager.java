package com.aglhz.yicommunity.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.door.call.CallActivity;
import com.aglhz.yicommunity.login.LoginActivity;
import com.aglhz.yicommunity.main.MainActivity;
import com.amap.api.location.AMapLocation;
import com.sipphone.sdk.SipCoreManager;
import com.sipphone.sdk.SipCorePreferences;
import com.sipphone.sdk.SipService;
import com.sipphone.sdk.access.WebApiConstants;
import com.sipphone.sdk.access.WebReponse;
import com.sipphone.sdk.access.WebUserApi;
import com.ta.utdid2.device.DeviceInfo;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreListenerBase;
import org.linphone.core.LinphoneProxyConfig;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import mlxy.utils.S;

import static com.aglhz.yicommunity.R.string.member;
import static com.alipay.sdk.app.statistic.c.w;

/**
 * Author：leguang on 2017/5/5 0009 10:49
 * Email：langmanleguang@qq.com
 * <p>
 * 门禁管理类
 */

public class DoorManager {
    private static final String TAG = DoorManager.class.getSimpleName();
    private static DoorManager mDoorManager;
    private WebUserApi mWebUserApi;

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


    public void init() {
        WebApiConstants.setHttpServer("http://member.planidea.cn");

        // 检查是否已经初始化SipCoreManager是否初始化。
        if (SipCoreManager.isInstanciated()) {
            ALog.e("SipCoreManager已经初始化");
            return;
        }

        BaseApplication.mContext.startService(new Intent(android.content.Intent.ACTION_MAIN)
                .setClass(BaseApplication.mContext, SipService.class));

        Observable.create(o -> {
            try {
                while (!SipService.isReady()) {
                    try {
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
                .subscribe(o -> SipService.instance().setActivityToLaunchOnIncomingReceived(CallActivity.class));
    }

    public DoorManager initWebUserApi(String userName, AccessCallBack accessCallBack) {

        mWebUserApi = new WebUserApi(BaseApplication.mContext);
        mWebUserApi.setOnAccessTokenListener(new WebUserApi.onAccessTokenListener() {
            @Override
            public void onPreAccessToken() {
                if (accessCallBack != null) {
                    accessCallBack.onPreAccess();
                }
            }

            @Override
            public void onPostAccessToken(WebReponse webReponse) {
                if (accessCallBack != null) {
                    accessCallBack.onPostAccess(webReponse);
                }

                if (webReponse != null && webReponse.getStatusCode() == 200) {
                    // 登陆成功，启动对讲服务
                    ALog.e("111111111111111111" + userName);
                    UserHelper.setSip(userName);
                    // 登陆失败，显示提示信息
                    Toast.makeText(BaseApplication.mContext, "对讲服务启动成功", Toast.LENGTH_LONG).show();
                } else {
                    // 登陆失败，显示提示信息
                    Toast.makeText(BaseApplication.mContext, "对讲服务启动失败", Toast.LENGTH_LONG).show();
                }
            }
        });
        mWebUserApi.accessToken(Device.UUID, userName);

        return this;
    }


    public DoorManager setCallListener(LinphoneCallBack callBack) {

        if (callBack == null) {
            throw new IllegalArgumentException("callBack参数不能为null");
        }

        LinphoneCoreListenerBase mListener = new LinphoneCoreListenerBase() {

            @Override
            public void callState(LinphoneCore lc, LinphoneCall call, LinphoneCall.State state, String message) {

                callBack.callState(lc, call, state, message);

                ALog.e("1111111111" + state.toString() + "：：：" + message);

            }
        };

        // 添加监听器对象到Native层的LinhoneCore监听器对象接口
        LinphoneCore lc = SipCoreManager.getLcIfManagerNotDestroyedOrNull();
        if (lc != null) {
            lc.addListener(mListener);
            /**
             * 这里总是报空指针，干脆让这个代码运行多次注册，同时捕获异常。
             */
            try {

                SipCorePreferences.instance().setAccountOutboundProxyEnabled(0, true);
                ALog.e("成功注册代理服务器…………………………………………");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }


    public void callOut(String to) {
        if (TextUtils.isEmpty(to)) {
            return;
        }

        try {
            if (!SipCoreManager.getInstance().acceptCallIfIncomingPending()) {
                ALog.e("77777777:" + to);

                ALog.e("77777777:" + "sip:D" + to + "@member");


                SipCoreManager.getInstance().newOutgoingCall("sip:D" + to + "@member");
            }
        } catch (LinphoneCoreException e) {    // 呼叫发生异常，终止当前的Call
            SipCoreManager.getInstance().terminateCall();
        }
    }

    public interface LinphoneCallBack {

        void callState(LinphoneCore lc, LinphoneCall call, LinphoneCall.State state, String message);
    }

    public interface AccessCallBack {

        void onPreAccess();

        void onPostAccess(WebReponse webReponse);
    }


    public static class Device {
        public final static String UUID = "b4ec659b-af86-4333-b753-98839a2d4dbb";
        public final static String UserName = "da";
        public final static String tenantCode = "T0001";
    }
}
