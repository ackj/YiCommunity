package com.aglhz.yicommunity.main.home.view;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.ServiceBean;
import com.aglhz.yicommunity.entity.bean.ServicesTypesBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： Administrator on 2017/5/3 0003.
 * Email： liujia95me@126.com
 */
public class ServiceVPAdapter extends PagerAdapter {

    private List<ServiceBean> serviceBeans = new ArrayList<>();

    private List<ServicesTypesBean.DataBean.ClassifyListBean> classifyListBeans = new ArrayList<>();

    public ServiceVPAdapter(List<ServicesTypesBean.DataBean.ClassifyListBean> classifyListBeans) {
        this.classifyListBeans = classifyListBeans;
        serviceBeans.add(new ServiceBean("家政服务", "您家里的一切，让我来搞定！", R.drawable.bg_housekeeping_900px_540px));
        serviceBeans.add(new ServiceBean("家居维修", "专业品质服务，大可放心！", R.drawable.bg_homemaintenanc_900px_540px));
        serviceBeans.add(new ServiceBean("送水上门", "社区周边送水上门服务！", R.drawable.bg_watersupply_900px_540px));
    }

    public List<ServiceBean> getServiceBeans() {
        return serviceBeans;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int finalPosition = position % (classifyListBeans == null || classifyListBeans.isEmpty() ?
                serviceBeans.size() : classifyListBeans.size());

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_vp_service_item, container, false);
        view.setOnClickListener(v -> {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(finalPosition);
        });
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title_item_vp_door2door_service);
        TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc_item_vp_door2door_service);
        ImageView ivImage = (ImageView) view.findViewById(R.id.iv_image_item_vp_door2door_service);

        if (classifyListBeans == null || classifyListBeans.isEmpty()) {
            tvTitle.setText(serviceBeans.get(finalPosition).title);
            tvDesc.setText(serviceBeans.get(finalPosition).desc);
            Glide.with(container.getContext())
                    .load(serviceBeans.get(finalPosition).imgRes)
                    .into(ivImage);
        } else {
            ivImage.setVisibility(View.VISIBLE);
            Glide.with(container.getContext())
                    .load(classifyListBeans.get(finalPosition).getClassifyPhotoUrl())
                    .into(ivImage);
            ServicesTypesBean.DataBean.ClassifyListBean classifyListBean = classifyListBeans.get(finalPosition);
            tvTitle.setText(classifyListBean.getName());
            tvDesc.setText(classifyListBean.getDesc());
        }

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
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
