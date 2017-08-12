package com.aglhz.yicommunity.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventPay;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = WXPayEntryActivity.class.getName();
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_pay);

        api = WXAPIFactory.createWXAPI(this, UserHelper.WXAPPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

        ALog.e(TAG, "resp.errCode=" + req.openId);
        ALog.e(TAG, "resp.errCode=" + req.getType());
        ALog.e(TAG, "resp.errCode=" + req.transaction);

    }

    @Override
    public void onResp(BaseResp resp) {
        ALog.e(TAG, "resp.errCode=" + resp.errStr);
        ALog.e(TAG, "resp.errCode=" + resp.openId);
        ALog.e(TAG, "resp.errCode=" + resp.transaction);
        ALog.e(TAG, "resp.errCode=" + resp.getType());
        ALog.e(TAG, "resp.errCode=" + resp.checkArgs());
        ((Vibrator) App.mContext.getSystemService(VIBRATOR_SERVICE)).vibrate(500);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            EventBus.getDefault().post(new EventPay(resp.errCode));
            finish();
        }
    }
}