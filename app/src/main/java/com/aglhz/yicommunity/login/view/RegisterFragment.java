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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.login.contract.RegisterContract;
import com.aglhz.yicommunity.login.presenter.RegisterPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/9 0009 19:26.
 * Email: liujia95me@126.com
 */

public class RegisterFragment extends BaseFragment<RegisterPresenter> implements RegisterContract.View {
    public static final String TAG = RegisterFragment.class.getSimpleName();
    @BindView(R.id.et_username)
    EditText etPhoneNo;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.tv_get_verify)
    TextView tvGetVerify;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;
    @BindView(R.id.btn_submit)
    Button btSubmit;
    @BindView(R.id.et_again_password)
    EditText etAgainPassword;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Thread getVerifyThread;
    private Unbinder unbinder;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @NonNull
    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        register();//注册短信广播和短信内容观察者。
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

    private void initToolbar() {
        initStateBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_36dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        etPhoneNo.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
        etVerifyCode.addTextChangedListener(textWatcher);
        etAgainPassword.addTextChangedListener(textWatcher);
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

    @OnClick(R.id.btn_submit)
    public void btRegister(View view) {
        String phoneNo = etPhoneNo.getText().toString();
        String password = etPassword.getText().toString();
        String againPassword = etAgainPassword.getText().toString();
        String verCode = etVerifyCode.getText().toString();
        Params params = Params.getInstance();
        params.account = phoneNo;
        params.verifyCode = verCode;
        params.password1 = password;
        params.password2 = againPassword;
        mPresenter.requestRegister(params);
    }

    @OnClick(R.id.tv_get_verify)
    public void getVerify(View view) {
        String phoneNo = etPhoneNo.getText().toString();
        Params params = Params.getInstance();
        params.phoneNo = phoneNo;
        params.verifyType = "v_regPhone";
        mPresenter.requestVerifyCode(params);

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
        getVerifyThread.start();
    }

    //检查是否输入完全
    private void checkIsInputComplete() {
        boolean agree = !cbAgree.isChecked();
        boolean userName = TextUtils.isEmpty(etPhoneNo.getText().toString());
        boolean password = TextUtils.isEmpty(etPassword.getText().toString());
        boolean againPassword = TextUtils.isEmpty(etAgainPassword.getText().toString());
        boolean verCode = TextUtils.isEmpty(etVerifyCode.getText().toString());

        boolean all = agree || userName || password || againPassword || verCode;

        btSubmit.setEnabled(!all);
        if (tvGetVerify.getText().toString().equals("获取验证码")) {
            tvGetVerify.setEnabled(!userName);
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void registerSuccess(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), "注册成功！");
        pop();
    }

    @Override
    public void getVerfyCodeSuccess(BaseBean baseBean) {
        DialogHelper.successSnackbar(getView(), "获取验证码成功！");
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
    public void onDestroy() {
        super.onDestroy();
        unRegister();
    }

    //*******************以下为短信广播和读取未读短信以获取验证码的广播和内容观察者*****************
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
            if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
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
    void getSmsCodeFromObserver(Cursor cursor) {
        if (cursor == null) return;

        while (cursor.moveToNext()) {
//            String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
            String smsBody = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
            if (checkSmsBody(smsBody)) {
                String smsCode = parseSmsBody(smsBody);
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
        registerReceiver();
        registerObserver();
    }

    /**
     * 注销广播接收者，内容观察者
     */
    private void unRegister() {
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
        return smsBody.contains(SMS_CONTAINS);
    }
}
