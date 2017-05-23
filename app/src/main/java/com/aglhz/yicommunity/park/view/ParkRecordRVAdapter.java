package com.aglhz.yicommunity.park.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.ParkRecordListBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/5/23 0023 09:31.
 * Email: liujia95me@126.com
 */

public class ParkRecordRVAdapter extends BaseRecyclerViewAdapter<ParkRecordListBean.PackRecordBean, BaseViewHolder> {

    public ParkRecordRVAdapter() {
        super(R.layout.item_park_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, ParkRecordListBean.PackRecordBean item) {
        helper.setText(R.id.tv_plate_num_item_park_record, item.getCarNo())
                .setText(R.id.tv_into_time_item_park_record, item.getInTime())
                .setText(R.id.tv_out_time_item_park_record, item.getOutTime())
                .setText(R.id.tv_park_duration_item_park_record, item.getTotalCostTime())
                .setText(R.id.tv_park_type_item_park_record, item.getParkType());
    }
}
