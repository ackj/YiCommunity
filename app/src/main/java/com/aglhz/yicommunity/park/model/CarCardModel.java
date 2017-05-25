package com.aglhz.yicommunity.park.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.CarCardListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.park.contract.CarCardContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class CarCardModel extends BaseModel implements CarCardContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<CarCardListBean> requestCarCardList(Params params) {
        return HttpHelper.getService(ApiService.class).requestCarCardList(ApiService.requestCarCardList
                , params.token, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> deleteCarCard(Params params) {
        return HttpHelper.getService(ApiService.class).deleteCarCard(ApiService.deleteCarCard
                , params.token, params.fid)
                .subscribeOn(Schedulers.io());
    }
}