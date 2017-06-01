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
        ALog.e(params.token);
        ALog.e(params.cmnt_c);
        ALog.e(params.page);
        ALog.e(params.pageSize);

        return HttpHelper.getService(ApiService.class)
                .requestNeighbourList(ApiService.requestNeighbourList,
                        params.token,
                        params.cmnt_c,
                        params.page,
                        params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> getExchangeList(Params params) {
        return HttpHelper.getService(ApiService.class).requestExchangeList(ApiService.requestExchangeList, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> getCarpoolList(Params params) {
        return HttpHelper.getService(ApiService.class).requestCarpoolList(ApiService.requestCarpoolList + params.carpoolType,
                params.token, params.cmnt_c, params.currentPositionLat, params.currentPositionLng, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> getMyNeihbourList(Params params) {
        return HttpHelper.getService(ApiService.class).requestMyNeighbourList(ApiService.requestMyNeighbourList, params.token, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> getMyExchangeList(Params params) {
        return HttpHelper.getService(ApiService.class).requestMyExchangeList(ApiService.requestMyExchangeList, params.token, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SocialityListBean> getMyCarpoolList(Params params) {
        return HttpHelper.getService(ApiService.class).requestMyCarpoolList(ApiService.requestMyCarpoolList, params.token, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> removeMyCarpool(Params params) {
        return HttpHelper.getService(ApiService.class).removeCarpoolMessage(ApiService.removeCarpoolMessage, params.token, params.fid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> removeMyExchange(Params params) {
        return HttpHelper.getService(ApiService.class).removeExchangeMessage(ApiService.removeExchangeMessage, params.token, params.fid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> removeMyNeighbour(Params params) {
        return HttpHelper.getService(ApiService.class).removeNeighbourMessage(ApiService.removeNeighbourMessage, params.token, params.fid)
                .subscribeOn(Schedulers.io());
    }


}