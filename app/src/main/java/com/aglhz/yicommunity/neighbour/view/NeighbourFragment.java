package com.aglhz.yicommunity.neighbour.view;

import android.annotation.SuppressLint;
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
import com.aglhz.abase.mvp.view.base.BaseLazyFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.NeighbourListBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.neighbour.contract.NeighbourContract;
import com.aglhz.yicommunity.neighbour.presenter.NeighbourPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Author：leguang on 2017/4/13 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的View层内容。
 */
public class NeighbourFragment extends BaseLazyFragment<NeighbourContract.Presenter> implements NeighbourContract.View {
    private static final String TAG = NeighbourFragment.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private LinearLayoutManager mLinearLayoutManager;
    private View mFooterLoading, mFooterNotLoading, mFooterError;
    private Unbinder unbinder;
    private NeighbourRVAdapter adapter;

    public static NeighbourFragment newInstance() {
        return new NeighbourFragment();
    }

    @NonNull
    @Override
    protected NeighbourContract.Presenter createPresenter() {
        return new NeighbourPresenter(this);
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
    }

    /**
     * 懒加载
     */
    @SuppressLint("RestrictedApi")
    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFooterLoading = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_loading, (ViewGroup) recyclerView.getParent(), false);
            mFooterNotLoading = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_not_loading, (ViewGroup) recyclerView.getParent(), false);
            mFooterError = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_error, (ViewGroup) recyclerView.getParent(), false);
            mFooterError.setOnClickListener(v -> {
//                    mAdapter.removeAllFooterView();
//                    mAdapter.addFooterView(mFooterLoading);
//                    mPresenter.queryData(pagination);
            });

            initData();
        }
    }

    private void initData() {
        //假数据
//        String json = "{\"data\":{\"momentsList\":[{\"fid\":\"8f1cf8c3-5630-4924-a083-54ccdd6cba42\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"communityName\":\"\",\"content\":\"秃头\",\"commentCount\":1,\"createTime\":\"2017-05-10 09:19:06\",\"commentList\":[{\"fid\":\"a3a37457-52ec-4943-add5-2294336a1426\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"content\":\"时空\",\"createTime\":\"2017-05-10 09:24:51\"}],\"pics\":[{\"type\":1,\"name\":\"20170510091905626601.jpeg\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510091905626601.jpeg\"}]},{\"fid\":\"5b6e504e-5654-41e2-b538-0ed422d4120f\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"communityName\":\"\",\"content\":\"拖拉机\",\"commentCount\":0,\"createTime\":\"2017-05-10 09:18:41\",\"commentList\":[],\"pics\":[]},{\"fid\":\"39f9eb94-b0c4-4c00-9cd8-0caa9cc6a5cf\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"\",\"content\":\"Wolaishishi\",\"commentCount\":0,\"createTime\":\"2017-05-10 09:10:11\",\"commentList\":[],\"pics\":[{\"type\":1,\"name\":\"20170510091010896510.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510091010896510.png\"},{\"type\":1,\"name\":\"20170510091011399373.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510091011399373.png\"},{\"type\":1,\"name\":\"20170510091011489841.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510091011489841.png\"}]},{\"fid\":\"a7108f6d-7ef6-427c-ab42-a2712dc24ca3\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"communityName\":\"\",\"content\":\"涂抹\",\"commentCount\":0,\"createTime\":\"2017-05-10 09:01:00\",\"commentList\":[],\"pics\":[]},{\"fid\":\"56c84d79-1176-4104-b05e-99f1891e898d\",\"member\":{\"memberNickName\":\"柏勇\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170302170742264136.png\"},\"communityName\":\"\",\"content\":\"加班\",\"commentCount\":0,\"createTime\":\"2017-05-10 01:02:41\",\"commentList\":[],\"pics\":[{\"type\":1,\"name\":\"20170510010240946337.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510010240946337.png\"},{\"type\":1,\"name\":\"20170510010241057114.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510010241057114.png\"},{\"type\":1,\"name\":\"20170510010241146244.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510010241146244.png\"}]},{\"fid\":\"3ee04d29-d0ee-414e-9704-70194b6d8c54\",\"member\":{\"memberNickName\":\"1223\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png\"},\"communityName\":\"\",\"content\":\"土豪金\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:54:36\",\"commentList\":[],\"pics\":[{\"type\":1,\"name\":\"20170510005435605507.jpeg\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510005435605507.jpeg\"}]},{\"fid\":\"5610a4a4-34d7-4450-9f34-3da5803ef7f7\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"广东省惠州市惠城区凯宾斯基\",\"content\":\"来试试\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:52:48\",\"commentList\":[],\"pics\":[{\"type\":2,\"name\":\"20170510005247917213.mp4\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510005247917213.mp4\",\"src\":\"http://aglhzysq.image.alimmdn.com/moments/20170510005248913534.jpg\"}]},{\"fid\":\"f871b9a7-49ea-4c9a-bbb9-21c0751672ce\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"\",\"content\":\"孟加拉\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:52:18\",\"commentList\":[],\"pics\":[{\"type\":1,\"name\":\"20170510005218286661.png\",\"url\":\"http://aglhzysq.image.alimmdn.com/moments/20170510005218286661.png\"}]},{\"fid\":\"cc141471-0e4d-48b3-9188-caabf372549c\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"\",\"content\":\"123456\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:51:39\",\"commentList\":[],\"pics\":[]},{\"fid\":\"821d2038-c6dc-4865-935a-6688ab97c855\",\"member\":{\"memberNickName\":\"拉斯维加斯-奥斯托洛夫斯基-龙哥\",\"avator\":\"http://aglhzmall.image.alimmdn.com/member/20170331161304285703.png\"},\"communityName\":\"广东省惠州市惠城区凯宾斯基\",\"content\":\"å\u00AD\u009Få\u008A æ\u008B\u0089äººæ°\u0091å\u0085±å\u0092\u008Cå\u009B½\",\"commentCount\":0,\"createTime\":\"2017-05-10 00:49:10\",\"commentList\":[],\"pics\":[]}]},\"other\":{\"code\":200,\"message\":\"data success\",\"time\":\"\",\"currpage\":1,\"next\":\"http://www.aglhz.com/sub_property_ysq/neighbor/moments/to-client/moments-list?page\\u003d2\\u0026pageSize\\u003d10\",\"forward\":\"\",\"refresh\":\"http://www.aglhz.com/sub_property_ysq/neighbor/moments/to-client/moments-list?page\\u003d1\\u0026pageSize\\u003d10\",\"first\":\"http://www.aglhz.com/sub_property_ysq/neighbor/moments/to-client/moments-list?page\\u003d1\\u0026pageSize\\u003d10\"}}\n";
//        Gson gson = new Gson();
//        NeighbourListBean bean = gson.fromJson(json, NeighbourListBean.class);
//        List<NeighbourListBean.DataBean.MomentsListBean> momentsList = bean.getData().getMomentsList();
//        for (int i = 0; i < 5; i++) {
//            momentsList.addAll(momentsList);
//        }

        initPtrFrameLayout(ptrFrameLayout);
        Params params = Params.getInstance();
        params.page = 1;
        params.pageSize = 10;
        mPresenter.requestNeihbourList(params);

        //下拉刷新必须得在懒加载里设置，因为下拉刷新是一进来就刷新，启动start()。
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        adapter = new NeighbourRVAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    private void initPtrFrameLayout(final PtrFrameLayout ptrFrameLayout) {
        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(BaseApplication.mContext, 15F), 0, DensityUtils.dp2px(BaseApplication.mContext, 10F));
        header.setPtrFrameLayout(ptrFrameLayout);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.autoRefresh(true);
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
                Params params = Params.getInstance();
                params.page = 1;
                params.pageSize = 10;
                mPresenter.requestNeihbourList(params);
            }
        });
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }


    protected void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("左邻右里");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void responseNeihbourList(List<NeighbourListBean.DataBean.MomentsListBean> datas) {
        ptrFrameLayout.refreshComplete();
        adapter.setNewData(datas);
    }
}


