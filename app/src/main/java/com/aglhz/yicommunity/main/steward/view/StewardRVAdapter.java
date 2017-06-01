package com.aglhz.yicommunity.main.steward.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.IconBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by leguang on 2017/4/14 0014.
 * Email：langmanleguang@qq.com
 */

public class StewardRVAdapter extends BaseRecyclerViewAdapter<IconBean, BaseViewHolder> {
    private static final String TAG = StewardRVAdapter.class.getSimpleName();

    public StewardRVAdapter() {
        super(R.layout.item_rv_steward_fragment, null);
    }


    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, IconBean item) {
        helper.setImageResource(R.id.iv_head_item_comment, item.icon)
                .setText(R.id.tv_title, item.title);
    }
}
