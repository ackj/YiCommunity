package com.aglhz.yicommunity.main.sociality.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.sociality.contract.SocialityContract;
import com.aglhz.yicommunity.main.sociality.model.SocialityModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
 */

public class SocialityPresenter extends BasePresenter<SocialityContract.View, SocialityContract.Model> implements SocialityContract.Presenter {
    private final String TAG = SocialityPresenter.class.getSimpleName();

    public SocialityPresenter(SocialityContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected SocialityContract.Model createModel() {
        return new SocialityModel();
    }


    @Override
    public void start(Object request) {

    }

    @Override
    public void requestNeighbourList(Params params) {
        mRxManager.add(mModel.requestNeighbourList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(neighbourListBean -> {
                    if (neighbourListBean.getOther().getCode() == 200) {
                        getView().responseSuccess(neighbourListBean.getData().getMomentsList());
                    } else {
                        getView().error(neighbourListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestExchangeList(Params params) {
        mRxManager.add(mModel.getExchangeList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(neighbourListBean -> {
                    if (neighbourListBean.getOther().getCode() == 200) {
                        getView().responseSuccess(neighbourListBean.getData().getExchangeList());
                    } else {
                        getView().error(neighbourListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestCarpoolList(Params params) {
        mRxManager.add(mModel.getCarpoolList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(neighbourListBean -> {
                    if (neighbourListBean.getOther().getCode() == 200) {
                        getView().responseSuccess(neighbourListBean.getData().getCarpoolList());
                    } else {
                        getView().error(neighbourListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestMyNeihbourList(Params params) {
        mRxManager.add(mModel.getMyNeihbourList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(neighbourListBean -> {
                    if (neighbourListBean.getOther().getCode() == 200) {
                        getView().responseSuccess(neighbourListBean.getData().getMomentsList());
                    } else {
                        getView().error(neighbourListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestMyExchangeList(Params params) {
        mRxManager.add(mModel.getMyExchangeList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(neighbourListBean -> {
                    if (neighbourListBean.getOther().getCode() == 200) {
                        getView().responseSuccess(neighbourListBean.getData().getExchangeList());
                    } else {
                        getView().error(neighbourListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestMyCarpoolList(Params params) {
        mRxManager.add(mModel.getMyCarpoolList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(neighbourListBean -> {
                    if (neighbourListBean.getOther().getCode() == 200) {
                        getView().responseSuccess(neighbourListBean.getData().getCarpoolList());
                    } else {
                        getView().error(neighbourListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void removeMyCarpool(Params params) {
        mRxManager.add(mModel.removeMyCarpool(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().removeSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void removeMyExchange(Params params) {
        mRxManager.add(mModel.removeMyExchange(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().removeSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void removeMyNeighbour(Params params) {
        mRxManager.add(mModel.removeMyNeighbour(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().removeSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }


}