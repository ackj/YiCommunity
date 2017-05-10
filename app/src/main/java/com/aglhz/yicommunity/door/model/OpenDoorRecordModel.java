package com.aglhz.yicommunity.door.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.OpenDoorRecordBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.door.contract.OpenDoorRecordContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/9 0009 17:09.
 * Email: liujia95me@126.com
 */

public class OpenDoorRecordModel extends BaseModel implements OpenDoorRecordContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<OpenDoorRecordBean> getOpenDoorRecord(Params params) {
        return HttpHelper.getService(ApiService.class).getOpenDoorRecord(params.token)
                .subscribeOn(Schedulers.io());
    }
}
