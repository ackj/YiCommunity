package com.aglhz.yicommunity.main.picker;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.main.picker.view.CommunityPickerFragment;
import com.aglhz.yicommunity.main.picker.view.ParkPickerFragment;


/**
 * Created by Administrator on 2017/4/29 0029.
 */
public class PickerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        int intFromTo = getIntent().getIntExtra(Constants.KEY_FROM_TO, 0);
        if (savedInstanceState == null) {
            if(intFromTo == 100){
                loadRootFragment(R.id.fl_picker_activity, ParkPickerFragment.newInstance());
            }else{
                loadRootFragment(R.id.fl_picker_activity, CommunityPickerFragment.newInstance());
            }
        }
    }
}
