package com.aglhz.yicommunity.main.park.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.park.contract.PublishOwnerCardContract;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * Author: LiuJia on 2017/5/24 0024 10:44.
 * Email: liujia95me@126.com
 */

public class PublishOwnerCardModel extends BaseModel implements PublishOwnerCardContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> requestSubmitOwnerCard(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("parkPlaceFid", params.parkPlaceFid);
        builder.addFormDataPart("carNo", params.carNo);
        builder.addFormDataPart("customerName", params.name);
        builder.addFormDataPart("phoneNo", params.phoneNo);
        return HttpHelper.getService(ApiService.class).requestSubmitOwnerCard(ApiService.requestSubmitOwnerCard,
                builder.build())
                .subscribeOn(Schedulers.io());
    }

    public Observable<BaseBean> requestModifyOwnerCard(Params params) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", params.token);
        map.put("parkCardFid", params.parkCardFid);
        map.put("parkPlaceFid", params.parkPlaceFid);
        map.put("carNo", params.carNo);
        map.put("customerName", params.name);
        map.put("phoneNo", params.phoneNo);
        return HttpHelper.getService(ApiService.class).requestModifyOwnerCard(ApiService.requestModifyOwnerCard,
                map)
                .subscribeOn(Schedulers.io());
    }
}
