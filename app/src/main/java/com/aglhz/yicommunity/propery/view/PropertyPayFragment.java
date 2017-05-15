package com.aglhz.yicommunity.propery.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunityChange;
import com.aglhz.yicommunity.picker.PickerActivity;
import com.aglhz.yicommunity.propery.contract.PropertyPayContract;
import com.aglhz.yicommunity.propery.presenter.PropertyPayPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.aglhz.yicommunity.R.id.ptrFrameLayout;

/**
 * Author: LiuJia on 2017/5/7 0007 20:16.
 * Email: liujia95me@126.com
 */

public class PropertyPayFragment extends BaseFragment<PropertyPayContract.Presenter> implements PropertyPayContract.View {
    public static final String TAG = PropertyPayFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout_property_pay_fragment)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_property_pay_fragment)
    ViewPager viewpager;
    @BindView(R.id.tv_community_property_pay_fragment)
    TextView tvCommunity;

    private Unbinder unbinder;
    private PropertyPayVPAdapter adapter;


    public static PropertyPayFragment newInstance() {
        return new PropertyPayFragment();
    }

    @NonNull
    @Override
    protected PropertyPayContract.Presenter createPresenter() {
        return new PropertyPayPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
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
        toolbarTitle.setText("物业账单");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        mPresenter.requestPropertyPay();
        adapter = new PropertyPayVPAdapter();
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tvCommunity.setText(UserHelper.city + "　" + UserHelper.communityName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responsePropertyPay(PropertyPayBean bean) {
        adapter.setPropertyPayBean(bean);
    }

    @OnClick(R.id.tv_community_property_pay_fragment)
    public void onViewClicked() {
        _mActivity.startActivity(new Intent(_mActivity, PickerActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunityChange event) {
        ALog.e(TAG, "onEvent:::" + event.bean.getName());
        tvCommunity.setText(UserHelper.city + "　" + UserHelper.communityName);
    }
}
