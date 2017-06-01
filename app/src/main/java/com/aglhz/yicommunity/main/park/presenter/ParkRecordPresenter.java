package com.aglhz.yicommunity.main.park.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.park.model.ParkRecordModel;
import com.aglhz.yicommunity.main.park.contract.ParkRecordContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/23 0023 09:19.
 * Email: liujia95me@126.com
 */

public class ParkRecordPresenter extends BasePresenter<ParkRecordContract.View, ParkRecordContract.Model> implements ParkRecordContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public ParkRecordPresenter(ParkRecordContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected ParkRecordContract.Model createModel() {
        return new ParkRecordModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestParkReocrd(Params params) {
        mRxManager.add(mModel.requestParkRecord(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(parkRecordListBean -> {
                    if (parkRecordListBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseParkRecord(parkRecordListBean.getData().getParkRecordList());
                    } else {
                        getView().error(parkRecordListBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
