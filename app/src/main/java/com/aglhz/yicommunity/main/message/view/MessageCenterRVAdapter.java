package com.aglhz.yicommunity.main.message.view;


import android.view.View;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.MessageCenterBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by leguang on 2017/4/24 0024.
 * Email：langmanleguang@qq.com
 */

public class MessageCenterRVAdapter extends BaseRecyclerViewAdapter<MessageCenterBean.DataBean.MemNewsBean, BaseViewHolder> {
    public MessageCenterRVAdapter() {
        super(R.layout.item_message_center_fragment);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageCenterBean.DataBean.MemNewsBean item) {
        helper.getView(R.id.v_mark_item_message_center_fragment)
                .setVisibility(item.isRead() ? View.INVISIBLE : View.VISIBLE);

        helper.addOnClickListener(R.id.ll_layout_item_message_center_fragment)
                .setText(R.id.tv_title_item_message_center_fragment, item.getTitle())
                .setText(R.id.tv_description_item_message_center_fragment, item.getDes())
                .setText(R.id.tv_date_item_message_center_fragment, item.getOpTime().substring(0, 10));
    }
}
