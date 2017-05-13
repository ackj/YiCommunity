package com.aglhz.yicommunity.publish.view;

import android.net.Uri;
import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/8 0008 17:18.
 * Email: liujia95me@126.com
 */

public class PublishImageRVAdapter extends BaseRecyclerViewAdapter<Uri, BaseViewHolder> {

    public PublishImageRVAdapter(List<Uri> data) {
        super(R.layout.item_image_add, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Uri uri) {
        helper.addOnClickListener(R.id.iv_img_item_image_add);
        ImageView image = helper.getView(R.id.iv_img_item_image_add);
        Glide.with(BaseApplication.mContext)
                .load(uri)
                .into(image);
    }
}