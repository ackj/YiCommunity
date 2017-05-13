package com.aglhz.yicommunity.publish.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.PublishContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/12 0012 15:07.
 * Email: liujia95me@126.com
 */

public class PublishNeighbourModel extends BaseModel implements PublishContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> post(Params params) {
        return HttpHelper.getService(ApiService.class).postNeighbourMessage(ApiService.postNeighbourMessage,
                params.token, params.cmnt_c, params.content, params.type)
                .subscribeOn(Schedulers.io());
    }
}

