package com.aglhz.yicommunity.login.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.login.contract.ResetPasswordContract;
import com.aglhz.yicommunity.login.model.ResetPasswordModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/6/23 0023 19:32.
 * Email: liujia95me@126.com
 */

public class ResetPasswordPresenter extends BasePresenter<ResetPasswordContract.View,ResetPasswordContract.Model> implements ResetPasswordContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public ResetPasswordPresenter(ResetPasswordContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected ResetPasswordContract.Model createModel() {
        return new ResetPasswordModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestReset(Params params) {
        mRxManager.add(mModel.requestReset(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().reponseResetSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestVerifyCode(Params params) {
        mRxManager.add(mModel.requestVerifyCode(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseVerfyCodeSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
