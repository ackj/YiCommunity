package com.aglhz.yicommunity.web;

import android.os.Bundle;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;

/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 *
 * 负责项目中的web部分。
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
        title = getIntent().getStringExtra(Constants.KEY_TITLE);
        link = getIntent().getStringExtra(Constants.KEY_LINK);
        ALog.e(link);
    }
}
