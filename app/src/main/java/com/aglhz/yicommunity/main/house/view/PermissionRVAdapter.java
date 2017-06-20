package com.aglhz.yicommunity.main.house.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.HouseRightsBean;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Author: LiuJia on 2017/4/20 9:37.
 * Email: liujia95me@126.com
 */
public class PermissionRVAdapter extends BaseRecyclerViewAdapter<HouseRightsBean.DataBean.AuthorityBean, BaseViewHolder> {
    public PermissionRVAdapter() {
        super(R.layout.item_rv_permission);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseRightsBean.DataBean.AuthorityBean item) {
        helper.setText(R.id.tv_desc, item.getName())
                .setChecked(R.id.switch_button, 1 == item.getStatus());
    }
}
