package com.aglhz.yicommunity.park.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.ParkOrderBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.park.contract.ParkOrderContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/6/1 0001 09:25.
 * Email: liujia95me@126.com
 */

public class ParkOrderModel extends BaseModel implements ParkOrderContract.Model {


    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<ParkOrderBean> requestParkOrder(Params params) {
        return HttpHelper.getService(ApiService.class).requestPayBill(ApiService.requestPayBill, params.token, params.parkPlaceFid, params.carNo)
                .subscribeOn(Schedulers.io());
    }
}
