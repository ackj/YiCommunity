package com.aglhz.yicommunity.common.share;

import android.text.TextUtils;

import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by leguang on 2017/6/16 0016.
 * Email：langmanleguang@qq.com
 * <p>
 * 负责微信分享的封装
 */

public class WxShare {
    public static final String TAG = WxShare.class.getSimpleName();

    public static void sendText(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }

        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.title = "亿社区临时密码分享";
        msg.description = text;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;

        IWXAPI api = WXAPIFactory.createWXAPI(App.mContext, Constants.WX_APP_ID);
        api.sendReq(req);
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
