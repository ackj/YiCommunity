package com.aglhz.yicommunity.main.services.view;


import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.ServiceDetailBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by leguang on 2017/6/26 0026.
 * Email：langmanleguang@qq.com
 */
public class ServicesDetailRemarkRVAdapter extends BaseRecyclerViewAdapter<ServiceDetailBean.DataBean.CommorityCommentBean, BaseViewHolder> {

    public ServicesDetailRemarkRVAdapter() {
        super(R.layout.item_rv_remark);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServiceDetailBean.DataBean.CommorityCommentBean item) {
        //——————————————给哪些子View添加事件————————————————
        helper.addOnClickListener(R.id.iv_head_item_rv_remark)
                .addOnClickListener(R.id.ll_comment_item_rv_remark)
                .addOnClickListener(R.id.tv_comment_count_item_rv_remark);

        //——————————————头像————————————————
        ImageView ivPhoto = helper.getView(R.id.iv_head_item_rv_remark);
        Glide.with(ivPhoto.getContext())
                .load(item.getMember().getAvator())
                .error(R.drawable.ic_default_img_120px)
                .placeholder(R.drawable.ic_default_img_120px)
                .bitmapTransform(new CropCircleTransformation(BaseApplication.mContext))
                .into(ivPhoto);


        helper.setText(R.id.tv_name_item_rv_remark, item.getMember().getMemberNickName())
                .setText(R.id.tv_content_item_rv_remark, item.getContent())
                .setText(R.id.tv_comment_count_item_rv_remark, item.getCommentReplyList().size() + "")
                .setText(R.id.tv_time_item_rv_remark, item.getCreateTime().substring(0, 16));

        //----------------- 评论 ------------------
        LinearLayout commentsLayout = helper.getView(R.id.ll_comment_item_rv_remark);

        if (item.getCommentReplyList().isEmpty()) {
            commentsLayout.setVisibility(View.GONE);
        } else {
            commentsLayout.removeAllViews();
            commentsLayout.setVisibility(View.VISIBLE);
            List<ServiceDetailBean.DataBean.CommorityCommentBean.CommentReplyListBean> replyList = item.getCommentReplyList();

            for (int i = 0; i < replyList.size(); i++) {
                ServiceDetailBean.DataBean.CommorityCommentBean.CommentReplyListBean replyBean = replyList.get(i);
                String name = replyBean.getMember().getMemberNickName();
                String content = replyBean.getContent();
                String text = name + ":" + content;

                TextView tvComment = new TextView(BaseApplication.mContext);
                tvComment.setTextSize(13);
                tvComment.setTextColor(0xBB222222);

                LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int marginValue = DensityUtils.dp2px(BaseApplication.mContext, 1);
                tvLp.setMargins(marginValue, marginValue, marginValue, marginValue);
                tvComment.setLayoutParams(tvLp);

                SpannableString nameSpan = new SpannableString(text);
                nameSpan.setSpan(new ForegroundColorSpan(0xFF5CA0C3), 0, name.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvComment.setText(nameSpan);

                commentsLayout.addView(tvComment);
            }
        }

    }
}
