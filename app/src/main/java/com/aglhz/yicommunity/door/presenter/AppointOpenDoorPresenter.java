package com.aglhz.yicommunity.door.presenter;


import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.door.contract.AppointOpenDoorContract;

import java.util.Map;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class AppointOpenDoorPresenter extends BasePresenter<AppointOpenDoorContract.View, AppointOpenDoorContract.Model> implements AppointOpenDoorContract.Presenter {
    private final String TAG = AppointOpenDoorPresenter.class.getSimpleName();

    public AppointOpenDoorPresenter(AppointOpenDoorContract.View mView) {
        super(mView);
    }

    @Override
    public void start(Object request) {
        ALog.e("NeighbourPresenter::start");

    }

    @Override
    public void requestDoorList(String token) {

//        Map params = BaseParams.getTokenMap();
//        HttpClient.post(ServiceApi.DOORLIST, params, new BeanCallback<DoorListBean>() {
//            @Override
//            public void onError(String errMsg) {
//                if (isViewAttached()) {
//                    getView().error(new Exception());
//                }
//            }
//
//            @Override
//            public void onSuccess(DoorListBean mDoorListBean) {
//                if (isViewAttached()) {
//
//                    getView().responseDoorList(mDoorListBean);
//                }
//
//            }
//        });
    }

    @Override
    public void requestAppointOpenDoor(String token, String dir) {
        ALog.e("2222222222");

        Map params = BaseParams.getTokenMap();
        params.put("dir", dir);

        ALog.e(params.get("token"));
        ALog.e(params.get("dir"));

//        HttpClient.post(ServiceApi.APPOINT_OPEN_DOOR, params, new BeanCallback<BaseBean>() {
//            @Override
//            public void onError(String errMsg) {
//                if (isViewAttached()) {
//                    ALog.e("3333333333333");
//
//                    getView().error(new Exception());
//                }
//            }
//
//            @Override
//            public void onSuccess(BaseBean mBaseBean) {
//                if (isViewAttached()) {
//                    ALog.e("444444444444");
//
//                    getView().responseAppointOpenDoor(mBaseBean);
//                }
//
//            }
//        });
    }
}