package com.aglhz.yicommunity.main.propery.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.PropertyPayDetailBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by leguang on 2017/4/29 0029.
 * Email：langmanleguang@qq.com
 */

public class PropertyNotPayDetailRVAdapter extends BaseRecyclerViewAdapter<PropertyPayDetailBean.DataBean.PptBillDetsBean, BaseViewHolder> {

    public PropertyNotPayDetailRVAdapter() {
        super(R.layout.item_rv_property_not_pay);
    }

    @Override
    protected void convert(BaseViewHolder helper, PropertyPayDetailBean.DataBean.PptBillDetsBean item) {
        helper.setText(R.id.tv_sum_item_rv_property_not_pay_detail, item.getITotalAmt() + "元")
                .setText(R.id.tv_name_item_rv_property_not_pay_detail, item.getIName())
                .setText(R.id.tv_description_item_rv_property_not_pay_detail, item.getDes());
    }
}
