package com.aglhz.yicommunity.house;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.house.view.HouseFragment;

public class HouseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_house_activity, HouseFragment.newInstance());
        }
    }
}
