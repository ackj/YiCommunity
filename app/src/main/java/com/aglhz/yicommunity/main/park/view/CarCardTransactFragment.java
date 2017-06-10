package com.aglhz.yicommunity.main.park.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/18 17:38.
 * [车卡办理]的View层。
 * 打开方式：StartApp-->管家-->办理车卡
 */
public class CarCardTransactFragment extends BaseFragment {
    private static final String TAG = CarCardTransactFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;

    public static CarCardTransactFragment newInstance() {
        return new CarCardTransactFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_card_transact, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("车卡办理");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_month_car_pay, R.id.ll_owner_garage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_month_car_pay://月卡缴费
                start(PublishMonthCardFragment.newInstance(0, null));
                break;
            case R.id.ll_owner_garage://业主车库
                start(PublishOwnerCardFragment.newInstance(null));
                break;
        }
    }
}
