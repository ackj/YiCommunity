package com.aglhz.yicommunity.main.publish;

import android.os.Bundle;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.main.publish.view.ComplainFragment;
import com.aglhz.yicommunity.main.propery.view.RepairRecordFragment;

/**
 * 物业模块的父容器
 */
public class PropertyActivity extends BaseActivity {
    private static final String TAG = PropertyActivity.class.getSimpleName();
    int intFromTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        //获取上一个Activity传递过来的数据。
        initData();
        if (savedInstanceState == null) {
            switch (intFromTo) {
                case Constants.PROPERTY_REPAIR:
                    loadRootFragment(R.id.fl_property_activity, RepairRecordFragment.newInstance());
                    break;
                case Constants.COMPLAIN:
                    loadRootFragment(R.id.fl_property_activity, ComplainFragment.newInstance());
                    break;
            }
        }
    }

    private void initData() {
        intFromTo = getIntent().getIntExtra(Constants.KEY_FROM_TO, 0);
        ALog.e(intFromTo);
    }
}
