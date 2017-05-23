package com.aglhz.yicommunity.park.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.event.EventPark;
import com.aglhz.yicommunity.park.contract.MonthCardPayContract;
import com.aglhz.yicommunity.park.presenter.MonthCardPayPresenter;
import com.aglhz.yicommunity.picker.PickerActivity;
import com.bigkoo.pickerview.TimePickerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/19 9:28.
 */
public class MonthCardPayFragment extends BaseFragment<MonthCardPayPresenter> implements MonthCardPayContract.View {

    private static final String TAG = MonthCardPayFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_car_city)
    TextView tvCarCity;
    @BindView(R.id.et_input_car_num)
    EditText etInputCarNum;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.appbar_toolbar)
    AppBarLayout appbarToolbar;
    @BindView(R.id.tv_start_time_fragment_month_car_pay)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time_fragment_month_car_pay)
    TextView tvEndTime;
    @BindView(R.id.tv_need_pay_money)
    TextView tvNeedPayMoney;
    @BindView(R.id.tv_beforehand_pay_month_count)
    TextView tvBeforehandPayMonthCount;
    @BindView(R.id.rl_beforehand_pay_month_count)
    RelativeLayout rlBeforehandPayMonthCount;
    @BindView(R.id.et_name_fragment_month_card_pay)
    EditText etInputName;
    @BindView(R.id.et_input_phone_fragment_month_card_pay)
    EditText etInputPhone;
    @BindView(R.id.tv_park_address)
    TextView tvParkAddress;

    private Unbinder unbinder;
    private Params params = Params.getInstance();

    public static MonthCardPayFragment newInstance() {
        return new MonthCardPayFragment();
    }

    @NonNull
    @Override
    protected MonthCardPayPresenter createPresenter() {
        return new MonthCardPayPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month_card_pay, container, false);
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
        toolbarTitle.setText("月卡缴费");
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

    @OnClick({R.id.tv_car_city,
            R.id.rl_park_address,
            R.id.tv_start_time_fragment_month_car_pay,
            R.id.tv_end_time_fragment_month_car_pay,
            R.id.bt_submit_fragment_month_card_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_car_city:
                startForResult(CarCityFragment.newInstance(), 100);
                break;
            case R.id.tv_start_time_fragment_month_car_pay:
                setTime(tvStartTime);
                break;
            case R.id.tv_end_time_fragment_month_car_pay:
                setTime(tvEndTime);
                break;
            case R.id.bt_submit_fragment_month_card_pay:
                submit();
                break;
            case R.id.rl_park_address:
                Intent intent = new Intent(_mActivity, PickerActivity.class);
                intent.putExtra(Constants.FROM_TO, 100);
                _mActivity.startActivity(intent);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventPark event) {
        //获取停车场fid
        params.fid = event.bean.getFid();
        tvParkAddress.setText(event.bean.getName());
    }

    private void submit() {
        //车牌号
        params.carNo = tvCarCity + etInputCarNum.getText().toString().trim();
        if (TextUtils.isEmpty(params.carNo)) {
            DialogHelper.warningSnackbar(getView(), "请输入车牌号");
            return;
        }
        //名字
        params.name = etInputName.getText().toString().trim();
        if (TextUtils.isEmpty(params.name)) {
            DialogHelper.warningSnackbar(getView(), "请输入姓名");
            return;
        }
        //联系方式
        params.phoneNo = etInputPhone.getText().toString().trim();
        if (TextUtils.isEmpty(params.phoneNo)) {
            DialogHelper.warningSnackbar(getView(), "请输入联系方式");
            return;
        }
        //todo:预缴费月数名称
        params.monthName = "一个月";
        //todo:预缴费数值
        params.monthCount = 1;
        params.price = "100";
        mPresenter.postMothCarPay(params);
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            tvCarCity.setText(data.getString("shortfrom"));
        }
    }


    private void setTime(TextView tv) {
        TimePickerView pvTime = new TimePickerView.Builder(_mActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String outTime = getTime(date);
                tv.setText(outTime);
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void start(Object response) {
        DialogHelper.successSnackbar(getView(), "提交成功!");
        pop();
    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }
}
