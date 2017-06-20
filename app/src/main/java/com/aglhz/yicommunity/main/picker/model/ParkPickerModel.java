package com.aglhz.yicommunity.main.picker.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.entity.bean.ParkSelectBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.picker.contract.ParkPickerContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/23 0023 15:02.
 * Email: liujia95me@126.com
 */

public class ParkPickerModel extends BaseModel implements ParkPickerContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<ParkSelectBean> requestParkList(Params params) {
        return HttpHelper.getService(ApiService.class).requestParkList(ApiService.requestParkList
                , params.token, params.page, params.pageSize, params.city)
                .subscribeOn(Schedulers.io());
    }
}
