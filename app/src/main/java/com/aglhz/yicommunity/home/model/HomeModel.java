package com.aglhz.yicommunity.home.model;


import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BannerBean;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.home.contract.HomeContract;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责管家模块的Model层内容。
 */

public class HomeModel extends BaseModel implements HomeContract.Model {
    private final String TAG = HomeModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BannerBean> getBanner() {
        return HttpHelper.getService(ApiService.class).getBanners()
                .subscribeOn(Schedulers.io());
    }

    //    .map(myHousesBean -> myHousesBean.getData().getAuthBuildings())
    // .flatMap(Flowable::fromIterable)
//     .map(authBuildingsBean -> new IconBean(R.drawable.ic_my_house_red_140px, authBuildingsBean.getB_name(), authBuildingsBean.getFid()))
//      .toList()
    @Override
    public Single<List<String>> getNotice(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestHomeNotices(ApiService.requestHomeNotices, params.token, params.cmnt_c)
                .map(noticeBean -> noticeBean.getData().getNoticeList())
                .flatMap(Flowable::fromIterable)
                .map(noticeListBean -> {
                    ALog.e(TAG,"title::"+noticeListBean.getTitle());
                    return noticeListBean.getTitle();
                })
                .toList()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> openDoor(Params params) {
        return HttpHelper.getService(ApiService.class).openDoor(params.token, params.dir)
                .subscribeOn(Schedulers.io());
    }
}