package com.aglhz.yicommunity.neighbour.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseLazyFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.neighbour.contract.NeighbourContract;
import com.aglhz.yicommunity.neighbour.presenter.NeighbourPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private LinearLayoutManager mLinearLayoutManager;
    private View mFooterLoading, mFooterNotLoading, mFooterError;

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
        View view = inflater.inflate(R.layout.fragment_neighbour, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(toolbar);

    }

    /**
     * 懒加载
     */
    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFooterLoading = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_loading, (ViewGroup) recyclerView.getParent(), false);
            mFooterNotLoading = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_not_loading, (ViewGroup) recyclerView.getParent(), false);
            mFooterError = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_error, (ViewGroup) recyclerView.getParent(), false);
            mFooterError.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mAdapter.removeAllFooterView();
//                    mAdapter.addFooterView(mFooterLoading);
//                    mPresenter.queryData(pagination);
                }
            });

            initData();
        }
    }

    private void initData() {
        initPtrFrameLayout(ptrFrameLayout);
        initRecyclerView();
    }

    private void initRecyclerView() {
        //下拉刷新必须得在懒加载里设置，因为下拉刷新是一进来就刷新，启动start()。
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(mLinearLayoutManager);

//        setAdapter();
//        setLoadMore();

//        recyclerView.setAdapter(mAdapter);
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
//        ptrFrameLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ptrFrameLayout.autoRefresh();
//            }
//        }, 100);

        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
                mPresenter.start();

            }
        });
    }

    @Override
    public void end() {
        ALog.e("结束…………………………………………");
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.refreshComplete();

            }
        }, 5000);
    }

    @Override
    public void error(Throwable t) {

    }


    protected void setToolbar(Toolbar toolbar) {
        initStateBar(toolbar);

    }

    @Override
    public void onDestroy() {
        /**
         * 记得清理adapter
         */
        super.onDestroy();
    }
}


