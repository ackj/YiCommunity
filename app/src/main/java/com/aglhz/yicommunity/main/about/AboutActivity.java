package com.aglhz.yicommunity.main.about;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;

/**
 * [关于我们]的父容器。
 */
public class AboutActivity extends BaseActivity {
    private static final String TAG = AboutActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, AboutUsFragment.newInstance());
        }
    }
}
