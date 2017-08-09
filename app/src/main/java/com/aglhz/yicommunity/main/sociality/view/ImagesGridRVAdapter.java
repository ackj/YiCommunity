package com.aglhz.yicommunity.main.sociality.view;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.SocialityListBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/10 0010 10:27.
 * Email: liujia95me@126.com
 */

public class ImagesGridRVAdapter extends BaseRecyclerViewAdapter<SocialityListBean.DataBean.PicsBean, BaseViewHolder> {

    int displayWidth = DensityUtils.getDisplayWidth(App.mContext);

    @Override
    public int getItemCount() {
        return getData().size() > 3 ? 3 : getData().size();
    }

    public ImagesGridRVAdapter(List<SocialityListBean.DataBean.PicsBean> data) {
        super(R.layout.item_image, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SocialityListBean.DataBean.PicsBean item) {
        helper.addOnClickListener(R.id.image_item_image);
        ImageView iv = helper.getView(R.id.image_item_image);

        int width;
        int height;
        int size = getData().size();
        if (size >= 3) {
            width = (displayWidth - DensityUtils.dp2px(App.mContext, 50)) / 3;
            height = width;
        } else if (size == 2) {
            width = (displayWidth - DensityUtils.dp2px(App.mContext, 40)) / 2;
            height = width;
        } else {
            width = displayWidth - DensityUtils.dp2px(App.mContext, 30);
            height = width / 2;
        }

        iv.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        Glide.with(App.mContext)
                .load(item.getUrl())
                .error(R.drawable.ic_default_img_120px)
                .placeholder(R.drawable.ic_default_img_120px)
                .into(iv);
    }
}
