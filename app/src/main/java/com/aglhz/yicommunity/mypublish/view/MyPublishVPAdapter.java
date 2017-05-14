package com.aglhz.yicommunity.mypublish.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class MyPublishVPAdapter extends FragmentPagerAdapter {
    private static final String TAG = MyPublishVPAdapter.class.getSimpleName();
    private String[] arrTabTitle = {"闲置交换", "拼车服务", "左邻右里"};

    public MyPublishVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ALog.e("position::" + position);
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = UnusedExchangeFragment.newInstance();
                break;
            case 1:
                break;
            case 2:
                fragment = UnusedExchangeFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        if (null != arrTabTitle && arrTabTitle.length > 0) {
            return arrTabTitle.length;
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (null != arrTabTitle && arrTabTitle.length > 0) {
            return arrTabTitle[position];
        }
        return null;
    }
}
