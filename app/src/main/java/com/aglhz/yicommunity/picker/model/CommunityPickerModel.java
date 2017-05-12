package com.aglhz.yicommunity.picker.model;

import com.aglhz.abase.log.ALog;
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

public class CommunityPickerModel extends BaseModel implements CityPickerContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<CommunitySelectBean> requestCommunitys(Params params) {
        ALog.e(params.sc);
        ALog.e(params.page + "");
        ALog.e(params.pageSize + "");
        ALog.e(params.province);
        ALog.e(params.city);
        ALog.e(params.county);

        return HttpHelper.getService(ApiService.class).requestCommunitys(ApiService.requestCommunitys
                , params.sc
                , params.page + ""
                , params.pageSize + ""
                , params.province
                , params.city
                , params.county)
                .subscribeOn(Schedulers.io());
    }
}
