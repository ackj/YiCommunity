package com.aglhz.yicommunity.main.services.view;


import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.ServicesCommodityListBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Created by leguang on 2017/6/26 0026.
 * Email：langmanleguang@qq.com
 */
public class ServicesRVAdapter extends BaseRecyclerViewAdapter<ServicesCommodityListBean.DataBean.DataListBean, BaseViewHolder> {

    public ServicesRVAdapter() {
        super(R.layout.item_rv_services);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServicesCommodityListBean.DataBean.DataListBean item) {
        helper.setText(R.id.tv_title_item_rv_services, item.getCommodityTitle())
                .setText(R.id.tv_describe_item_rv_services, item.getCommodityDesc())
                .setText(R.id.tv_cost_item_rv_services, item.getCommodityPrice())
                .setText(R.id.tv_address_item_rv_services, item.getCoverageArea());

        ImageView ivPhoto = helper.getView(R.id.iv_photo_item_rv_services);
        Glide.with(ivPhoto.getContext())
                .load(item.getCommodityUrl())
                .into(ivPhoto);
    }
}
