package com.aglhz.yicommunity.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;

import com.aglhz.abase.log.ALog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leguang on 2017/7/1 0001.
 * Email：langmanleguang@qq.com
 * <p>
 * 用于手机接收到短信验证码后自动解析出验证码供外部填充在相应控件上。
 * 为保证最大化解析成功，同时使用广播和内容观察者。因为有些ROM是不允许监听短信广播的。
 */

public class SmsHelper {
    private static final String TAG = SmsHelper.class.getSimpleName();
    private static final String SMS_RECEIVED_ACTION = Telephony.Sms.Intents.SMS_RECEIVED_ACTION;// 接收到短信时的action
    private static final String SMS_INBOX_URI = "content://sms/inbox";//API level>=23,可直接使用Telephony.Sms.Inbox.CONTENT_URI
    private static final String SMS_URI = "content://sms";//API level>=23,可直接使用Telephony.Sms.CONTENT_URI
    static final String[] PROJECTION = new String[]{
            Telephony.Sms._ID,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY,
            Telephony.Sms.DATE
    };
    private Context mContext;
    private OnSmsParsedListener mOnSmsParsedListener;

    public SmsHelper(Context context) {
        mContext = context;
    }

    /**
     * 短信广播接收者
     */
    private BroadcastReceiver mReadSmsCodeReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
                getSmsCodeFromReceiver(intent);
            }
        }
    };

    /**
     * 读取未读短信，用以填写验证码
     */
    private ContentObserver mReadSmsObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Cursor cursor = mContext.getContentResolver().query(Uri.parse(SMS_INBOX_URI), PROJECTION,
                    Telephony.Sms.READ + "=?", new String[]{"0"}, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER);
            getSmsCodeFromObserver(cursor);
        }
    };


    /**
     * 包访问级别:提高性能
     * 从接收者中得到短信验证码
     *
     * @param intent
     */
    private void getSmsCodeFromReceiver(Intent intent) {
        SmsMessage[] messages = null;
        if (Build.VERSION.SDK_INT >= 19) {
            messages = android.provider.Telephony.Sms.Intents.getMessagesFromIntent(intent);
            if (messages == null) return;
        } else {
            messages = getSmsUnder19(intent);
            if (messages == null) return;
        }
        if (messages.length > 0) {
            for (int i = 0; i < messages.length; i++) {
                SmsMessage sms = messages[i];
//                String smsSender = sms.getOriginatingAddress();
                String smsBody = sms.getMessageBody();
                if (checkSmsBody(smsBody)) {
                    String smsCode = parseSmsBody(smsBody);
                    //如果通过广播接收到了短信并成功解析出验证码，则通过接口传出去。
                    if (mOnSmsParsedListener != null) {
                        mOnSmsParsedListener.onSmsParsed(smsCode);
                    }
                    break;
                }
            }
        }
    }

    @Nullable
    private SmsMessage[] getSmsUnder19(Intent intent) {
        SmsMessage[] messages;
        Bundle bundle = intent.getExtras();
        // 相关链接:https://developer.android.com/reference/android/provider/Telephony.Sms.Intents.html#SMS_DELIVER_ACTION
        Object[] pdus = (Object[]) bundle.get("pdus");

        if ((pdus == null) || (pdus.length == 0)) {
            return null;
        }

        messages = new SmsMessage[pdus.length];
        for (int i = 0; i < pdus.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        }
        return messages;
    }

    /**
     * 包访问级别:提高性能
     * 从内容观察者得到短信验证码
     *
     * @param cursor
     */
    private void getSmsCodeFromObserver(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        while (cursor.moveToNext()) {
//            String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
            String smsBody = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
            if (checkSmsBody(smsBody)) {
                String smsCode = parseSmsBody(smsBody);
                ALog.e("smsCode-->" + smsCode);
                if (mOnSmsParsedListener != null) {
                    mOnSmsParsedListener.onSmsParsed(smsCode);
                }
                break;
            }
        }
        closeCursor(cursor);
    }

    private void closeCursor(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) return;

        if (!cursor.isClosed()) {
            cursor.close();
        }
    }

    /**
     * 注册广播接收者，内容观察者
     */
    public void register() {
        registerReceiver();
        registerObserver();
    }

    /**
     * 注销广播接收者，内容观察者
     */
    public void unRegister() {
        unRegisterReceiver();
        unRegisterObserver();
    }

    /**
     * 注册广播接收者
     */
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(SMS_RECEIVED_ACTION);
        filter.addAction(SMS_RECEIVED_ACTION);
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        mContext.registerReceiver(mReadSmsCodeReceiver, filter);
    }

    /**
     * 注册内容观察者
     */
    private void registerObserver() {
        mContext.getContentResolver().registerContentObserver(Uri.parse(SMS_URI), true, mReadSmsObserver);
    }

    /**
     * 注销广播接收者
     */
    private void unRegisterReceiver() {
        if (mReadSmsCodeReceiver == null) return;
        mContext.unregisterReceiver(mReadSmsCodeReceiver);
        mReadSmsCodeReceiver = null;
    }

    /**
     * 注销内容观察者
     */
    private void unRegisterObserver() {
        if (mReadSmsObserver == null) return;

        mContext.getContentResolver().unregisterContentObserver(mReadSmsObserver);
        mReadSmsObserver = null;
    }

    /**
     * 解析短信得到验证码
     *
     * @param smsBody
     * @return
     */
    private String parseSmsBody(String smsBody) {
        String regex = new String("(\\d{" + 6 + "})");// 匹配规则为短信中的连续数字
        String smsCode = "";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(smsBody);

        while (matcher.find()) {
            smsCode = matcher.group(0);
        }
        return smsCode;
    }

    /**
     * @param smsBody
     * @return
     */
    private boolean checkSmsBody(String smsBody) {
        String reg = "^.*验证码\\d{6}.*$";
        return smsBody.matches(reg);
    }

    public interface OnSmsParsedListener {
        void onSmsParsed(String code);
    }

    public void setOnSmsParsedListener(OnSmsParsedListener listener) {
        mOnSmsParsedListener = listener;
    }

    public void clear() {
        unRegister();
        if (mContext != null) {
            mContext = null;
        }
    }
}
