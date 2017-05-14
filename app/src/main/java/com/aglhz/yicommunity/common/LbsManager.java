package com.aglhz.yicommunity.common;


import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.BaseApplication;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;


/**
 * 选择 地址 map
 * <p>
 * Created by YandZD on 2016/8/22.
 */

public class LbsManager {
    private static final String TAG = LbsManager.class.getSimpleName();
    private static AMapLocationClient mLocationClient;
    private static LocateCallBack mCallBack;
    private static volatile LbsManager INSTANCE;

    //构造方法私有
    private LbsManager() {
        init();
    }

    //获取单例
    public static LbsManager getInstance() {
        if (INSTANCE == null) {
            synchronized (LbsManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LbsManager();
                }
            }
        }
        return INSTANCE;
    }


    private void init() {
        //初始化定位
        if (mLocationClient != null) {
            return;
        }
        mLocationClient = new AMapLocationClient(BaseApplication.mContext);
        //设置定位回调监听

        //初始化AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //该方法默认为false。
        mLocationOption.setOnceLocation(false);

        //设置setOnceLocationLatest(boolean b)接口为true，
        // 启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        //获取最近3s内精度最高的一次定位结果：
        mLocationOption.setOnceLocationLatest(false);

        //定位的间隔
        mLocationOption.setInterval(1000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }


    public void startLocation(LocateCallBack callBack) {
        try {
            //启动定位
            mCallBack = callBack;

            mLocationClient.setLocationListener(amapLocation -> {
                if (mCallBack != null) {
                    mCallBack.CallBack(amapLocation);
                }

                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        ALog.e(amapLocation.toString());
                        UserHelper.setCity(amapLocation.getCity());
                        UserHelper.setLatitude(String.valueOf(amapLocation.getLatitude()));
                        UserHelper.setLongitude(String.valueOf(amapLocation.getLongitude()));
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        ALog.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            });

            mLocationClient.startLocation();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void startLocation() {
        mLocationClient.startLocation();
    }

    public void stopLocation() {
        if (mLocationClient == null) {
            return;
        }
        mLocationClient.stopLocation();
    }


    //若销毁，则需要重新创建LocationClient，所以一般只要stopLocation。
    public void clear() {
        if (mLocationClient == null) {
            return;
        }
        if (mLocationClient.isStarted()) {
            mLocationClient.stopLocation();
        }
        mLocationClient.onDestroy();
    }

    public interface LocateCallBack {

        void CallBack(AMapLocation aMapLocation);
    }
}
