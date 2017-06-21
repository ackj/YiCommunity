package com.aglhz.yicommunity.main.door.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.OpenDoorRecordBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/4/21 11:11.
 * Email: liujia95me@126.com
 */
public class OpenDoorRecordRVAdapter extends BaseRecyclerViewAdapter<OpenDoorRecordBean.DataBean, BaseViewHolder> {

    public OpenDoorRecordRVAdapter() {
        super(R.layout.item_opendoor_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenDoorRecordBean.DataBean item) {
        helper.setText(R.id.tv_door_name, item.getDeviceName())
                .setText(R.id.tv_unlock_way, item.getAccessWay() == 22 ? "App" : "其他")
                .setText(R.id.tv_date, item.getAccessTime())
                .setText(R.id.tv_opendoor_man, item.getUserName());
    }
}
