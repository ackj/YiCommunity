package com.aglhz.yicommunity.door.presenter;


import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.door.contract.QuickOpenDoorContract;
import com.aglhz.yicommunity.door.model.QuickOpenDoorModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class QuickOpenDoorPresenter extends BasePresenter<QuickOpenDoorContract.View, QuickOpenDoorContract.Model> implements QuickOpenDoorContract.Presenter {
    private final String TAG = QuickOpenDoorPresenter.class.getSimpleName();

    public QuickOpenDoorPresenter(QuickOpenDoorContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected QuickOpenDoorContract.Model createModel() {
        return new QuickOpenDoorModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestDoorList(Params params) {
        mRxManager.add(mModel.getDoorListBean(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(doorListBean -> {
                    if (doorListBean.getOther().getCode() == 200) {
                        getView().responseDoorList(doorListBean);
                    } else {
                        getView().error(doorListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestQuickOpenDoor(Params params) {
        mRxManager.add(mModel.setQuickOpenDoor(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseQuickOpenDoor(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }
}