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
import com.aglhz.yicommunity.door.contract.QuickOpenDoorContract;
import com.aglhz.yicommunity.door.presenter.QuickOpenDoorPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

import static com.aglhz.yicommunity.R.id.recyclerView;

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
    @BindView(recyclerView)
    RecyclerView rvQuickOpendoor;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private QuickOpenDoorRVAdapter mAdapter;
    private int prePosition;
    private Unbinder unbinder;
    private Params params = Params.getInstance();

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
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
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
                return ScrollingHelper.isRecyclerViewToTop(rvQuickOpendoor);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
                params.page = 1;
                params.pageSize = Constants.PAGE_SIZE;
                mPresenter.requestDoors(params);
            }
        });
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("设置一键开门");
        toolbarMenu.setText("保存");
        toolbarMenu.setOnClickListener(v -> {
            String dir = mAdapter.getData().get(prePosition).getDir();
            String name = mAdapter.getData().get(prePosition).getName();
            Params params = Params.getInstance();
            params.directory = dir;
            params.deviceName = name;
            mPresenter.requestQuickOpenDoor(params);
        });
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        rvQuickOpendoor.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new QuickOpenDoorRVAdapter();
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(() -> {
            ALog.e("加载更多………………………………");
            params.page++;
            mPresenter.requestDoors(params);
        }, rvQuickOpendoor);

        rvQuickOpendoor.setAdapter(mAdapter);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            QuickOpenDoorFragment.this.mAdapter.getData().get(prePosition).setQuickopen(false);
            QuickOpenDoorFragment.this.mAdapter.getData().get(position).setQuickopen(true);
            mAdapter.notifyItemChanged(prePosition);
            mAdapter.notifyItemChanged(position);
            prePosition = position;
        });
    }

    @Override
    public void responseDoors(DoorListBean datas) {
        ptrFrameLayout.refreshComplete();

        if (datas == null || datas.getData().isEmpty()) {
            mAdapter.loadMoreEnd();
            return;
        }

        if (params.page == 1) {
            mAdapter.setNewData(datas.getData());
            mAdapter.disableLoadMoreIfNotFullPage(rvQuickOpendoor);
        } else {
            mAdapter.addData(datas.getData());
            mAdapter.setEnableLoadMore(true);
            mAdapter.loadMoreComplete();
        }

        List<DoorListBean.DataBean> list = datas.getData();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isQuickopen()) {
                prePosition = i;
            }
        }
    }

    @Override
    public void responseQuickOpenDoor(BaseBean mBaseBean) {
        DialogHelper.successSnackbar(getView(), "设置成功！");
        UserHelper.setDir(mAdapter.getData().get(prePosition).getDir());
    }

    @Override
    public void onDestroy() {
        if (mAdapter != null) {
            mAdapter = null;
        }
        super.onDestroy();
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
            mAdapter.loadMoreFail();
            params.page--;
        }

        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
