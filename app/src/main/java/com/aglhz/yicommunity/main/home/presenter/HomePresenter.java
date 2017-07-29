package com.aglhz.yicommunity.main.home.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.main.home.contract.HomeContract;
import com.aglhz.yicommunity.main.home.model.HomeModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责管家模块Presenter层内容。
 */

public class HomePresenter extends BasePresenter<HomeContract.View, HomeContract.Model> implements HomeContract.Presenter {
    private final String TAG = HomePresenter.class.getSimpleName();

    public HomePresenter(HomeContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected HomeContract.Model createModel() {
        return new HomeModel();
    }

    @Override
    public void start(Object request) {
    }

    @Override
    public void requestBanners(Params params) {
        mRxManager.add(mModel.requestBanners(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bannerBean -> {
                    if (bannerBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseBanners(bannerBean.getData().getAdvs());
                    } else {
                        getView().error(bannerBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestHomeNotices(Params params) {
        mRxManager.add(mModel.requestHomeNotices(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings -> getView().responseHomeNotices(strings), this::error));
    }

    @Override
    public void requestOpenDoor() {
        Params params = Params.getInstance();
        params.dir = UserHelper.dir;
        mRxManager.add(mModel.requestOpenDoor(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseOpenDoor();
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestServiceTypes(Params params) {
        mRxManager.add(mModel.requestServiceTypes(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(classifyListBean -> {
                    if (classifyListBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseServiceClassifyList(classifyListBean.getData().getClassifyList());
                    } else {
                        getView().error(classifyListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestOneKeyOpenDoorDeviceList(Params params) {
        mRxManager.add(mModel.requestOneKeyOpenDoorDeviceList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(oneKeyDoorBean -> {
                    if (oneKeyDoorBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        getView().responseOneKeyOpenDoorDeviceList(oneKeyDoorBean.getData().getItemList());
                    } else {
                        getView().error(oneKeyDoorBean.getOther().getMessage());
                    }
                }, this::error));
    }
}