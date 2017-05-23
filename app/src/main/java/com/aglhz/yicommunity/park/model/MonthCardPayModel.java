package com.aglhz.yicommunity.park.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.park.contract.MonthCardPayContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * Author: LiuJia on 2017/5/23 0023 16:12.
 * Email: liujia95me@126.com
 */

public class MonthCardPayModel extends BaseModel implements MonthCardPayContract.Model {
    @Override
    public void start(Object request) {
    }

    @Override
    public Observable<BaseBean> postMothCarPay(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("parkPlaceFid", params.fid);
        builder.addFormDataPart("carNo", params.carNo);
        builder.addFormDataPart("customerName", params.name);
        builder.addFormDataPart("phoneNo", params.phoneNo);
        builder.addFormDataPart("monthName", params.monthName);
        builder.addFormDataPart("monthCount", params.monthCount + "");
        builder.addFormDataPart("money", params.price);

        return HttpHelper.getService(ApiService.class).postMothCarPay(ApiService.postMothCarPay,
                builder.build())
                .subscribeOn(Schedulers.io());
    }
}
