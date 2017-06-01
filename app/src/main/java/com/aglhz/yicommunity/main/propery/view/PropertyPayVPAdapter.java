package com.aglhz.yicommunity.main.propery.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class PropertyPayVPAdapter extends FragmentPagerAdapter {
    private static final String TAG = PropertyPayVPAdapter.class.getSimpleName();

    public PropertyPayVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PropertyPayListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "待缴费" : "已缴费";
    }
}
