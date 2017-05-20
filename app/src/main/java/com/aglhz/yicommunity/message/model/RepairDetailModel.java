package com.aglhz.yicommunity.message.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.RepairDetailBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.message.contract.RepairDetailContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/19 0019 09:38.
 * Email: liujia95me@126.com
 */

public class RepairDetailModel extends BaseModel implements RepairDetailContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<RepairDetailBean> requestRepairDetail(Params params) {
        return HttpHelper.getService(ApiService.class).requestRepairDetail(ApiService.requestRepairDetail,
                params.token, params.fid)
                .subscribeOn(Schedulers.io());
    }
}
