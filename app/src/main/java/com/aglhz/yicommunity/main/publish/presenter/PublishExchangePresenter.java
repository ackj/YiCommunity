package com.aglhz.yicommunity.main.publish.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.luban.Luban;
import com.aglhz.yicommunity.main.publish.contract.PublishContract;
import com.aglhz.yicommunity.main.publish.model.PublishExchangeModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/12 0012 14:06.
 * Email: liujia95me@126.com
 */

public class PublishExchangePresenter extends BasePresenter<PublishContract.View, PublishContract.Model> implements PublishContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public PublishExchangePresenter(PublishContract.View mView) {
        super(mView);


    }

    @Override
    public void start(Object request) {

    }

    @NonNull
    @Override
    protected PublishContract.Model createModel() {
        return new PublishExchangeModel();
    }

    @Override
    public void requestSubmit(Params params) {
        if (params.type == 1) {
            compress(params);
        } else {
            beginPost(params);
        }
    }


    public void compress(Params params) {
        for (int i = 0; i < params.files.size(); i++) {
        }
        Luban.get(App.mContext)
                .load(params.files)
                .putGear(Luban.THIRD_GEAR)
                .asList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        files -> {
                            ALog.e(Thread.currentThread().getName());
                            params.files = files;
                            beginPost(params);
                        });
    }

    private void beginPost(Params params) {
        mRxManager.add(mModel.requestSubmit(params)
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
