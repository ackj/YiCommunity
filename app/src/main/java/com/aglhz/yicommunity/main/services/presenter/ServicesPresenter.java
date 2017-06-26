package com.aglhz.yicommunity.main.services.presenter;


import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.main.services.contract.ServicesContract;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 社区服务Presenter层内容。
 */

public class ServicesPresenter extends BasePresenter<ServicesContract.View, ServicesContract.Model> implements ServicesContract.Presenter {
    private final String TAG = ServicesPresenter.class.getSimpleName();

    public ServicesPresenter(ServicesContract.View mView) {
        super(mView);
    }


    @Override
    public void start(Object request) {

    }
}