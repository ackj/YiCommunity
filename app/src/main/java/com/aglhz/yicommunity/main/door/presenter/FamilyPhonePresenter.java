package com.aglhz.yicommunity.main.door.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.door.contract.FamilyPhoneContract;
import com.aglhz.yicommunity.main.door.model.FamilyPhoneModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/9/15 0015 11:18.
 * Email: liujia95me@126.com
 */

public class FamilyPhonePresenter extends BasePresenter<FamilyPhoneContract.View, FamilyPhoneContract.Model> implements FamilyPhoneContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public FamilyPhonePresenter(FamilyPhoneContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected FamilyPhoneContract.Model createModel() {
        return new FamilyPhoneModel();
    }

    @Override
    public void requestSetFamilyPhone(Params params) {
        mRxManager.add(mModel.requestSetFamilyPhone(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == 200) {
                        getView().responseSetFamilyPhone(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error, this::complete, disposable -> start("")));
    }
}
