package com.aglhz.yicommunity.picker.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.picker.contract.CityPickerContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/8 0008 11:35.
 * Email: liujia95me@126.com
 */

public class CityPickerModel extends BaseModel implements CityPickerContract.Model {


    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<CommunitySelectBean> getCommunityList(Params params) {
        return HttpHelper.getService(ApiService.class).getCommunityList(params.sc, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }
}
