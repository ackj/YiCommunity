package com.aglhz.yicommunity.main.services.model;


import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.RemarkListBean;
import com.aglhz.yicommunity.main.services.contract.RemarkContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 社区服务模块的Model层内容。
 */

public class RemarkModel extends BaseModel implements RemarkContract.Model {
    private final String TAG = RemarkModel.class.getSimpleName();

    @Override
    public void start(Object request) {
    }

    @Override
    public Observable<RemarkListBean> requestRemarkList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestRemarkList(ApiService.requestRemarkList,
                        params.page,
                        params.pageSize,
                        params.commodityFid)
                .subscribeOn(Schedulers.io());
    }
}