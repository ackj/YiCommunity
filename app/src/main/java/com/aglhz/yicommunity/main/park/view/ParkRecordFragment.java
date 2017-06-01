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

        mPresenter.requestParkReocrd(params);

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new ParkRecordRVAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseParkRecord(List<ParkRecordListBean.PackRecordBean> datas) {
        adapter.setNewData(datas);
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

    private void setTime(TextView tv) {
        TimePickerView pvTime = new TimePickerView.Builder(_mActivity, (date, v) -> {

            tv.setText(getTime(date));
            requestSearch();
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private void requestSearch() {
        params.searchStartTime = tvStartTime.getText().toString().trim();
        params.searchEndTime = tvEndTime.getText().toString().trim();
        mPresenter.requestParkReocrd(params);
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
