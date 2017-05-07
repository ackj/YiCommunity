package com.aglhz.yicommunity.mine.presenter;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.mine.contract.MineContract;



/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
 */

public class MinePresenter extends BasePresenter<MineContract.View, MineContract.Model> implements MineContract.Presenter {
    private final String TAG = MinePresenter.class.getSimpleName();

    public MinePresenter(MineContract.View mView) {
        super(mView);
    }



    @Override
    public void start(Object request) {

    }

    @Override
    public void logout() {
//        HttpClient.uploadFile(ServiceApi.logout, BaseParams.getTokenMap(), new BeanCallback<BaseBean>() {
//            @Override
//            public void onError(String errMsg) {
//                if (isViewAttached()) {
//
//                    getView().logoutSuccess();
//                }
//            }
//
//            @Override
//            public void onSuccess(BaseBean Bean) {
//                if (isViewAttached()) {
//
//                    getView().logoutSuccess();
//                }
//            }
//        });
    }
}