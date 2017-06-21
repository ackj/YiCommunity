package com.aglhz.yicommunity.main.mine.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.entity.bean.HouseRightsBean;
import com.aglhz.yicommunity.entity.bean.MyHousesBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.mine.contract.MyHousesContract;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/17 0017 16:05.
 * Email: liujia95me@126.com
 */

public class MyHousesModel extends BaseModel implements MyHousesContract.Model {
    private static final String TAG = MyHousesModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }

    @Override
    public Flowable<MyHousesBean> requsetMyHouse(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestMyhouses(ApiService.requestMyhouses,
                        params.token)
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<HouseRightsBean> requestRights(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestRights(ApiService.requestRights
                        , params.token
                        , params.fid)
                .subscribeOn(Schedulers.io());
    }
}
