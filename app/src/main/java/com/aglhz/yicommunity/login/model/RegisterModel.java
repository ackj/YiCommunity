package com.aglhz.yicommunity.login.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.login.contract.RegisterContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/9 0009 19:37.
 * Email: liujia95me@126.com
 */

public class RegisterModel extends BaseModel implements RegisterContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> register(Params params) {
        return HttpHelper.getService(ApiService.class).requestRegister(ApiService.requestRegister, params.sc, params.account, params.verifyCode, params.password1, params.password2)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> getVerifyCode(Params params) {
        return HttpHelper.getService(ApiService.class).requestVerifyCode(ApiService.requestVerifyCode, params.sc, params.phoneNo, params.verifyType)
                .subscribeOn(Schedulers.io());
    }
}
