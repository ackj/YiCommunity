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
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.door.contract.AppointOpenDoorContract;
import com.aglhz.yicommunity.door.presenter.AppointOpenDoorPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: LiuJia on 2017/4/21 9:14.
 * Email: liujia95me@126.com
 */
public class AppointOpenDoorFragment extends BaseFragment<AppointOpenDoorContract.Presenter> implements AppointOpenDoorContract.View {
    private static final String TAG = AppointOpenDoorFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_appoint_opendoor)
    RecyclerView rvAppointOpendoor;
    private AppointOpenDoorRVAdapter adapter;
    private ViewGroup rootView;


    public static AppointOpenDoorFragment newInstance() {
        return new AppointOpenDoorFragment();
    }

    @NonNull
    @Override
    protected AppointOpenDoorContract.Presenter createPresenter() {
        return new AppointOpenDoorPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appoint_opendoor, container, false);
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
        mPresenter.requestDoorList("");
        initStateBar(toolbar);
        toolbarTitle.setText("指定开门");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        rvAppointOpendoor.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new AppointOpenDoorRVAdapter();
        rvAppointOpendoor.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ALog.e("111111111");
                mPresenter.requestAppointOpenDoor("", AppointOpenDoorFragment.this.adapter.getData().get(position).getDir());
            }
        });
    }

    @Override
    public void responseDoorList(DoorListBean mDoorListBean) {
        adapter.setNewData(mDoorListBean.getData());
    }

    @Override
    public void responseAppointOpenDoor(BaseBean mBaseBean) {
        ALog.e("8888888888");
        if (mBaseBean.getOther().getCode() == 200) {
            ALog.e("9999999999");

            DialogHelper.successSnackbar(rootView, "开门成功！");
        } else {
            DialogHelper.warningSnackbar(rootView, "开门失败");
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ALog.e("77777777");
        DialogHelper.warningSnackbar(rootView, "网络异常，请重试！");
    }
}
