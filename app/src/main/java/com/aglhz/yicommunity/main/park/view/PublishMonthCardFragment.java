package com.aglhz.yicommunity.main.park.view;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.abase.utils.RegexUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.CarCardBean;
import com.aglhz.yicommunity.bean.CardRechargeBean;
import com.aglhz.yicommunity.bean.MonthCardRuleBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.event.EventPark;
import com.aglhz.yicommunity.main.guide.GuideHelper;
import com.aglhz.yicommunity.main.park.contract.PublishMonthCardContract;
import com.aglhz.yicommunity.main.park.presenter.PublishMonthCardPresenter;
import com.aglhz.yicommunity.main.picker.PickerActivity;

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
public class PublishMonthCardFragment extends BaseFragment<PublishMonthCardContract.Presenter> implements PublishMonthCardContract.View {

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
    @BindView(R.id.tv_hint_message)
    TextView tvHintMessage;
    @BindView(R.id.rl_park_address)
    RelativeLayout rlParkAddress;
    @BindView(R.id.bt_submit_fragment_month_card_pay)
    Button btSubmit;
    @BindView(R.id.rl_month_card_rule)
    RelativeLayout rlMonthCardRule;

    private Unbinder unbinder;
    private Params params = Params.getInstance();

    public static final int TYPE_FIRST_PAY = 1;
    public static final int TYPE_RECHARGE = 2;

    private int type;
    private String fid;
    private CardRechargeBean.DataBean cardRechargeBean;

    public static PublishMonthCardFragment newInstance(int type, String fid) {
        PublishMonthCardFragment fragment = new PublishMonthCardFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_FID, fid);
        bundle.putInt(Constants.KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        type = bundle.getInt(Constants.KEY_TYPE);
        fid = bundle.getString(Constants.KEY_FID);
    }

    @NonNull
    @Override
    protected PublishMonthCardContract.Presenter createPresenter() {
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
        if (type == TYPE_FIRST_PAY) {
            params.fid = fid;
            mPresenter.requestCardPay(params);
            tvHintMessage.setVisibility(View.VISIBLE);
            btSubmit.setText("立即缴费");
        } else if (type == TYPE_RECHARGE) {
            params.fid = fid;
            mPresenter.requestCardRecharge(params);
            btSubmit.setText("立即续费");
            GuideHelper.showCardPaydGuide(_mActivity);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        KeyBoardUtils.hideKeybord(getView(), _mActivity);
    }

    @OnClick({R.id.tv_car_city, R.id.rl_park_address, R.id.bt_submit_fragment_month_card_pay, R.id.rl_month_card_rule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_car_city:
                startForResult(CarCityFragment.newInstance(), 100);
                break;
            case R.id.bt_submit_fragment_month_card_pay:
                switch (type) {
                    case TYPE_FIRST_PAY:
                    case TYPE_RECHARGE:
                        pay();
                        break;
                    default:
                        submit();
                        break;
                }
                break;
            case R.id.rl_park_address:
                Intent intent = new Intent(_mActivity, PickerActivity.class);
                intent.putExtra(Constants.KEY_FROM_TO, 100);
                _mActivity.startActivity(intent);
                break;
            case R.id.rl_month_card_rule:
                if (type == TYPE_RECHARGE) {
                    if (cardRechargeBean != null) {
                        showRuleDialog(cardRechargeBean.getMonthCardRuleList());
                    }
                } else {
                    mPresenter.requestMonthCardRule(params);
                }
                break;
        }
    }

    private void pay() {
        //todo:跳支付宝 or 微信


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
        params.carNo = (tvCarCity.getText().toString() + carNum).toUpperCase();
        if (!RegexUtils.isCarNo(params.carNo)) {
            DialogHelper.warningSnackbar(getView(), "请输入正确的车牌号");
            return;
        }
        //停车地址
        if (TextUtils.isEmpty(tvParkAddress.getText().toString())) {
            DialogHelper.warningSnackbar(getView(), "请选择停车地址");
            return;
        }
        //预交费月数
        if (TextUtils.isEmpty(tvBeforehandPayMonthCount.getText().toString())) {
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
        mPresenter.requestSubmitMonthCard(params);
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            tvCarCity.setText(data.getString(Constants.KEY_SHORTFROM));
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
    public void responseSubmitSuccess(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), bean.getOther().getMessage());
        pop();
    }

    @Override
    public void responseRuleList(List<MonthCardRuleBean> datas) {
        showRuleDialog(datas);
    }

    private void showRuleDialog(List<MonthCardRuleBean> datas) {
        String[] ruleArr = new String[datas.size()];
        for (int i = 0; i < datas.size(); i++) {
            ruleArr[i] = datas.get(i).getName();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(_mActivity);
        builder.setItems(ruleArr, (dialog, which) -> {
            MonthCardRuleBean clickBean = datas.get(which);
            selectRule(clickBean);
        });
        builder.show();
    }

    @Override
    public void responseCardPay(CarCardBean.DataBean bean) {
        String carNo = bean.getCarNo();
        tvCarCity.setText(carNo.substring(0, 1));
        etInputCarNum.setText(carNo.substring(1));
        tvParkAddress.setText(bean.getParkPlaceName());
        tvBeforehandPayMonthCount.setText(bean.getMonthName());
        tvStartTime.setText(bean.getStartTime());
        tvEndTime.setText(bean.getEndTime());
        etInputName.setText(bean.getCustomerName());
        etInputPhone.setText(bean.getPhoneNo());
        tvNeedPayMoney.setText(bean.getCostMoney() + "元");

        tvCarCity.setEnabled(false);
        etInputCarNum.setEnabled(false);
        rlParkAddress.setEnabled(false);
        rlMonthCardRule.setEnabled(false);
        etInputName.setEnabled(false);
        etInputPhone.setEnabled(false);
    }

    public void responseCardRecharge(CardRechargeBean.DataBean bean) {
        cardRechargeBean = bean;
        String carNo = bean.getCarNo();
        tvCarCity.setText(carNo.substring(0, 1));
        etInputCarNum.setText(carNo.substring(1));
        tvParkAddress.setText(bean.getParkPlaceName());
        tvBeforehandPayMonthCount.setText(bean.getMonthName());
        tvStartTime.setText(bean.getStartTime());
        tvEndTime.setText(bean.getEndTime());
        etInputName.setText(bean.getCustomerName());
        etInputPhone.setText(bean.getPhoneNo());

        tvCarCity.setEnabled(false);
        etInputCarNum.setEnabled(false);
        rlParkAddress.setEnabled(false);
        etInputName.setEnabled(false);
        etInputPhone.setEnabled(false);
    }

    private void selectRule(MonthCardRuleBean clickBean) {
        tvStartTime.setText(clickBean.getStartDate());
        tvEndTime.setText(clickBean.getEndDate());
        tvNeedPayMoney.setText(clickBean.getMoney() + "元");
        tvBeforehandPayMonthCount.setText(clickBean.getName());

        params.monthName = clickBean.getName();
        params.monthCount = clickBean.getMonthCount();
        params.price = String.valueOf(clickBean.getMoney());
    }
}
