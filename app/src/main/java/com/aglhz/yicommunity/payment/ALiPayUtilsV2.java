package com.aglhz.yicommunity.payment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 支付宝工具
 */
public class ALiPayUtilsV2 {
    private JSONObject mOrder;
    private Activity mContext;

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    PayResultV2 payResult = new PayResultV2((Map<String, String>) msg.obj);

                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
//                        EventBus.getDefault().post(new PaySuccess(mContext, resultInfo));
//                        //回调 服务器成功
//
//                    } else {
//                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                       Toast.makeText(mContext, payResult.getMemo(), Toast.LENGTH_SHORT).show();
//                    }
                    break;
                }

                default:
                    break;
            }
        }
    };

    public ALiPayUtilsV2(Activity context) {
        this.mContext = context;
    }


    public void alipay2(String a) {
//        Gson gson = new Gson();
//        payBean = gson.fromJson(a, ALiPayBean.class);
//        payBean.setData(gson.fromJson(a, ALiPayBean.class));
        pay(a);
    }


    public void alipay(String a) {
        Gson gson = new Gson();
//        payBean = gson.fromJson(a, ALiPayBean.class);
        pay(a);
    }

//    private ALiPayBean payBean;

    private void pay(final String orderInfo) {
//        String appId = payBean.getData().getAppId();
//        String out_trade_no = payBean.getData().getOut_trade_no();
//        String body = payBean.getData().getBody();
//        String subject = payBean.getData().getSubject();
//        String price = payBean.getData().getTotal_fee();
//        String res_pricate = payBean.getData().getPrivateKey();
//
//        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(appId, out_trade_no, body, subject, price);
//        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//        String sign = OrderInfoUtil2_0.getSign(params, res_pricate);
//        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}



