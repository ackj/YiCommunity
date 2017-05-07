package com.aglhz.yicommunity.property.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.RepairBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19 15:02.
 */
public class RepairRecordRVAdapter extends BaseRecyclerViewAdapter<RepairBean, BaseViewHolder> {
    private final String TAG = RepairRecordRVAdapter.class.getSimpleName();

    public RepairRecordRVAdapter(List<RepairBean> data) {
        super(R.layout.item_rv_property_repair, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepairBean item) {
        helper.setText(R.id.tv_title, item.title)
                .setText(R.id.tv_date, item.date)
                .setText(R.id.tv_desc, item.desc)
                .setImageResource(R.id.iv_status, item.status);
    }
}
