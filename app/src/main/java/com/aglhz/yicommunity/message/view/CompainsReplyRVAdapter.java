package com.aglhz.yicommunity.message.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/5/18 0018 17:38.
 * Email: liujia95me@126.com
 */

public class CompainsReplyRVAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder> {

    public CompainsReplyRVAdapter() {
        super(R.layout.item_title_time);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title_item_title_time,item);
    }


}
