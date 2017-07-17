package com.aglhz.yicommunity.main.services.presenter;


import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.services.contract.RemarkContract;
import com.aglhz.yicommunity.main.services.model.RemarkModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 社区服务Presenter层内容。
 */

public class RemarkPresenter extends BasePresenter<RemarkContract.View, RemarkContract.Model> implements RemarkContract.Presenter {
    private final String TAG = RemarkPresenter.class.getSimpleName();

    public RemarkPresenter(RemarkContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected RemarkContract.Model createModel() {
        return new RemarkModel();
    }

    @Override
    public void start(Object request) {
        mRxManager.add(mModel.requestRemarkList((Params) request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(remarkListBean -> {
                    if (remarkListBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().start(remarkListBean);
                    } else {
                        getView().error(remarkListBean.getOther().getMessage());
                    }
                }, this::error));
    }
}