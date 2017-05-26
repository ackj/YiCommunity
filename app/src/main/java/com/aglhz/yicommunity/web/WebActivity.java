package com.aglhz.yicommunity.web;

import android.os.Bundle;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;

/**
 * 加载网页
 * <p>
 * Created by YandZD on 2017/1/5.
 */

public class WebActivity extends BaseActivity {
    private static final String TAG = WebActivity.class.getSimpleName();
    private String title;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initData();

        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_web_activity, WebFragment.newInstance(title, link));
        }
    }

    private void initData() {
        title = getIntent().getStringExtra("title");
        link = getIntent().getStringExtra("link");
        ALog.e(link);
    }
}
