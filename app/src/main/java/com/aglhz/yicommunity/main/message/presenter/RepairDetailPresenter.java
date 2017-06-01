package com.aglhz.yicommunity.main.message.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.message.contract.RepairDetailContract;
import com.aglhz.yicommunity.main.message.model.RepairDetailModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/19 0019 09:42.
 * Email: liujia95me@126.com
 */

public class RepairDetailPresenter extends BasePresenter<RepairDetailContract.View,RepairDetailContract.Model> implements RepairDetailContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public RepairDetailPresenter(RepairDetailContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected RepairDetailContract.Model createModel() {
        return new RepairDetailModel();
    }

    @Override
    public void requestRepairDetail(Params params) {
        mRxManager.add(mModel.requestRepairDetail(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repairDetailBean -> {
                    if (repairDetailBean.getOther().getCode() == 200) {
                        getView().responseRepairDetail(repairDetailBean);
                    } else {
                        getView().error(repairDetailBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void start(Object request) {

    }
}
