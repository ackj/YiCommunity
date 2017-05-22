package com.aglhz.yicommunity.smarthome.view;

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
import com.aglhz.yicommunity.bean.GoodsBean;
import com.aglhz.yicommunity.bean.SubCategoryBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.smarthome.contract.SmartHomeMallContract;
import com.aglhz.yicommunity.smarthome.presenter.SmartHomeMallPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/22 0022 08:58.
 * Email: liujia95me@126.com
 */

public class SmartHomeMallFragment extends BaseFragment<SmartHomeMallPresenter> implements SmartHomeMallContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView_menu)
    RecyclerView recyclerViewMenu;
    @BindView(R.id.recyclerView_commodity)
    RecyclerView recyclerViewCommodity;

    private Unbinder unbinder;
    private Params params = Params.getInstance();
    private SmartHomeMenuRVAdapter menuAdapter;

    public static SmartHomeMallFragment newInstance(String id) {
        SmartHomeMallFragment fragment = new SmartHomeMallFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected SmartHomeMallPresenter createPresenter() {
        return new SmartHomeMallPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        params.id = getArguments().getString("id");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smart_home_mall, container, false);
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
        toolbarTitle.setText("智能家居商城");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        mPresenter.requestSubCategoryList(params);

        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(_mActivity));
        menuAdapter = new SmartHomeMenuRVAdapter();
        recyclerViewMenu.setAdapter(menuAdapter);
    }

    private void initListener() {
        menuAdapter.setOnItemClickListener((adapter, view, position) -> {
            SubCategoryBean.DataBean bean = (SubCategoryBean.DataBean) adapter.getData().get(position);

        });
    }

    @Override
    public void responseSubCategoryList(List<SubCategoryBean.DataBean> datas) {
        menuAdapter.setNewData(datas);
    }

    @Override
    public void responseGoodsList(List<GoodsBean.DataBean> datas) {

    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
