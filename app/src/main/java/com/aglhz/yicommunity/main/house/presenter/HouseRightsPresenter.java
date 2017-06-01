package com.aglhz.yicommunity.main.house.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.house.contract.HouseRightsContract;
import com.aglhz.yicommunity.main.house.model.HouseRightsModel;


import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class HouseRightsPresenter extends BasePresenter<HouseRightsContract.View, HouseRightsContract.Model> implements HouseRightsContract.Presenter {
    private final String TAG = HouseRightsPresenter.class.getSimpleName();

    public HouseRightsPresenter(HouseRightsContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected HouseRightsContract.Model createModel() {
        return new HouseRightsModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestRights(Params params) {
        mRxManager.add(mModel.requestRights(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseRights(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestUpdateRights(Params params) {
        mRxManager.add(mModel.requestUpdateRights(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseUpdateRights(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestDelete(Params params) {
        mRxManager.add(mModel.requestDelete(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseDelete(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}