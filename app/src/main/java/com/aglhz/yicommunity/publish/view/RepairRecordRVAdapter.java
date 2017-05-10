package com.aglhz.yicommunity.publish.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.RepairApplyBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19 15:02.
 */
public class RepairRecordRVAdapter extends BaseRecyclerViewAdapter<RepairApplyBean.DataBean.RepairApplysBean, BaseViewHolder> {
    private final String TAG = RepairRecordRVAdapter.class.getSimpleName();

    public RepairRecordRVAdapter(List<RepairApplyBean.DataBean.RepairApplysBean> data) {
        super(R.layout.item_rv_property_repair, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepairApplyBean.DataBean.RepairApplysBean item) {
        helper.setText(R.id.tv_title, item.isSingle()?"私人报修":"公共报修")
                .setText(R.id.tv_date, item.getCtime())
                .setText(R.id.tv_desc, item.getDes());
//                .setImageResource(R.id.iv_status, item.getStatus());
    }
}
