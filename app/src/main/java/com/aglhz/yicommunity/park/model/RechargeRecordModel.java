package com.aglhz.yicommunity.park.model;


import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.MonthCardBillListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.park.contract.RechargeRecordContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/27 0027 15:38.
 * Email: liujia95me@126.com
 */

public class RechargeRecordModel extends BaseModel implements RechargeRecordContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<MonthCardBillListBean> requsetMonthCardBillList(Params params) {
        return HttpHelper.getService(ApiService.class).requestRechargeRecord(ApiService.requestRechargeRecord,
                params.token,params.page,params.pageSize)
                .subscribeOn(Schedulers.io());
    }
}
