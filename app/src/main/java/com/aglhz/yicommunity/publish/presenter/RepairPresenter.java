package com.aglhz.yicommunity.publish.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.RepairContract;
import com.aglhz.yicommunity.publish.model.RepairModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/9 0009 10:36.
 * Email: liujia95me@126.com
 */

public class RepairPresenter extends BasePresenter<RepairContract.View, RepairContract.Model> implements RepairContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public RepairPresenter(RepairContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected RepairContract.Model createModel() {
        return new RepairModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void postRepair(Params params) {
        params.cmnt_c = "KBSJ-agl-00005";
        mRxManager.add(mModel.postRepair(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseRepair(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
