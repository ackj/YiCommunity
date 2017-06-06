package com.aglhz.yicommunity.main.about;

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
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.main.about.contract.FeedbackContract;
import com.aglhz.yicommunity.main.about.presenter.FeedbackPresenter;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Author: LiuJia on 2017/4/21 18:06.
 * Email: liujia95me@126.com
 */
public class FeedbackFragment extends BaseFragment<FeedbackContract.Presenter> implements FeedbackContract.View, TextWatcher {
    private static final String TAG = FeedbackFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bt_submit_feedback_fragment)
    Button btSubmit;
    private Unbinder unbind;
    private Params params = Params.getInstance();
    private Dialog loadingDialog;

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @NonNull
    @Override
    protected FeedbackContract.Presenter createPresenter() {
        return new FeedbackPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        unbind = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("意见反馈");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void start(Object response) {
        dismissLoadingDialog();
        if (response instanceof String) {
            DialogHelper.successSnackbar(getView(), (String) response);
        }
    }

    @Override
    public void error(String errorMessage) {
        dismissLoadingDialog();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @OnClick(R.id.bt_submit_feedback_fragment)
    public void onViewClicked() {
        params.des = btSubmit.getText().toString().trim();
        showLoadingDialog();
        mPresenter.start(params);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        btSubmit.setEnabled(!TextUtils.isEmpty(btSubmit.getText().toString().trim()));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
