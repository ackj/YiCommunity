package com.aglhz.yicommunity.main.services.view;

import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.ServiceDetailBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/7/2 0002 12:06.
 * Email: liujia95me@126.com
 */

public class ServiceDetailSceneRVAdapter extends BaseRecyclerViewAdapter<ServiceDetailBean.DataBean.MerchantSceneBean,BaseViewHolder> {

    public ServiceDetailSceneRVAdapter() {
        super(R.layout.item_rv_service_detail_pics);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServiceDetailBean.DataBean.MerchantSceneBean item) {
        ImageView ivPics = helper.getView(R.id.iv_pic);
        Glide.with(BaseApplication.mContext)
                .load(item.getUrl())
                .error(R.drawable.ic_default_img_120px)
                .placeholder(R.drawable.ic_default_img_120px)
                .into(ivPics);
    }
}
