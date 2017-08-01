package com.aglhz.yicommunity.main.park.view;

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
import com.aglhz.yicommunity.entity.bean.ParkOrderBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.abase.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.park.contract.ParkOrderContract;
import com.aglhz.yicommunity.main.park.presenter.ParkOrderPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/31 0031 17:00.
 * Email: liujia95me@126.com
 * [停车订单]的View层。
 * 打开方式：StartApp-->社区-->临时停车-->填好车牌号+选择停车场后生成
 */

public class ParkOrderFragment extends BaseFragment<ParkOrderContract.Presenter> implements ParkOrderContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_bill_code)
    TextView tvBillCode;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.tv_in_time)
    TextView tvInTime;
    @BindView(R.id.tv_out_time)
    TextView tvOutTime;
    @BindView(R.id.tv_total_cost_time)
    TextView tvTotalCostTime;
    @BindView(R.id.tv_park_place_name)
    TextView tvParkPlaceName;
    @BindView(R.id.tv_cost_money)
    TextView tvCostMoney;

    private Unbinder unbinder;
    private String parkFid;
    private String carNo;

    private Params params = Params.getInstance();

    /**
     * ParkOrderFragment创建的入口
     *
     * @param parkFid 停车场的fid
     * @param carNo   车牌号
     * @return
     */
    public static ParkOrderFragment newInstance(String parkFid, String carNo) {
        ParkOrderFragment fragment = new ParkOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_FID, parkFid);
        bundle.putString(Constants.KEY_CARNO, carNo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parkFid = getArguments().getString(Constants.KEY_FID);
        carNo = getArguments().getString(Constants.KEY_CARNO);
    }

    @NonNull
    @Override
    protected ParkOrderContract.Presenter createPresenter() {
        return new ParkOrderPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parking_order, container, false);
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
        toolbarTitle.setText("停车订单");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        params.parkPlaceFid = parkFid;
        params.carNo = carNo;
        mPresenter.requestParkOrder(params);//请求停车订单
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void start(Object response) {
    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
        pop();
    }

    /**
     * 响应请求停车订单
     * @param bean
     */
    @Override
    public void responseParkOrder(ParkOrderBean.DataBean bean) {
        tvBillCode.setText(bean.getBillCode());
        tvCreateTime.setText(bean.getCreateTime());
        tvCarNum.setText(bean.getCarNo());
        tvInTime.setText(bean.getInTime());
        tvOutTime.setText(bean.getOutTime());
        tvTotalCostTime.setText(bean.getTotalCostTime());
        tvParkPlaceName.setText(bean.getParkPlaceName());
        tvCostMoney.setText(bean.getCostMoney() + "元");
    }

    @OnClick(R.id.btn_confirm_pay)
    public void onViewClicked() {
    }
}
