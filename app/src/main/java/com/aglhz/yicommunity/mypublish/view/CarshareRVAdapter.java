package com.aglhz.yicommunity.mypublish.view;


import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.CarShareServiceBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by leguang on 2017/4/24 0024.
 * Email：langmanleguang@qq.com
 */

public class CarshareRVAdapter extends BaseRecyclerViewAdapter<CarShareServiceBean.DataBean.CarpoolListBean, BaseViewHolder> {
    public CarshareRVAdapter() {
        super(R.layout.item_opendoor_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarShareServiceBean.DataBean.CarpoolListBean item) {
        helper.setText(R.id.tv_door_name, "1111");
    }
}
