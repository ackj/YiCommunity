package com.aglhz.yicommunity.main.park.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.ParkRecordListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.park.contract.ParkRecordContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/23 0023 09:18.
 * Email: liujia95me@126.com
 */

public class ParkRecordModel extends BaseModel implements ParkRecordContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<ParkRecordListBean> requestParkRecord(Params params) {
        return HttpHelper.getService(ApiService.class).requestParkRecord(ApiService.requestParkRecord,
                params.token, params.page, params.pageSize, params.searchStartTime, params.searchEndTime)
                .subscribeOn(Schedulers.io());
    }
}
