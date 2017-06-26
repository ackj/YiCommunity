package com.aglhz.yicommunity.main.services;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.main.services.view.ServicesListFragment;

/**
 * Created by leguang on 2017/6/26 0026.
 * Email：langmanleguang@qq.com
 */

public class ServicesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int serviceType = getIntent().getIntExtra(Constants.SERVICE_TYPE, 0);
        if (savedInstanceState == null) {

            loadRootFragment(R.id.fl_main_activity, ServicesListFragment.newInstance(serviceType));
        }
    }
}
