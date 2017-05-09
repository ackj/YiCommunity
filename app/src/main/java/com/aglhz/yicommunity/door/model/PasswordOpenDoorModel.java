package com.aglhz.yicommunity.door.model;


import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.PasswordBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.door.contract.PasswordOpenDoorContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块的Model层内容。
 */

public class PasswordOpenDoorModel extends BaseModel implements PasswordOpenDoorContract.Model {
    private final String TAG = PasswordOpenDoorModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<PasswordBean> getPassword(Params params) {
        return HttpHelper.getService(ApiService.class).getPassword(params.token, params.dir)
                .subscribeOn(Schedulers.io());
    }
}