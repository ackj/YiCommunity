package com.aglhz.yicommunity.mine.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.luban.Luban;
import com.aglhz.yicommunity.mine.contract.UserDataContract;
import com.aglhz.yicommunity.mine.model.UserDataModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 反馈模块Presenter层内容。
 */

public class UserDataPresenter extends BasePresenter<UserDataContract.View, UserDataContract.Model> implements UserDataContract.Presenter {
    private final String TAG = UserDataPresenter.class.getSimpleName();

    public UserDataPresenter(UserDataContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected UserDataContract.Model createModel() {
        return new UserDataModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void changePortrait(Params params) {
        compress(params);
    }

    public void compress(Params params) {
        Luban.get(BaseApplication.mContext)
                .load(params.file)
                .putGear(Luban.THIRD_GEAR)
                .asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(file -> {
                    params.file = file;
                    updatePortait(params);
                });
    }

    private void updatePortait(Params params) {
        mRxManager.add(mModel.updatePortrait(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().start(baseBean.getOther().getMessage());
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void updateUserData(Params params) {
        mRxManager.add(mModel.updateUserData(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().start(baseBean.getOther().getMessage());
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void updatePassword(Params params) {
        mRxManager.add(mModel.updatePassword(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().start(baseBean.getOther().getMessage());
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}