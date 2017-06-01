package com.aglhz.yicommunity.main.home.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.ServiceBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： Administrator on 2017/5/3 0003.
 * Email： liujia95me@126.com
 */
public class LifeRVAdapter extends BaseRecyclerViewAdapter<ServiceBean, BaseViewHolder> {

    public LifeRVAdapter(List<ServiceBean> data) {
        super(R.layout.item_rv_life_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServiceBean item) {
        helper.setImageResource(R.id.iv_head_item_life, item.imgRes)
                .setText(R.id.tv_title_item_life, item.title)
                .setText(R.id.tv_desc_item_life, item.desc);
    }
}
