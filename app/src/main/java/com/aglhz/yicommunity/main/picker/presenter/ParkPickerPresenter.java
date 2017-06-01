package com.aglhz.yicommunity.main.picker.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.picker.contract.ParkPickerContract;
import com.aglhz.yicommunity.main.picker.model.ParkPickerModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/23 0023 15:07.
 * Email: liujia95me@126.com
 */

public class ParkPickerPresenter extends BasePresenter<ParkPickerContract.View,ParkPickerContract.Model> implements ParkPickerContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public ParkPickerPresenter(ParkPickerContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected ParkPickerContract.Model createModel() {
        return  new ParkPickerModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestParkList(Params params) {
        mRxManager.add(mModel.requestParkList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(parkSelectBean -> {
                    if (parkSelectBean.getOther().getCode() == 200) {
                        getView().responsePark(parkSelectBean.getData().getParkPlaceList());
                    } else {
                        getView().error(parkSelectBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
