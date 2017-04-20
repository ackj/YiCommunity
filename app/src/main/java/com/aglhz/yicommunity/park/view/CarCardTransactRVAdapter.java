package com.aglhz.yicommunity.park.view;

import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.bean.CarCardTransactBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by Administrator on 2017/4/19 11:41.
 */
public class CarCardTransactRVAdapter extends BaseRecyclerViewAdapter<CarCardTransactBean,BaseViewHolder> {
    private final String TAG = CarCardTransactRVAdapter.class.getSimpleName();

    public CarCardTransactRVAdapter(List<CarCardTransactBean> data) {
        super(R.layout.item_rv_car_card_transact, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarCardTransactBean item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        tvTitle.setBackgroundResource(item.bgRes);

        helper.setImageResource(R.id.iv_icon,item.icon)
                .setText(R.id.tv_title,item.title)
                .setTextColor(R.id.tv_title,item.textColor)
                .setText(R.id.tv_desc,item.desc);
    }
}
