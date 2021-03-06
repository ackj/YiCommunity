package com.aglhz.yicommunity.main.mine.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.MyHousesBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/5/17 0017 15:43.
 * Email: liujia95me@126.com
 */

public class MyHousesRVAdapter extends BaseRecyclerViewAdapter<MyHousesBean.DataBean.AuthBuildingsBean, BaseViewHolder> {

    public MyHousesRVAdapter() {
        super(R.layout.item_mine_house);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyHousesBean.DataBean.AuthBuildingsBean item) {
        helper.setText(R.id.tv_community_name, item.getC_name())
                .setText(R.id.tv_house_name, item.getAddress())
                .setText(R.id.tv_owner, item.getIsOwner() == 1 ? "业主" : "成员")
                .setText(R.id.tv_people_num, item.getMembers().size() + "");
    }
}
