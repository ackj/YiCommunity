package com.aglhz.yicommunity.main.house;

import android.os.Bundle;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.main.house.view.AddHouseFragment;
import com.aglhz.yicommunity.main.house.view.HouseRightsFragment;

public class HouseActivity extends BaseActivity {
    private static final String TAG = HouseActivity.class.getSimpleName();
    private int intFromTo;
    private String fid;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        //获取上一个Activity传递过来的数据。
        initData();
        if (savedInstanceState == null) {
            switch (intFromTo) {
                case Constants.HOUSE_RIGHTS:
                    loadRootFragment(R.id.fl_house_activity, HouseRightsFragment.newInstance(fid, address));
                    ALog.e("Constants.HOUSE_RIGHTS::" + Constants.HOUSE_RIGHTS);
                    break;
                case Constants.ADD_HOUSE:
                    loadRootFragment(R.id.fl_house_activity, AddHouseFragment.newInstance(address));
                    ALog.e("Constants.ADD_HOUSE::" + Constants.ADD_HOUSE);

                    break;
            }
        }
    }

    private void initData() {
        intFromTo = getIntent().getIntExtra(Constants.KEY_FROM_TO, 0);
        fid = getIntent().getStringExtra(Constants.KEY_FID);
        address = getIntent().getStringExtra(Constants.KEY_ADDRESS);
        ALog.e(intFromTo);
        ALog.e(fid);
    }
}
