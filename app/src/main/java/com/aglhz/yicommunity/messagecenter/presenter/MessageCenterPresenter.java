package com.aglhz.yicommunity.messagecenter.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.messagecenter.contract.MessageCenterContract;
import com.aglhz.yicommunity.messagecenter.model.MessageCenterModel;

import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 消息中心模块Presenter层内容。
 */

public class MessageCenterPresenter extends BasePresenter<MessageCenterContract.View, MessageCenterContract.Model> implements MessageCenterContract.Presenter {
    private final String TAG = MessageCenterPresenter.class.getSimpleName();

    public MessageCenterPresenter(MessageCenterContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected MessageCenterContract.Model createModel() {
        return new MessageCenterModel();
    }

    @Override
    public void start(Object request) {
        mRxManager.add(mModel.requestMessages((Params) request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(messageCenterBean -> {
                    if (messageCenterBean.getOther().getCode() == 200) {
                        getView().start(messageCenterBean.getData().getMemNews());
                    } else {
                        getView().error(messageCenterBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

}