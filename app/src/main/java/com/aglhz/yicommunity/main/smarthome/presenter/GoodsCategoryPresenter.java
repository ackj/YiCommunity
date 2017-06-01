package com.aglhz.yicommunity.main.smarthome.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.smarthome.model.GoodsCategoryModel;
import com.aglhz.yicommunity.main.smarthome.contract.GoodsCategoryContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/22 0022 10:24.
 * Email: liujia95me@126.com
 */

public class GoodsCategoryPresenter extends BasePresenter<GoodsCategoryContract.View,GoodsCategoryContract.Model> implements GoodsCategoryContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public GoodsCategoryPresenter(GoodsCategoryContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected GoodsCategoryContract.Model createModel() {
        return new GoodsCategoryModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestFirstLevel(Params params) {
        mRxManager.add(mModel.requestFirstLevel(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(firstLevelBean -> {
                    if (firstLevelBean.getOther().getCode() == 200) {
                        getView().responseFirstLevel(firstLevelBean.getData());
                    } else {
                        getView().error(firstLevelBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
