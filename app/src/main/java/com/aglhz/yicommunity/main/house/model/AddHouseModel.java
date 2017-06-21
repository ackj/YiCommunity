package com.aglhz.yicommunity.main.house.model;


import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.BuildingBean;
import com.aglhz.yicommunity.entity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.entity.bean.FloorBean;
import com.aglhz.yicommunity.entity.bean.RoomBean;
import com.aglhz.yicommunity.entity.bean.UnitBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.house.contract.AddHouseContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

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
        ALog.e(params.sc);
        ALog.e(params.page);
        ALog.e(params.pageSize);
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
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("cmnt_c", params.cmnt_c);
        builder.addFormDataPart("bdg_c", params.bdg_c);
        builder.addFormDataPart("bdg_u_c", params.bdg_u_c);
        builder.addFormDataPart("bdg_f_c", params.bdg_f_c);
        builder.addFormDataPart("bdg_f_h_c", params.bdg_f_h_c);
        builder.addFormDataPart("applyName", params.name);
        builder.addFormDataPart("idNO", params.idCard);
        return HttpHelper.getService(ApiService.class).requestApply(apply(params.isProprietor)
                , builder.build())
                .subscribeOn(Schedulers.io());
    }

    String apply(boolean b) {
        return b ? ApiService.ownerApply : ApiService.fmApply;
    }
}
