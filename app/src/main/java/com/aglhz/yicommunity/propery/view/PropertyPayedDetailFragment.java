package com.aglhz.yicommunity.propery.view;

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
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.bean.PropertyPayDetailBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.propery.contract.PropertyPayContract;
import com.aglhz.yicommunity.propery.presenter.PropertyPayPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.R.attr.order;

/**
 * Created by leguang on 2017/4/29 0029.
 * Email：langmanleguang@qq.com
 */

public class PropertyPayedDetailFragment extends BaseFragment<PropertyPayContract.Presenter> implements PropertyPayContract.View {
    public static final String TAG = PropertyPayedDetailFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_address_property_payed_detail_fragment)
    TextView tvAddress;
    @BindView(R.id.tv_sum_property_payed_detail_fragment)
    TextView tvSum;
    @BindView(R.id.tv_payment_method_property_payed_detail_fragment)
    TextView tvPaymentMethod;
    @BindView(R.id.tv_payment_instructions_property_payed_detail_fragment)
    TextView tvPaymentInstructions;
    @BindView(R.id.tv_payment_number_property_payed_detail_fragment)
    TextView tvPaymentNumber;
    @BindView(R.id.tv_creat_time_property_payed_detail_fragment)
    TextView tvCreatTimeProperty;
    Unbinder unbinder;
    private Params params = Params.getInstance();

    public static PropertyPayedDetailFragment newInstance(String fid) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PROPERTY_BILL_FID, fid);

        PropertyPayedDetailFragment fragment = new PropertyPayedDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            params.fid = bundle.getString(Constants.PROPERTY_BILL_FID);
        }
    }

    @NonNull
    @Override
    protected PropertyPayContract.Presenter createPresenter() {
        return new PropertyPayPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_payed_detail, container, false);
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
        toolbarTitle.setText("账单详情");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        mPresenter.requestPropertyPayDetail(params);
    }

    @Override
    public void start(Object response) {
    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responsePropertyNotPay(PropertyPayBean bean) {
        //无用方法,无需理会
    }

    @Override
    public void responsePropertyPayed(PropertyPayBean bean) {
        //无用方法,无需理会
    }

    @Override
    public void responsePropertyPayDetail(PropertyPayDetailBean bean) {
        if (bean == null) {
            return;
        }
        tvAddress.setText(bean.getData().getBuildingInfo().getAddress());
        tvSum.setText(bean.getData().getTotalAmt() + "元");
        tvPaymentMethod.setText(bean.getData().getPayType() == 1 ? Constants.ALIPAY : Constants.WXPAY);
        tvPaymentInstructions.setText(bean.getData().getBName());
        tvPaymentNumber.setText(bean.getData().getBCode());
        tvCreatTimeProperty.setText(bean.getData().getCtime());
    }

    @Override
    public void responseALiPay(String order) {
        //无用方法,无需理会
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
