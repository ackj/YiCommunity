package com.aglhz.yicommunity.park.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.park.contract.ParkOrderContract;
import com.aglhz.yicommunity.park.model.ParkOrderModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/6/1 0001 09:28.
 * Email: liujia95me@126.com
 */

public class ParkOrderPresenter extends BasePresenter<ParkOrderContract.View,ParkOrderContract.Model> implements ParkOrderContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public ParkOrderPresenter(ParkOrderContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected ParkOrderContract.Model createModel() {
        return new ParkOrderModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestParkOrder(Params params) {
        mRxManager.add(mModel.requestParkOrder(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(parkOrderBean -> {
                    if (parkOrderBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseParkOrder(parkOrderBean.getData());
                    } else {
                        getView().error(parkOrderBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
