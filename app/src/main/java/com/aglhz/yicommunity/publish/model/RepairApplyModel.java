package com.aglhz.yicommunity.publish.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.RepairApplyBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.RepairApplyContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/8 0008 21:31.
 * Email: liujia95me@126.com
 */

public class RepairApplyModel extends BaseModel implements RepairApplyContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<RepairApplyBean> getRepairApply(Params params) {
        return HttpHelper.getService(ApiService.class).getRepairApply(params.token)
                .subscribeOn(Schedulers.io());
    }
}
