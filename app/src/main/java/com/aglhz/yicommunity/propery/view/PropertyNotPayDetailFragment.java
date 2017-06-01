package com.aglhz.yicommunity.propery.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.mvp.view.base.Decoration;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.bean.PropertyPayDetailBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.payment.ALiPayHelper;
import com.aglhz.yicommunity.propery.contract.PropertyPayContract;
import com.aglhz.yicommunity.propery.presenter.PropertyPayPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by leguang on 2017/4/29 0029.
 * Email：langmanleguang@qq.com
 */

public class PropertyNotPayDetailFragment extends BaseFragment<PropertyPayContract.Presenter> implements PropertyPayContract.View {
    public static final String TAG = PropertyNotPayDetailFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_address_property_not_pay_detail_fragment)
    TextView tvAddress;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.tv_sum_property_not_pay_detail_fragment)
    TextView tvSum;
    @BindView(R.id.bt_pay_property_not_pay_detail_fragment)
    Button btPay;
    private Unbinder unbinder;
    private Params params = Params.getInstance();
    private PropertyNotPayDetailRVAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private String[] arrPayType = {Constants.ALIPAY, Constants.WXPAY};

    public static PropertyNotPayDetailFragment newInstance(String fid) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PROPERTY_BILL_FID, fid);

        PropertyNotPayDetailFragment fragment = new PropertyNotPayDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_property_not_pay_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("账单详情");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new PropertyNotPayDetailRVAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new Decoration(_mActivity, Decoration.VERTICAL_LIST));
    }

    @Override
    public void onRefresh() {
        mPresenter.requestPropertyPayDetail(params);
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
        ptrFrameLayout.refreshComplete();
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
        ptrFrameLayout.refreshComplete();
        mAdapter.setNewData(bean.getData().getPptBillDets());
        tvAddress.setText(bean.getData().getBuildingInfo().getAddress());
        tvSum.setText("合计：" + bean.getData().getTotalAmt() + "元");
        params.billFids = bean.getData().getFid();
    }

    @Override
    public void responseALiPay(String order) {
        new ALiPayHelper().pay(_mActivity, order);
    }

    @OnClick(R.id.bt_pay_property_not_pay_detail_fragment)
    public void onViewClicked() {
        new AlertDialog.Builder(_mActivity).setTitle("请选择支付类型")
                .setItems(arrPayType, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            params.payType = Constants.TYPE_ALIPAY;
                            break;
                        case 1:
                            params.payType = Constants.TYPE_WXPAY;
                            break;
                    }
                    mPresenter.requestOrder(params);
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
