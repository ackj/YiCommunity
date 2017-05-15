package com.aglhz.yicommunity.neighbour.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.NeighbourListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.neighbour.contract.NeighbourContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class NeighbourModel extends BaseModel implements NeighbourContract.Model {
    private final String TAG = NeighbourModel.class.getSimpleName();


    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<NeighbourListBean> getNeihbourList(Params params) {
        return HttpHelper.getService(ApiService.class).getNeighbourList(params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<NeighbourListBean> getExchangeList(Params params) {
        return HttpHelper.getService(ApiService.class).getExchangeList(params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<NeighbourListBean> getCarpoolList(Params params) {
        return HttpHelper.getService(ApiService.class).getCarpoolList(params.carpoolType,
                params.currentPositionLat, params.currentPostionLng, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<NeighbourListBean> getMyNeihbourList(Params params) {
        return HttpHelper.getService(ApiService.class).getMyNeighbourList(params.token, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<NeighbourListBean> getMyExchangeList(Params params) {
        return HttpHelper.getService(ApiService.class).getMyExchangeList(params.token, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<NeighbourListBean> getMyCarpoolList(Params params) {
        return HttpHelper.getService(ApiService.class).getMyCarpoolList(params.token, params.page, params.pageSize)
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