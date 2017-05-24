package com.aglhz.yicommunity.park.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/5/24 0024 19:17.
 * Email: liujia95me@126.com
 */

public class RechargeReocrdAdapter extends BaseRecyclerViewAdapter<Object, BaseViewHolder> {

    public RechargeReocrdAdapter() {
        super(R.layout.item_recharge_record);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
