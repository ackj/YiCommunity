package com.aglhz.yicommunity.mine.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.MyHousesBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.mine.contract.MineHouseContract;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/17 0017 16:05.
 * Email: liujia95me@126.com
 */

public class MineHouseModel extends BaseModel implements MineHouseContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Flowable<MyHousesBean> requsetMyHouse(Params params) {
        return HttpHelper.getService(ApiService.class).requestMyhouses(ApiService.requestMyhouses, params.token, params.cmnt_c)
                .subscribeOn(Schedulers.io());
    }
}
