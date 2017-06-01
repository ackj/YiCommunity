package com.aglhz.abase.mvp.view.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;

import com.aglhz.abase.R;
import com.aglhz.abase.common.ScrollingHelper;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.abase.utils.ScreenUtils;
import com.aglhz.abase.widget.Dialog.LoadingDialog;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 所有Fragment的基类。将Fragment作为View层对象，专职处理View的试图渲染和事件。
 */
public abstract class BaseFragment<P extends BaseContract.Presenter> extends SwipeBackFragment {
    private final String TAG = BaseFragment.class.getSimpleName();
    public P mPresenter;
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        //一旦启动某个Fragment就打印Log，方便找到该类
        ALog.e(TAG);
    }

    @NonNull
    protected P createPresenter() {
        return null;
    }

    public P getPresenter() {
        return mPresenter;
    }

    public void setPresenter(@NonNull P presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void onDestroy() {
        ALog.e(TAG + "onDestroy()");
        if (mPresenter != null) {
            mPresenter.clear();
            mPresenter = null;
        }

        super.onDestroy();
    }

    public void initStateBar(View view) {
        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
            view.setPadding(view.getPaddingLeft(),
                    view.getPaddingTop() + ScreenUtils.getStatusBarHeight(_mActivity),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        // return new DefaultHorizontalAnimator();
        // 设置无动画
        return new DefaultNoAnimator();
        // 设置自定义动画
        // return new FragmentAnimator(enter,exit,popEnter,popExit);
        // 默认竖向(和安卓5.0以上的动画相同)
//        return super.onCreateFragmentAnimator();
    }

    public void initPtrFrameLayout(final PtrFrameLayout ptrFrameLayout, final View view) {
        if (ptrFrameLayout == null || view == null) {
            return;
        }

        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(_mActivity, 15F), 0, DensityUtils.dp2px(_mActivity, 10F));
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
                if (view instanceof ScrollView || view instanceof WebView) {
                    ALog.e("111111");
                    return ScrollingHelper.isScrollViewOrWebViewToTop(view);
                } else if (view instanceof RecyclerView) {
                    ALog.e("22222");
                    return ScrollingHelper.isRecyclerViewToTop((RecyclerView) view);
                } else if (view instanceof AbsListView) {
                    ALog.e("333333");
                    return ScrollingHelper.isAbsListViewToTop((AbsListView) view);
                }

                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                onRefresh();
            }
        });
    }

    public void onRefresh() {
        ALog.e("______________onRefresh________________");
    }

    public void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(_mActivity);
        }
        loadingDialog.show();
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
