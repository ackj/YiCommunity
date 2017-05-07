package com.aglhz.yicommunity.door.presenter;


import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.door.contract.QuickOpenDoorContract;


import java.util.Map;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class QuickOpenDoorPresenter extends BasePresenter<QuickOpenDoorContract.View, QuickOpenDoorContract.Model> implements QuickOpenDoorContract.Presenter {
    private final String TAG = QuickOpenDoorPresenter.class.getSimpleName();

    public QuickOpenDoorPresenter(QuickOpenDoorContract.View mView) {
        super(mView);
    }


//    @Override
//    public void start() {
//        requestDoorList("");
//    }

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
    public void start(Object request) {

    }

    @Override
    public void requestQuickOpenDoor(String token, String directory, String deviceName) {
        ALog.e("2222222222");

        Map params = BaseParams.getTokenMap();
        params.put("directory", directory);
        params.put("deviceName", deviceName);

        ALog.e(params.get("token"));
        ALog.e(params.get("directory"));
        ALog.e(params.get("deviceName"));

//        HttpClient.post(ServiceApi.SETQUICKOPEN, params, new BeanCallback<BaseBean>() {
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
//                    getView().responseQuickOpenDoor(mBaseBean);
//                }
//
//            }
//        });
    }
}