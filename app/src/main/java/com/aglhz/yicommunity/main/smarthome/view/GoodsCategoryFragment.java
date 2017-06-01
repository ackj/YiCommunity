package com.aglhz.yicommunity.main.smarthome.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.FirstLevelBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.smarthome.presenter.GoodsCategoryPresenter;
import com.aglhz.yicommunity.main.smarthome.contract.GoodsCategoryContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/5/22 0022 10:21.
 * Email: liujia95me@126.com
 */

public class GoodsCategoryFragment extends BaseFragment<GoodsCategoryPresenter> implements GoodsCategoryContract.View {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    Params params = Params.getInstance();

    private Unbinder unbinder;
    private GoodsCategoryRVAdapter adapter;

    public static GoodsCategoryFragment newInstance() {
        return new GoodsCategoryFragment();
    }

    @NonNull
    @Override
    protected GoodsCategoryPresenter createPresenter() {
        return new GoodsCategoryPresenter(this);
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
        toolbarTitle.setText("商品分类");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        mPresenter.requestFirstLevel(params);

        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        adapter = new GoodsCategoryRVAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter, view, position) -> {
            FirstLevelBean.DataBean bean = (FirstLevelBean.DataBean) adapter.getData().get(position);
            start(SmartHomeMallFragment.newInstance(bean.getId()));
        });
    }

    @Override
    public void responseFirstLevel(List<FirstLevelBean.DataBean> datas) {
        startForResult(SmartHomeMallFragment.newInstance(datas.get(datas.size() - 1).getId()), 100);
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            pop();
        }
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
