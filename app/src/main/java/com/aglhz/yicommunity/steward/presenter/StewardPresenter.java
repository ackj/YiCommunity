package com.aglhz.yicommunity.steward.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.IconBean;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.steward.contract.StewardContract;
import com.aglhz.yicommunity.steward.model.StewardModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.alipay.sdk.app.statistic.c.u;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责管家模块Presenter层内容。
 */

public class StewardPresenter extends BasePresenter<StewardContract.View, StewardContract.Model> implements StewardContract.Presenter {
    private final String TAG = StewardPresenter.class.getSimpleName();

    public StewardPresenter(StewardContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected StewardContract.Model createModel() {
        return new StewardModel();
    }

    @Override
    public void start(Object request) {
        mRxManager.add(mModel.requestHouses((Params) request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(iconBeanList -> {
                    ALog.e(Thread.currentThread().getName());
                    iconBeanList.add(new IconBean(R.drawable.ic_add_house_red_140px, "添加房屋", ""));
                    getView().responseHouses(iconBeanList);

                }, this::error)
        );
    }

    @Override
    public void requestContact(Params params) {
        mRxManager.add(mModel.requestContact(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contactBean -> {
                    if (contactBean.getOther().getCode() == 200) {
                        List<String> listPhone = new ArrayList<>();
                        listPhone.add("座机：" + contactBean.getData().getTelephoneNo());
                        listPhone.add("手机：" + contactBean.getData().getMobileNo());
                        getView().responseContact(listPhone);
                    } else {
                        getView().error(contactBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestGetSip(Params params) {
//        ALog.e("111111111");
//        Map params = BaseParams.getTokenMap();
//
//        HttpClient.post(ServiceApi.GET_SIP, params, new BeanCallback<SipBean>() {
//            @Override
//            public void onError(String errMsg) {
//                if (isViewAttached()) {
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(SipBean mSipBean) {
//                if (isViewAttached()) {
//
//                    getView().responseGetSip(mSipBean);
//                }
//            }
//        });
    }


}