package com.aglhz.yicommunity.propery.view;

import android.content.Intent;
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
import com.aglhz.yicommunity.bean.NoticeBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.propery.contract.NoticeListContract;
import com.aglhz.yicommunity.propery.presenter.NoticeListPresenter;
import com.aglhz.yicommunity.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Author: LiuJia on 2017/5/9 0009 22:33.
 * Email: liujia95me@126.com
 */

public class NoticeListFragment extends BaseFragment<NoticeListPresenter> implements NoticeListContract.View {
    public static final String TAG = NoticeListFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private Unbinder unbinder;
    private NoticeListRVAdapter adapter;
    private Params params = Params.getInstance();


    public static NoticeListFragment newInstance() {
        return new NoticeListFragment();
    }

    @NonNull
    @Override
    protected NoticeListPresenter createPresenter() {
        return new NoticeListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initPtrFrameLayout();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("物业公告");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        params.summerable = true;
        params.timeable = true;
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        ArrayList<NoticeBean.DataBean.NoticeListBean> data = new ArrayList<>();
        adapter = new NoticeListRVAdapter(data);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            ALog.e("加载更多………………………………");
            params.page++;
            mPresenter.requestNotices(params);

        }, recyclerView);

        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            NoticeBean.DataBean.NoticeListBean bean = (NoticeBean.DataBean.NoticeListBean) adapter.getData().get(position);
            go2Web("物业公告", ApiService.requestNoticeDetail + bean.getFid());
        });
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
                return ScrollingHelper.isRecyclerViewToTop(recyclerView);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
                params.page = 1;
                params.pageSize = Constants.PAGE_SIZE;
                params.cmnt_c = UserHelper.communityCode;
                mPresenter.requestNotices(params);
            }
        });
    }

    private void go2Web(String title, String link) {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("link", link);
        _mActivity.startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
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
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseNotices(List<NoticeBean.DataBean.NoticeListBean> datas) {
        ptrFrameLayout.refreshComplete();

        if (datas == null || datas.isEmpty()) {
            adapter.loadMoreEnd();
            return;
        }

        ALog.e("datas::" + datas.size());
        if (params.page == 1) {
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }
}
