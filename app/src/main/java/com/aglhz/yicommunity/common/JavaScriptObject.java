package com.aglhz.yicommunity.common;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.aglhz.yicommunity.payment.ALiPayUtilsV2;

/**
 * Created by YandZD on 2017/1/22.
 */
public class JavaScriptObject {
    private Activity mActivity;

    public JavaScriptObject(Activity context) {
        mActivity = context;
    }

    @JavascriptInterface
    public void appWeixinPay(String str) {
        Toast.makeText(mActivity, "正在完善功能", Toast.LENGTH_SHORT).show();
    }


    @JavascriptInterface
    public void appAliPay(String str) {
        new ALiPayUtilsV2(mActivity).alipay2(str);
    }
}
