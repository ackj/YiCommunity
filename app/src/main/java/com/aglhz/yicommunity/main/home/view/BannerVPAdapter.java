package com.aglhz.yicommunity.main.home.view;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.bean.BannerBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: LiuJia on 2017/5/7 0007 17:13.
 * Email: liujia95me@126.com
 */

public class BannerVPAdapter extends PagerAdapter {

    List<BannerBean.DataBean.AdvsBean> datas = new ArrayList<>();

    public BannerVPAdapter(List<BannerBean.DataBean.AdvsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(BaseApplication.mContext);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(BaseApplication.mContext)
                .load(datas.get(position).getCover())
                .into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
