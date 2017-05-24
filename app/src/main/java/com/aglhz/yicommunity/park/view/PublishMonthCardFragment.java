package com.aglhz.yicommunity.park.view;

import android.app.AlertDialog;
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
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.MonthCardRuleListBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.event.EventPark;
import com.aglhz.yicommunity.park.presenter.PublishMonthCardPresenter;
import com.aglhz.yicommunity.picker.PickerActivity;
import com.aglhz.yicommunity.publish.contract.PublishContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/19 9:28.
 */
public class PublishMonthCardFragment extends BaseFragment<PublishMonthCardPresenter> implements PublishContract.View {

    private static final String TAG = PublishMonthCardFragment.class.getSimpleName();

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
    @BindView(R.id.et_name_fragment_month_card_pay)
    EditText etInputName;
    @BindView(R.id.et_input_phone_fragment_month_card_pay)
    EditText etInputPhone;
    @BindView(R.id.tv_park_address)
    TextView tvParkAddress;

    private Unbinder unbinder;
    private Params params = Params.getInstance();

    public static PublishMonthCardFragment newInstance() {
        return new PublishMonthCardFragment();
    }

    @NonNull
    @Override
    protected PublishMonthCardPresenter createPresenter() {
        return new PublishMonthCardPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_month_card, container, false);
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

    @OnClick({R.id.tv_car_city, R.id.rl_park_address, R.id.bt_submit_fragment_month_card_pay, R.id.rl_month_card_rule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_car_city:
                startForResult(CarCityFragment.newInstance(), 100);
                break;
            case R.id.bt_submit_fragment_month_card_pay:
                submit();
                break;
            case R.id.rl_park_address:
                Intent intent = new Intent(_mActivity, PickerActivity.class);
                intent.putExtra(Constants.FROM_TO, 100);
                _mActivity.startActivity(intent);
                break;
            case R.id.rl_month_card_rule:
                mPresenter.requestMonthCardRule(params);
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
        String carNum = etInputCarNum.getText().toString().trim();
        params.carNo = tvCarCity.getText().toString() + carNum;
        if (TextUtils.isEmpty(carNum)) {
            DialogHelper.warningSnackbar(getView(), "请输入车牌号");
            return;
        }
        //停车地址
        if(TextUtils.isEmpty(tvParkAddress.getText().toString())){
            DialogHelper.warningSnackbar(getView(), "请选择停车地址");
            return;
        }
        //预交费月数
        if(TextUtils.isEmpty(tvBeforehandPayMonthCount.getText().toString())){
            DialogHelper.warningSnackbar(getView(), "请选择预交费月数");
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
        mPresenter.post(params);
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            tvCarCity.setText(data.getString("shortfrom"));
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
    public void responseSuccess(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), bean.getOther().getMessage());
        pop();
    }

    public void responseRuleList(List<MonthCardRuleListBean.DataBean.MonthCardRuleBean> datas) {
        String[] arr = new String[datas.size()];
        for (int i = 0; i < datas.size(); i++) {
            arr[i] = datas.get(i).getName();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(_mActivity);
        builder.setItems(arr, (dialog, which) -> {
            MonthCardRuleListBean.DataBean.MonthCardRuleBean clickBean = datas.get(which);
            selectRule(clickBean);
        });
        builder.show();
    }

    private void selectRule(MonthCardRuleListBean.DataBean.MonthCardRuleBean clickBean) {
        tvStartTime.setText(clickBean.getStartDate());
        tvEndTime.setText(clickBean.getEndDate());
        tvNeedPayMoney.setText(clickBean.getMoney() + "元");
        tvBeforehandPayMonthCount.setText(clickBean.getName());

        params.monthName = clickBean.getName();
        params.monthCount = clickBean.getMonthCount();
        params.price = String.valueOf(clickBean.getMoney());
    }
}
