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
import com.aglhz.yicommunity.bean.OpenDoorRecordBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.door.contract.OpenDoorRecordContract;
import com.aglhz.yicommunity.door.presenter.OpenDoorRecordPresenter;
import com.aglhz.yicommunity.event.EventCommunity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
public class OpenDoorRecordFragment extends BaseFragment<OpenDoorRecordContract.Presenter> implements OpenDoorRecordContract.View {
    private static final String TAG = OpenDoorRecordFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(recyclerView)
    RecyclerView rvOpenDoorRecord;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private OpenDoorRecordRVAdapter mAdapter;
    private Unbinder unbinder;
    private Params params = Params.getInstance();

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

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("开门记录");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        rvOpenDoorRecord.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new OpenDoorRecordRVAdapter();
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(() -> {
            ALog.e("加载更多………………………………");
            params.page++;
            mPresenter.requestRecord(params);
        }, rvOpenDoorRecord);
        rvOpenDoorRecord.setAdapter(mAdapter);
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
                return ScrollingHelper.isRecyclerViewToTop(rvOpenDoorRecord);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
//                mPresenter.start();
                params.page = 1;
                params.pageSize = Constants.PAGE_SIZE;
                params.cmnt_c = UserHelper.communityCode;
                mPresenter.requestRecord(params);
            }
        });
    }

    @Override
    public void responseRecord(List<OpenDoorRecordBean.DataBean> datas) {
        ptrFrameLayout.refreshComplete();
        if (datas == null || datas.isEmpty()) {
            mAdapter.loadMoreEnd();
            return;
        }

        if (params.page == 1) {
            mAdapter.setNewData(datas);
            mAdapter.disableLoadMoreIfNotFullPage(rvOpenDoorRecord);
        } else {
            mAdapter.addData(datas);
            mAdapter.setEnableLoadMore(true);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(getView(), errorMessage);
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
