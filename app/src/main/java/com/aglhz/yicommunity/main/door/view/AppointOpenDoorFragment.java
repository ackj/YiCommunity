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
import com.aglhz.abase.widget.statemanager.StateManager;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.abase.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.DoorListBean;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.main.door.contract.AppointOpenDoorContract;
import com.aglhz.yicommunity.main.door.presenter.AppointOpenDoorPresenter;
import com.aglhz.yicommunity.widget.OpenDoorDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/4/21 9:14.
 * Email: liujia95me@126.com
 * <p>
 * [指定开门]的View层。
 * 打开方式：AppStart-->管家-->智慧门禁[指定开门]
 */
public class AppointOpenDoorFragment extends BaseFragment<AppointOpenDoorContract.Presenter> implements AppointOpenDoorContract.View {
    private static final String TAG = AppointOpenDoorFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private AppointOpenDoorRVAdapter adapter;
    private Unbinder unbinder;
    private Params params = Params.getInstance();
    private StateManager mStateManager;
    private OpenDoorDialog openDoorDialog;

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
        initStateManager();
        initListener();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    @Override
    public void onRefresh() {
        params.page = 1;
        params.pageSize = Constants.PAGE_SIZE;
        params.cmnt_c = UserHelper.communityCode;
        mPresenter.requestDoors(params);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("指定开门");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
        toolbarTitle.setOnClickListener(v -> recyclerView.scrollToPosition(0));
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new AppointOpenDoorRVAdapter();
        adapter.setEnableLoadMore(true);
        recyclerView.setAdapter(adapter);
        //设置Item动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.isFirstOnly(true);
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyImage(R.drawable.ic_open_record_empty_state_gray_200px)
                .setEmptyText("暂无开门列表！")
                .setErrorOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setEmptyOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .build();
    }

    private void initListener() {
        //设置允许加载更多
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.requestDoors(params);//请求获取开门列表
        }, recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            showQuickOpenDoorDialog();
            view.postDelayed(() -> {
                Params params = Params.getInstance();
                params.dir = AppointOpenDoorFragment.this.adapter.getData().get(position).getDir();
                mPresenter.requestAppointOpenDoor(params);//请求一键开门
            },1000);
        });
    }

    /**
     * 响应请求
     *
     * @param data
     */
    @Override
    public void responseDoors(DoorListBean data) {
        ptrFrameLayout.refreshComplete();
        if (data == null || data.getData().isEmpty()) {
            if (params.page == 1) {
                mStateManager.showEmpty();
            }
            adapter.loadMoreEnd();
            return;
        }
        if (params.page == 1) {
            mStateManager.showContent();
            adapter.setNewData(data.getData());
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(data.getData());
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    /**
     * 响应请求开门的结果
     *
     * @param mBaseBean
     */
    @Override
    public void responseAppointOpenDoor(BaseBean mBaseBean) {
        openDoorDialog.setSuccess();
        DialogHelper.successSnackbar(getView(), "开门成功！");
    }

    @Override
    public void start(Object response) {

    }

    public void showQuickOpenDoorDialog(){
        if (openDoorDialog == null) {
            openDoorDialog = new OpenDoorDialog(_mActivity);
        }
        openDoorDialog.setOpenDoor();
        openDoorDialog.show();
    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        if(openDoorDialog !=null){
            openDoorDialog.setError();
        }
        DialogHelper.warningSnackbar(getView(), errorMessage);//后面换成pagerstate的提示，不需要这种了
        if (params.page == 1) {
            mStateManager.showError();
        } else if (params.page > 1) {
            adapter.loadMoreFail();
            params.page--;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 选择社区后要刷新房门列表
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }
}
