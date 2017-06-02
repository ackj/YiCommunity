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
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.SocialityListBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.event.EventPublish;
import com.aglhz.yicommunity.main.sociality.presenter.SocialityPresenter;
import com.aglhz.yicommunity.main.sociality.contract.SocialityContract;
import com.aglhz.yicommunity.main.publish.CommentActivity;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/5/11 0011 14:06.
 * Email: liujia95me@126.com
 */

public class SocialityListFragment extends BaseFragment<SocialityContract.Presenter> implements SocialityContract.View {
    private static final String TAG = NeighbourFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    public static final int TYPE_EXCHANGE = 100;
    public static final int TYPE_CARPOOL_OWNER = 101;
    public static final int TYPE_CARPOOL_passenger = 106;
    public static final int TYPE_NEIGHBOUR = 102;
    public static final int TYPE_MY_EXCHANGE = 103;
    public static final int TYPE_MY_CARPOOL = 104;
    public static final int TYPE_MY_NEIGHBOUR = 105;

    private LinearLayoutManager mLinearLayoutManager;
    private Unbinder unbinder;
    private SocialityListRVAdapter adapter;
    private int type;
    private int removePosition;
    private Params params = Params.getInstance();

    public static SocialityListFragment newInstance(int type) {
        SocialityListFragment fragment = new SocialityListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    protected SocialityContract.Presenter createPresenter() {
        return new SocialityPresenter(this);
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
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initData() {
        //假数据
//        String json = "{\"data\":{\"momentsList\":[{\"fid\":\"8f1cf8c3-5630-4924-a083-54ccdd6cba42\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"communityName\":\"\",\"content\":\"秃头\",\"commentCount\":1,\"createTime\":\"2017-05-10 09:19:06\",\"commentList\":[{\"fid\":\"a3a37457-52ec-4943-add5-2294336a1426\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"content\":\"时空\",\"createTime\":\"2017-05-10 09:24:51\"}],\"pics\":[{\"type\":1,\"name\":\"20170510091905626601.jpeg\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510091905626601.jpeg\"}]},{\"fid\":\"5b6e504e-5654-41e2-b538-0ed422d4120f\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"communityName\":\"\",\"content\":\"拖拉机\",\"commentCount\":0,\"createTime\":\"2017-05-10 09:18:41\",\"commentList\":[],\"pics\":[]},{\"fid\":\"39f9eb94-b0c4-4c00-9cd8-0caa9cc6a5cf\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"\",\"content\":\"Wolaishishi\",\"commentCount\":0,\"createTime\":\"2017-05-10 09:10:11\",\"commentList\":[],\"pics\":[{\"type\":1,\"name\":\"20170510091010896510.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510091010896510.png\"},{\"type\":1,\"name\":\"20170510091011399373.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510091011399373.png\"},{\"type\":1,\"name\":\"20170510091011489841.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510091011489841.png\"}]},{\"fid\":\"a7108f6d-7ef6-427c-ab42-a2712dc24ca3\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"communityName\":\"\",\"content\":\"涂抹\",\"commentCount\":0,\"createTime\":\"2017-05-10 09:01:00\",\"commentList\":[],\"pics\":[]},{\"fid\":\"56c84d79-1176-4104-b05e-99f1891e898d\",\"member\":{\"memberNickName\":\"柏勇\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170302170742264136.png\"},\"communityName\":\"\",\"content\":\"加班\",\"commentCount\":0,\"createTime\":\"2017-05-10 01:02:41\",\"commentList\":[],\"pics\":[{\"type\":1,\"name\":\"20170510010240946337.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510010240946337.png\"},{\"type\":1,\"name\":\"20170510010241057114.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510010241057114.png\"},{\"type\":1,\"name\":\"20170510010241146244.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510010241146244.png\"}]},{\"fid\":\"3ee04d29-d0ee-414e-9704-70194b6d8c54\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"communityName\":\"\",\"content\":\"土豪金\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:54:36\",\"commentList\":[],\"pics\":[{\"type\":1,\"name\":\"20170510005435605507.jpeg\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510005435605507.jpeg\"}]},{\"fid\":\"5610a4a4-34d7-4450-9f34-3da5803ef7f7\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"广东省惠州市惠城区凯宾斯基\",\"content\":\"来试试\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:52:48\",\"commentList\":[],\"pics\":[{\"type\":2,\"name\":\"20170510005247917213.mp4\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510005247917213.mp4\",\"src\":\"http://aglhzysq.image.alimmdn.com/moments/20170510005248913534.jpg\"}]},{\"fid\":\"f871b9a7-49ea-4c9a-bbb9-21c0751672ce\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"\",\"content\":\"孟加拉\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:52:18\",\"commentList\":[],\"pics\":[{\"type\":1,\"name\":\"20170510005218286661.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510005218286661.png\"}]},{\"fid\":\"cc141471-0e4d-48b3-9188-caabf372549c\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"\",\"content\":\"123456\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:51:39\",\"commentList\":[],\"pics\":[]},{\"fid\":\"821d2038-c6dc-4865-935a-6688ab97c855\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"广东省惠州市惠城区凯宾斯基\",\"content\":\"å\u00AD\u009Få\u008A æ\u008B\u0089äººæ°\u0091å\u0085±å\u0092\u008Cå\u009B½\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:49:10\",\"commentList\":[],\"pics\":[]}]},\"other\":{\"code\":200,\"message\":\"data success\",\"time\":\"\",\"currpage\":1,\"next\":\"http://www.aglhz.com/sub_property_ysq/neighbor/moments/to-client/moments-list?page\\u003d2\\u0026pageSize\\u003d10\",\"forward\":\"\",\"refresh\":\"http://www.aglhz.com/sub_property_ysq/neighbor/moments/to-client/moments-list?page\\u003d1\\u0026pageSize\\u003d10\",\"first\":\"http://www.aglhz.com/sub_property_ysq/neighbor/moments/to-client/moments-list?page\\u003d1\\u0026pageSize\\u003d10\"}}\n";
//        Gson gson = new Gson();
//        SocialityListBean bean = gson.fromJson(json, SocialityListBean.class);
//        List<SocialityListBean.DataBean.MomentsListBean> momentsList = bean.getData().getMomentsList();
//        for (int i = 0; i < 5; i++) {
//            momentsList.addAll(momentsList);
//        }
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
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            SocialityListBean.DataBean.MomentsListBean bean = (SocialityListBean.DataBean.MomentsListBean) adapter.getData().get(position);
            switch (view.getId()) {
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
                    Glide.with(SocialityListFragment.this).resumeRequests();
                } else {
                    Glide.with(SocialityListFragment.this).pauseRequests();
                }
            }
        });
    }

    private void removeMessage(String fid) {
        Params params = Params.getInstance();
        params.fid = fid;
        switch (type) {
            case TYPE_MY_CARPOOL:
                mPresenter.requestRemoveMyCarpool(params);
                break;
            case TYPE_MY_EXCHANGE:
                mPresenter.requestRemoveMyExchange(params);
                break;
            case TYPE_MY_NEIGHBOUR:
                mPresenter.requestRemoveMyNeighbour(params);
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
        if (type == TYPE_EXCHANGE) {
            mPresenter.requestExchangeList(params);
        } else if (type == TYPE_NEIGHBOUR) {
            mPresenter.requestNeighbourList(params);
        } else if (type == TYPE_CARPOOL_OWNER || type == TYPE_CARPOOL_passenger) {
            params.currentPositionLat = UserHelper.latitude;
            params.currentPositionLng = UserHelper.longitude;
            params.carpoolType = type == TYPE_CARPOOL_OWNER ? 1 : 2;
            mPresenter.requestCarpoolList(params);
        } else if (type == TYPE_MY_CARPOOL) {
            mPresenter.requestMyCarpoolList(params);
        } else if (type == TYPE_MY_EXCHANGE) {
            mPresenter.requestMyExchangeList(params);
        } else if (type == TYPE_MY_NEIGHBOUR) {
            mPresenter.requestMyNeihbourList(params);
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

    @Override
    public void responseSuccess(List<SocialityListBean.DataBean.MomentsListBean> datas) {
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
        Glide.with(this).pauseRequests();
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        adapter.getData().clear();
        adapter.notifyDataSetChanged();
        ptrFrameLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventPublish event) {
        ptrFrameLayout.autoRefresh();
    }
}
