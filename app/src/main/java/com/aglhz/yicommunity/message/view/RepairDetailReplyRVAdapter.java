package com.aglhz.yicommunity.message.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.RepairDetailBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/19 0019 11:50.
 * Email: liujia95me@126.com
 */

public class RepairDetailReplyRVAdapter extends BaseRecyclerViewAdapter<RepairDetailBean.ReplysBean, BaseViewHolder> {

    public RepairDetailReplyRVAdapter(List<RepairDetailBean.ReplysBean> data) {
        super(R.layout.item_title_time, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepairDetailBean.ReplysBean item) {
        helper.setText(R.id.tv_title_item_title_time, item.getDes()).
                setText(R.id.tv_time_item_title_time, item.getCtime());
    }
}
