package com.aglhz.yicommunity.common.payment;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.event.EventPay;
import com.alipay.sdk.app.PayTask;

import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.Map;

/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 支付宝工具
 */
public class ALiPayHelper {
    private static final String TAG = ALiPayHelper.class.getSimpleName();
    private static final int SDK_PAY_FLAG = 1;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == SDK_PAY_FLAG) {
                Map<String, String> map = (Map<String, String>) msg.obj;
                if (TextUtils.equals(map.get("result"), "9000")) {
                    Toast.makeText(BaseApplication.mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new EventPay());
                } else {
                    Toast.makeText(BaseApplication.mContext, map.get("memo"), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public void pay(Activity mActivity, String orderInfo) {

        ALog.e("orderInfo::" + orderInfo);

        new Thread(() -> {
            PayTask alipay = new PayTask(mActivity);

            Map<String, String> mapResult = alipay.payV2(orderInfo, true);
            Message msg = new Message();
            msg.what = SDK_PAY_FLAG;
            msg.obj = mapResult;
            mHandler.sendMessage(msg);

            Iterator<Map.Entry<String, String>> it = mapResult.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            }

        }).start();
    }
}



