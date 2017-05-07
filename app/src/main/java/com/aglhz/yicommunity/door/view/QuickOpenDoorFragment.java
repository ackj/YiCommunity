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

import com.aglhz.abase.cache.SPCache;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.door.contract.QuickOpenDoorContract;
import com.aglhz.yicommunity.door.presenter.QuickOpenDoorPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aglhz.yicommunity.R.id.ptrFrameLayout;

/**
 * Author: LiuJia on 2017/4/21 10:31.
 * Email: liujia95me@126.com
 */
public class QuickOpenDoorFragment extends BaseFragment<QuickOpenDoorContract.Presenter> implements QuickOpenDoorContract.View {
    private static final String TAG = QuickOpenDoorFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_quick_open_door)
    RecyclerView rvQuickOpendoor;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    private QuickOpenDoorRVAdapter mAdapter;
    private int prePosition;
    private ViewGroup rootView;

    public static QuickOpenDoorFragment newInstance() {
        return new QuickOpenDoorFragment();
    }

    @NonNull
    @Override
    protected QuickOpenDoorContract.Presenter createPresenter() {
        return new QuickOpenDoorPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quick_open_door, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = (ViewGroup) _mActivity.findViewById(android.R.id.content).getRootView();
        initToolbar();
        initData();
        initListener();
//        mPresenter.start();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("设置一键开门");
        toolbarMenu.setText("保存");
        toolbarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dir = mAdapter.getData().get(prePosition).getDir();
                String name = mAdapter.getData().get(prePosition).getName();
                mPresenter.requestQuickOpenDoor("", dir, name);
            }
        });
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        rvQuickOpendoor.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new QuickOpenDoorRVAdapter();
        rvQuickOpendoor.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(rvQuickOpendoor);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                QuickOpenDoorFragment.this.mAdapter.getData().get(prePosition).setQuickopen(false);
                QuickOpenDoorFragment.this.mAdapter.getData().get(position).setQuickopen(true);
                mAdapter.notifyItemChanged(prePosition);
                mAdapter.notifyItemChanged(position);
                prePosition = position;
            }
        });
    }

    @Override
    public void responseDoorList(DoorListBean mDoorListBean) {
        mAdapter.setNewData(mDoorListBean.getData());
        List<DoorListBean.DataBean> list = mDoorListBean.getData();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isQuickopen()) {
                prePosition = i;
            }
        }
    }

    @Override
    public void responseQuickOpenDoor(BaseBean mBaseBean) {
        ALog.e("8888888888");
        if (mBaseBean.getOther().getCode() == 200) {
            ALog.e("9999999999");
            DialogHelper.successSnackbar(rootView, "设置成功！");
            SPCache.put(BaseApplication.mContext, Constants.DOOR_DIR, mAdapter.getData().get(prePosition).getDir());
        } else {
            DialogHelper.warningSnackbar(rootView, "设置失败");
        }
    }

    @Override
    public void start(Object response) {

    }
    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(rootView, "网络异常，请刷新！");
    }

    @Override
    public void onDestroy() {

        if (mAdapter != null) {
            mAdapter = null;

        }
        super.onDestroy();
    }
}
