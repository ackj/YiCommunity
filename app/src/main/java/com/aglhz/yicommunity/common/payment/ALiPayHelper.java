package com.aglhz.yicommunity.common.payment;

import android.app.Activity;
import android.os.Vibrator;
import android.text.TextUtils;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.event.EventPay;
import com.alipay.sdk.app.PayTask;

import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.Map;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 支付宝工具
 */
public class ALiPayHelper {
    private static final String TAG = ALiPayHelper.class.getSimpleName();

    public void pay(Activity mActivity, String orderInfo) {
        ALog.e("orderInfo-->" + orderInfo);

        new Thread(() -> {
            PayTask alipay = new PayTask(mActivity);

            Map<String, String> mapResult = alipay.payV2(orderInfo, true);

            Iterator<Map.Entry<String, String>> it = mapResult.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                ALog.e(TAG, "key= " + entry.getKey() + " and value= " + entry.getValue());
            }

            if (TextUtils.equals(mapResult.get("resultStatus"), "9000")) {
                EventBus.getDefault().post(new EventPay(0));
            } else {
                EventBus.getDefault().post(new EventPay(-1));
            }

            ((Vibrator) App.mContext.getSystemService(VIBRATOR_SERVICE)).vibrate(500);
        }).start();
    }
}



