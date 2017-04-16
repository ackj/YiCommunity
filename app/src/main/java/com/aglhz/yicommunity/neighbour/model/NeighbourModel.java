package com.aglhz.yicommunity.neighbour.model;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.yicommunity.neighbour.contract.NeighbourContract;
import com.aglhz.yicommunity.neighbour.presenter.NeighbourPresenter;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class NeighbourModel extends BaseModel implements NeighbourContract.Model {
    private final String TAG = NeighbourPresenter.class.getSimpleName();

    @Override
    public void start() {
        ALog.e(TAG + "start()");
    }
}