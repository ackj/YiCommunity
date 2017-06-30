package com.aglhz.yicommunity.main.services.model;


import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.ServicesCommodityListBean;
import com.aglhz.yicommunity.main.services.contract.ServicesContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 社区服务模块的Model层内容。
 */

public class ServicesModel extends BaseModel implements ServicesContract.Model {
    private final String TAG = ServicesModel.class.getSimpleName();

    @Override
    public void start(Object request) {
    }

    @Override
    public Observable<ServicesCommodityListBean> requestServiceCommodityList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestServiceCommodityList(ApiService.requestServiceCommodityList,
                        params.page,
                        params.pageSize,
                        params.fid)
                .subscribeOn(Schedulers.io());
    }
}