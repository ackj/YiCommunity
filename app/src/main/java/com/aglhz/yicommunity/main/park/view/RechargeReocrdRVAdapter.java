package com.aglhz.yicommunity.main.park.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.MonthCardBillListBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/5/24 0024 19:17.
 * Email: liujia95me@126.com
 */

public class RechargeReocrdRVAdapter extends BaseRecyclerViewAdapter<MonthCardBillListBean.DataBean.MonthCardBillBean, BaseViewHolder> {

    public RechargeReocrdRVAdapter() {
        super(R.layout.item_recharge_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, MonthCardBillListBean.DataBean.MonthCardBillBean item) {
        helper.setText(R.id.tv_plate_num, item.getCarNo())
                .setText(R.id.tv_park_address, item.getParkPlace().getName())
                .setText(R.id.tv_month_count, item.getMonthCount()+"个月")
                .setText(R.id.tv_start_time, item.getStartTime())
                .setText(R.id.tv_end_time, item.getEndTime())
                .setText(R.id.tv_recharge_money, item.getMoney() + "元")
                .setText(R.id.tv_recharge_date,item.getPayTime());
    }
}
