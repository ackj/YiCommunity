package com.aglhz.yicommunity.main.park.view;

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
import com.aglhz.yicommunity.entity.bean.CarCityListBean;
import com.aglhz.yicommunity.common.Constants;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/23 0023 11:23.
 * Email: liujia95me@126.com
 * [车牌地区]的View层
 * 供用户选择车牌的归属地
 * 打开方式：StartApp-->管家-->办理车卡-->月卡办理-->点击[粤]
 */

public class CarCityFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private CarCityRVAdapter adapter;

    public static CarCityFragment newInstance() {
        return new CarCityFragment();
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
        initData();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("车牌地区");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        //从strings读取车牌归属地数据的json。
        String carCityJson = _mActivity.getString(R.string.car_city_json);
        Gson gson = new Gson();
        CarCityListBean cityListBean = gson.fromJson(carCityJson, CarCityListBean.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new CarCityRVAdapter(cityListBean.carCityList);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter, view, position) -> {
            CarCityListBean.CarCityBean bean = (CarCityListBean.CarCityBean) adapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_SHORTFROM, bean.shortfrom);
            setFragmentResult(RESULT_OK, bundle);
            pop();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
