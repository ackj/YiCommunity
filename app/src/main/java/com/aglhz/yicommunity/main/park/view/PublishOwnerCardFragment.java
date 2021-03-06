package com.aglhz.yicommunity.main.park.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.abase.utils.RegexUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.CarCardListBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.abase.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.event.EventPark;
import com.aglhz.yicommunity.main.park.contract.PublishOwnerCardContract;
import com.aglhz.yicommunity.main.park.presenter.PublishOwnerCardPresenter;
import com.aglhz.yicommunity.main.picker.PickerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/19 9:40.
 * [业主车库]的View层
 * 打开方式：StartApp-->管家-->办理车卡-->业主车卡
 */
public class PublishOwnerCardFragment extends BaseFragment<PublishOwnerCardContract.Presenter> implements PublishOwnerCardContract.View {

    private final String TAG = PublishOwnerCardFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_car_city)
    TextView tvCarCity;
    @BindView(R.id.tv_park_address)
    TextView tvParkAddress;
    @BindView(R.id.et_input_car_num)
    EditText etInputCarNum;
    @BindView(R.id.et_input_name)
    EditText etInputName;
    @BindView(R.id.et_input_phone)
    EditText etInputPhone;
    @BindView(R.id.bt_submit_fragment_owner_card)
    Button btSubmit;
    @BindView(R.id.rl_park_address)
    RelativeLayout rlParkAddress;

    private Unbinder unbinder;
    Params params = Params.getInstance();
    private CarCardListBean.DataBean.CardListBean bean;
    private boolean isUpdate;

    /**
     * PublishOwnerCardFragment的创建入口
     * @param bean [我的车卡]中点击业主车卡跳转的bean，用于显示数据
     * @return
     */
    public static PublishOwnerCardFragment newInstance(CarCardListBean.DataBean.CardListBean bean) {
        PublishOwnerCardFragment fragment = new PublishOwnerCardFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_BEAN, bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected PublishOwnerCardContract.Presenter createPresenter() {
        return new PublishOwnerCardPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bean = (CarCardListBean.DataBean.CardListBean) getArguments().getSerializable(Constants.KEY_BEAN);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_owner_card, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        initToolbar();
        initData();
    }

    private void initData() {
        //如果为空则视为业主车卡的办理，否则视为修改车卡信息
        if (bean != null) {
            tvCarCity.setText(bean.getCarNo().substring(0, 1));
            etInputCarNum.setText(bean.getCarNo().substring(1));
            tvParkAddress.setText(bean.getParkPlace().getName());
            etInputName.setText(bean.getCustomerName());
            etInputPhone.setText(bean.getPhoneNo());

            rlParkAddress.setEnabled(false);

            params.parkCardFid = bean.getFid();
            params.parkPlaceFid = bean.getParkPlace().getFid();

            isUpdate = true;
            btSubmit.setText("修改车卡");
        }
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
        KeyBoardUtils.hideKeybord(getView(), _mActivity);
        unbinder.unbind();
    }

    @OnClick({R.id.tv_car_city, R.id.rl_park_address, R.id.bt_submit_fragment_owner_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_car_city:
                startForResult(CarCityFragment.newInstance(), 100);
                break;
            case R.id.rl_park_address:
                Intent intent = new Intent(_mActivity, PickerActivity.class);
                intent.putExtra(Constants.KEY_FROM_TO, 100);
                _mActivity.startActivity(intent);
                break;
            case R.id.bt_submit_fragment_owner_card:
                submit();
                break;
        }
    }

    private void submit() {
        String carNum = etInputCarNum.getText().toString();
        params.carNo = (tvCarCity.getText().toString() + carNum).toUpperCase();
        if (!RegexUtils.isCarNo(params.carNo)) {
            DialogHelper.warningSnackbar(getView(), "请输入正确的车牌号");
            return;
        }
        if (TextUtils.isEmpty(params.parkPlaceFid)) {
            DialogHelper.warningSnackbar(getView(), "请选择停车地址");
            return;
        }
        params.name = etInputName.getText().toString();
        if (TextUtils.isEmpty(params.name)) {
            DialogHelper.warningSnackbar(getView(), "请输入姓名");
            return;
        }
        params.phoneNo = etInputPhone.getText().toString();
        if (TextUtils.isEmpty(params.phoneNo)) {
            DialogHelper.warningSnackbar(getView(), "请输入联系方式");
            return;
        }
        if (isUpdate) {
            mPresenter.requestModifyOwnerCard(params);//请求修改业主车卡信息
        } else {
            mPresenter.requestSubmitOwnerCard(params);//请求提交业主车卡办理
        }
    }

    /**
     * 车牌归属地的数据返回结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            tvCarCity.setText(data.getString("shortfrom"));
        }
    }

    /**
     * 选择停车场的后的事件响应
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventPark event) {
        tvParkAddress.setText(event.bean.getName());
        params.parkPlaceFid = event.bean.getFid();
        ALog.e(TAG, "parkPlaceFid:::" + params.fid + " token:::" + params.token);
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    /**
     * 响应请求提交成功的回调
     * @param bean
     */
    @Override
    public void responseSuccess(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), bean.getOther().getMessage());
        if (isUpdate) {
            setFragmentResult(RESULT_OK, null);
        }
        pop();
    }
}
