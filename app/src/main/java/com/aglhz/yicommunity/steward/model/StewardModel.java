package com.aglhz.yicommunity.steward.model;


import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.ContactBean;
import com.aglhz.yicommunity.bean.IconBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.steward.contract.StewardContract;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import static java.util.stream.Collectors.toList;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责管家模块的Model层内容。
 */

public class StewardModel extends BaseModel implements StewardContract.Model {
    private final String TAG = StewardModel.class.getSimpleName();

    @Override
    public void start(Object request) {
        ALog.e("start");
    }

    @Override
    public Observable<ContactBean> requestContact(Params params) {
        return HttpHelper.getService(ApiService.class).requestContact(ApiService.CONTACT, params.token, params.cmnt_c)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<IconBean>> requestHouses(Params params) {
        return HttpHelper.getService(ApiService.class).requestMyhouses(ApiService.requestMyhouses, params.token)
                .map(myHousesBean -> myHousesBean.getData().getAuthBuildings())
                .flatMap(Flowable::fromIterable)
                .map(authBuildingsBean -> new IconBean(R.drawable.ic_my_house_red_140px, authBuildingsBean.getB_name(), authBuildingsBean.getFid()))
                .toList()
                .subscribeOn(Schedulers.io());
    }
}