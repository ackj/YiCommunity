package com.aglhz.yicommunity.login.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.UserBean;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.login.contract.LoginContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class LoginModel extends BaseModel implements LoginContract.Model {

    @Override
    public Observable<UserBean> login(Params params) {
        return HttpHelper.getService(ApiService.class).login(params.sc, params.user, params.pwd)
                .subscribeOn(Schedulers.io());

    }

    @Override
    public void start(Object request) {

    }
}