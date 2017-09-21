package com.aglhz.yicommunity.main.door.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.main.door.contract.FamilyPhoneContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/9/15 0015 11:11.
 * Email: liujia95me@126.com
 */

public class FamilyPhoneModel extends BaseModel implements FamilyPhoneContract.Model {
    public static final String TAG = FamilyPhoneModel.class.getSimpleName();

    @Override
    public Observable<BaseBean> requestSetFamilyPhone(Params params) {
        return HttpHelper.getService(ApiService.class).requestSetFamilyPhone(ApiService.requestSetFamilyPhone,
                params.token, params.phoneNo, params.roomDir)
                .subscribeOn(Schedulers.io());
    }
}
