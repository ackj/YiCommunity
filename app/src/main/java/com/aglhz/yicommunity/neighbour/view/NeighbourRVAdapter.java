package com.aglhz.yicommunity.neighbour.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by leguang on 2017/4/14 0014.
 * Email：langmanleguang@qq.com
 */

public class NeighbourRVAdapter extends BaseRecyclerViewAdapter<BaseBean, BaseViewHolder> {
    private static final String TAG = NeighbourRVAdapter.class.getSimpleName();

    public NeighbourRVAdapter() {
        super(R.layout.item_rv_neighbour_fragment, null);
    }


    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {

    }
}
