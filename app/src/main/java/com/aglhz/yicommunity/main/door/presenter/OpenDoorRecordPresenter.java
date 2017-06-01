package com.aglhz.yicommunity.main.door.presenter;


import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.door.contract.OpenDoorRecordContract;
import com.aglhz.yicommunity.main.door.model.OpenDoorRecordModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class OpenDoorRecordPresenter extends BasePresenter<OpenDoorRecordContract.View, OpenDoorRecordContract.Model> implements OpenDoorRecordContract.Presenter {
    private final String TAG = OpenDoorRecordPresenter.class.getSimpleName();

    public OpenDoorRecordPresenter(OpenDoorRecordContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected OpenDoorRecordContract.Model createModel() {
        return new OpenDoorRecordModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestRecord(Params params) {
        ALog.e(TAG,"1111111111111111111111");
        mRxManager.add(mModel.getOpenDoorRecord(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(openDoorRecordBean -> {
                    if (openDoorRecordBean.getOther().getCode() == 200) {
                        ALog.e(TAG,"2222222222222222222222222");
                        getView().responseRecord(openDoorRecordBean.getData());
                    } else {
                        ALog.e(TAG,"333333333333333333333333");
                        getView().error(openDoorRecordBean.getOther().getMessage());
                    }
                }, this::error));
    }
}