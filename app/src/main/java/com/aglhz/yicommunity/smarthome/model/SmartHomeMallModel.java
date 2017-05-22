package com.aglhz.yicommunity.smarthome.model;


import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.GoodsBean;
import com.aglhz.yicommunity.bean.SubCategoryBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.smarthome.contract.SmartHomeMallContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/22 0022 09:07.
 * Email: liujia95me@126.com
 */

public class SmartHomeMallModel extends BaseModel implements SmartHomeMallContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<SubCategoryBean> requestSubCategoryList(Params params) {
        return HttpHelper.getService(ApiService.class).requestSubCategoryLevel(
                ApiService.requestSubCategoryLevel,params.token,params.appType,params.id)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<GoodsBean> requestGoodsList(Params params) {
        return HttpHelper.getService(ApiService.class).requestGoodsList(
                ApiService.requestGoodsList,params.token,params.appType,params.secondCategoryId)
                .subscribeOn(Schedulers.io());
    }
}
