package com.aglhz.yicommunity.common.payment;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.BaseApplication;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.List;

/**
 * Created by YandZD on 2017/3/2.
 */

public class WxPayHelper {
    private static final String TAG = WxPayHelper.class.getSimpleName();

    public static String appId;

    public static void WxPay(String string) {
        ALog.e("微信支付：：" + string);
        JSONObject json;
        try {
            json = new JSONObject(string);
            JSONObject payData = json.getJSONObject("data");
            ALog.e("json", json.toString());

            PayReq req = new PayReq();
            //{"data":{"appId":"wxe5dbac804d7bb267","timeStamp":"1488436959","noncestr":"cfb9296583e2461caeda09d0d3621f9a","sign":"BC569731EE8560316A719BC1F8EBCF0E","partnerid":"1335876101","prepayid":"wx20170302144239e711b2897c0269541507","package_":"Sign=WXPay","wxOrderSn":"20170302144238868719"},"other":{"code":200,"message":"","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}}
            req.appId = payData.optString("appid");
            ALog.e("appId", req.appId);
            req.partnerId = payData.optString("partnerid");
            ALog.e("partnerId", req.partnerId);
            req.prepayId = payData.optString("prepayid");
            ALog.e("prepayId", req.prepayId);
            req.packageValue = "Sign=WXPay";
            ALog.e("packageValue", req.packageValue);
            req.nonceStr = payData.optString("noncestr");
            ALog.e("nonceStr", req.nonceStr);
            req.timeStamp = payData.optString("timestamp");
            ALog.e("timeStamp", req.timeStamp);
            req.sign = payData.optString("sign");
            ALog.e("sign", req.sign);

            appId = req.appId;
            IWXAPI api = WXAPIFactory.createWXAPI(BaseApplication.mContext, req.appId);
            api.sendReq(req);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // 二次签名
    private static String genAppSign(List<NameValuePair> params, String key) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(key);

        String appSign = getMessageDigest(sb.toString().getBytes()).toUpperCase();

        return appSign;
    }

    public static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
