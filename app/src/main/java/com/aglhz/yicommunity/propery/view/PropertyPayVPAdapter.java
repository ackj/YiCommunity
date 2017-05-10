package com.aglhz.yicommunity.propery.view;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.PropertyPayBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: LiuJia on 2017/5/7 0007 20:37.
 * Email: liujia95me@126.com
 */

public class PropertyPayVPAdapter extends PagerAdapter {

    private PropertyPayBean propertyPayBean;

    public void setPropertyPayBean(PropertyPayBean propertyPayBean) {
        this.propertyPayBean = propertyPayBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(BaseApplication.mContext).inflate(R.layout.recyclerview, null, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(BaseApplication.mContext));
        List<PropertyPayBean.DataBean.ObpptBillsBean> data = new ArrayList<>();
        if (propertyPayBean != null) {
            data = propertyPayBean.getData().getObpptBills();
        }
        ALog.d("PropertyPayVPAdapter", "data size:" + data.size());
        PropertyPayRVAdapter adapter = new PropertyPayRVAdapter(data);
        recyclerView.setAdapter(adapter);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "待缴费" : "已缴费";
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
