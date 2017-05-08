package com.aglhz.yicommunity.home.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.home.contract.HomeContract;
import com.aglhz.yicommunity.home.model.HomeModel;

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
    public void requestBanner() {
        mRxManager.add(mModel.getBanner()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bannerBean -> {
                    if (bannerBean.getOther().getCode() == 200) {
                        getView().responseBanner(bannerBean.getData().getAdvs());
                    } else {
                        getView().error(bannerBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestNotice() {
        Params params = Params.getInstance();
        params.cmnt_c = "KBSJ-agl-00005";
        mRxManager.add(mModel.getNotice(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(noticeBean -> {
                    if (noticeBean.getOther().getCode() == 200) {
                        getView().responseNotice(noticeBean.getData().getNoticeList());
                    } else {
                        getView().error(noticeBean.getOther().getMessage());
                    }
                }, this::error));

    }

    @Override
    public void openDoor() {
        Params params = Params.getInstance();
        params.dir = "6-31-1";
        mRxManager.add(mModel.openDoor(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseOpenDoor();
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }
}