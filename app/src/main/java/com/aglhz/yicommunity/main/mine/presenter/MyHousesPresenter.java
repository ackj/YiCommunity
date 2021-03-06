package com.aglhz.yicommunity.main.mine.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.mine.model.MyHousesModel;
import com.aglhz.yicommunity.main.mine.contract.MyHousesContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/17 0017 16:15.
 * Email: liujia95me@126.com
 */

public class MyHousesPresenter extends BasePresenter<MyHousesContract.View,MyHousesContract.Model> implements MyHousesContract.Presenter {
    private static final String TAG = MyHousesPresenter.class.getSimpleName();

    public MyHousesPresenter(MyHousesContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected MyHousesContract.Model createModel() {
        return new MyHousesModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requsetMyHouse(Params params) {
        mRxManager.add(mModel.requsetMyHouse(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myHousesBean -> {
                    if (myHousesBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseHouses(myHousesBean.getData().getAuthBuildings());
                    } else {
                        getView().error(myHousesBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
