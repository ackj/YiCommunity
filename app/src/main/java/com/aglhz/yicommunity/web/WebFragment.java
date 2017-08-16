package com.aglhz.yicommunity.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.JavaScriptObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 负责项目中的web部分。
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
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    private String title;
    private String link;
    private Unbinder unbinder;

    public static WebFragment newInstance(String title, String link) {
        ALog.e(TAG, "link-->" + link);
        Bundle args = new Bundle();
        args.putString(Constants.KEY_TITLE, title);
        args.putString(Constants.KEY_LINK, link);

        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            title = args.getString(Constants.KEY_TITLE);
            link = args.getString(Constants.KEY_LINK);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initPtrFrameLayout(ptrFramlayout, mWebView);
        initWebView();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        if (!TextUtils.isEmpty(title)) {
            toolbarTitle.setText(title);
        }
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        toolbarMenu.setText("关闭");
        toolbarMenu.setOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @SuppressLint("SetJavaScriptEnabled")
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
                link = url;
                if (ptrFramlayout != null) {
                    ptrFramlayout.refreshComplete();
                }
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

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                ALog.e("onReceiveValue::" + message);
                return super.onJsAlert(view, url, message, result);
            }
        });
    }

    @Override
    public void onRefresh() {
        mWebView.loadUrl(link);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            if (mWebView.getParent() != null) {
                ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            }
            mWebView = null;
        }
    }
}
