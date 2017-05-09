//package com.aglhz.yicommunity.house.presenter;
//
//import android.support.annotation.NonNull;
//
//import com.aglhz.abase.log.ALog;
//import com.aglhz.abase.mvp.presenter.base.BasePresenter;
//import com.aglhz.yicommunity.bean.BaseBean;
//import com.aglhz.yicommunity.bean.HouseRightsBean;
//import com.aglhz.yicommunity.common.BaseParams;
//import com.aglhz.yicommunity.common.ServiceApi;
//import com.aglhz.yicommunity.house.contract.HouseRightsContract;
//import com.aglhz.yicommunity.house.model.HouseRightsModel;
//
//import org.apache.http.client.HttpClient;
//
//import java.util.Map;
//
///**
// * Author：leguang on 2016/10/9 0009 10:35
// * Email：langmanleguang@qq.com
// * <p>
// * 负责房屋模块Presenter层内容。
// */
//
//public class HouseRightsPresenter extends BasePresenter<HouseRightsContract.View, HouseRightsContract.Model> implements HouseRightsContract.Presenter {
//    private final String TAG = HouseRightsPresenter.class.getSimpleName();
//
//    public HouseRightsPresenter(HouseRightsContract.View mView) {
//        super(mView);
//    }
//
//    @NonNull
//    @Override
//    protected HouseRightsContract.Model createModel() {
//        return new HouseRightsModel();
//    }
//
//    @Override
//    public void start() {
//        ALog.e("NeighbourPresenter::start");
//
//    }
//
//    @Override
//    public void requestRights(String token, String fid) {
//        Map<String, Object> param = BaseParams.getTokenMap();
//        param.put("fid", fid);
//
//
//        ALog.e("token::" + param.get("token"));
//        ALog.e("fid::" + param.get("fid"));
//
//        HttpClient.post(ServiceApi.HOUSE_RIGHTS, param, new BeanCallback<HouseRightsBean>() {
//            @Override
//            public void onError(String errMsg) {
//                ALog.a("onErroronErroronErroronError");
//                if (isViewAttached()) {
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(HouseRightsBean mHouseRights) {
//                ALog.a("onSuccessonSuccessonSuccessonSuccess");
//
//                if (mHouseRights.getOther().getCode() == 200) {
//
//                    ALog.a("申请成功");
//                    if (isViewAttached()) {
//                        getView().responseRights(mHouseRights);
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    public void requestUpdateRights(String url, String mfid, String fid, String picode, int status) {
//        Map<String, Object> param = BaseParams.getTokenMap();
//        param.put("mfid", mfid);
//        param.put("fid", fid);
//        param.put("picode", picode);
//        param.put("status", status);
//
//        ALog.e("url::" + url);
//        ALog.e("token::" + param.get("token"));
//        ALog.e("mfid::" + param.get("mfid"));
//        ALog.e("fid::" + param.get("fid"));
//        ALog.e("picode::" + param.get("picode"));
//        ALog.e("status::" + param.get("status"));
//
//        HttpClient.post(url, param, new BeanCallback<BaseBean>() {
//            @Override
//            public void onError(String errMsg) {
//
//                if (isViewAttached()) {
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(BaseBean mBaseBean) {
//                if (mBaseBean.getOther().getCode() == 200) {
//
//                    ALog.a("申请成功");
//                    if (isViewAttached()) {
//                        getView().responseUpdateRights(mBaseBean);
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    public void requestDelete(String token, String fid, String mfid) {
//        Map<String, Object> param = BaseParams.getTokenMap();
//        param.put("fid", fid);
//        param.put("mfid", mfid);
//
//        ALog.e("token::" + param.get("token"));
//        ALog.e("fid::" + param.get("fid"));
//        ALog.e("mfid::" + param.get("mfid"));
//
//        HttpClient.post(ServiceApi.DELETE_MEMBER, param, new BeanCallback<BaseBean>() {
//            @Override
//            public void onError(String errMsg) {
//
//                if (isViewAttached()) {
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(BaseBean mBaseBean) {
//                if (mBaseBean.getOther().getCode() == 200) {
//
//                    ALog.a("申请成功");
//                    if (isViewAttached()) {
//                        getView().responseDelete(mBaseBean);
//                    }
//                }
//            }
//        });
//    }
//}