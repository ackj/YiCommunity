package com.aglhz.yicommunity.common;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.umeng.message.entity.UMessage;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by leguang on 2017/9/18 0018.
 * Email：langmanleguang@qq.com
 */

public class NotificationHelper {
    public static final String TAG = "NotificationHelper";
    public static final int CALL_IN = 0x01;

    public static Notification callIn(UMessage msg) {
        ALog.e(TAG, "callIn");

        Notification notification = new NotificationCompat.Builder(App.mContext)
                .setContentTitle(msg.title)
                .setContentText(msg.text)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_app_logo)
                .setLargeIcon(BitmapFactory.decodeResource(App.mContext.getResources(), R.mipmap.ic_app_logo))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .setLights(Color.RED, 1000, 1000)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .build();
        notification.flags |= Notification.FLAG_INSISTENT;

        Observable.timer(15, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        NotificationManager manager = (NotificationManager) App.mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                        notification.flags = Notification.DEFAULT_SOUND;
                        manager.notify(NotificationHelper.CALL_IN, notification);
//                        cancel();
                    }
                });
        return notification;
    }

    public static void cancel() {
        ALog.e(TAG, "cancel");
        NotificationManager manager = (NotificationManager) App.mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }
}
