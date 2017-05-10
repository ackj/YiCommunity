package com.aglhz.yicommunity.house.model;


import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.BuildingBean;
import com.aglhz.yicommunity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.bean.FloorBean;
import com.aglhz.yicommunity.bean.RoomBean;
import com.aglhz.yicommunity.bean.UnitBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.house.contract.AddHouseContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块的Model层内容。
 */

public class AddHouseModel extends BaseModel implements AddHouseContract.Model {
    private final String TAG = AddHouseModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<CommunitySelectBean> requestCommunitys(Params params) {
        return HttpHelper.getService(ApiService.class).requestCommunitys(ApiService.requestCommunitys
                , params.sc
                , params.page + ""
                , params.pageSize + ""
                , params.province
                , params.city
                , params.county)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BuildingBean> requestBuildings(Params params) {
        return HttpHelper.getService(ApiService.class).requestBuildings(ApiService.requestBuildings
                , params.sc
                , params.cmnt_c)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<UnitBean> requestUnits(Params params) {
        return HttpHelper.getService(ApiService.class).requestUnits(ApiService.requestUnits
                , params.sc
                , params.cmnt_c
                , params.bdg_c)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<FloorBean> requestFloors(Params params) {
        return HttpHelper.getService(ApiService.class).requestFloors(ApiService.requestFloors
                , params.sc
                , params.cmnt_c
                , params.bdg_c
                , params.bdg_u_c)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<RoomBean> requestRooms(Params params) {
        ALog.e(params.sc);
        ALog.e(params.cmnt_c);
        ALog.e(params.bdg_c);
        ALog.e(params.bdg_u_c);
        ALog.e(params.bdg_f_c);


        return HttpHelper.getService(ApiService.class).requestRooms(ApiService.requestRooms
                , params.sc
                , params.cmnt_c
                , params.bdg_c
                , params.bdg_u_c
                , params.bdg_f_c)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestApply(Params params) {
        return HttpHelper.getService(ApiService.class).requestApply(apply(params.isProprietor)
                , params.token
                , params.cmnt_c
                , params.bdg_c
                , params.bdg_u_c
                , params.bdg_f_c
                , params.bdg_f_h_c
                , params.name
                , params.idCard)
                .subscribeOn(Schedulers.io());
    }

    String apply(boolean b) {
        return b ? ApiService.ownerApply : ApiService.fmApply;
    }
}
