package com.aglhz.yicommunity.main.mypublish;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.main.mypublish.view.MyPublishFragment;

public class MyPublishActivity extends BaseActivity {
    private static final String TAG = MyPublishActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, MyPublishFragment.newInstance());
        }
    }
}
