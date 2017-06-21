package com.aglhz.yicommunity.login.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DoorManager;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.login.contract.LoginContract;
import com.aglhz.yicommunity.login.model.LoginModel;
import com.sipphone.sdk.access.WebReponse;

import org.greenrobot.eventbus.EventBus;

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
        ALog.e("1111startstart");

        Params params = (Params) request;
        mRxManager.add(mModel.requestLogin((Params) request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userBean -> {
                    if (userBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        //注册友盟
                        mModel.requestUMeng(params.user);
                        //保存用户信息
                        UserHelper.setAccount(params.user, params.pwd);//setAccount要先于setUserInfo调用，不然无法切换SP文件。
                        UserHelper.setUserInfo(userBean.getData().getMemberInfo());
                        //注册Sip到全视通服务器
                        requestSip(Params.getInstance());
                        //登录成功后，通知相关页面刷新。
                        EventBus.getDefault().post(new EventCommunity(null));

                    } else {
                        getView().error(userBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    private void requestSip(Params params) {
        mModel.requestSip(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sipBean -> {
                    if (sipBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        UserHelper.setSip(sipBean.getData().getAccount());
                        DoorManager.getInstance().initWebUserApi(UserHelper.sip, new DoorManager.AccessCallBack() {

                            @Override
                            public void onPreAccessToken() {
                                getView().start(null);
                            }

                            @Override
                            public void onPostAccessToken(WebReponse webReponse) {
                                ALog.e("11111PostPostPostPost");
                                DoorManager.getInstance().startService();
                                getView().start(null);
                            }
                        });
                    } else {
                        getView().start(null);
                    }
                }, throwable -> getView().start(null));
    }
}