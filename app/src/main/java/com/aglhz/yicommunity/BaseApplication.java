package com.aglhz.yicommunity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.boxingimpl.BoxingGlideLoader;
import com.aglhz.yicommunity.common.AppContext;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import me.yokeyword.fragmentation.Fragmentation;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class BaseApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = BaseApplication.class.getSimpleName();
    public static Context mContext;
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initPush();

        registerActivityLifecycleCallbacks(this);

        tempInit();

        initBoxing();

    }

    private void initBoxing() {
        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
//        BoxingCrop.getInstance().init(new BoxingUcrop());初始化图片裁剪（可选）

    }

    private void tempInit() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .install();

        //初始化内存泄露监听
        mRefWatcher = LeakCanary.install(this);

        // 初始化卡顿监听
        BlockCanary.install(this, new AppContext()).start();

        //  Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler.getInstance(this));
        ALog.init(true, "ysq");

    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ALog.e("onActivityCreated");
        PushAgent.getInstance(mContext).onAppStart();
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ALog.e("onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ALog.e("onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ALog.e("onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ALog.e("onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        ALog.e("onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ALog.e("onActivityDestroyed");
    }

    //初始化友盟推送
    private void initPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setDebugMode(true);

        //sdk开启通知声音
//        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
//
//        mPushAgent.setResourcePackageName("com.aglhz.yicommunity");

        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                ALog.e("deviceToken::" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                ALog.e("register failed: " + s + " ---  " + s1);
            }
        });

//        UmengMessageHandler messageHandler = new UmengMessageHandler() {
//            /**
//             * 自定义消息的回调方法
//             */
//            @Override
//            public void dealWithCustomMessage(final Context context, final UMessage msg) {
//            }
//
//            /**
//             * 自定义通知栏样式的回调方法
//             */
//            @Override
//            public Notification getNotification(Context context, UMessage msg) {
//                switch (msg.builder_id) {
//                    default:
//                    case 0:
////                        MediaPlayer mp = MediaPlayer.create(MyApplication.this, R.raw.soundfile2);
//////                        mp.setVolume(80f,80f);
////                        mp.start();
////                        EventBus.getDefault().post(new UmengNotificationEvent());
//                        return super.getNotification(context, msg);
//                }
//            }
//        };
//        mPushAgent.setMessageHandler(messageHandler);
    }
}
