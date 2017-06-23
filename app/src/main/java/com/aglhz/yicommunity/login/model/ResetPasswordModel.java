package com.aglhz.yicommunity.login.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.login.contract.ResetPasswordContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/6/23 0023 19:21.
 * Email: liujia95me@126.com
 */

public class ResetPasswordModel extends BaseModel implements ResetPasswordContract.Model {

    @Override
    public Observable<BaseBean> requestReset(Params params) {
        return HttpHelper.getService(ApiService.class).requestResetPassword(ApiService.requestResetPassword, params.sc, params.account, params.verifyCode, params.password1, params.password2)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestVerifyCode(Params params) {
        return HttpHelper.getService(ApiService.class).requestVerifyCode(ApiService.requestVerifyCode, params.sc, params.phoneNo, params.verifyType)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void start(Object request) {

    }
}
