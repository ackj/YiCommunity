package com.aglhz.yicommunity.main.propery.view;

import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.RepairApplyBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2017/4/19 15:02.
 */
public class RepairRecordRVAdapter extends BaseRecyclerViewAdapter<RepairApplyBean.DataBean.RepairApplysBean, BaseViewHolder> {
    private final String TAG = RepairRecordRVAdapter.class.getSimpleName();

    public RepairRecordRVAdapter() {
        super(R.layout.item_rv_property_repair);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepairApplyBean.DataBean.RepairApplysBean item) {
        helper.setText(R.id.tv_title, item.isSingle() ? "私人报修" : "公共报修")
                .setText(R.id.tv_date, item.getCtime())
                .setText(R.id.tv_desc, item.getDes());

        ImageView ivStatus = helper.getView(R.id.iv_status);
        if (item.getStatus() == 0) {
            ivStatus.setImageResource(R.drawable.ic_property_repair_untreated_172px);
        } else if (item.getStatus() == 1) {
            ivStatus.setImageResource(R.drawable.ic_property_repair_finished_172px);
        } else {
            ivStatus.setImageResource(R.drawable.ic_property_repair_processing_172px);
        }
    }
}
