package com.aglhz.yicommunity.publish.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.PublishContract;
import com.aglhz.yicommunity.publish.model.ComplainModel;

import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
 */

public class ComplainPresenter extends BasePresenter<PublishContract.View, PublishContract.Model> implements PublishContract.Presenter {
    private final String TAG = ComplainPresenter.class.getSimpleName();

    public ComplainPresenter(PublishContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected PublishContract.Model createModel() {
        return new ComplainModel();
    }

    @Override
    public void post(Params params) {

        params.cmnt_c = "KBSJ-agl-00005";
        mRxManager.add(mModel.post(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));

    }

    @Override
    public void start(Object request) {

    }
}