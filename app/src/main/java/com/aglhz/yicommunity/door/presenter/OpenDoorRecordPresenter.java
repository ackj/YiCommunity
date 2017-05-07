package com.aglhz.yicommunity.door.presenter;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.door.contract.OpenDoorRecordContract;

import java.util.Map;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class OpenDoorRecordPresenter extends BasePresenter<OpenDoorRecordContract.View, OpenDoorRecordContract.Model> implements OpenDoorRecordContract.Presenter {
    private final String TAG = OpenDoorRecordPresenter.class.getSimpleName();

    public OpenDoorRecordPresenter(OpenDoorRecordContract.View mView) {
        super(mView);
    }


    @Override
    public void start(Object request) {

    }

    @Override
    public void requestRecord(String token) {
        ALog.e("2222222222");
        Map params = BaseParams.getTokenMap();
        ALog.e(params.get("token"));

//        HttpClient.post(ServiceApi.OPEN_DOOR_RECORD, params, new BeanCallback<OpenDoorRecordBean>() {
//            @Override
//            public void onError(String errMsg) {
//                if (isViewAttached()) {
//                    ALog.e("3333333333333");
//
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(OpenDoorRecordBean bean) {
//                if (isViewAttached()) {
//                    ALog.e("444444444444");
//
//                    if (bean.getOther().getCode() == 200) {
//
//                        getView().responseRecord(bean.getData());
//                    } else {
//                        getView().error(null);
//                    }
//                }
//
//            }
//        });
    }
}