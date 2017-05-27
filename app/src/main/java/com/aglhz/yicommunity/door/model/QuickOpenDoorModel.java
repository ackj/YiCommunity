package com.aglhz.yicommunity.door.model;


import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.door.contract.QuickOpenDoorContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块的Model层内容。
 */

public class QuickOpenDoorModel extends BaseModel implements QuickOpenDoorContract.Model {

    private final String TAG = QuickOpenDoorModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<DoorListBean> requestDoors(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestDoors(ApiService.requestDoors,
                        params.token,
                        params.cmnt_c)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> setQuickOpenDoor(Params params) {
        return HttpHelper.getService(ApiService.class)
                .postQuickOpenDoor(ApiService.postQuickOpenDoor,
                        params.token,
                        params.directory,
                        params.deviceName)
                .subscribeOn(Schedulers.io());
    }
}