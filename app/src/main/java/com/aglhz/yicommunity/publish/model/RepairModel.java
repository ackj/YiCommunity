package com.aglhz.yicommunity.publish.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.RepairContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/9 0009 10:35.
 * Email: liujia95me@126.com
 */

public class RepairModel extends BaseModel implements RepairContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> postRepair(Params params) {
        return HttpHelper.getService(ApiService.class).postRepair(params.token,
                params.cmnt_c, params.contact, params.des, params.name, params.single, params.files, params.type)
                .subscribeOn(Schedulers.io());

    }
}
