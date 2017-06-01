package com.aglhz.yicommunity.main.house.view;

import android.widget.ImageView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.HouseRightsBean;
import com.aglhz.yicommunity.common.UserHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Author: LiuJia on 2017/4/20 13:53.
 * Email: liujia95me@126.com
 */
public class MemberRVAdapter extends BaseRecyclerViewAdapter<HouseRightsBean.DataBean, BaseViewHolder> {

    public MemberRVAdapter() {
        super(R.layout.item_rv_member);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseRightsBean.DataBean item) {

        Glide.with(BaseApplication.mContext)
                .load(item.getMember().getIcon())
                .bitmapTransform(new CropCircleTransformation(BaseApplication.mContext))
                .into((ImageView) helper.getView(R.id.iv_avatar));

        helper.setText(R.id.tv_name, item.getMember().getMname())
                .setText(R.id.tv_role, "业主")
                .setVisible(R.id.tv_role, 1 == (item.getMember().getIsOwner()));
    }
}
