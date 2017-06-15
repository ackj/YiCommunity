package com.aglhz.yicommunity.main.sociality.model;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.SocialityListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.sociality.contract.SocialityContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class SocialityModel extends BaseModel implements SocialityContract.Model {
    private final String TAG = SocialityModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<SocialityListBean> requestNeighbourList(Params params) {

        return HttpHelper.getService(ApiService.class)
                .requestNeighbourList(ApiService.requestNeighbourList,
                        params.token,
                        params.cmnt_c,
                        params.page,
                        params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> requestExchangeList(Params params) {
        return HttpHelper.getService(ApiService.class).requestExchangeList(ApiService.requestExchangeList, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> requestCarpoolList(Params params) {
        return HttpHelper.getService(ApiService.class).requestCarpoolList(ApiService.requestCarpoolList + params.carpoolType,
                params.token, params.cmnt_c, params.currentPositionLat, params.currentPositionLng, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> requestMyNeihbourList(Params params) {
        return HttpHelper.getService(ApiService.class).requestMyNeighbourList(ApiService.requestMyNeighbourList, params.token, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> requestMyExchangeList(Params params) {
        return HttpHelper.getService(ApiService.class).requestMyExchangeList(ApiService.requestMyExchangeList, params.token, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> requestMyCarpoolList(Params params) {
        return HttpHelper.getService(ApiService.class).requestMyCarpoolList(ApiService.requestMyCarpoolList, params.token, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestRemoveMyCarpool(Params params) {
        return HttpHelper.getService(ApiService.class).requestRemoveCarpool(ApiService.requestRemoveCarpool, params.token, params.fid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestRemoveMyExchange(Params params) {
        return HttpHelper.getService(ApiService.class).requestRemoveExchange(ApiService.requestRemoveExchange, params.token, params.fid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestRemoveMyNeighbour(Params params) {
        return HttpHelper.getService(ApiService.class).requestRemoveNeighbour(ApiService.requestRemoveNeighbour, params.token, params.fid)
                .subscribeOn(Schedulers.io());
    }


}