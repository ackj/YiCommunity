package com.aglhz.yicommunity.publish.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.ComplainContract;
import com.aglhz.yicommunity.publish.model.ComplainModel;

import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
 */

public class ComplainPresenter extends BasePresenter<ComplainContract.View, ComplainContract.Model> implements ComplainContract.Presenter {
    private final String TAG = ComplainPresenter.class.getSimpleName();

    public ComplainPresenter(ComplainContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected ComplainContract.Model createModel() {
        return new ComplainModel();
    }

    @Override
    public void postComplain(Params params) {

        params.cmnt_c = "KBSJ-agl-00005";
        mRxManager.add(mModel.postCmplain(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseComplain(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));

    }

    @Override
    public void start(Object request) {

    }
}