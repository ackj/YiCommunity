package com.aglhz.yicommunity.main.door.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.PasswordBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.common.share.WxShare;
import com.aglhz.yicommunity.main.door.contract.PasswordOpenDoorContract;
import com.aglhz.yicommunity.main.door.presenter.PasswordOpenDoorPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: LiuJia on 2017/4/21 9:54.
 * Email: liujia95me@126.com
 * [密码开门]的View层
 * 打开方式：Start App-->管家-->智慧门禁[密码开门]
 */
public class PasswordOpenDoorFragment extends BaseFragment<PasswordOpenDoorContract.Presenter> implements PasswordOpenDoorContract.View {
    private static final String TAG = PasswordOpenDoorFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.bt_share)
    Button btShare;
    private Params params = Params.getInstance();


    public static PasswordOpenDoorFragment newInstance() {
        return new PasswordOpenDoorFragment();
    }

    @NonNull
    @Override
    protected PasswordOpenDoorContract.Presenter createPresenter() {
        return new PasswordOpenDoorPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_opendoor, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("密码开门");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    /**
     * 响应请求密码
     *
     * @param mPasswordBean
     */
    @Override
    public void responsePassword(PasswordBean mPasswordBean) {
        dismissLoadingDialog();
        tvPassword.setTextColor(Color.parseColor("#000000"));
        tvPassword.setText(mPasswordBean.getData().getSecretCode());
    }

    @OnClick({R.id.bt_password, R.id.bt_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_password:
                params.dir = UserHelper.dir;
                mPresenter.requestPassword(params);
                showLoadingDialog();
                break;
            case R.id.bt_share:
                sharePassword();
                break;
        }
    }

    /**
     * 密码分享
     */
    private void sharePassword() {
        new AlertDialog.Builder(_mActivity)
                .setTitle("选择分享类型")
                .setItems(new String[]{"微信", "短信"}, (dialog, which) -> {
                    switch (which) {
                        case 0: //微信
                            WxShare.sendText(tvPassword.getText().toString().trim());
                            break;
                        case 1: //短信
                            sendSMS();
                            break;
                    }
                }).show();
    }

    /**
     * 短信分享
     */
    private void sendSMS() {
        Uri smsToUri = Uri.parse("smsto:");
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, smsToUri);
        //sendIntent.putExtra("address", "123456"); // 电话号码，这行去掉的话，默认就没有电话
        //短信内容
        sendIntent.putExtra("sms_body", tvPassword.getText().toString().trim());
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivityForResult(sendIntent, 1002);
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        dismissLoadingDialog();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }
}
