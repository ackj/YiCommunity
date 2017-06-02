package com.aglhz.yicommunity.main.park.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.park.contract.PublishOwnerCardContract;
import com.aglhz.yicommunity.main.park.model.PublishOwnerCardModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/24 0024 10:44.
 * Email: liujia95me@126.com
 */

public class PublishOwnerCardPresenter extends BasePresenter<PublishOwnerCardContract.View,PublishOwnerCardContract.Model> implements PublishOwnerCardContract.Presenter{


    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public PublishOwnerCardPresenter(PublishOwnerCardContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected PublishOwnerCardContract.Model createModel() {
        return new PublishOwnerCardModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestSubmitOwnerCard(Params params) {
        mRxManager.add(mModel.requestSubmitOwnerCard(params)
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

    public void requestModifyOwnerCard(Params params){
        mRxManager.add(mModel.requestModifyOwnerCard(params)
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
}
