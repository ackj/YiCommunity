package com.aglhz.yicommunity.message.view;

import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/19 0019 11:00.
 * Email: liujia95me@126.com
 */

public class RepairDetailPicsRVAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder> {

    public RepairDetailPicsRVAdapter(List<String> datas) {
        super(R.layout.item_image, datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.image_item_image);
        Glide.with(BaseApplication.mContext)
                .load(item)
                .into(imageView);
    }
}
