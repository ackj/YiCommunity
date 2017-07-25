package com.aglhz.yicommunity.login.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
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
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.SmsHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.login.contract.RegisterContract;
import com.aglhz.yicommunity.login.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

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
    private SmsHelper smsHelper;
    private Params params = Params.getInstance();

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
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
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

        smsHelper = new SmsHelper(_mActivity);
        smsHelper.setOnSmsParsedListener(code -> {
            etVerifyCode.setText(code);
        });
        smsHelper.register();
    }

    @OnClick(R.id.btn_submit)
    public void btRegister(View view) {
        String phoneNo = etPhoneNo.getText().toString();
        String password = etPassword.getText().toString();
        String againPassword = etAgainPassword.getText().toString();
        String verCode = etVerifyCode.getText().toString();

        if (!password.equals(againPassword)) {
            ToastUtils.showToast(_mActivity, "两次输入密码不一致");
            return;
        }
        if (!(password.length() >= 6 && password.length() <= 12)) {
            ToastUtils.showToast(_mActivity, "请设置6-12位密码");
            return;
        }
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
        Bundle bundle = new Bundle();
        bundle.putString(UserHelper.ACCOUNT, params.account);
        bundle.putString(UserHelper.PASSWORD, params.password1);
        setFragmentResult(SupportFragment.RESULT_OK, bundle);
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
        if (smsHelper != null) {
            smsHelper.clear();
            smsHelper = null;
        }
    }
}
