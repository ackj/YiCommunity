package com.aglhz.yicommunity.home.presenter;

import android.support.annotation.NonNull;


import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.home.contract.HomeContract;
import com.aglhz.yicommunity.home.model.HomeModel;

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
//        HttpClient.post(ServiceApi.indexadvs, new BeanCallback<IndexadvsBean>() {
//            @Override
//            public void onError(String errMsg) {
//
//            }
//
//            @Override
//            public void onSuccess(IndexadvsBean bean) {
//                if (isViewAttached()) {
//                    getView().responseBanner(bean.getData().getAdvs());
//                }
//            }
//        });
    }
//
//    @Override
//    public void requestNotice(String token) {
//        Map params = BaseParams.getTokenMap();
//        HttpClient.post(ServiceApi.NOTICE_TOP, params, new BeanCallback<MsgCenterBean>() {
//            @Override
//            public void onError(String errMsg) {
//                if (isViewAttached()) {
//                    getView().error(new Exception());
//                }
//            }
//
//            @Override
//            public void onSuccess(MsgCenterBean bean) {
//                if (isViewAttached()) {
//                    getView().responseNotice(bean.getData().getNoticeList());
//                }
//
//            }
//        });
//    }

}