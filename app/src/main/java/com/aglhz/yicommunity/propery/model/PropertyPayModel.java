package com.aglhz.yicommunity.propery.model;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.ALiPayBean;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.bean.PropertyPayDetailBean;
import com.aglhz.yicommunity.bean.WxPayBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.propery.contract.PropertyPayContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Author: LiuJia on 2017/5/7 0007 21:41.
 * Email: liujia95me@126.com
 */

public class PropertyPayModel extends BaseModel implements PropertyPayContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<PropertyPayBean> requestPropertyNotPay(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestPropertyNotPay(ApiService.requestPropertyNotPay,
                        params.token,
                        params.cmnt_c,
                        params.page)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PropertyPayBean> requestPropertyPayed(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestPropertyPayed(ApiService.requestPropertyPayed,
                        params.token,
                        params.cmnt_c,
                        params.page)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PropertyPayDetailBean> requestPropertyPayDetail(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestPropertyPayDetail(ApiService.requestPropertyPayDetail,
                        params.token,
                        params.fid)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ResponseBody> requestOrder(Params params) {
        ALog.e("requestOrder::" + params.payType);
        ALog.e("requestOrder::" + params.billFids);

        return HttpHelper.getService(ApiService.class)
                .requestOrder(ApiService.requestOrder,
                        params.token,
                        params.payType,
                        params.billFids)
                .subscribeOn(Schedulers.io());
    }
}
