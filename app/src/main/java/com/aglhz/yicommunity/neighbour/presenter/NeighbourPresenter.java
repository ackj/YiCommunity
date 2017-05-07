package com.aglhz.yicommunity.neighbour.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.neighbour.contract.NeighbourContract;
import com.aglhz.yicommunity.neighbour.model.NeighbourModel;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
 */

public class NeighbourPresenter extends BasePresenter<NeighbourContract.View, NeighbourContract.Model> implements NeighbourContract.Presenter {
    private final String TAG = NeighbourPresenter.class.getSimpleName();

    public NeighbourPresenter(NeighbourContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected NeighbourContract.Model createModel() {
        return new NeighbourModel();
    }


    @Override
    public void start(Object request) {

    }
}