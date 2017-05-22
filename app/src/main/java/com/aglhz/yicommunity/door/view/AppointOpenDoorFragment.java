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
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.door.contract.AppointOpenDoorContract;
import com.aglhz.yicommunity.door.presenter.AppointOpenDoorPresenter;
import com.aglhz.yicommunity.event.EventCommunity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

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
    @BindView(R.id.recyclerView)
    RecyclerView rvAppointOpendoor;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private AppointOpenDoorRVAdapter adapter;
    private Unbinder unbinder;
    private Params params = Params.getInstance();


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
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initPtrFrameLayout();
    }

    private void initPtrFrameLayout() {
        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(BaseApplication.mContext, 15F), 0, DensityUtils.dp2px(BaseApplication.mContext, 10F));
        header.setPtrFrameLayout(ptrFrameLayout);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.postDelayed(() -> ptrFrameLayout.autoRefresh(true), 100);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //判断是否滑动到顶部。
                return ScrollingHelper.isRecyclerViewToTop(rvAppointOpendoor);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
                params.page = 1;
                params.pageSize = Constants.PAGE_SIZE;
                params.cmnt_c = UserHelper.communityCode;
                mPresenter.requestDoors(params);
            }
        });
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("指定开门");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        rvAppointOpendoor.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new AppointOpenDoorRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            ALog.e("加载更多………………………………");
            mPresenter.requestDoors(params);
        }, rvAppointOpendoor);

        rvAppointOpendoor.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            ALog.e("111111111");
            Params params = Params.getInstance();
            params.dir = AppointOpenDoorFragment.this.adapter.getData().get(position).getDir();
            mPresenter.requestAppointOpenDoor(params);
        });
    }

    @Override
    public void responseDoors(DoorListBean datas) {
        ptrFrameLayout.refreshComplete();
        if (datas == null || datas.getData().isEmpty()) {
            adapter.loadMoreEnd();
            return;
        }

        if (params.page == 1) {
            adapter.setNewData(datas.getData());
            adapter.disableLoadMoreIfNotFullPage(rvAppointOpendoor);
        } else {
            adapter.addData(datas.getData());
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void responseAppointOpenDoor(BaseBean mBaseBean) {
        DialogHelper.successSnackbar(getView(), "开门成功！");
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        if (params.page == 1) {
            //为后面的pageState做准备
        } else if (params.page > 1) {
            adapter.loadMoreFail();
            params.page--;
        }
        DialogHelper.warningSnackbar(getView(), errorMessage);//后面换成pagerstate的提示，不需要这种了
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }
}
