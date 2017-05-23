package com.aglhz.yicommunity.park.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.CarCityListBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/23 0023 11:28.
 * Email: liujia95me@126.com
 */

public class CarCityRVAdapter extends BaseRecyclerViewAdapter<CarCityListBean.CarCityBean, BaseViewHolder> {

    public CarCityRVAdapter(List<CarCityListBean.CarCityBean> carCityList) {
        super(R.layout.item_car_city,carCityList);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarCityListBean.CarCityBean item) {
        helper.setText(R.id.tv_province_item_car_city, item.province)
                .setText(R.id.tv_shortfrom_item_car_city, item.shortfrom);
    }
}
