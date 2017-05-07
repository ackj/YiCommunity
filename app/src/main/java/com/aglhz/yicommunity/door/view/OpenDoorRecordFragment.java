package com.aglhz.yicommunity.door.view;

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

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.OpenDoorRecordBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.door.contract.OpenDoorRecordContract;
import com.aglhz.yicommunity.door.presenter.OpenDoorRecordPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: LiuJia on 2017/4/21 10:31.
 * Email: liujia95me@126.com
 */
public class OpenDoorRecordFragment extends BaseFragment<OpenDoorRecordContract.Presenter> implements OpenDoorRecordContract.View {
    private static final String TAG = OpenDoorRecordFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_opendoor_record)
    RecyclerView rvOpenDoorRecord;
    private OpenDoorRecordRVAdapter mAdapter;
    private ViewGroup rootView;


    public static OpenDoorRecordFragment newInstance() {
        return new OpenDoorRecordFragment();
    }

    @NonNull
    @Override
    protected OpenDoorRecordContract.Presenter createPresenter() {
        return new OpenDoorRecordPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opendoor_record, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = (ViewGroup) _mActivity.findViewById(android.R.id.content).getRootView();
        initToolbar();
        initData();
    }


    private void initToolbar() {
        mPresenter.requestRecord("");
        initStateBar(toolbar);
        toolbarTitle.setText("开门记录");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        rvOpenDoorRecord.setLayoutManager(new LinearLayoutManager(_mActivity));

        mAdapter = new OpenDoorRecordRVAdapter();
        rvOpenDoorRecord.setAdapter(mAdapter);
    }

    @Override
    public void responseRecord(List<OpenDoorRecordBean.DataBean> listRecord) {
        mAdapter.setNewData(listRecord);
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ALog.e("77777777");
        DialogHelper.warningSnackbar(rootView, errorMessage);
    }

}
