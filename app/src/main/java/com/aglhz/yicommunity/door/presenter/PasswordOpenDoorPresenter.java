package com.aglhz.yicommunity.door.presenter;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.door.contract.PasswordOpenDoorContract;

import java.util.Map;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class PasswordOpenDoorPresenter extends BasePresenter<PasswordOpenDoorContract.View, PasswordOpenDoorContract.Model> implements PasswordOpenDoorContract.Presenter {
    private final String TAG = PasswordOpenDoorPresenter.class.getSimpleName();

    public PasswordOpenDoorPresenter(PasswordOpenDoorContract.View mView) {
        super(mView);
    }

    @Override
    public void start(Object request) {
        ALog.e("::start");
    }

    @Override
    public void requestPassword(String token, String dir) {
        ALog.e("2222222222");

        Map params = BaseParams.getTokenMap();
        params.put("dir", dir);

        ALog.e(params.get("token"));
        ALog.e(params.get("dir"));

//        HttpClient.post(ServiceApi.PASSWORD_OPEN_DOOR, params, new BeanCallback<PasswordBean>() {
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
//            public void onSuccess(PasswordBean mPasswordBean) {
//                if (isViewAttached()) {
//                    ALog.e("444444444444");
//
//                    if (mPasswordBean.getOther().getCode() == 200) {
//
//                        getView().responsePassword(mPasswordBean);
//                    } else {
//                        getView().error(null);
//                    }
//                }
//
//            }
//        });
    }
}