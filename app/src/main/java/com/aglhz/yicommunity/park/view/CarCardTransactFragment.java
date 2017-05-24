package com.aglhz.yicommunity.park.view;

import android.graphics.Color;
import android.os.Bundle;
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
import com.aglhz.yicommunity.bean.CarCardTransactBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/18 17:38.
 */
public class CarCardTransactFragment extends BaseFragment {

    private static final String TAG = CarCardTransactFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_car_card_transact)
    RecyclerView rvCarCardTransact;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private CarCardTransactRVAdapter adapter;

    public static CarCardTransactFragment newInstance() {
        return new CarCardTransactFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_card_transact, container, false);
        ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("车卡办理");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        rvCarCardTransact.setLayoutManager(new LinearLayoutManager(_mActivity,LinearLayoutManager.VERTICAL,false));
        List<CarCardTransactBean> datas= new ArrayList<>();
        datas.add(new CarCardTransactBean(R.drawable.ic_car_card_transact_orange_210px,"月卡缴费","按照每月各小区收费标准收取月租费用",R.drawable.bg_round_orange_border, Color.parseColor("#FEB953")));
        datas.add(new CarCardTransactBean(R.drawable.ic_car_card_transact_greed_210px,"业主车库","业主自有车库，方便管理",R.drawable.bg_round_green_border,Color.parseColor("#0ABE9B")));
        adapter = new CarCardTransactRVAdapter(datas);
        rvCarCardTransact.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter, view, position) -> {
            if (position == 0) {
                start(PublishMonthCardFragment.newInstance());
            }else{
                start(PublishOwnerCardFragment.newInstance());
            }
        });
    }
}
