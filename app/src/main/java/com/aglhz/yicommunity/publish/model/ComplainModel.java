package com.aglhz.yicommunity.publish.model;


import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.ComplainContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class ComplainModel extends BaseModel implements ComplainContract.Model {
    private final String TAG = ComplainModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> postCmplain(Params params) {
        return HttpHelper.getService(ApiService.class).postComplain(params.token,params.cmnt_c,
                params.name,params.phoneNo,params.content,params.type,params.files)
                .subscribeOn(Schedulers.io());
    }


}