package com.aglhz.yicommunity.park.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.park.model.PublishMonthCardModel;
import com.aglhz.yicommunity.park.view.PublishMonthCardFragment;
import com.aglhz.yicommunity.publish.contract.PublishContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/23 0023 16:28.
 * Email: liujia95me@126.com
 */

public class PublishMonthCardPresenter extends BasePresenter<PublishContract.View, PublishContract.Model> implements PublishContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public PublishMonthCardPresenter(PublishContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected PublishContract.Model createModel() {
        return new PublishMonthCardModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void post(Params params) {
        mRxManager.add(mModel.post(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    public void requestMonthCardRule(Params params){
        mRxManager.add(((PublishMonthCardModel)mModel).requestMonthCardRule(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(monthCardRuleListBean -> {
                    if (monthCardRuleListBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        ((PublishMonthCardFragment)getView()).responseRuleList(monthCardRuleListBean.getData().getMonthCardRuleList());
                    } else {
                        getView().error(monthCardRuleListBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    public void requestCardPay(Params params){
        mRxManager.add(((PublishMonthCardModel)mModel).requestCardPay(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carCardBean -> {
                    if (carCardBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        ((PublishMonthCardFragment)getView()).responseCardPay(carCardBean.getData());
                    } else {
                        getView().error(carCardBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    public void requestCardRecharge(Params params){
        mRxManager.add(((PublishMonthCardModel)mModel).requestCardRecharge(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rechargeBean -> {
                    if (rechargeBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        ((PublishMonthCardFragment)getView()).responseCardRecharge(rechargeBean.getData());
                    } else {
                        getView().error(rechargeBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}
