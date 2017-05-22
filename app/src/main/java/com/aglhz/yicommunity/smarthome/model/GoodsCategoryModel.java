package com.aglhz.yicommunity.smarthome.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.FirstLevelBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.smarthome.contract.GoodsCategoryContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/22 0022 10:22.
 * Email: liujia95me@126.com
 */

public class GoodsCategoryModel extends BaseModel implements GoodsCategoryContract.Model {
    @Override
    public void start(Object request) {

    }


    @Override
    public Observable<FirstLevelBean> requestFirstLevel(Params params) {
        return HttpHelper.getService(ApiService.class).requestFirstLevel(ApiService.requestFirstLevel)
                .subscribeOn(Schedulers.io());
    }
}
