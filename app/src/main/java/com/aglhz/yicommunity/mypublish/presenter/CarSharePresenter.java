package com.aglhz.yicommunity.mypublish.presenter;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.LbsManager;
import com.aglhz.yicommunity.mypublish.contract.CarShareContract;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
 */

public class CarSharePresenter extends BasePresenter<CarShareContract.View, CarShareContract.Model> implements CarShareContract.Presenter {
    private final String TAG = CarSharePresenter.class.getSimpleName();
    private String mLat = "", mLng = "";
    private String URL;

    public CarSharePresenter(CarShareContract.View mView) {
        super(mView);
    }

    @Override
    public void start(Object request) {
        ALog.e("1111111111111");
        LbsManager.getInstance().startLocation(aMapLocation -> {
            mLat = aMapLocation.getLatitude() + "";
            mLng = aMapLocation.getLongitude() + "";

        });
        requestData(0);
    }

    @Override
    public void requestData(final int page) {
//        if (!UserInfoHelper.isLogin()) {
//            return;
//        }
//
//        if (page == 0) {
//            URL = ServiceApi.carpoolMineList;
//        }
//
//
//        HttpClient.post(URL, params, new BeanCallback<CarpoolServiceListBean>() {
//            @Override
//            public void onError(String errMsg) {
//                if (isViewAttached()) {
//                    getView().loadComplete();
//                    ALog.e("222222222");
//                    if (page != 0) {
//                        getView().addErrorFooter();
//                    } else {
//                        getView().error(null);
//                    }
//                }
//            }
//
//            @Override
//            public void onSuccess(CarpoolServiceListBean bean) {
//                ALog.e("333333333");
//                if (isViewAttached()) {
//                    getView().loadComplete();
//
//                    getView().loadComplete();
//                    if (null != bean.getData() && null != bean.getData().getCarpoolList()
//                            && bean.getData().getCarpoolList().size() > 0) {
//                        getView().responseData(bean.getData().getCarpoolList(), page);
//                    } else {
//                        getView().empty();
//                        return;//此处需要return，因为没必要再add footer了。
//                    }
//
//                    if (!TextUtils.isEmpty(bean.getOther().getNext())) {
//                        URL = bean.getOther().getNext();
//                        getView().addLoadingFooter();
//                    } else if (page != 0) {
//                        getView().end();
//                    }
//                }
//            }
//        });
    }
}