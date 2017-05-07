package com.aglhz.yicommunity.door.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.door.contract.DoorContract;
import com.aglhz.yicommunity.door.model.DoorModel;

import java.util.Map;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class DoorPresenter extends BasePresenter<DoorContract.View, DoorContract.Model> implements DoorContract.Presenter {
    private final String TAG = DoorPresenter.class.getSimpleName();

    public DoorPresenter(DoorContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected DoorContract.Model createModel() {
        return new DoorModel();
    }


    @Override
    public void requestDoorList(String token) {

        Map params = BaseParams.getTokenMap();
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
    public void requestSave(String token, String directory, String deviceName) {

        Map params = BaseParams.getTokenMap();
        params.put("directory", directory);
        params.put("deviceName", deviceName);

//        HttpClient.post(ServiceApi.SETQUICKOPEN, params, new BeanCallback<SetQuickOpenBean>() {
//            @Override
//            public void onError(String errMsg) {
//                if (isViewAttached()) {
//                    getView().error(new Exception());
//                }
//            }
//
//            @Override
//            public void onSuccess(SetQuickOpenBean mSetQuickOpenBean) {
//                if (isViewAttached()) {
//
//                    getView().responseSave(mSetQuickOpenBean);
//                }
//
//            }
//        });
    }

    @Override
    public void start(Object request) {

    }
}