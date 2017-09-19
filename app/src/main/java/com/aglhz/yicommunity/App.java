package com.aglhz.yicommunity;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

import com.aglhz.abase.BaseApplication;
import com.aglhz.abase.common.ActivityHelper;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.NotificationHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.common.boxing.BoxingGlideLoader;
import com.aglhz.yicommunity.event.EventRefreshMessageList;
import com.aglhz.yicommunity.event.EventUnread;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class App extends BaseApplication implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initData();//数据的初始化要在友盟推送之前，因为要注册别名时，用到用户名。
        initPush();//初始化友盟推送。
        initBoxing();//初始化图片选择器。
    }

    private void initData() {
        UserHelper.init();
        registerActivityLifecycleCallbacks(this);
    }

    private void initBoxing() {
        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
//        BoxingCrop.getInstance().init(new BoxingUcrop());初始化图片裁剪（可选）
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
        mPushAgent.setResourcePackageName("com.aglhz.yicommunity");
        mPushAgent.setNotificaitonOnForeground(false);
        //sdk开启通知声音
//        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);

        ALog.e(TAG, "UserHelper.account-->" + UserHelper.account);

        mPushAgent.addExclusiveAlias(UserHelper.account, "userType", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                ALog.e(TAG, "addAlias-->" + b + "……" + s);
            }
        });

        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {

                ALog.e(TAG, "deviceToken-->" + deviceToken);

                HttpHelper.getService(ApiService.class)
                        .requestUMeng(ApiService.requestUMeng, UserHelper.token, "and_" + deviceToken, UserHelper.account, "userType")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseBean -> ALog.e(TAG, baseBean.getOther().getMessage()));
            }

            @Override
            public void onFailure(String s, String s1) {
                ALog.e(TAG, "register failed: " + s + " ---  " + s1);
            }
        });

        UmengMessageHandler messageHandler = new UmengMessageHandler() {

            //自定义通知样式
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                //每当有通知送达时，均会回调getNotification方法，因此可以通过监听此方法来判断通知是否送达。
                ALog.e(TAG, msg.getRaw().toString());
                ALog.e(TAG, "msg.builder_id-->" + msg.builder_id);

                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, NotificationHelper.CALL_IN + "");
                wakeLock.acquire();
//                wakeLock.release();

                if (msg.builder_id == 1 && !ActivityHelper.getInstance().isEmpty()) {
                    msg.builder_id = 0;
                }

                EventBus.getDefault().post(new EventUnread());
                EventBus.getDefault().post(new EventRefreshMessageList());

                switch (msg.builder_id) {
                    //自定义通知样式编号
                    case 1:
                        ALog.e(TAG, msg.builder_id);
                        ALog.e(TAG, msg.custom);
                        ALog.e(TAG, msg.play_vibrate);
                        ALog.e(TAG, msg.play_lights);
                        ALog.e(TAG, msg.play_sound);

//                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        Notification notification = new NotificationCompat.Builder(App.mContext)
//                                .setContentTitle(msg.title)
//                                .setContentText(msg.text)
//                                .setWhen(System.currentTimeMillis())
//                                .setSmallIcon(R.mipmap.ic_app_logo)
//                                .setLargeIcon(BitmapFactory.decodeResource(App.mContext.getResources(), R.mipmap.ic_app_logo))
//                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
//                                .setVibrate(new long[]{0, 1000, 1000, 1000})
//                                .setLights(Color.RED, 1000, 1000)
//                                .setPriority(NotificationCompat.PRIORITY_MAX)
//                                .setAutoCancel(true)
//                                .build();
//                        notification.flags |= Notification.FLAG_INSISTENT;
//
//                        Observable.timer(10, TimeUnit.SECONDS)
//                                .subscribe(new Consumer<Long>() {
//                                    @Override
//                                    public void accept(@NonNull Long aLong) throws Exception {
//                                        NotificationHelper.cancel();
//                                        notification.flags = Notification.DEFAULT_SOUND;
//                                        manager.notify(NotificationHelper.CALL_IN, notification);
//                                    }
//                                });

                        return NotificationHelper.callIn(msg);
//                        return notification;
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };

        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 该Handler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * 参考集成文档的1.6.2
         * [url=http://dev.umeng.com/push/android/integration#1_6_2]http://dev.umeng.com/push/android/integration#1_6_2[/url]
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            //点击通知的自定义行为
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                ALog.e(TAG, msg.getRaw().toString());//未来考虑把这个写入本地日志系统，当然要考虑异步形式。
                ALog.e(TAG, msg.custom);
//                ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).cancel();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }
}
