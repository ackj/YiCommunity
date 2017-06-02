package com.aglhz.yicommunity.main.park.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.park.contract.PublishMonthCardContract;
import com.aglhz.yicommunity.main.park.model.PublishMonthCardModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/23 0023 16:28.
 * Email: liujia95me@126.com
 */

public class PublishMonthCardPresenter extends BasePresenter<PublishMonthCardContract.View, PublishMonthCardContract.Model> implements PublishMonthCardContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public PublishMonthCardPresenter(PublishMonthCardContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected PublishMonthCardContract.Model createModel() {
        return new PublishMonthCardModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestSubmitMonthCard(Params params) {
        mRxManager.add(mModel.requestSubmitMonthCard(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseSubmitSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestMonthCardRule(Params params) {
        mRxManager.add(mModel.requestMonthCardRule(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(monthCardRuleListBean -> {
                    if (monthCardRuleListBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseRuleList(monthCardRuleListBean.getData().getMonthCardRuleList());
                    } else {
                        getView().error(monthCardRuleListBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestCardPay(Params params) {
        mRxManager.add(mModel.requestCardPay(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carCardBean -> {
                    if (carCardBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseCardPay(carCardBean.getData());
                    } else {
                        getView().error(carCardBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestCardRecharge(Params params) {
        mRxManager.add(mModel.requestCardRecharge(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rechargeBean -> {
                    if (rechargeBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                         getView().responseCardRecharge(rechargeBean.getData());
                    } else {
                        getView().error(rechargeBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
