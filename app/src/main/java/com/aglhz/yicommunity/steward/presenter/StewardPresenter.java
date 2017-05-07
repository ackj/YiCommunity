package com.aglhz.yicommunity.steward.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.steward.contract.StewardContract;
import com.aglhz.yicommunity.steward.model.StewardModel;

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
//        ALog.e("111111111");
//        Map params = BaseParams.getTokenMap();
//        params.put("cmnt_c", cmnt_c);
//
//        ALog.e(params.get("cmnt_c"));
//
//        HttpClient.post(ServiceApi.CONTACT, params, new BeanCallback<ContactBean>() {
//            @Override
//            public void onError(String errMsg) {
////                if (isViewAttached()) {
////                    getView().error(new Exception());
////                }
//            }
//
//            @Override
//            public void onSuccess(ContactBean bean) {
//                if (isViewAttached()) {
//                    List<String> listPhone = new ArrayList();
//                    if (!TextUtils.isEmpty(bean.getData().getTelephoneNo())) {
//                        listPhone.add("座机：" + bean.getData().getTelephoneNo());
//                    }
//                    if (!TextUtils.isEmpty(bean.getData().getMobileNo())) {
//                        listPhone.add("手机：" + bean.getData().getMobileNo());
//                    }
//                    getView().responseContact(listPhone);
//                }
//            }
//        });
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