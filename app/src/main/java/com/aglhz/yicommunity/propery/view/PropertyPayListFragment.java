package com.aglhz.yicommunity.propery.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.bean.PropertyPayDetailBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.common.payment.ALiPayHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.event.EventPay;
import com.aglhz.yicommunity.propery.contract.PropertyPayContract;
import com.aglhz.yicommunity.propery.presenter.PropertyPayPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * Created by leguang on 2017/4/29 0029.
 * Email：langmanleguang@qq.com
 */

public class PropertyPayListFragment extends BaseFragment<PropertyPayContract.Presenter> implements PropertyPayContract.View {
    public static final String TAG = PropertyPayListFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.tv_sum_property_pay_list_fragment)
    TextView tvSum;
    @BindView(R.id.bt_pay_property_pay_list_fragment)
    Button btPay;
    @BindView(R.id.ll_property_pay_list_fragment)
    LinearLayout ll;
    private Unbinder unbinder;
    private int payState;
    private LinearLayoutManager mLinearLayoutManager;
    private PropertyPayRVAdapter mAdapter;
    private Params params = Params.getInstance();
    private String[] arrPayType = {Constants.ALIPAY, Constants.WXPAY};

    public static PropertyPayListFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.PAY_STATE, position);

        PropertyPayListFragment fragment = new PropertyPayListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            payState = bundle.getInt(Constants.PAY_STATE);
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
        View view = inflater.inflate(R.layout.fragment_property_pay_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initData() {
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new PropertyPayRVAdapter();
        recyclerView.setAdapter(mAdapter);
        ll.setVisibility(payState == 0 ? View.VISIBLE : View.GONE);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (getParentFragment() instanceof PropertyPayFragment) {
                //2为未支付状态
                if (mAdapter.getData().get(position).getStatus() == 2) {
                    ((PropertyPayFragment) getParentFragment())
                            .start(PropertyNotPayDetailFragment.newInstance(mAdapter.getData().get(position).getFid()));
                } else {
                    ((PropertyPayFragment) getParentFragment())
                            .start(PropertyPayedDetailFragment.newInstance(mAdapter.getData().get(position).getFid()));
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        params.cmnt_c = UserHelper.communityCode;
        if (payState == 0) {
            mPresenter.requestPropertyNotPay(params);
        } else if (payState == 1) {
            mPresenter.requestPropertyPayed(params);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void start(Object response) {
        //无用方法,无需理会
    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @OnClick(R.id.bt_pay_property_pay_list_fragment)
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventPay event) {
        ptrFrameLayout.autoRefresh();
        if (event.code == 0) {
            DialogHelper.successSnackbar(getView(), "恭喜！支付成功");
        } else {
            DialogHelper.warningSnackbar(getView(), "很遗憾，支付失败,请重试");
        }
    }

    @Override
    public void responsePropertyNotPay(PropertyPayBean bean) {
        PropertyPayBean.DataBean data = bean.getData();
        ptrFrameLayout.refreshComplete();
        mAdapter.setNewData(data.getObpptBills());
        tvSum.setText("合计：" + data.getTotalAmt() + "元");

        //以下是为生成参数
        StringBuilder sb = new StringBuilder();
        for (PropertyPayBean.DataBean.ObpptBillsBean obpptBillsBean : data.getObpptBills()) {
            sb.append(obpptBillsBean.getFid()).append(",");
        }
        params.billFids = sb.toString();
        if (params.billFids.endsWith(",")) {
            params.billFids = params.billFids.substring(0, params.billFids.length() - 1);
        }
    }

    @Override
    public void responsePropertyPayed(PropertyPayBean bean) {
        ptrFrameLayout.refreshComplete();
        mAdapter.setNewData(bean.getData().getObpptBills());
    }

    @Override
    public void responsePropertyPayDetail(PropertyPayDetailBean bean) {
        //无用方法,无需理会
    }

    @Override
    public void responseALiPay(String order) {
        new ALiPayHelper().pay(_mActivity, order);
    }
}
