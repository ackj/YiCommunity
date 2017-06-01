package com.aglhz.yicommunity.main.propery.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.NoticeBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.propery.contract.NoticeListContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/9 0009 22:34.
 * Email: liujia95me@126.com
 * <p>
 * Modify by leguang in 2017.05.11
 */

public class NoticeListModel extends BaseModel implements NoticeListContract.Model {


    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<NoticeBean> requestNotices(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestNotices(
                        ApiService.requestNotices,
                        params.token,
                        params.cmnt_c,
                        params.page + "",
                        params.pageSize + "",
                        params.summerable,
                        params.timeable)
                .subscribeOn(Schedulers.io());
    }
}
