package com.aglhz.yicommunity.park.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.CarCardManageBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by Administrator on 2017/4/19 10:42.
 */
public class CarCardRVAdapter extends BaseRecyclerViewAdapter<CarCardManageBean, BaseViewHolder> {

    public CarCardRVAdapter(List<CarCardManageBean> datas) {
        super(R.layout.item_rv_car_card_manage, datas);
    }

    public String getStringRes(int resId) {
        return mContext.getResources().getString(resId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarCardManageBean item) {
        helper.setText(R.id.tv_plate_num, item.plateNum)
                .setBackgroundRes(R.id.ll_view_group, getBgFromType(item.type))
                .setVisible(R.id.ll_surplus_days, item.type == 0)
                .setText(R.id.tv_surplus_days, String.valueOf(item.surplusDays))
                .setText(R.id.tv_name, String.format(getStringRes(R.string.name), item.name))
                .setText(R.id.tv_phone, String.format(getStringRes(R.string.phone), item.phone))
                .setText(R.id.tv_parking, String.format(getStringRes(R.string.parking), item.parking))
                .setText(R.id.tv_term_of_validity, String.format(getStringRes(R.string.term_of_validity), item.timeStart, item.timeEnd));
    }

    public int getBgFromType(int type) {
        switch (type) {
            case 0:
                return R.drawable.bg_month_card_1017px_657px;
            case 1:
                return R.drawable.bg_expired_month_card_1017px_657px;
            default:
                return R.drawable.bg_proprietor_card_1017px_657px;
        }
    }
}
