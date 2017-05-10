package com.aglhz.yicommunity.neighbour.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.NeighbourListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.neighbour.contract.NeighbourContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class NeighbourModel extends BaseModel implements NeighbourContract.Model {
    private final String TAG = NeighbourModel.class.getSimpleName();


    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<NeighbourListBean> getNeihbourList(Params params) {
        return HttpHelper.getService(ApiService.class).getNeighbourList(params.page,params.pageSize)
                .subscribeOn(Schedulers.io());
    }
}