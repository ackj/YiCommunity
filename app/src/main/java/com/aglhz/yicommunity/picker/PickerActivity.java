package com.aglhz.yicommunity.picker;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.picker.view.CommunityPickerFragment;
import com.aglhz.yicommunity.picker.view.ParkPickerFragment;


/**
 * Created by Administrator on 2017/4/29 0029.
 */
public class PickerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        int intFromTo = getIntent().getIntExtra(Constants.FROM_TO, 0);
        if (savedInstanceState == null) {
            if(intFromTo == 100){
                loadRootFragment(R.id.fl_picker_activity, ParkPickerFragment.newInstance());
            }else{
                loadRootFragment(R.id.fl_picker_activity, CommunityPickerFragment.newInstance());
            }
        }
    }
}
