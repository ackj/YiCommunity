package com.aglhz.yicommunity.main.home.model;


import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.entity.bean.BannerBean;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.NoticeBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.ServicesClassifyListBean;
import com.aglhz.yicommunity.main.home.contract.HomeContract;

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
    public Observable<BannerBean> requestBanners() {
        return HttpHelper.getService(ApiService.class)
                .requestBanners(ApiService.requestBanners)
                .subscribeOn(Schedulers.io());
    }

    public Single<List<String>> requestHomeNotices(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestHomeNotices(ApiService.requestHomeNotices, params.token, params.cmnt_c, params.topnum)
                .map(noticeBean -> noticeBean.getData().getNoticeList())
                .flatMap(Flowable::fromIterable)
                .map(NoticeBean.DataBean.NoticeListBean::getTitle)
                .toList()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestOpenDoor(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestOpenDoor(ApiService.requestOpenDoor,
                        params.token,
                        params.dir)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ServicesClassifyListBean> requestServiceClassifyList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestServiceClassifyList(ApiService.requestServiceClassifyList,
                        params.page,
                        params.pageSize,
                        Params.cmnt_c)
                .subscribeOn(Schedulers.io());
    }
}