package com.aglhz.yicommunity.home.model;


import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BannerBean;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.NoticeBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.home.contract.HomeContract;

import io.reactivex.Observable;
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

    @Override
    public Observable<NoticeBean> getNotice(Params params) {
        return HttpHelper.getService(ApiService.class).getNotice(params.token,params.cmnt_c,1)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> openDoor(Params params) {
        return HttpHelper.getService(ApiService.class).openDoor(params.token,params.dir)
                .subscribeOn(Schedulers.io());
    }
}