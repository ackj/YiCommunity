package com.aglhz.yicommunity.mypublish.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.mypublish.contract.CarShareContract;
import com.aglhz.yicommunity.mypublish.presenter.CarSharePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Author: LiuJia on 2017/4/21 19:46.
 * Email: liujia95me@126.com
 */
public class CarShareFragment extends BaseFragment<CarShareContract.Presenter> implements CarShareContract.View {
    private static final String TAG = CarShareFragment.class.getSimpleName();
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private View mFooterLoading, mFooterNotLoading, mFooterError;
    private CarshareRVAdapter mAdapter;
    private int lastPosition;
    private boolean isLoading;


    public static CarShareFragment newInstance() {
        return new CarShareFragment();
    }

    @NonNull
    @Override
    protected CarShareContract.Presenter createPresenter() {
        return new CarSharePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFooterLoading = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_loading, (ViewGroup) recyclerView.getParent(), false);
        mFooterNotLoading = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_not_loading, (ViewGroup) recyclerView.getParent(), false);
        mFooterError = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_error, (ViewGroup) recyclerView.getParent(), false);
        initRecyclerview();
        initPtrFrameLayout();
    }

    private void initRecyclerview() {
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new CarshareRVAdapter();
        mFooterError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mAdapter.removeAllFooterView();
//                mAdapter.addFooterView(mFooterLoading);
                mPresenter.requestData(1);
            }
        });

//        recyclerView.setAdapter(mAdapter);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE
////                        && lastPosition + 1 == mAdapter.getItemCount()
//                        //目的是判断第一页数据条数是否满足一整页。
////                        && mAdapter.getItemCount() >= Constants.PAGE_SIZE) {
//                    if (!isLoading) {
//                        isLoading = true;
//                        mPresenter.requestData(1);
//                        ALog.e("加载更多……加载更多……加载更多……加载更多……加载更多……加载更多……");
//                    }
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                ALog.e("dy::" + dy);
//                lastPosition = mLinearLayoutManager.findLastVisibleItemPosition();
//            }
//        });
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
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh(true);
            }
        }, 100);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //判断是否滑动到顶部。
                return ScrollingHelper.isRecyclerViewToTop(recyclerView);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了…………");
//                mPresenter.start();

            }
        });
    }


//    @Override
//    public void end() {
//        //此处一定要先清除之前加载的FooterView，否则会报错。
//        mAdapter.removeAllFooterView();
//        mAdapter.addFooterView(mFooterNotLoading);
//        mAdapter.notifyDataSetChanged();//这里必须要notify一下，否则会报错，因为我修改了footer。
//    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
    }

//    @Override
//    public void responseData(List<CarpoolServiceListBean.DataBean.CarpoolListBean> list, int page) {
//        if (page == 0) {
//            mAdapter.setNewData(list);
//        } else {
//            mAdapter.addData(list);
//        }
//        isLoading = false;
//    }

//    @Override
//    public void addLoadingFooter() {
//        mAdapter.removeAllFooterView();
//        mAdapter.addFooterView(mFooterLoading);
//    }
//
//    @Override
//    public void addErrorFooter() {
//        mAdapter.removeAllFooterView();
//        mAdapter.addFooterView(mFooterError);
//    }

    @Override
    public void addLoadingFooter() {

    }

    @Override
    public void addErrorFooter() {

    }

    @Override
    public void empty() {
        //把页面状态设置成空。
    }

    @Override
    public void loadComplete() {
        ptrFrameLayout.refreshComplete();
    }
}
