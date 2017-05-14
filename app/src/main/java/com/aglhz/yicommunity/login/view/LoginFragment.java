package com.aglhz.yicommunity.login.view;

import android.app.Dialog;
import android.os.Bundle;
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

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventData;
import com.aglhz.yicommunity.login.contract.LoginContract;
import com.aglhz.yicommunity.login.presenter.LoginPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View, TextWatcher {
    private static final String TAG = LoginFragment.class.getSimpleName();
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.cb_remember_password)
    CheckBox cbRememberPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btRegister;
    Unbinder unbinder;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Params params = Params.getInstance();
    private ViewGroup rootView;
    private Dialog loadingDialog;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @NonNull
    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = (ViewGroup) _mActivity.findViewById(android.R.id.content).getRootView();
        initData();
    }

    private void initData() {
        etUsername.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        if (!TextUtils.isEmpty(UserHelper.account)) {
            etUsername.setText(UserHelper.account);
            etPassword.setText(UserHelper.password);
            btLogin.setEnabled(true);
        }

        initStateBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_36dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void start(Object response) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

        if (cbRememberPassword.isChecked()) {
            UserHelper.setAccount(etUsername.getText().toString().trim()
                    , etPassword.getText().toString().trim());
        }
        ALog.e("222222222222");
        EventBus.getDefault().post(new EventData(Constants.login));
        _mActivity.finish();
    }

    @Override
    public void error(String errorMessage) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        DialogHelper.warningSnackbar(rootView, errorMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_forget_password, R.id.cb_remember_password, R.id.bt_login, R.id.bt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_password:
                start(ForgetPasswordFragment.newInstance());
                break;
            case R.id.cb_remember_password:
                break;
            case R.id.bt_login:
                if (loadingDialog == null) {
                    loadingDialog = DialogHelper.loading(_mActivity);
                }
                loadingDialog.show();
                params.user = etUsername.getText().toString().trim();
                params.pwd = etPassword.getText().toString().trim();
                mPresenter.start(params);
                break;
            case R.id.bt_register:
                start(RegisterFragment.newInstance());
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean enabled = TextUtils.isEmpty(etUsername.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString());
        btLogin.setEnabled(!enabled);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}


