package com.aglhz.yicommunity.launch;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class LaunchActivity extends BaseActivity {
    private static final String TAG = LaunchActivity.class.getSimpleName();
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_launch_activity, SplashFragment.newInstance());
        }
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }

    @Override
    public void onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
        }
    }
}
