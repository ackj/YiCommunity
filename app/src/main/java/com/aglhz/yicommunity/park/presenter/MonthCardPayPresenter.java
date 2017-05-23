package com.aglhz.yicommunity.park.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.park.contract.MonthCardPayContract;
import com.aglhz.yicommunity.park.model.MonthCardPayModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/23 0023 16:28.
 * Email: liujia95me@126.com
 */

public class MonthCardPayPresenter extends BasePresenter<MonthCardPayContract.View, MonthCardPayContract.Model> implements MonthCardPayContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public MonthCardPayPresenter(MonthCardPayContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected MonthCardPayContract.Model createModel() {
        return new MonthCardPayModel();
    }

    @Override
    public void postMothCarPay(Params params) {
        mRxManager.add(mModel.postMothCarPay(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().start(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void start(Object request) {

    }
}
