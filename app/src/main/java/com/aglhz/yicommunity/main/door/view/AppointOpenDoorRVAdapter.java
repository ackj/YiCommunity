package com.aglhz.yicommunity.main.door.view;


import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Author: LiuJia on 2017/4/21 9:27.
 * Email: liujia95me@126.com
 */
public class AppointOpenDoorRVAdapter extends BaseRecyclerViewAdapter<DoorListBean.DataBean, BaseViewHolder> {

    public AppointOpenDoorRVAdapter() {
        super(R.layout.item_appoint_opendoor);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoorListBean.DataBean item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
