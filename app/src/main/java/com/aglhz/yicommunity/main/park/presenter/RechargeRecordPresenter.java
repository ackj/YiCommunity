package com.aglhz.yicommunity.main.park.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.park.contract.RechargeRecordContract;
import com.aglhz.yicommunity.main.park.model.RechargeRecordModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/27 0027 15:39.
 * Email: liujia95me@126.com
 */

public class RechargeRecordPresenter extends BasePresenter<RechargeRecordContract.View,RechargeRecordContract.Model> implements RechargeRecordContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public RechargeRecordPresenter(RechargeRecordContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected RechargeRecordContract.Model createModel() {
        return new RechargeRecordModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestMonthCardBillList(Params params) {
        mRxManager.add(mModel.requsetMonthCardBillList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cardBillListBean -> {
                    if (cardBillListBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseRechargeRecord(cardBillListBean.getData().getMonthCardBillList());
                    } else {
                        getView().error(cardBillListBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
