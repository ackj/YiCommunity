package com.aglhz.yicommunity.main.home.view;

import android.widget.FrameLayout;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.SubCategoryBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Author: LiuJia on 2017/9/11 0011 10:53.
 * Email: liujia95me@126.com
 */

public class WisdomLifeAdapter extends BaseRecyclerViewAdapter<SubCategoryBean.DataBean, BaseViewHolder> {

    public WisdomLifeAdapter(List<SubCategoryBean.DataBean> wisdomLife) {
        super(R.layout.item_home_fragment_rv_wisdom_life, wisdomLife);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubCategoryBean.DataBean item) {
        helper.setText(R.id.tv_name, item.getName());
        FrameLayout frameLayout = helper.getView(R.id.fl_wisdom_life);
        switch (helper.getLayoutPosition()) {
            case 0:
                frameLayout.setBackgroundResource(R.drawable.bg_a01_420px_300px);
                break;
            case 1:
                frameLayout.setBackgroundResource(R.drawable.bg_a02_420px_300px);
                break;
            case 2:
                frameLayout.setBackgroundResource(R.drawable.bg_mensuo_420px_300px);
                break;
            case 3:
                frameLayout.setBackgroundResource(R.drawable.bg_peijian_420px_300px);
                break;
        }

        FrameLayout flWisdomLife = helper.getView(R.id.fl_wisdom_life);
        flWisdomLife.setLayoutParams(new FrameLayout.LayoutParams(
                DensityUtils.dp2px(App.mContext, 140),
                DensityUtils.dp2px(App.mContext, 100)));
    }
}
