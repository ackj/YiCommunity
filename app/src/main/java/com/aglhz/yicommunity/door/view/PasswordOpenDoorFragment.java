package com.aglhz.yicommunity.door.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.aglhz.yicommunity.door.contract.PasswordOpenDoorContract;
import com.aglhz.yicommunity.door.presenter.PasswordOpenDoorPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: LiuJia on 2017/4/21 9:54.
 * Email: liujia95me@126.com
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    @Override
    public void responsePassword(PasswordBean mPasswordBean) {
        tvPassword.setTextColor(Color.parseColor("#000000"));
        tvPassword.setText(mPasswordBean.getData().getSecretCode());
    }

    @OnClick({R.id.bt_password, R.id.bt_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_password:
                Params params = Params.getInstance();
                mPresenter.requestPassword(params);
                break;
            case R.id.bt_share:
                break;
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }
}
