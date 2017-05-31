package com.aglhz.yicommunity.park.view;

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
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.CarCardListBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.park.contract.CarCardContract;
import com.aglhz.yicommunity.park.presenter.CarCardPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by Administrator on 2017/4/19 9:39.
 */
public class CarCardFragment extends BaseFragment<CarCardContract.Presenter> implements CarCardContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    private Unbinder unbinder;
    private CarCardRVAdapter adapter;
    Params params = Params.getInstance();
    private int removePosition;

    public static CarCardFragment newInstance() {
        return new CarCardFragment();
    }

    @NonNull
    @Override
    protected CarCardContract.Presenter createPresenter() {
        return new CarCardPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initPtrFrameLayout();
        initData();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("我的车卡");
        toolbarMenu.setText("充值记录");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initPtrFrameLayout() {
        final MaterialHeader header = new MaterialHeader(getContext());
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
                params.page = 1;
                params.pageSize = Constants.PAGE_SIZE;
                mPresenter.requestCarCardList(params);
            }
        });
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new CarCardRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.requestCarCardList(params);
        }, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            CarCardListBean.DataBean.CardListBean bean = (CarCardListBean.DataBean.CardListBean) adapter.getData().get(position);
            switch (view.getId()) {
                case R.id.ll_view_group:
                    if (bean.getApproveState() == 0) {
                        //-------------- 审核中 -------------
                        startForResult(SubmitResultFragment.newInstance(bean.getCardType(), true), 100);
                    } else if (bean.getApproveState() == 1) {
                        //-------------- 审核通过 -------------
                        if ("月租卡".equals(bean.getCardType())) {
                            //-------------- 月租卡 -------------
                            if (bean.getNeedToPayType() > 1) {
                                start(PublishMonthCardFragment.newInstance(PublishMonthCardFragment.TYPE_RECHARGE, bean.getFid()));
                            } else {
                                start(PublishMonthCardFragment.newInstance(PublishMonthCardFragment.TYPE_FIRST_PAY, bean.getFid()));
                            }
                        } else {
                            //-------------- 业主卡 -------------
                            startForResult(PublishOwnerCardFragment.newInstance(bean), 200);
                        }
                    } else {
                        //-------------- 审核被拒 -------------
                        startForResult(SubmitResultFragment.newInstance(bean.getCardType(), false), 100);
                    }
                    break;
                case R.id.iv_delete_card:
                    AlertDialog.Builder builder = new AlertDialog.Builder(_mActivity);
                    builder.setMessage("确认删除吗？");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确认", (dialog, which) -> {
                        removePosition = position;
                        params.fid = bean.getFid();
                        mPresenter.deleteCarCard(params);
                    });
                    builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
                    builder.create().show();
                    break;
            }
        });
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    pop();
                    break;
                case 200:
                    mPresenter.requestCarCardList(params);
                    break;
            }
        }
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
        if (params.page == 1) {
            //为后面的pageState做准备
        } else if (params.page > 1) {
            adapter.loadMoreFail();
            params.page--;
        }
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseCarCardList(List<CarCardListBean.DataBean.CardListBean> datas) {
        ptrFrameLayout.refreshComplete();
        if (datas == null || datas.isEmpty()) {
            adapter.loadMoreEnd();
            return;
        }
        ALog.e("datas::" + datas.size());
        if (params.page == 1) {
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void deleteSuccess(BaseBean baseBean) {
        adapter.remove(removePosition);
    }

    @OnClick(R.id.toolbar_menu)
    public void onViewClicked() {
        start(RechargeRecordFragment.newInstance());
    }
}
