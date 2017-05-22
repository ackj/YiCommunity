package com.aglhz.yicommunity.smarthome.view;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.GoodsBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/5/22 0022 14:48.
 * Email: liujia95me@126.com
 */

public class SmartHomeGoodsRVAdapter extends BaseRecyclerViewAdapter<GoodsBean.DataBean, BaseViewHolder> {

    public SmartHomeGoodsRVAdapter() {
        super(R.layout.item_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean item) {
        ImageView image = helper.getView(R.id.iv_cover_item_goods);

        int width = (DensityUtils.getDisplayWidth(BaseApplication.mContext) - DensityUtils.dp2px(BaseApplication.mContext, 120)) / 2;
        int height = width;
        image.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        helper.setText(R.id.tv_name_item_goods, item.getName())
                .setText(R.id.tv_money_item_goods, "￥:" + item.getPrice());

        Glide.with(BaseApplication.mContext)
                .load(item.getPictureURL())
                .into(image);
    }
}
