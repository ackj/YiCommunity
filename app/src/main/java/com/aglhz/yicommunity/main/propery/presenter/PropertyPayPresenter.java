package com.aglhz.yicommunity.main.propery.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.payment.WxPayHelper;
import com.aglhz.yicommunity.main.propery.model.PropertyPayModel;
import com.aglhz.yicommunity.main.propery.contract.PropertyPayContract;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/7 0007 21:41.
 * Email: liujia95me@126.com
 */

public class PropertyPayPresenter extends BasePresenter<PropertyPayContract.View, PropertyPayContract.Model> implements PropertyPayContract.Presenter {

    public PropertyPayPresenter(PropertyPayContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected PropertyPayContract.Model createModel() {
        return new PropertyPayModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestPropertyNotPay(Params params) {
        mRxManager.add(mModel.requestPropertyNotPay(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == 200) {
                        getView().responsePropertyNotPay(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }


    @Override
    public void requestPropertyPayed(Params params) {
        mRxManager.add(mModel.requestPropertyPayed(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == 200) {
                        getView().responsePropertyPayed(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestPropertyPayDetail(Params params) {
        mRxManager.add(mModel.requestPropertyPayDetail(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getOther().getCode() == 200) {
                        getView().responsePropertyPayDetail(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestOrder(Params params) {
        mRxManager.add(mModel.requestOrder(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {

                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(responseBody.string());
                        JSONObject jsonOther = jsonObject.optJSONObject("other");

                        String code = jsonOther.optString("code");
                        if ("200".equals(code)) {
                            if (params.payType == 1) {
                                //支付宝

                                JSONObject jsonData = jsonObject.optJSONObject("data");
                                getView().responseALiPay(jsonData.optString("body"));

                            } else if (params.payType == 2) {
                                //微信
                                WxPayHelper.WxPay(jsonObject.toString());
                            }
                        } else {
                            getView().error(jsonOther.optString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, this::error));
    }
}
