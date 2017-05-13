package com.aglhz.yicommunity.publish.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.PublishContract;
import com.aglhz.yicommunity.publish.model.PublishNeighbourModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/12 0012 15:09.
 * Email: liujia95me@126.com
 */

public class PublishNeighbourPresenter extends BasePresenter<PublishContract.View,PublishContract.Model> implements PublishContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public PublishNeighbourPresenter(PublishContract.View mView) {
        super(mView);
    }

    @Override
    public void start(Object request) {

    }

    @NonNull
    @Override
    protected PublishContract.Model createModel() {
        return new PublishNeighbourModel();
    }

    @Override
    public void post(Params params) {
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


}
