package com.aglhz.yicommunity.main.about;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.AppUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.PermissionHelper;
import com.aglhz.yicommunity.web.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: LiuJia on 2017/4/21 17:41.
 * Email: liujia95me@126.com
 */
public class AboutUsFragment extends BaseFragment {
    private static final String TAG = AboutUsFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;
    @BindView(R.id.ll_product_introduction)
    LinearLayout llProductIntroduction;
    @BindView(R.id.ll_service_terms)
    LinearLayout llServiceTerms;
    @BindView(R.id.ll_feedback)
    LinearLayout llFeedback;

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("关于我们");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        tvVersionName.setText("版本：" + AppUtils.getVersionName(BaseApplication.mContext));
    }

    @OnClick({R.id.ll_product_introduction, R.id.ll_service_terms, R.id.ll_feedback, R.id.ll_permission})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_product_introduction:
                Intent introductionIntent = new Intent(_mActivity, WebActivity.class);
                introductionIntent.putExtra(Constants.KEY_TITLE, "产品介绍");
                introductionIntent.putExtra(Constants.KEY_LINK, ApiService.PRODUCT_INTRODUCTION);
                startActivity(introductionIntent);
                break;
            case R.id.ll_service_terms:
                Intent termsIntent = new Intent(_mActivity, WebActivity.class);
                termsIntent.putExtra(Constants.KEY_TITLE, "服务条款");
                termsIntent.putExtra(Constants.KEY_LINK, ApiService.SERVICE_TERMS);
                startActivity(termsIntent);
                break;
            case R.id.ll_feedback:
                start(FeedbackFragment.newInstance());
                break;
            case R.id.ll_permission:
                new PermissionHelper(_mActivity).gotoPermission();
                break;
        }
    }
}
