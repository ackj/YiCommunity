package com.aglhz.yicommunity.door.presenter;


import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.door.contract.PasswordOpenDoorContract;
import com.aglhz.yicommunity.door.model.PasswordOpenDoorModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class PasswordOpenDoorPresenter extends BasePresenter<PasswordOpenDoorContract.View, PasswordOpenDoorContract.Model> implements PasswordOpenDoorContract.Presenter {
    private final String TAG = PasswordOpenDoorPresenter.class.getSimpleName();

    public PasswordOpenDoorPresenter(PasswordOpenDoorContract.View mView) {
        super(mView);
    }

    @Override
    public void start(Object request) {

    }

    @NonNull
    @Override
    protected PasswordOpenDoorContract.Model createModel() {
        return new PasswordOpenDoorModel();
    }

    @Override
    public void requestPassword(Params params) {
        params.dir = "6-31-1";
        mRxManager.add(mModel.getPassword(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(passwordBean -> {
                    if (passwordBean.getOther().getCode() == 200) {
                        getView().responsePassword(passwordBean);
                    } else {
                        getView().error(passwordBean.getOther().getMessage());
                    }
                }, this::error));
    }
}