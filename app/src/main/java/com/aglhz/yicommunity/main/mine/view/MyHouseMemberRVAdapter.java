package com.aglhz.yicommunity.main.mine.view;

import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.MyHousesBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Author: LiuJia on 2017/4/20 13:53.
 * Email: liujia95me@126.com
 */
public class MyHouseMemberRVAdapter extends BaseRecyclerViewAdapter<MyHousesBean.DataBean.AuthBuildingsBean.MembersBean, BaseViewHolder> {

    public MyHouseMemberRVAdapter() {
        super(R.layout.item_rv_member);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyHousesBean.DataBean.AuthBuildingsBean.MembersBean item) {

        Glide.with(BaseApplication.mContext)
                .load(item.getIcon())
                .bitmapTransform(new CropCircleTransformation(BaseApplication.mContext))
                .into((ImageView) helper.getView(R.id.iv_avatar));

        helper.setText(R.id.tv_name, item.getMname())
                .setText(R.id.tv_role, 1 == item.getIsOwner() ? "业主" : "成员");
    }
}