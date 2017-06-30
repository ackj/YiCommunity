package com.aglhz.yicommunity.main.services.presenter;


import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.services.contract.ServicesContract;
import com.aglhz.yicommunity.main.services.model.ServicesModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 社区服务Presenter层内容。
 */

public class ServicesPresenter extends BasePresenter<ServicesContract.View, ServicesContract.Model> implements ServicesContract.Presenter {
    private final String TAG = ServicesPresenter.class.getSimpleName();

    public ServicesPresenter(ServicesContract.View mView) {
        super(mView);
    }

    @Override
    public void start(Object request) {

    }

    @NonNull
    @Override
    protected ServicesContract.Model createModel() {
        return new ServicesModel();
    }

    @Override
    public void requestServiceCommodityList(Params params) {
        mRxManager.add(mModel.requestServiceCommodityList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commodityListBean -> {
                    if (commodityListBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseServiceCommodityList(commodityListBean.getData().getDataList());
                    } else {
                        getView().error(commodityListBean.getOther().getMessage());
                    }
                }, this::error));
    }
}