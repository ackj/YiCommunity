package com.aglhz.yicommunity.neighbour.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.CommentBean;
import com.aglhz.yicommunity.bean.NeighbourListBean;
import com.aglhz.yicommunity.preview.PreviewActivity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by leguang on 2017/4/14 0014.
 * Email：langmanleguang@qq.com
 */

public class NeighbourRVAdapter extends BaseRecyclerViewAdapter<NeighbourListBean.DataBean.MomentsListBean, BaseViewHolder> {
    private static final String TAG = NeighbourRVAdapter.class.getSimpleName();

    public NeighbourRVAdapter() {
        super(R.layout.item_moments_list);
    }

    private int type;

    @Override
    protected void convert(BaseViewHolder helper, NeighbourListBean.DataBean.MomentsListBean item) {

//        ALog.e(">>>>>>>>>>>>>>", "position:" + helper.getLayoutPosition());

        helper.setText(R.id.tv_create_at_item_moments_list, item.getCreateTime())
                .setText(R.id.tv_name_item_moments_list, item.getMember().getMemberNickName())
                .setText(R.id.tv_comment_count_item_moments_list, item.getCommentCount() + "");

        helper.addOnClickListener(R.id.ll_comment_item_moments_list)
                .addOnClickListener(R.id.tv_remove_item_moments_list)
                .addOnClickListener(R.id.tv_comment_count_item_moments_list);

        //-------------- 删除 ---------------
        TextView tvRemove = helper.getView(R.id.tv_remove_item_moments_list);
        if (type == MessageFragment.TYPE_MY_CARPOOL || type == MessageFragment.TYPE_MY_EXCHANGE || type == MessageFragment.TYPE_MY_NEIGHBOUR) {
            tvRemove.setVisibility(View.VISIBLE);
        } else {
            tvRemove.setVisibility(View.GONE);
        }

        //-------------- 当前地址 --------------
        TextView tvAddress = helper.getView(R.id.tv_location_item_moments_list);
        if (type == MessageFragment.TYPE_CARPOOL_OWNER
                || type == MessageFragment.TYPE_CARPOOL_passenger
                || type == MessageFragment.TYPE_MY_CARPOOL) {
            tvAddress.setText(item.getPublishPositionAddress());
        } else {
            tvAddress.setText(item.getCommunityName());
        }

        //--------------- 内容 ------------
        TextView tvContent = helper.getView(R.id.tv_content_item_moments_list);
        if (type == MessageFragment.TYPE_CARPOOL_OWNER
                || type == MessageFragment.TYPE_CARPOOL_passenger
                || type == MessageFragment.TYPE_MY_CARPOOL) {
            String route = item.getStartPlace() + "——" + item.getEndPlace() + "\n";
            String outTime = item.getSetOutTime() + "\n";
            String content = item.getContent();
            tvContent.setText(route + outTime + content);
        } else {
            tvContent.setText(item.getContent());
        }

        //--------------- 根据type显隐------------
        TextView tvMoney = helper.getView(R.id.tv_money_item_moments_list);
        if (type == MessageFragment.TYPE_EXCHANGE || type == MessageFragment.TYPE_MY_EXCHANGE) {
            tvMoney.setVisibility(View.VISIBLE);
            tvMoney.setText(item.getPrice() + "元");
        } else {
            tvMoney.setVisibility(View.GONE);
        }
        //------------------ 头像 -----------------
        ImageView ivAvatar = helper.getView(R.id.iv_avatar_item_moments_list);
        Glide.with(BaseApplication.mContext)
                .load(item.getMember().getAvator())
                .bitmapTransform(new CropCircleTransformation(BaseApplication.mContext))
                .into(ivAvatar);

        //------------------- 照片 ----------------
        RecyclerView recyclerView = helper.getView(R.id.rv_pics_item_moments_list);
        if (item.getPics().size() <= 0 || item.getPics().get(0).getType() != 1) {
            recyclerView.setVisibility(View.GONE);
        } else {
            ALog.e("pics size:" + item.getPics().size());
            recyclerView.setVisibility(View.VISIBLE);
            ImagesGridRVAdapter adapter = new ImagesGridRVAdapter(item.getPics());
            adapter.setOnItemChildClickListener((adapter1, view, position) -> {
                Context context = BaseApplication.mContext;
                Intent intent = new Intent(context, PreviewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                ArrayList<String> picsUrls = new ArrayList<>();
                for (int i = 0; i < item.getPics().size(); i++) {
                    picsUrls.add(item.getPics().get(i).getUrl());
                }
                bundle.putStringArrayList("picsList", picsUrls);
                intent.putExtra("pics", bundle);
                intent.putExtra("position", position);
                context.startActivity(intent);
                return false;
            });
            int spanCount = item.getPics().size() < 3 ? item.getPics().size() : 3;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(BaseApplication.mContext, spanCount) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        }

        //------------------- 评论 ----------------
        LinearLayout commentsLayout = helper.getView(R.id.ll_comment_item_moments_list);

        if (item.getCommentCount() <= 0) {
            commentsLayout.setVisibility(View.GONE);
        } else {
            commentsLayout.removeAllViews();
            commentsLayout.setVisibility(View.VISIBLE);
            List<CommentBean> commentList = item.getCommentList();

            for (int i = 0; i < commentList.size(); i++) {
                CommentBean commentBean = commentList.get(i);
                String name = commentBean.getMember().getMemberNickName();
                String content = commentBean.getContent();
                String text = name + ":" + content;

                TextView tvComment = new TextView(BaseApplication.mContext);
                tvComment.setTextSize(13);
                tvComment.setTextColor(0xBB222222);

                LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int marginValue = DensityUtils.dp2px(BaseApplication.mContext, 1);
                tvLp.setMargins(marginValue, marginValue, marginValue, marginValue);
                tvComment.setLayoutParams(tvLp);

                SpannableString nameSpan = new SpannableString(text);
                nameSpan.setSpan(new ForegroundColorSpan(0xFF5CA0C3), 0, (name + ":").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvComment.setText(nameSpan);

                commentsLayout.addView(tvComment);
            }
        }
    }

    public void setType(int type) {
        this.type = type;
    }
}
