package com.aglhz.yicommunity.main.door.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.common.DialogHelper;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.abase.utils.RegexUtils;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.main.door.contract.FamilyPhoneContract;
import com.aglhz.yicommunity.main.door.presenter.FamilyPhonePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/9/15 0015 10:51.
 * Email: liujia95me@126.com
 */

public class FamilyPhoneFragment extends BaseFragment<FamilyPhoneContract.Presenter> implements FamilyPhoneContract.View {
    public static final String TAG = QuickOpenDoorFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.et_phone)
    EditText etPhone;
    private Unbinder unbinder;
    private Params params = Params.getInstance();

    public static FamilyPhoneFragment newInstance(String roomDir, String phone) {
        FamilyPhoneFragment fragment = new FamilyPhoneFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomDir", roomDir);
        bundle.putString("phone", phone);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected FamilyPhoneContract.Presenter createPresenter() {
        return new FamilyPhonePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family_phone, container, false);
        unbinder = ButterKnife.bind(this, view);
        params.roomDir = getArguments().getString("roomDir");
        params.phoneNo = getArguments().getString("phone");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initStateManager();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("设置亲情号码");
        toolbarMenu.setText("保存");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        if (!TextUtils.isEmpty(params.phoneNo)) {
            etPhone.setText(params.phoneNo);
        }
    }

    private void initStateManager() {
    }

    private void initListener() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDestroyView() {
        KeyBoardUtils.hideKeybord(etPhone, App.mContext);
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_menu)
    public void onViewClicked() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            DialogHelper.warningSnackbar(getView(), "请输入亲情号码");
            return;
        }
        if (RegexUtils.isMobileExact(phone) || RegexUtils.isTel(phone)) {
            params.phoneNo = phone;
            mPresenter.requestSetFamilyPhone(params);
        } else {
            DialogHelper.warningSnackbar(getView(), "请输入正确的手机号码或固话号码");
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
    public void responseSetFamilyPhone(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), "设置亲情号码成功");
        pop();
    }
}
