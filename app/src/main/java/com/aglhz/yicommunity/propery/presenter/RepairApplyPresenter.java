package com.aglhz.yicommunity.propery.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.propery.contract.RepairApplyContract;
import com.aglhz.yicommunity.propery.model.RepairApplyModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/8 0008 21:35.
 * Email: liujia95me@126.com
 */

public class RepairApplyPresenter extends BasePresenter<RepairApplyContract.View,RepairApplyContract.Model> implements RepairApplyContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public RepairApplyPresenter(RepairApplyContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected RepairApplyContract.Model createModel() {
        return new RepairApplyModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestRepairApplyList(Params params) {
        mRxManager.add(mModel.getRepairApply(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repairApplyBean -> {
                    if (repairApplyBean.getOther().getCode() == 200) {
                        getView().responseRepairApplyList(repairApplyBean.getData().getRepairApplys());
                    } else {
                        getView().error(repairApplyBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
