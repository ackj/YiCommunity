package com.aglhz.yicommunity.main.house.model;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.HouseRightsBean;
import com.aglhz.yicommunity.main.house.contract.HouseRightsContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
        return HttpHelper.getService(ApiService.class)
                .requestRights(ApiService.requestRights
                        , params.token
                        , params.fid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestUpdateRights(Params params) {
        ALog.e(params.url);
        ALog.e(params.token);
        ALog.e(params.mfid);
        ALog.e(params.rfid);
        ALog.e(params.picode);
        ALog.e(params.status);

        return HttpHelper.getService(ApiService.class)
                .requestUpdateRights(params.url
                        , params.token
                        , params.mfid
                        , params.rfid
                        , params.picode
                        , params.status)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestDeleteMember(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestDeleteMember(ApiService.requestDeleteMember
                        , params.token
                        , params.fid
                        , params.mfid)
                .subscribeOn(Schedulers.io());
    }
}