package com.aglhz.yicommunity.mypublish.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.mypublish.contract.UnusedExchangeContract;
import com.aglhz.yicommunity.mypublish.presenter.UnusedExchangePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: LiuJia on 2017/4/21 19:37.
 * Email: liujia95me@126.com
 */
public class UnusedExchangeFragment extends BaseFragment<UnusedExchangeContract.Presenter> implements UnusedExchangeContract.View {
    private static final String TAG = UnusedExchangeFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ViewGroup rootView;
//    private GoodsChangeAdapter mAdapter;

    public static UnusedExchangeFragment newInstance() {
        return new UnusedExchangeFragment();
    }

    @NonNull
    @Override
    protected UnusedExchangeContract.Presenter createPresenter() {
        return new UnusedExchangePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = (ViewGroup) _mActivity.findViewById(android.R.id.content).getRootView();

        initRecyclerView();
        //获取数据
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//        mAdapter = new GoodsChangeAdapter(_mActivity);
//        mAdapter.setNoDataView(R.drawable.neighbour_no_data, R.string.neighbour_group_no_data_msg);
//        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ALog.e("77777777");
        DialogHelper.warningSnackbar(rootView, errorMessage);
    }

    @Override
    public void onDestroy() {
//        if (mAdapter != null) {
//            mAdapter = null;
//        }
        super.onDestroy();
    }
}
