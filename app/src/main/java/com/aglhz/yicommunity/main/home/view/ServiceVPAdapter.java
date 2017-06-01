package com.aglhz.yicommunity.main.home.view;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.ServiceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： Administrator on 2017/5/3 0003.
 * Email： liujia95me@126.com
 */
public class ServiceVPAdapter extends PagerAdapter {

    private List<ServiceBean> mDatas = new ArrayList<>();

    public ServiceVPAdapter(List<ServiceBean> datas) {
        mDatas = datas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_vp_service_item, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(position);
            }
        });
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title_item_vp_door2door_service);
        TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc_item_vp_door2door_service);
        LinearLayout llVpDoor2door = (LinearLayout) view.findViewById(R.id.ll_item_vp_door2door_service);

        tvTitle.setText(mDatas.get(position).title);
        tvDesc.setText(mDatas.get(position).desc);
        llVpDoor2door.setBackgroundResource(mDatas.get(position).imgRes);
        container.addView(view);
        return view;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
