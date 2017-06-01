package com.aglhz.yicommunity.main.message.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.MessageCenterBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.message.contract.ApplyCheckContract;
import com.aglhz.yicommunity.main.message.presenter.ApplyCheckPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/19 0019 15:07.
 * Email: liujia95me@126.com
 */

public class ApplyCheckFragment extends BaseFragment<ApplyCheckContract.Presenter> implements ApplyCheckContract.View {

    private static final String TAG = ApplyCheckFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_desc_apply_check_fragment)
    TextView tvDesc;

    private Unbinder unbinder;
    private Params params = Params.getInstance();
    private MessageCenterBean.DataBean.MemNewsBean bean;

    public static ApplyCheckFragment newInstance(MessageCenterBean.DataBean.MemNewsBean bean) {
        ApplyCheckFragment checkFragment = new ApplyCheckFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        checkFragment.setArguments(bundle);
        return checkFragment;
    }

    @NonNull
    @Override
    protected ApplyCheckContract.Presenter createPresenter() {
        return new ApplyCheckPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().getSerializable("bean") != null) {
            bean = (MessageCenterBean.DataBean.MemNewsBean) getArguments().getSerializable("bean");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_check, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText(bean.getTitle());
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        params.fid = bean.getFid();
        params.authFid = bean.getSfid();
        tvDesc.setText(bean.getDes());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_pass_check_fragment, R.id.bt_refuse_check_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_pass_check_fragment:
                params.status = 1;
                mPresenter.requestApplyCheck(params);
                break;
            case R.id.bt_refuse_check_fragment:
                params.status = 2;
                mPresenter.requestApplyCheck(params);
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

    @Override
    public void responseApplySuccess(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), "申请已通过");
        pop();
    }
}
