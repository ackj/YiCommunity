package com.aglhz.yicommunity.login.view;

import android.annotation.SuppressLint;
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
import android.os.Message;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.login.contract.ResetPasswordContract;
import com.aglhz.yicommunity.login.presenter.ResetPasswordPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/10 0010 01:02.
 * Email: liujia95me@126.com
 */

public class ResetPasswordFragment extends BaseFragment<ResetPasswordContract.Presenter> implements ResetPasswordContract.View {
    public static final String TAG = ResetPasswordFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_phoneNo)
    EditText etPhoneNo;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.tv_get_verify)
    TextView tvGetVerify;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_again_password)
    EditText etAgainPassword;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Unbinder unbinder;

    private Thread getVerifyThread;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                tvGetVerify.setText("获取验证码");
                tvGetVerify.setEnabled(true);
            } else {
                if (tvGetVerify == null) {
                    return;
                }
                tvGetVerify.setText(msg.what + "秒后重试");
                tvGetVerify.setEnabled(false);
            }
        }
    };

    @NonNull
    @Override
    protected ResetPasswordContract.Presenter createPresenter() {
        return new ResetPasswordPresenter(this);
    }

    public static ResetPasswordFragment newInstance() {
        return new ResetPasswordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        register();//注册短信广播和短信内容观察者。
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("重置密码");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_36dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        getVerifyThread = new Thread(() -> {
            for (int i = 60; i >= 0; i--) {
                if (getVerifyThread == null) return;
                mHandler.sendEmptyMessage(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initListener() {
        etPhoneNo.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
        etVerifyCode.addTextChangedListener(textWatcher);
        etAgainPassword.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkIsInputComplete();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @OnClick(R.id.tv_get_verify)
    public void getVerify(View view) {
        String phoneNo = etPhoneNo.getText().toString();
        Params params = Params.getInstance();
        params.phoneNo = phoneNo;
        params.verifyType = "v_rePwd";
        mPresenter.requestVerifyCode(params);

        getVerifyThread.start();
    }

    @OnClick(R.id.btn_submit)
    public void btnReset(View view) {
        String phoneNo = etPhoneNo.getText().toString();
        String password = etPassword.getText().toString();
        String againPassword = etAgainPassword.getText().toString();
        String verCode = etVerifyCode.getText().toString();

        Params params = Params.getInstance();
        params.account = phoneNo;
        params.verifyCode = verCode;
        params.password1 = password;
        params.password2 = againPassword;

        mPresenter.requestReset(params);
    }

    //检查是否输入完全
    private void checkIsInputComplete() {
        boolean userName = TextUtils.isEmpty(etPhoneNo.getText().toString());
        boolean password = TextUtils.isEmpty(etPassword.getText().toString());
        boolean againPassword = TextUtils.isEmpty(etAgainPassword.getText().toString());
        boolean verCode = TextUtils.isEmpty(etVerifyCode.getText().toString());

        boolean all = userName || password || againPassword || verCode;

        btnSubmit.setEnabled(!all);
        if (tvGetVerify.getText().toString().equals("获取验证码")) {
            tvGetVerify.setEnabled(!userName);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getVerifyThread != null) {
            getVerifyThread.interrupt();
            getVerifyThread = null;
        }
        unbinder.unbind();
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void reponseResetSuccess(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), "重置密码成功！");
        pop();
    }

    @Override
    public void responseVerfyCodeSuccess(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), "获取验证码成功！");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegister();
    }

    //*******************以下为短信广播和读取未读短信以获取验证码的广播和内容观察者**********************************************
    private static final String SMS_CONTAINS = "验证码";// 短信内容含有“验证码”。
    private static final String SMS_RECEIVED_ACTION = Telephony.Sms.Intents.SMS_RECEIVED_ACTION;// 接收到短信时的action
    private static final String SMS_INBOX_URI = "content://sms/inbox";//API level>=23,可直接使用Telephony.Sms.Inbox.CONTENT_URI
    private static final String SMS_URI = "content://sms";//API level>=23,可直接使用Telephony.Sms.CONTENT_URI
    static final String[] PROJECTION = new String[]{
            Telephony.Sms._ID,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY,
            Telephony.Sms.DATE
    };

    /**
     * 读取未读短信，用以填写验证码
     */
    private ContentObserver mReadSmsObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            ALog.e("onChange");


            Cursor cursor = _mActivity.getContentResolver().query(Uri.parse(SMS_INBOX_URI), PROJECTION,
                    Telephony.Sms.READ + "=?", new String[]{"0"}, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER);
            getSmsCodeFromObserver(cursor);
        }
    };

    /**
     * 短信广播接收者
     */
    private BroadcastReceiver mReadSmsCodeReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            ALog.e("onReceive");

            if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
                ALog.e("onReceive-->getAction().equals");

                getSmsCodeFromReceiver(intent);
            }
        }
    };

    /**
     * 包访问级别:提高性能
     * 从接收者中得到短信验证码
     *
     * @param intent
     */
    private void getSmsCodeFromReceiver(Intent intent) {
        ALog.e("getSmsCodeFromReceiver");

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
                    etVerifyCode.setText(smsCode);
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
        ALog.e("getSmsCodeFromObserver");

        if (cursor == null) {
            ALog.e("判空为空");

            return;
        }

        while (cursor.moveToNext()) {
            ALog.e("不为空");

//            String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
            String smsBody = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
            if (checkSmsBody(smsBody)) {
                ALog.e("含有验证码");


                String smsCode = parseSmsBody(smsBody);

                ALog.e("smsCode-->" + smsCode);
                etVerifyCode.setText(smsCode);
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
    private void register() {
        ALog.e("register");
        registerReceiver();
        registerObserver();
    }

    /**
     * 注销广播接收者，内容观察者
     */
    private void unRegister() {
        ALog.e("unRegister");

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
        _mActivity.registerReceiver(mReadSmsCodeReceiver, filter);
    }

    /**
     * 注册内容观察者
     */
    private void registerObserver() {
        _mActivity.getContentResolver().registerContentObserver(Uri.parse(SMS_URI), true, mReadSmsObserver);
    }


    /**
     * 注销广播接收者
     */
    private void unRegisterReceiver() {
        if (mReadSmsCodeReceiver == null) return;

        _mActivity.unregisterReceiver(mReadSmsCodeReceiver);
        mReadSmsCodeReceiver = null;
    }

    /**
     * 注销内容观察者
     */
    private void unRegisterObserver() {
        if (mReadSmsObserver == null) return;

        _mActivity.getContentResolver().unregisterContentObserver(mReadSmsObserver);
        mReadSmsObserver = null;
    }

    /**
     * 解析短信得到验证码
     *
     * @param smsBody
     * @return
     */
    private String parseSmsBody(String smsBody) {
        ALog.e("parseSmsBody");


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
        ALog.e("checkSmsBody");
        ALog.e("smsBody-->" + smsBody);

        return smsBody.contains(SMS_CONTAINS);
    }
}
