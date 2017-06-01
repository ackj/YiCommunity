package com.aglhz.yicommunity.main.message.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.message.contract.ComplainReplyContract;
import com.aglhz.yicommunity.main.message.model.ComplainReplyModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/18 0018 18:04.
 * Email: liujia95me@126.com
 */

public class ComplainReplyPresenter extends BasePresenter<ComplainReplyContract.View, ComplainReplyContract.Model> implements ComplainReplyContract.Presenter {

    public ComplainReplyPresenter(ComplainReplyContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected ComplainReplyContract.Model createModel() {
        return new ComplainReplyModel();
    }

    @Override
    public void start(Object request) {
        mRxManager.add(mModel.requestComplainReplies((Params) request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().start(bean);
                    } else {
                        getView().error(bean.getOther().getMessage());
                    }
                }, this::error));
    }
}
