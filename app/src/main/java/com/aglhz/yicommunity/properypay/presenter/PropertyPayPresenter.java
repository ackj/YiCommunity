package com.aglhz.yicommunity.properypay.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.properypay.contract.PropertyPayContract;
import com.aglhz.yicommunity.properypay.model.PropertyPayModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/7 0007 21:41.
 * Email: liujia95me@126.com
 */

public class PropertyPayPresenter extends BasePresenter<PropertyPayContract.View, PropertyPayContract.Model> implements PropertyPayContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public PropertyPayPresenter(PropertyPayContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected PropertyPayContract.Model createModel() {
        return new PropertyPayModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestPropertyPay() {
        Params params = Params.getInstance();
        params.token = "tk_06013f6d-cd77-4e93-a87c-5bba38dbb84d";
        params.cmnt_c = "KBSJ-agl-00005";
        params.page = 1;
        mRxManager.add(mModel.getPropertyPay(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(propertyPayBean -> {
                    if (propertyPayBean.getOther().getCode() == 200) {
                        getView().responsePropertyPay(propertyPayBean);
                    } else {
                        getView().error(propertyPayBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
