package com.aglhz.yicommunity.neighbour.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.neighbour.contract.NeighbourContract;
import com.aglhz.yicommunity.neighbour.model.NeighbourModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

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

    @Override
    public void requestNeihbourList(Params params) {
        mRxManager.add(mModel.getNeihbourList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(neighbourListBean -> {
                    if (neighbourListBean.getOther().getCode() == 200) {
                        getView().responseNeihbourList(neighbourListBean.getData().getMomentsList());
                    } else {
                        getView().error(neighbourListBean.getOther().getMessage());
                    }
                }, this::error));
    }
}