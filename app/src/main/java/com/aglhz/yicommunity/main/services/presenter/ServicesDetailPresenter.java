package com.aglhz.yicommunity.main.services.presenter;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.services.contract.ServicesDetailContract;
import com.aglhz.yicommunity.main.services.model.ServicesDetailModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;

/**
 * Author: LiuJia on 2017/6/30 0030 17:34.
 * Email: liujia95me@126.com
 */

public class ServicesDetailPresenter extends BasePresenter<ServicesDetailContract.View, ServicesDetailContract.Model> implements ServicesDetailContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public ServicesDetailPresenter(ServicesDetailContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected ServicesDetailContract.Model createModel() {
        return new ServicesDetailModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestServiceDetail(Params params) {
        mRxManager.add(mModel.requestServiceDetail(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detailBean -> {
                    if (detailBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseServiceDetail(detailBean);
                    } else {
                        getView().error(detailBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
