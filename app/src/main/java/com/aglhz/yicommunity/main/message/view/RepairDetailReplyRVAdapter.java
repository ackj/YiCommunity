package com.aglhz.yicommunity.main.message.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.RepairDetailBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/19 0019 11:50.
 * Email: liujia95me@126.com
 */

public class RepairDetailReplyRVAdapter extends BaseRecyclerViewAdapter<RepairDetailBean.ReplysBean, BaseViewHolder> {

    public RepairDetailReplyRVAdapter(List<RepairDetailBean.ReplysBean> data) {
        super(R.layout.item_rv_complain_reply, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepairDetailBean.ReplysBean item) {
        helper.setText(R.id.tv_content_item_rv_complain_reply, item.getDes()).
                setText(R.id.tv_time_item_rv_complain_reply, item.getCtime());
    }
}
