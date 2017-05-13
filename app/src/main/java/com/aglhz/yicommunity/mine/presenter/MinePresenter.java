package com.aglhz.yicommunity.mine.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ServiceApi;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.mine.contract.MineContract;
import com.aglhz.yicommunity.mine.model.MineModel;

import org.apache.http.client.HttpClient;

import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
 */

public class MinePresenter extends BasePresenter<MineContract.View, MineContract.Model> implements MineContract.Presenter {
    private final String TAG = MinePresenter.class.getSimpleName();

    public MinePresenter(MineContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected MineContract.Model createModel() {
        return new MineModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestLogout(Params params) {
        mRxManager.add(mModel.requestLogout(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseLogout(baseBean.getOther().getMessage());
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestCache() {
        mRxManager.add(mModel.requestCache()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (isViewAttached()) {
                        getView().responseCache(s);
                    }

                }, this::error)
        );
    }

    @Override
    public void requestClearCache() {
        mRxManager.add(mModel.requestClearCache()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    ALog.e("Thread.currentThread().getName()::" + Thread.currentThread().getName());
                    if (isViewAttached()) {
                        getView().responseCache(s);
                    }

                }, this::error)
        );
    }
}