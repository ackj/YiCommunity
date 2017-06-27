package com.aglhz.yicommunity.main.services.view;

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
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.services.contract.ServicesContract;
import com.aglhz.yicommunity.main.services.presenter.ServicesPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: leguang on 2017/4/21 9:14.
 * Email: langmanleguang@qq.com
 * <p>
 * [上门服务列表]的View层。
 * 打开方式：AppStart-->首页-->社区服务列表-->Item。
 */
public class ServicesDetailFragment extends BaseFragment<ServicesContract.Presenter> implements ServicesContract.View {
    private static final String TAG = ServicesDetailFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.tv_cost_detail_services_fragment)
    TextView tvCost;
    private Params params = Params.getInstance();

    public static ServicesDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(Constants.SERVICE_ID, id);
        ServicesDetailFragment fragment = new ServicesDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
//            id = args.getInt(Constants.SERVICE_TYPE);
        }
    }

    @NonNull
    @Override
    protected ServicesContract.Presenter createPresenter() {
        return new ServicesPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_services, container, false);
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
        toolbarTitle.setText("商品详情");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> pop());
    }

    private void initData() {
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
