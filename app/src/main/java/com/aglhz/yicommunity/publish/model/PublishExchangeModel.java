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
 * Author: LiuJia on 2017/5/12 0012 14:04.
 * Email: liujia95me@126.com
 */

public class PublishExchangeModel extends BaseModel implements PublishContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> post(Params params) {
        return HttpHelper.getService(ApiService.class).postExchangeMessage(ApiService.postExchangeMessage
        ,params.token,params.cmnt_c,params.content,params.price,params.type)
                .subscribeOn(Schedulers.io());
    }
}
