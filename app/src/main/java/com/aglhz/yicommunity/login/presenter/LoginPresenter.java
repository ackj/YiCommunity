package com.aglhz.yicommunity.login.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DoorManager;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.login.contract.LoginContract;
import com.aglhz.yicommunity.login.model.LoginModel;
import com.sipphone.sdk.access.WebReponse;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    public LoginPresenter(LoginContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected LoginContract.Model createModel() {
        return new LoginModel();
    }

    @Override
    public void start(Object request) {
        mRxManager.add(mModel.requestLogin((Params) request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userBean -> {
                    if (userBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        //注册友盟
                        mModel.requestUMeng(((Params) request).user);
                        //保存用户信息
                        UserHelper.setUserInfo(userBean.getData().getMemberInfo());
                        //注册Sip到全视通服务器
                        requestSip();
                    } else {
                        getView().error(userBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    private void requestSip() {
        mModel.requestSip(Params.getInstance())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sipBean -> {

                    if (sipBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        UserHelper.setSip(sipBean.getData().getAccount());
                        ALog.e("11111" + UserHelper.string());

                        DoorManager.getInstance().initWebUserApi(UserHelper.sip, new DoorManager.AccessCallBack() {
                            @Override
                            public void onPreAccess() {
                                getView().start(null);
                            }

                            @Override
                            public void onPostAccess(WebReponse webReponse) {
                                getView().start(null);
                            }
                        });
                    } else {
                        getView().start(null);
                    }
                }, throwable -> getView().start(null));
    }
}