package com.aglhz.yicommunity.house.model;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.HouseRightsBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.house.contract.HouseRightsContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.aglhz.yicommunity.common.ServiceApi.apply;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块的Model层内容。
 */

public class HouseRightsModel extends BaseModel implements HouseRightsContract.Model {
    private final String TAG = HouseRightsModel.class.getSimpleName();


    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<HouseRightsBean> requestRights(Params params) {
        return HttpHelper.getService(ApiService.class).requestRights(ApiService.requestRights
                , params.token
                , params.fid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestUpdateRights(Params params) {
        return HttpHelper.getService(ApiService.class).requestUpdateRights(params.url
                , params.token
                , params.mfid
                , params.fid
                , params.picode
                , params.status)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDelete(Params params) {
        return HttpHelper.getService(ApiService.class).requestDelete(params.url
                , params.token
                , params.mfid
                , params.fid)
                .subscribeOn(Schedulers.io());
    }
}