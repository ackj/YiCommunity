package com.aglhz.yicommunity.park.view;

import android.widget.LinearLayout;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.CarCardListBean;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Created by Administrator on 2017/4/19 10:42.
 */
public class CarCardRVAdapter extends BaseRecyclerViewAdapter<CarCardListBean.DataBean.CardListBean, BaseViewHolder> {

    public CarCardRVAdapter() {
        super(R.layout.item_rv_car_card_manage);
    }

    public String getStringRes(int resId) {
        return mContext.getResources().getString(resId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarCardListBean.DataBean.CardListBean item) {
        helper.setText(R.id.tv_plate_num, item.getCarNo())
                .setVisible(R.id.tv_surplus_days, item.getApproveState() == 0)
                .setText(R.id.tv_surplus_days, item.getApproveState() == 0 ? "正在审核中" :
                        String.format(getStringRes(R.string.surplus_days), String.valueOf(item.getApproveState())))
                .setText(R.id.tv_name, String.format(getStringRes(R.string.car_card_name), item.getCustomerName()))
                .setText(R.id.tv_phone, String.format(getStringRes(R.string.phone), item.getPhoneNo()))
                .setText(R.id.tv_parking, String.format(getStringRes(R.string.parking), item.getParkPlace().getName()))
                .setText(R.id.tv_term_of_validity, item.getApproveState() == 0 ? "有效期：暂无有效期" :
                        String.format(getStringRes(R.string.term_of_validity), item.getStartTime(), item.getEndTime()));

        //----------------- 换背景 ------------------
        LinearLayout llLayout = helper.getView(R.id.ll_view_group);
        if ("月租卡".equals(item.getCardType())) {
            if (item.getApproveState() == 0) {
                llLayout.setBackgroundResource(R.drawable.bg_month_card_1017px_657px);
            } else {
                llLayout.setBackgroundResource(R.drawable.bg_expired_month_card_1017px_657px);
            }
        } else {
            llLayout.setBackgroundResource(R.drawable.bg_proprietor_card_1017px_657px);
        }
    }
}
