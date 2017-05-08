package com.aglhz.yicommunity.properypay.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.properypay.contract.PropertyPayContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/7 0007 21:41.
 * Email: liujia95me@126.com
 */

public class PropertyPayModel extends BaseModel implements PropertyPayContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<PropertyPayBean> getPropertyPay(Params params) {
        return HttpHelper.getService(ApiService.class).getPropertyPay(params.token,params.cmnt_c,params.page)
                .subscribeOn(Schedulers.io());
    }
}
