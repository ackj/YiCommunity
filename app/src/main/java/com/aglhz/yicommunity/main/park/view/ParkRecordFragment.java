package com.aglhz.yicommunity.main.park.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DateUtils;
import com.aglhz.abase.widget.statemanager.StateManager;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.ParkRecordListBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.park.contract.ParkRecordContract;
import com.aglhz.yicommunity.main.park.presenter.ParkRecordPresenter;
import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/19 9:35.
 * [停车记录]的View层。
 * 打开方式：StartApp-->管家-->智慧停车[停车记录]
 */
public class ParkRecordFragment extends BaseFragment<ParkRecordContract.Presenter> implements ParkRecordContract.View {
    private static final String TAG = ParkRecordFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView_fragment_park_record)
    RecyclerView recyclerView;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    private Unbinder unbinder;
    private ParkRecordRVAdapter adapter;
    private Params params = Params.getInstance();
    private StateManager mStateManager;

    public static ParkRecordFragment newInstance() {
        return new ParkRecordFragment();
    }

    @NonNull
    @Override
    protected ParkRecordContract.Presenter createPresenter() {
        return new ParkRecordPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parking_record, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initStateManager();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("停车记录");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        Date date = new Date();
        tvStartTime.setText(getTime(date));
        tvEndTime.setText(getTime(date));
        requestSearch();
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new ParkRecordRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.requestParkReocrd(params);//请求停车记录
        }, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyImage(R.drawable.ic_find_car_empty_state_gray_200px)
                .setEmptyText("暂无停车记录！")
                .setErrorOnClickListener(v -> requestSearch())
                .setEmptyOnClickListener(v -> requestSearch())
                .build();
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        dismissLoadingDialog();
        if (params.page == 1) {
            //为后面的pageState做准备
            mStateManager.showError();
        } else if (params.page > 1) {
            adapter.loadMoreFail();
            params.page--;
        }
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    /**
     * 响应请求停车记录
     * @param datas
     */
    @Override
    public void responseParkRecord(List<ParkRecordListBean.PackRecordBean> datas) {
        dismissLoadingDialog();
        if (datas == null || datas.isEmpty()) {
            if (params.page == 1) {
                mStateManager.showEmpty();
            }
            adapter.loadMoreEnd();
            return;
        }
        if (params.page == 1) {
            mStateManager.showContent();
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_start_time, R.id.rl_end_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_start_time:
                setTime(tvStartTime);
                break;
            case R.id.rl_end_time:
                setTime(tvEndTime);
                break;
        }
    }

    /**
     * 弹出Dialog供用户选择时间筛选停车记录
     * @param tv
     */
    private void setTime(TextView tv) {
        TimePickerView pvTime = new TimePickerView.Builder(_mActivity, (date, v) -> {
            tv.setText(getTime(date));
            requestSearch();//每次选择完后时间就进行一次搜索请求
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .build();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(DateUtils.formatTime2Long("yyyy-MM-dd", tv.getText().toString())));
        pvTime.setDate(calendar);//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    /**
     * 请求搜索停车记录
     */
    private void requestSearch() {
        params.searchStartTime = tvStartTime.getText().toString().trim();
        params.searchEndTime = tvEndTime.getText().toString().trim();
        params.page = 1;
        if (adapter != null) {
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
        }
        mPresenter.requestParkReocrd(params);//请求停车记录
        showLoadingDialog();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
