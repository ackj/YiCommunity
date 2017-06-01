package com.aglhz.yicommunity.main.smarthome.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.smarthome.contract.SmartHomeMallContract;
import com.aglhz.yicommunity.main.smarthome.model.SmartHomeMallModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/22 0022 09:07.
 * Email: liujia95me@126.com
 */

public class SmartHomeMallPresenter extends BasePresenter<SmartHomeMallContract.View,SmartHomeMallContract.Model> implements SmartHomeMallContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public SmartHomeMallPresenter(SmartHomeMallContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected SmartHomeMallContract.Model createModel() {
        return new SmartHomeMallModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestSubCategoryList(Params params) {
        mRxManager.add(mModel.requestSubCategoryList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryBean -> {
                    if (categoryBean.getOther().getCode() == 200) {
                        getView().responseSubCategoryList(categoryBean.getData());
                    } else {
                        getView().error(categoryBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestGoodsList(Params params) {
        mRxManager.add(mModel.requestGoodsList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(goodsBean -> {
                    if (goodsBean.getOther().getCode() == 200) {
                        getView().responseGoodsList(goodsBean.getData());
                    } else {
                        getView().error(goodsBean.getOther().getMessage());
                    }
                }, this::error));
    }


}
