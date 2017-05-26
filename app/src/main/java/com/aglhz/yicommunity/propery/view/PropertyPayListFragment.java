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

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.common.payment.ALiPayHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.event.EventPay;
import com.aglhz.yicommunity.propery.contract.PropertyPayContract;
import com.aglhz.yicommunity.propery.presenter.PropertyPayPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;


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
    private int payPosition;
    private LinearLayoutManager mLinearLayoutManager;
    private PropertyPayRVAdapter mAdapter;
    private Params params = Params.getInstance();
    private String[] arrPayType = {"支付宝支付", "微信支付"};

    public static PropertyPayListFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.PAY_POSITION, position);

        PropertyPayListFragment fragment = new PropertyPayListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            payPosition = bundle.getInt(Constants.PAY_POSITION);
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
        initPtrFrameLayout();
    }

    private void initData() {
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new PropertyPayRVAdapter();
        recyclerView.setAdapter(mAdapter);
        ll.setVisibility(payPosition == 0 ? View.VISIBLE : View.GONE);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
//            mAdapter.getData().get(position)
        });
    }

    private void initPtrFrameLayout() {
        MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(BaseApplication.mContext, 15F), 0, DensityUtils.dp2px(BaseApplication.mContext, 10F));
        header.setPtrFrameLayout(ptrFrameLayout);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.postDelayed(() -> ptrFrameLayout.autoRefresh(true), 100);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //判断是否滑动到顶部。
                return ScrollingHelper.isRecyclerViewToTop(recyclerView);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
                params.cmnt_c = UserHelper.communityCode;
                if (payPosition == 0) {
                    mPresenter.requestPropertyNotPay(params);
                } else if (payPosition == 1) {
                    mPresenter.requestPropertyPayed(params);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void start(Object response) {
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
                            params.payType = Constants.ALIPAY;
                            break;
                        case 1:
                            params.payType = Constants.WXPAY;
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
            DialogHelper.warningSnackbar(getView(), "很遗憾，支付失败");
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
    public void responsePropertyPayDetail(PropertyPayBean bean) {

    }

    @Override
    public void responseALiPay(String order) {
        new ALiPayHelper().pay(_mActivity, order);
    }
}
