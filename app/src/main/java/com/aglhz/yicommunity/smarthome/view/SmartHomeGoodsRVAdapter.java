package com.aglhz.yicommunity.smarthome.view;

import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.GoodsBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/5/22 0022 14:48.
 * Email: liujia95me@126.com
 */

public class SmartHomeGoodsRVAdapter extends BaseRecyclerViewAdapter<GoodsBean.DataBean,BaseViewHolder> {

    public SmartHomeGoodsRVAdapter() {
        super(R.layout.item_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean item) {
        ImageView image = helper.getView(R.id.iv_cover_item_goods);

    }
}
