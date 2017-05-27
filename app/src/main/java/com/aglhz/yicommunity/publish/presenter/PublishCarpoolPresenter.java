package com.aglhz.yicommunity.publish.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.luban.Luban;
import com.aglhz.yicommunity.publish.contract.PublishContract;
import com.aglhz.yicommunity.publish.model.PublishCarpoolModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/12 0012 15:03.
 * Email: liujia95me@126.com
 */

public class PublishCarpoolPresenter extends BasePresenter<PublishContract.View, PublishContract.Model> implements PublishContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public PublishCarpoolPresenter(PublishContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected PublishContract.Model createModel() {
        return new PublishCarpoolModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void post(Params params) {
        if (params.type == 1) {
            compress(params);
        }else{
            beginPost(params);
        }
    }

    public void compress(Params params) {
        for (int i = 0; i < params.files.size(); i++) {
        }
        Luban.get(BaseApplication.mContext)
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
