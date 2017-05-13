package com.aglhz.yicommunity.common;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;

import java.lang.reflect.Field;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/10/28 上午2:26
 * 描述:
 */
public class ScrollingHelper {

    private ScrollingHelper() {
    }

    public static boolean isScrollViewOrWebViewToTop(View view) {
        return view != null && view.getScrollY() == 0;
    }

    public static boolean isAbsListViewToTop(AbsListView absListView) {
        if (absListView != null) {
            int firstChildTop = 0;
            if (absListView.getChildCount() > 0) {
                // 如果AdapterView的子控件数量不为0，获取第一个子控件的top
                firstChildTop = absListView.getChildAt(0).getTop() - absListView.getPaddingTop();
            }
            if (absListView.getFirstVisiblePosition() == 0 && firstChildTop == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRecyclerViewToTop(RecyclerView recyclerView) {
        if (recyclerView != null) {
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager == null) {
                return true;
            }
            if (manager.getItemCount() == 0) {
                return true;
            }

            if (manager instanceof LinearLayoutManager) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) manager;

                int firstChildTop = 0;
                if (recyclerView.getChildCount() > 0) {
                    // 处理item高度超过一屏幕时的情况
                    View firstVisibleChild = recyclerView.getChildAt(0);
                    if (firstVisibleChild != null && firstVisibleChild.getMeasuredHeight() >= recyclerView.getMeasuredHeight()) {
                        if (android.os.Build.VERSION.SDK_INT < 14) {
                            return !(ViewCompat.canScrollVertically(recyclerView, -1) || recyclerView.getScrollY() > 0);
                        } else {
                            return !ViewCompat.canScrollVertically(recyclerView, -1);
                        }
                    }

                    // 如果RecyclerView的子控件数量不为0，获取第一个子控件的top

                    // 解决item的topMargin不为0时不能触发下拉刷新
                    View firstChild = recyclerView.getChildAt(0);
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) firstChild.getLayoutParams();
                    firstChildTop = firstChild.getTop() - layoutParams.topMargin - getRecyclerViewItemTopInset(layoutParams) - recyclerView.getPaddingTop();
                }

                if (layoutManager.findFirstCompletelyVisibleItemPosition() < 1 && firstChildTop == 0) {
                    return true;
                }
            } else if (manager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) manager;

                int[] out = layoutManager.findFirstCompletelyVisibleItemPositions(null);
                if (out[0] < 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 通过反射获取RecyclerView的item的topInset
     *
     * @param layoutParams
     * @return
     */
    private static int getRecyclerViewItemTopInset(RecyclerView.LayoutParams layoutParams) {
        try {
            Field field = RecyclerView.LayoutParams.class.getDeclaredField("mDecorInsets");
            field.setAccessible(true);
            // 开发者自定义的滚动监听器
            Rect decorInsets = (Rect) field.get(layoutParams);
            return decorInsets.top;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static boolean isWebViewToBottom(WebView webView) {
        return webView != null && webView.getContentHeight() * webView.getScale() == (webView.getScrollY() + webView.getMeasuredHeight());
    }

    public static boolean isScrollViewToBottom(ScrollView scrollView) {
        if (scrollView != null) {
            int scrollContentHeight = scrollView.getScrollY() + scrollView.getMeasuredHeight() - scrollView.getPaddingTop() - scrollView.getPaddingBottom();
            int realContentHeight = scrollView.getChildAt(0).getMeasuredHeight();
            if (scrollContentHeight == realContentHeight) {
                return true;
            }
        }
        return false;
    }



    public static void scrollToBottom(final ScrollView scrollView) {
        if (scrollView != null) {
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }

    public static void scrollToBottom(final AbsListView absListView) {
        if (absListView != null) {
            if (absListView.getAdapter() != null && absListView.getAdapter().getCount() > 0) {
                absListView.post(new Runnable() {
                    @Override
                    public void run() {
                        absListView.setSelection(absListView.getAdapter().getCount() - 1);
                    }
                });
            }
        }
    }

    public static void scrollToBottom(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            if (recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() > 0) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                    }
                });
            }
        }
    }
}