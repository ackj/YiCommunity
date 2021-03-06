package com.aglhz.yicommunity.main.about;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.abase.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.about.contract.FeedbackContract;
import com.aglhz.yicommunity.main.about.presenter.FeedbackPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/4/21 18:06.
 * Email: liujia95me@126.com
 * <p>
 * [意见反馈] 的View层。
 * 打开方式：Start App-->我的-->关于我们-->意见反馈。
 */
public class FeedbackFragment extends BaseFragment<FeedbackContract.Presenter> implements FeedbackContract.View, TextWatcher {
    private static final String TAG = FeedbackFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bt_submit_feedback_fragment)
    Button btSubmit;
    @BindView(R.id.et_describe_feedback_fragment)
    EditText etDescribe;
    private Unbinder unbind;
    private Params params = Params.getInstance();

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
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    public void start(Object response) {
        dismissLoading();
        if (response instanceof String) {
            DialogHelper.successSnackbar(getView(), (String) response);
        }
    }

    @Override
    public void error(String errorMessage) {
        dismissLoading();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @OnClick(R.id.bt_submit_feedback_fragment)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etDescribe.getText().toString())) {
            DialogHelper.warningSnackbar(getView(), "描述不能为空");
            return;
        }
        params.des = etDescribe.getText().toString().trim();
        mPresenter.start(params);//请求提交反馈
        showLoading();
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

    @Override
    public boolean onBackPressedSupport() {
        if (!TextUtils.isEmpty(etDescribe.getText().toString())) {
            new AlertDialog.Builder(_mActivity)
                    .setTitle("提示")
                    .setMessage("如果退出，当前填写信息将会丢失，是否退出？")
                    .setPositiveButton("退出", (dialog, which) -> pop())
                    .show();
            return true;
        } else {
            pop();
            return true;
        }
    }
}
