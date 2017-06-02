package com.aglhz.yicommunity.main.door.presenter;


import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.door.contract.AppointOpenDoorContract;
import com.aglhz.yicommunity.main.door.model.AppointOpenDoorModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class AppointOpenDoorPresenter extends BasePresenter<AppointOpenDoorContract.View, AppointOpenDoorContract.Model> implements AppointOpenDoorContract.Presenter {
    private final String TAG = AppointOpenDoorPresenter.class.getSimpleName();

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public AppointOpenDoorPresenter(AppointOpenDoorContract.View mView) {
        super(mView);
    }


    @Override
    public void start(Object request) {

    }

    @NonNull
    @Override
    protected AppointOpenDoorContract.Model createModel() {
        return new AppointOpenDoorModel();
    }

    @Override
    public void requestDoors(Params params) {
        mRxManager.add(mModel.requestDoors(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(doorListBean -> {
                    if (doorListBean.getOther().getCode() == 200) {
                        getView().responseDoors(doorListBean);
                    } else {
                        getView().error(doorListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestAppointOpenDoor(Params params) {
        mRxManager.add(mModel.requestAppointOpenDoor(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseAppointOpenDoor(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }
}