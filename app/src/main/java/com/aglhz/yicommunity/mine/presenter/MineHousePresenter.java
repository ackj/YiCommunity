package com.aglhz.yicommunity.mine.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.mine.contract.MineHouseContract;
import com.aglhz.yicommunity.mine.model.MineHouseModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/17 0017 16:15.
 * Email: liujia95me@126.com
 */

public class MineHousePresenter extends BasePresenter<MineHouseContract.View,MineHouseContract.Model> implements MineHouseContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public MineHousePresenter(MineHouseContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected MineHouseContract.Model createModel() {
        return new MineHouseModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requsetMyHouse(Params params) {
        mRxManager.add(mModel.requsetMyHouse(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myHousesBean -> {
                    if (myHousesBean.getOther().getCode() == 200) {
                        getView().responseHouseList(myHousesBean.getData().getAuthBuildings());
                    } else {
                        getView().error(myHousesBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
