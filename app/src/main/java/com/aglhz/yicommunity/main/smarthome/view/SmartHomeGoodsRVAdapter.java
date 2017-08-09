package com.aglhz.yicommunity.main.smarthome.view;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.GoodsBean;
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
        //图片宽高需要动态设置
        ImageView image = helper.getView(R.id.iv_cover_item_goods);
        int width = (DensityUtils.getDisplayWidth(App.mContext) - DensityUtils.dp2px(App.mContext, 120)) / 2;
        int height = width;
        image.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        helper.setText(R.id.tv_name_item_goods, item.getName())
                .setText(R.id.tv_money_item_goods, "￥:" + item.getPrice());

        Glide.with(App.mContext)
                .load(item.getPictureURL())
                .into(image);
    }
}
