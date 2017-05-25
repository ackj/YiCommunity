package com.aglhz.yicommunity.park.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
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
                .setText(R.id.tv_name, String.format(getStringRes(R.string.car_card_name), item.getCustomerName()))
                .setText(R.id.tv_phone, String.format(getStringRes(R.string.phone), item.getPhoneNo()))
                .setText(R.id.tv_parking, String.format(getStringRes(R.string.parking), item.getParkPlace().getName()))
                .addOnClickListener(R.id.iv_delete_card)
                .addOnClickListener(R.id.ll_view_group);


        LinearLayout llLayout = helper.getView(R.id.ll_view_group);
        TextView tvSurplusDays = helper.getView(R.id.tv_surplus_days);
        TextView tvTermOfValidity = helper.getView(R.id.tv_term_of_validity);
        ImageView ivDeleteCard = helper.getView(R.id.iv_delete_card);

        //-------------- 初始化 -------------
        tvTermOfValidity.setText("有效期：暂无有效期");

        if ("月租卡".equals(item.getCardType())) {
            //-------------- 月租卡 -------------
            llLayout.setBackgroundResource(R.drawable.bg_month_card_1017px_657px);
            tvSurplusDays.setVisibility(View.VISIBLE);
            ivDeleteCard.setVisibility(View.GONE);
            tvTermOfValidity.setVisibility(View.VISIBLE);

            if (item.getApproveState() == 0) {
                //-------------- 未审核-------------
                tvSurplusDays.setText("正在审核中");
            } else if (item.getApproveState() == 1) {
                //-------------- 审核通过 -------------
                if (item.getNeedToPayType() > 1) {
                    //-------------- 立即续费 -------------
                    if (item.getSurplusDays() <= 0) {
                        //-------------- 已过期 -------------
                        llLayout.setBackgroundResource(R.drawable.bg_expired_month_card_1017px_657px);
                        tvSurplusDays.setVisibility(View.GONE);
                        ivDeleteCard.setVisibility(View.VISIBLE);
                    } else {
                        //-------------- 未过期 -------------
                        tvSurplusDays.setText(String.format(getStringRes(R.string.surplus_days), String.valueOf(item.getSurplusDays())));
                    }
                    tvTermOfValidity.setText(String.format(getStringRes(R.string.term_of_validity), item.getStartTime(), item.getEndTime()));
                } else {
                    //-------------- 立即缴费 -------------
                    tvSurplusDays.setText("审核已通过，请缴费");
                }
            } else {
                //-------------- 被拒绝 -------------
                tvSurplusDays.setText("审核不通过");
            }
        } else {
            //-------------- 业主卡-------------
            llLayout.setBackgroundResource(R.drawable.bg_proprietor_card_1017px_657px);
            tvSurplusDays.setVisibility(View.VISIBLE);
            ivDeleteCard.setVisibility(View.GONE);
            tvTermOfValidity.setVisibility(View.GONE);
            ALog.e("业主卡 item.getApproveState()："+item.getApproveState());
            if (item.getApproveState() == 0) {
                tvSurplusDays.setText("正在审核中");
            } else if (item.getApproveState() == 1) {
                tvSurplusDays.setVisibility(View.GONE);
            } else {
                tvSurplusDays.setText("审核不通过");
            }

        }
    }
}
