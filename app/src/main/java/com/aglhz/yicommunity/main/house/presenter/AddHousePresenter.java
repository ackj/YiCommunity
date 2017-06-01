package com.aglhz.yicommunity.main.house.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.house.contract.AddHouseContract;
import com.aglhz.yicommunity.main.house.model.AddHouseModel;


import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class AddHousePresenter extends BasePresenter<AddHouseContract.View, AddHouseContract.Model> implements AddHouseContract.Presenter {
    private final String TAG = AddHousePresenter.class.getSimpleName();

    public AddHousePresenter(AddHouseContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddHouseContract.Model createModel() {
        return new AddHouseModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestCommunitys(Params params) {
        mRxManager.add(mModel.requestCommunitys(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseCommunitys(bean.getData().getCommunities());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestBuildings(Params params) {
        mRxManager.add(mModel.requestBuildings(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseBuildings(bean.getData().getBuildings());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestUnits(Params params) {
        mRxManager.add(mModel.requestUnits(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {

                        getView().responseUnits(bean.getData().getBuildingUnits());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestFloors(Params params) {
        mRxManager.add(mModel.requestFloors(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {

                        getView().responseFloors(bean.getData().getFloors());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestRooms(Params params) {
        mRxManager.add(mModel.requestRooms(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseRooms(bean.getData().getHouses());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestApply(Params params) {
        mRxManager.add(mModel.requestApply(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseApply(bean.getOther().getMessage());
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}