package com.aglhz.yicommunity.steward.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.steward.contract.StewardContract;
import com.aglhz.yicommunity.steward.model.StewardModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

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

    }

//    @Override
//    public void start() {
//        ALog.e("NeighbourPresenter::start");
//        getMyHouse();
//        requestContact(SelectCommunityHelper.getCommunityId());
//    }

    private void getMyHouse() {
//        Map params = BaseParams.getTokenMap();
//        HttpClient.post(ServiceApi.authBdgs, params, new BeanCallback<MyHourseBean>() {
//            @Override
//            public void onError(String errMsg) {
//                if (isViewAttached()) {
//                    getView().error(new Exception());
//                }
//            }
//
//            @Override
//            public void onSuccess(MyHourseBean bean) {
//                if (isViewAttached()) {
//                    List<IconBean> listIcons = new ArrayList<IconBean>();
//                    if (bean != null && !bean.getData().getAuthBuildings().isEmpty()) {
//                        for (MyHourseBean.DataBean.AuthBuildingsBean authBuildingsBean : bean.getData().getAuthBuildings()) {
//                            listIcons.add(new IconBean(R.drawable.ic_my_house_red_140px, authBuildingsBean.getB_name(), authBuildingsBean.getFid()));
//                            ALog.e("fid::" + authBuildingsBean.getFid());
//                        }
//                    }
//                    getView().responseHouses(listIcons);
//                }
//
//            }
//        });
    }


    @Override
    public void requestContact(String cmnt_c) {

        Params params = Params.getInstance();
        params.cmnt_c = "KBSJ-agl-00005";

        mRxManager.add(mModel.getContact(params)
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
    public void requestGetSip(String token) {
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