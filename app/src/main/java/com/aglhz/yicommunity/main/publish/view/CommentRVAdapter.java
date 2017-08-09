package com.aglhz.yicommunity.main.publish.view;

import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.abase.utils.DateUtils;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.CommentBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Author: LiuJia on 2017/5/11 0011 16:10.
 * Email: liujia95me@126.com
 */

public class CommentRVAdapter extends BaseRecyclerViewAdapter<CommentBean, BaseViewHolder> {

    public CommentRVAdapter() {
        super(R.layout.item_comment, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean item) {
        helper.setText(R.id.tv_username_item_comment, item.getMember().getMemberNickName())
                .setText(R.id.tv_content_item_comment, item.getContent())
                .setText(R.id.tv_time_item_comment, DateUtils.formatCommentDate(item.getCreateTime()));

        ImageView ivHead = helper.getView(R.id.iv_head_item_comment);
        Glide.with(App.mContext)
                .load(item.getMember().getAvator())
                .placeholder(R.drawable.ic_default_head_image_200px)
                .bitmapTransform(new CropCircleTransformation(App.mContext))
                .into(ivHead);
    }
}
