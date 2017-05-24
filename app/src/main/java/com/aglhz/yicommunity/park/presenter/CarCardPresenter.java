package com.aglhz.yicommunity.park.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.park.contract.CarCardContract;
import com.aglhz.yicommunity.park.model.CarCardModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
 */

public class CarCardPresenter extends BasePresenter<CarCardContract.View, CarCardContract.Model> implements CarCardContract.Presenter {
    private final String TAG = CarCardPresenter.class.getSimpleName();

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public CarCardPresenter(CarCardContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected CarCardContract.Model createModel() {
        return new CarCardModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestCarCardList(Params params) {
        mRxManager.add(mModel.requestCarCardList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carCardListBean -> {
                    if (carCardListBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseCarCardList(carCardListBean.getData().getCardList());
                    } else {
                        getView().error(carCardListBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }
}