package com.aglhz.yicommunity.main.services.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.ServicesCommodityDetailBean;
import com.aglhz.yicommunity.main.services.contract.ServicesDetailContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/6/30 0030 17:33.
 * Email: liujia95me@126.com
 */

public class ServicesDetailModel extends BaseModel implements ServicesDetailContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<ServicesCommodityDetailBean> requestServiceDetail(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestServiceDetail(ApiService.requestServiceDetail, params.fid)
                .subscribeOn(Schedulers.io());
    }
}
