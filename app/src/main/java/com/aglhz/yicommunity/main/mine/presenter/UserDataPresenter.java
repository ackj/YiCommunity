package com.aglhz.yicommunity.main.mine.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.common.luban.Luban;
import com.aglhz.yicommunity.main.mine.contract.UserDataContract;
import com.aglhz.yicommunity.main.mine.model.UserDataModel;

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
    public void requestChangePortrait(Params params) {
        compress(params);
    }

    public void compress(Params params) {
        Luban.get(App.mContext)
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
        mRxManager.add(mModel.requestChangePortrait(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userDataBean -> {
                    if (userDataBean.getOther().getCode() == 200) {
                        UserHelper.userInfo.setFace(userDataBean.getData());
                        UserHelper.setUserInfo(UserHelper.userInfo);
                        getView().start(userDataBean.getOther().getMessage());
                    } else {
                        getView().error(userDataBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestUpdateUserData(Params params) {
        mRxManager.add(mModel.requestUpdateUserData(params)
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
    public void requestUpdatePassword(Params params) {
        mRxManager.add(mModel.requestUpdatePassword(params)
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