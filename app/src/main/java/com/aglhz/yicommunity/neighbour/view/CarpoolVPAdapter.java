package com.aglhz.yicommunity.neighbour.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Author: LiuJia on 2017/5/16 0016 17:21.
 * Email: liujia95me@126.com
 */

public class CarpoolVPAdapter extends FragmentPagerAdapter {

    public CarpoolVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        int type = position == 0 ? MessageFragment.TYPE_CARPOOL_OWNER : MessageFragment.TYPE_CARPOOL_passenger;
        return MessageFragment.newInstance(type);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "找车主" : "找乘客";
    }
}
