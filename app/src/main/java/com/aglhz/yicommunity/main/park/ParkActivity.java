package com.aglhz.yicommunity.main.park;

import android.os.Bundle;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.main.park.view.CarCardFragment;
import com.aglhz.yicommunity.main.park.view.CarCardTransactFragment;
import com.aglhz.yicommunity.main.park.view.ParkRecordFragment;

/**
 * 停车场模块的父容器
 */
public class ParkActivity extends BaseActivity {
    private static final String TAG = ParkActivity.class.getSimpleName();
    int intFromTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取上一个Activity传递过来的数据。
        initData();
        if (savedInstanceState == null) {
            switch (intFromTo) {
                case Constants.MY_CARD:
                    loadRootFragment(R.id.fl_main_activity, CarCardFragment.newInstance());
                    break;
                case Constants.PARKING_RECORD:
                    loadRootFragment(R.id.fl_main_activity, ParkRecordFragment.newInstance());
                    break;
                case Constants.CARD_TRANSACT:
                    //该小区目前
                    loadRootFragment(R.id.fl_main_activity, CarCardTransactFragment.newInstance());
                    break;
            }
        }
    }

    private void initData() {
        intFromTo = getIntent().getIntExtra(Constants.KEY_FROM_TO, 0);
        ALog.e(intFromTo);
    }
}
