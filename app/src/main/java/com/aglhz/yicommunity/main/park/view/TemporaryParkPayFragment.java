package com.aglhz.yicommunity.main.park.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.RegexUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.event.EventPark;
import com.aglhz.yicommunity.main.picker.PickerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/31 0031 16:30.
 * Email: liujia95me@126.com
 */

public class TemporaryParkPayFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_car_city)
    TextView tvCarCity;
    @BindView(R.id.et_input_car_num)
    EditText etInputCarNum;

    private Unbinder unbinder;

    public static TemporaryParkPayFragment newInstance() {
        return new TemporaryParkPayFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parking_temporary_pay, container, false);
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
        toolbarTitle.setText("临停缴费");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.ll_select_park, R.id.ll_scan_pay, R.id.tv_car_city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_select_park:
                Intent intent = new Intent(_mActivity, PickerActivity.class);
                intent.putExtra(Constants.KEY_FROM_TO, 100);
                _mActivity.startActivity(intent);
                break;
            case R.id.ll_scan_pay:
                break;
            case R.id.tv_car_city:
                startForResult(CarCityFragment.newInstance(), 100);
                break;
        }
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            tvCarCity.setText(data.getString("shortfrom"));
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventPark event) {
        String carNo = (tvCarCity.getText().toString() + etInputCarNum.getText().toString().trim()).toUpperCase();
        if (!RegexUtils.isCarNo(carNo)) {
            DialogHelper.warningSnackbar(getView(), "请输入正确的车牌号");
            return;
        }
        start(ParkOrderFragment.newInstance(event.bean.getFid(), carNo));
    }
}
