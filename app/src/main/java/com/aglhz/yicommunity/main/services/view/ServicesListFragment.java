package com.aglhz.yicommunity.main.services.view;

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
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.ServicesListBean;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.main.services.contract.ServicesContract;
import com.aglhz.yicommunity.main.services.presenter.ServicesPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: leguang on 2017/4/21 9:14.
 * Email: langmanleguang@qq.com
 * <p>
 * [上门服务列表]的View层。
 * 打开方式：AppStart-->首页-->社区服务，如：送水上门等。
 */
public class ServicesListFragment extends BaseFragment<ServicesContract.Presenter> implements ServicesContract.View {
    private static final String TAG = ServicesListFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private ServicesRVAdapter adapter;
    private Unbinder unbinder;
    private Params params = Params.getInstance();
    private int serviceType;

    public static ServicesListFragment newInstance(int serviceType) {
        Bundle args = new Bundle();
        args.putInt(Constants.SERVICE_TYPE, serviceType);
        ServicesListFragment fragment = new ServicesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            serviceType = args.getInt(Constants.SERVICE_TYPE);
        }
    }

    @NonNull
    @Override
    protected ServicesContract.Presenter createPresenter() {
        return new ServicesPresenter(this);
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
//        mPresenter.requestDoors(params);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText(serviceType + "");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
        toolbarTitle.setOnClickListener(v -> recyclerView.scrollToPosition(0));
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new ServicesRVAdapter();
        adapter.setEnableLoadMore(true);
        recyclerView.setAdapter(adapter);
        //设置Item动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.isFirstOnly(true);

        //临时数据  后期删掉
        List<ServicesListBean> listServices = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listServices.add(new ServicesListBean("保洁2小时", "按时收费，有保障", "惠州市内", "55.0"));

        }
        adapter.setNewData(listServices);
    }

    private void initListener() {
        //设置允许加载更多
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
//            mPresenter.requestDoors(params);//请求获取开门列表
        }, recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> {

            start(ServicesDetailFragment.newInstance(""));
        });
    }

//    /**
//     * 响应请求
//     *
//     * @param datas
//     */
//    @Override
//    public void responseDoors(DoorListBean datas) {
//        ptrFrameLayout.refreshComplete();
//        if (datas == null || datas.getData().isEmpty()) {
//            adapter.loadMoreEnd();
//            return;
//        }
//        if (params.page == 1) {
//            adapter.setNewData(datas.getData());
//            adapter.disableLoadMoreIfNotFullPage(recyclerView);
//        } else {
//            adapter.addData(datas.getData());
//            adapter.setEnableLoadMore(true);
//            adapter.loadMoreComplete();
//        }
//    }


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

    /**
     * 选择社区后要刷新服务列表
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }
}
