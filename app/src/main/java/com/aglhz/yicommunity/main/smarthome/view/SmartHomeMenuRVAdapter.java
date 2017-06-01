package com.aglhz.yicommunity.main.smarthome.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.SubCategoryBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/5/22 0022 11:57.
 * Email: liujia95me@126.com
 */

public class SmartHomeMenuRVAdapter extends BaseRecyclerViewAdapter<SubCategoryBean.DataBean, BaseViewHolder> {

    public SmartHomeMenuRVAdapter() {
        super(R.layout.item_smart_home_menu);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubCategoryBean.DataBean item) {
        helper.setText(R.id.tv_menu, item.getName())
                .setTextColor(R.id.tv_menu, selectItem == item ? 0xFFE95D5A : 0xFF999999)
                .setBackgroundColor(R.id.tv_menu,selectItem == item ? 0xFFFFFFFF : 0x00000000);
    }

    private SubCategoryBean.DataBean selectItem;

    public void setSelectItem(SubCategoryBean.DataBean selectItem) {
        this.selectItem = selectItem;
        notifyDataSetChanged();
    }
}
