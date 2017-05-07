package com.aglhz.yicommunity.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.JavaScriptObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class WebFragment extends BaseFragment {
    private static final String TAG = WebFragment.class.getSimpleName();
    @BindView(R.id.wv_web_fragment)
    WebView mWebView;
    @BindView(R.id.ptr_web_fragment)
    PtrFrameLayout ptrFramlayout;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String title;
    private String link;

    public static WebFragment newInstance(String title, String link) {
        Bundle args = new Bundle();
        args.putString(Constants.WEB_TITLE, title);
        args.putString(Constants.WEB_LINK, link);

        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            title = args.getString(Constants.WEB_TITLE);
            link = args.getString(Constants.WEB_LINK);
//            link = "http://192.168.2.111:8000/sub_ysq_h5/m/html/report.html";
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initPtrFrameLayout(ptrFramlayout);
        initWebView();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText(title);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initWebView() {
        mWebView.clearCache(true);
        mWebView.clearHistory();

        WebSettings webSettings = mWebView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //webview自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);

        mWebView.addJavascriptInterface(new JavaScriptObject(_mActivity), "android");

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ptrFramlayout.refreshComplete();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                link = url;
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, true);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });

        mWebView.loadUrl(link);
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
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh();
            }
        }, 100);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //判断是否滑动到顶部。
                return mWebView != null && mWebView.getScrollY() == 0;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
                mWebView.loadUrl(link);
            }
        });
//        ptrFrameLayout.autoRefresh();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            pop();
            _mActivity.finish();
        }
        return true;
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            if (mWebView.getParent() != null) {
                ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            }
            mWebView = null;
        }
        super.onDestroy();
    }
}
