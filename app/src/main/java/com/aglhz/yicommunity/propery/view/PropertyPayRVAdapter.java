package com.aglhz.yicommunity.propery.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/7 0007 20:49.
 * Email: liujia95me@126.com
 */

public class PropertyPayRVAdapter extends BaseRecyclerViewAdapter<PropertyPayBean.DataBean.ObpptBillsBean, BaseViewHolder> {

    public PropertyPayRVAdapter(List<PropertyPayBean.DataBean.ObpptBillsBean> data) {
        super(R.layout.item_property_pay, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PropertyPayBean.DataBean.ObpptBillsBean item) {
        helper.setText(R.id.tv_price_item_property_pay, item.getTotalAmt() + "元")
                .setText(R.id.tv_house_name_item_property_pay, item.getBName());
    }
}
