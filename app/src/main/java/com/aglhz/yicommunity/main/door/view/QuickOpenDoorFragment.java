package com.aglhz.yicommunity.main.door.view;

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
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.main.door.contract.QuickOpenDoorContract;
import com.aglhz.yicommunity.main.door.presenter.QuickOpenDoorPresenter;
import com.aglhz.yicommunity.event.EventCommunity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/4/21 10:31.
 * Email: liujia95me@126.com
 * [设置一键开门[的View层。
 * 打开方式：Start App-->管家-->智能门禁[设置开门]
 */
public class QuickOpenDoorFragment extends BaseFragment<QuickOpenDoorContract.Presenter> implements QuickOpenDoorContract.View {
    private static final String TAG = QuickOpenDoorFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private QuickOpenDoorRVAdapter adapter;
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
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    @Override
    public void onRefresh() {
        params.page = 1;
        params.pageSize = Constants.PAGE_SIZE;
        params.cmnt_c = UserHelper.communityCode;
        mPresenter.requestDoors(params);//请求门禁列表
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("设置一键开门");
        toolbarMenu.setText("保存");
        toolbarMenu.setOnClickListener(v -> {
            String dir = adapter.getData().get(prePosition).getDir();
            String name = adapter.getData().get(prePosition).getName();
            Params params = Params.getInstance();
            params.directory = dir;
            params.deviceName = name;
            mPresenter.requestQuickOpenDoor(params);//请求设置一键开门
        });
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new QuickOpenDoorRVAdapter();
        //设置Item动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.isFirstOnly(true);
        //设置允许加载更多
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.requestDoors(params);//请求门禁列表
        }, recyclerView);

        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemClickListener((adapter, view, position) -> {
            QuickOpenDoorFragment.this.adapter.getData().get(prePosition).setQuickopen(false);
            QuickOpenDoorFragment.this.adapter.getData().get(position).setQuickopen(true);
            this.adapter.notifyItemChanged(prePosition);
            this.adapter.notifyItemChanged(position);
            prePosition = position;
        });
    }

    /**
     * 响应请求门禁列表
     * @param datas
     */
    @Override
    public void responseDoors(DoorListBean datas) {
        ptrFrameLayout.refreshComplete();

        if (datas == null || datas.getData().isEmpty()) {
            adapter.loadMoreEnd();
            return;
        }

        if (params.page == 1) {
            adapter.setNewData(datas.getData());
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas.getData());
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }

        List<DoorListBean.DataBean> list = datas.getData();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isQuickopen()) {
                prePosition = i;
            }
        }
    }

    /**
     * 响应请求设置一键开门
     * @param mBaseBean
     */
    @Override
    public void responseQuickOpenDoor(BaseBean mBaseBean) {
        DialogHelper.successSnackbar(getView(), "设置成功！");
        UserHelper.setDir(adapter.getData().get(prePosition).getDir());
    }

    @Override
    public void onDestroy() {
        if (adapter != null) {
            adapter = null;
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
            adapter.loadMoreFail();
            params.page--;
        }
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 选择完社区后刷新门禁列表
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }
}
