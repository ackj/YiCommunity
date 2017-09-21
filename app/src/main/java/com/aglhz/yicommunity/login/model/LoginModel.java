package com.aglhz.yicommunity.login.model;

import android.app.Notification;
import android.content.Context;
import android.widget.Toast;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.SipBean;
import com.aglhz.yicommunity.entity.bean.UserBean;
import com.aglhz.yicommunity.login.contract.LoginContract;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class LoginModel extends BaseModel implements LoginContract.Model {
    private static final String TAG = LoginModel.class.getSimpleName();

    @Override
    public Observable<UserBean> requestLogin(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestLogin(ApiService.requestLogin, params.sc, params.user, params.pwd)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SipBean> requestSip(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestSip(ApiService.requestSip, params.token)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void requestUMeng(String alias) {
        PushAgent mPushAgent = PushAgent.getInstance(App.mContext);
        mPushAgent.addExclusiveAlias(alias, "userType", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                ALog.e(TAG, "addAlias::" + b + "……" + s);
            }
        });

        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                ALog.e(TAG, "deviceToken-->" + deviceToken);

                HttpHelper.getService(ApiService.class)
                        .requestUMeng(ApiService.requestUMeng, UserHelper.token, "and_" + deviceToken, alias, "userType")
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

            @Override
            public Notification getNotification(Context context, UMessage msg) {

                switch (msg.builder_id) {
                    case 1:
                        ALog.e(TAG, msg.builder_id);

//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
//                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
//                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
//                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
//                        myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
//                        builder.setContent(myNotificationView);
//                        builder.setAutoCancel(true);
//                        Notification mNotification = builder.build();
//                        //由于Android v4包的bug，在2.3及以下系统，Builder创建出来的Notification，并没有设置RemoteView，故需要添加此代码
//                        mNotification.contentView = myNotificationView;
//                        return mNotification;
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };

        mPushAgent.setMessageHandler(messageHandler);

        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            //点击通知的自定义行为
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }
}