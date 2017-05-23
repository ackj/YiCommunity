package com.aglhz.yicommunity.park.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.event.EventPark;
import com.aglhz.yicommunity.picker.PickerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/19 9:40.
 */
public class ProprietorGarageFragment extends BaseFragment {

    private final String TAG = ProprietorGarageFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_car_city)
    TextView tvCarCity;
    @BindView(R.id.tv_park_address)
    TextView tvParkAddress;

    private Unbinder unbinder;

    public static ProprietorGarageFragment newInstance() {
        return new ProprietorGarageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proprietor_garage, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        initToolbar();
    }


    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("业主车库");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @OnClick({R.id.tv_car_city, R.id.rl_park_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_car_city:
                startForResult(CarCityFragment.newInstance(), 100);
                break;
            case R.id.rl_park_address:
                Intent intent = new Intent(_mActivity, PickerActivity.class);
                intent.putExtra(Constants.FROM_TO, 100);
                _mActivity.startActivity(intent);
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
        tvParkAddress.setText(event.bean.getName());
    }

}
