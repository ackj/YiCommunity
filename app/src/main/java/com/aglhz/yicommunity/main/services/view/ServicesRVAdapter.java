package com.aglhz.yicommunity.main.services.view;


import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.ServicesListBean;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Created by leguang on 2017/6/26 0026.
 * Email：langmanleguang@qq.com
 */
public class ServicesRVAdapter extends BaseRecyclerViewAdapter<ServicesListBean, BaseViewHolder> {

    public ServicesRVAdapter() {
        super(R.layout.item_rv_services);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServicesListBean item) {
        helper.setText(R.id.tv_title_item_rv_services, item.title);
    }
}
