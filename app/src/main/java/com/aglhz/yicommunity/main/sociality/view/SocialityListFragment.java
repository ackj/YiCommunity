package com.aglhz.yicommunity.main.sociality.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.widget.statemanager.StateLayout;
import com.aglhz.abase.widget.statemanager.StateManager;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.abase.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.SocialityListBean;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.event.EventPublish;
import com.aglhz.yicommunity.event.EventRefreshSocialityList;
import com.aglhz.yicommunity.main.publish.CommentActivity;
import com.aglhz.yicommunity.main.sociality.contract.SocialityListContract;
import com.aglhz.yicommunity.main.sociality.presenter.SocialityListPresenter;
import com.aglhz.yicommunity.preview.PreviewActivity;
import com.aglhz.yicommunity.web.WebActivity;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/5/11 0011 14:06.
 * Email: liujia95me@126.com
 * 所有与社交相关（拼车服务，左邻右里，闲置交换）的真正内容的View层
 */

public class SocialityListFragment extends BaseFragment<SocialityListContract.Presenter> implements SocialityListContract.View {
    private static final String TAG = SocialityFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.stateLayout)
    StateLayout stateLayout;
    public static final int TYPE_EXCHANGE = 100;//闲置交换
    public static final int TYPE_CARPOOL_OWNER = 101;//拼车服务（找车主）
    public static final int TYPE_CARPOOL_PASSENGER = 106;//拼车服务（找乘客）
    public static final int TYPE_NEIGHBOUR = 102;//左邻右里
    public static final int TYPE_MY_EXCHANGE = 103;//我发布的闲置交换
    public static final int TYPE_MY_CARPOOL = 104;//我发布的拼车服务
    public static final int TYPE_MY_NEIGHBOUR = 105;//我发布的左邻右里
    private LinearLayoutManager mLinearLayoutManager;
    private Unbinder unbinder;
    private SocialityListRVAdapter adapter;
    private int type;//显示类型
    private int removePosition;//记录要删除的item的位置
    private Params params = Params.getInstance();
    private StateManager mStateManager;
    private int infoType = 1;

    /**
     * SocialityListFragment的创建入口
     *
     * @param type 根据类型显示不同的数据
     * @return
     */
    public static SocialityListFragment newInstance(int type) {
        SocialityListFragment fragment = new SocialityListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected SocialityListContract.Presenter createPresenter() {
        return new SocialityListPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(Constants.KEY_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
        initStateManager();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initStateManager() {
        String emptyText;
        int emptyImage;
        switch (type) {
            case TYPE_EXCHANGE:
                emptyText = "还没有闲置信息呢，赶紧去发布吧！";
                emptyImage = R.drawable.ic_exchange_empty_state_gray_200px;
                break;
            case TYPE_CARPOOL_OWNER:
                emptyText = "还没有乘客发布信息呢！";
                emptyImage = R.drawable.ic_find_car_empty_state_gray_200px;
                break;
            case TYPE_CARPOOL_PASSENGER:
                emptyText = "还没有车主发布信息呢！";
                emptyImage = R.drawable.ic_find_passenger_empty_state_gray_200px;
                break;
            case TYPE_NEIGHBOUR:
                emptyText = "赶紧发一条邻里圈，刷一下存在感！";
                emptyImage = R.drawable.ic_neighbour_empty_state_gray_200px;
                break;
            case TYPE_MY_EXCHANGE:
            case TYPE_MY_NEIGHBOUR:
            case TYPE_MY_CARPOOL:
                emptyText = "您还没发布过任何信息呢，赶快去发布吧！";
                emptyImage = R.drawable.ic_publish_empty_state_gray_200px;
                break;
            default:
                emptyText = "暂无信息，请点击刷新";
                emptyImage = R.drawable.ic_comment_empty_state_gray_200px;
        }

        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyImage(emptyImage)
                .setEmptyText(emptyText)
                .setErrorOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setEmptyOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .build();
    }

    private void initData() {
        //下拉刷新必须得在懒加载里设置，因为下拉刷新是一进来就刷新，启动start()。
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        adapter = new SocialityListRVAdapter();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            requestNet();
        }, recyclerView);

        adapter.setType(type);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemChildLongClickListener((adapter1, view, position) -> {
            SocialityListBean.DataBean.MomentsListBean bean = adapter.getData().get(position);
            switch (view.getId()) {
                case R.id.iv_avatar_item_moments_list:
                    new AlertDialog.Builder(_mActivity)
                            .setItems(new String[]{"举报"}, (dialog, which) -> {
                                Intent introductionIntent = new Intent(_mActivity, WebActivity.class);
                                introductionIntent.putExtra(Constants.KEY_TITLE, "举报投诉");
                                String link = String.format(ApiService.REPORT_URL, UserHelper.token, infoType, bean.getFid());
                                ALog.e(TAG, "report url:::" + link);
                                introductionIntent.putExtra(Constants.KEY_LINK, link);
                                _mActivity.startActivity(introductionIntent);
                            }).show();
                    break;
            }
            return false;
        });
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            SocialityListBean.DataBean.MomentsListBean bean = (SocialityListBean.DataBean.MomentsListBean) adapter.getData().get(position);
            switch (view.getId()) {
                case R.id.iv_avatar_item_moments_list:
                    Intent preIntent = new Intent(_mActivity, PreviewActivity.class);
                    preIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle = new Bundle();
                    ArrayList<String> picsUrls = new ArrayList<>();
                    picsUrls.add(bean.getMember().getAvator());
                    bundle.putStringArrayList("picsList", picsUrls);
                    preIntent.putExtra("pics", bundle);
                    preIntent.putExtra("position", 0);
                    _mActivity.startActivity(preIntent);
                    break;
                case R.id.ll_comment_item_moments_list:
                case R.id.tv_comment_count_item_moments_list:
                    Intent intent = new Intent(_mActivity, CommentActivity.class);
                    intent.putExtra(Constants.KEY_FID, bean.getFid());
                    intent.putExtra(Constants.KEY_TYPE, type);
                    _mActivity.startActivity(intent);
                    break;
                case R.id.tv_remove_item_moments_list:
                    AlertDialog.Builder builder = new AlertDialog.Builder(_mActivity);
                    builder.setMessage("确认删除吗？");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确认", (dialog, which) -> {
                        removePosition = position;
                        removeMessage(bean.getFid());
                    });
                    builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
                    builder.create().show();
                    break;
            }
        });

        //滑动时不让图片加载
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(App.mContext).resumeRequests();
                } else {
                    Glide.with(App.mContext).pauseRequests();
                }
            }
        });
    }

    private void removeMessage(String fid) {
        Params params = Params.getInstance();
        params.fid = fid;
        switch (type) {
            case TYPE_MY_CARPOOL:
                mPresenter.requestRemoveMyCarpool(params); //请求删除我发布的拼车服务信息
                break;
            case TYPE_MY_EXCHANGE:
                mPresenter.requestRemoveMyExchange(params);//请求删除我发布的闲置交换信息
                break;
            case TYPE_MY_NEIGHBOUR:
                mPresenter.requestRemoveMyNeighbour(params);//请求删除我发布的左邻右里信息
                break;
        }
    }

    @Override
    public void onRefresh() {
        params.page = 1;
        params.pageSize = Constants.PAGE_SIZE;
        params.cmnt_c = UserHelper.communityCode;
        requestNet();
    }

    public void requestNet() {
        if (mPresenter == null) {//当滑动过快，力度过大的时候，瞬间退出，则会在过一会儿触发加载更多，所以会触发这段，而此时mPresenter已经被置空了。
            return;
        }
        if (type == TYPE_EXCHANGE) {
            mPresenter.requestExchangeList(params);//请求闲置交换数据
            infoType = 2;
        } else if (type == TYPE_NEIGHBOUR) {
            mPresenter.requestNeighbourList(params);//请求左邻右里
            infoType = 1;
        } else if (type == TYPE_CARPOOL_OWNER || type == TYPE_CARPOOL_PASSENGER) {
            params.currentPositionLat = UserHelper.latitude;
            params.currentPositionLng = UserHelper.longitude;
            params.carpoolType = type == TYPE_CARPOOL_OWNER ? 1 : 2;
            mPresenter.requestCarpoolList(params);//请求拼车服务
            infoType = 3;
        } else if (type == TYPE_MY_CARPOOL) {
            mPresenter.requestMyCarpoolList(params);//请求我发布的拼车服务
            infoType = 3;
        } else if (type == TYPE_MY_EXCHANGE) {
            mPresenter.requestMyExchangeList(params);//请求我发布的闲置交换
            infoType = 2;
        } else if (type == TYPE_MY_NEIGHBOUR) {
            mPresenter.requestMyNeihbourList(params);//请求我发布的左邻右里
            infoType = 1;
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ALog.e("error:" + errorMessage);
        ptrFrameLayout.refreshComplete();
        if (params.page == 1) {
            //为后面的pageState做准备
            mStateManager.showError();
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
     * 响应请求数据成功
     *
     * @param datas
     */
    @Override
    public void responseSuccess(List<SocialityListBean.DataBean.MomentsListBean> datas) {
        ptrFrameLayout.refreshComplete();
        if (datas == null || datas.isEmpty()) {
            if (params.page == 1) {
                mStateManager.showEmpty();
            }
            adapter.loadMoreEnd();
            return;
        }

        if (params.page == 1) {
            mStateManager.showContent();
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    /**
     * 响应请求删除成功
     *
     * @param bean
     */
    @Override
    public void removeSuccess(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), "删除成功");
        adapter.remove(removePosition);
    }

    @Override
    public boolean onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return false;
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        if (recyclerView == null || ptrFrameLayout == null) {
            return;
        }
        adapter.getData().clear();
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(0);
        ptrFrameLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void go2TopAndRefresh(EventPublish event) {
        if (recyclerView == null || ptrFrameLayout == null) {
            return;
        }
        recyclerView.scrollToPosition(0);
        ptrFrameLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventRefreshSocialityList event) {
        if (recyclerView == null || ptrFrameLayout == null) {
            return;
        }
        recyclerView.scrollToPosition(0);
        ptrFrameLayout.autoRefresh();
    }

    public void go2Top() {
        if (recyclerView == null) {
            return;
        }
        recyclerView.scrollToPosition(0);
    }

    public void refresh(String fid){
        params.cmnt_c_en = fid;
        onRefresh();
    }
}
