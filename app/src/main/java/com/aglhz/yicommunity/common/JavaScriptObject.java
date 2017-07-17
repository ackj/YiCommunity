package com.aglhz.yicommunity.common;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.common.payment.ALiPayHelper;
import com.aglhz.yicommunity.common.payment.WxPayHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * JS调用。
 */
public class JavaScriptObject {
    private Activity mActivity;

    public JavaScriptObject(Activity context) {
        mActivity = context;
    }

    @JavascriptInterface
    public void appWeixinPay(String str) {
        ALog.e("JS--WX-->" + str);
        WxPayHelper.WxPay(str);
    }

    @JavascriptInterface
    public void appAliPay(String str) {
        ALog.e("JS--appAliPay-->" + str);

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(str);

            JSONObject jsonData = jsonObject.optJSONObject("data");
            new ALiPayHelper().pay(mActivity, jsonData.optString("body"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
