package com.aglhz.yicommunity.common;

import android.content.Context;

import com.aglhz.abase.log.ALog;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * 选择 地址 map
 * <p>
 * Created by YandZD on 2016/8/22.
 */

public class LbsManager {
    public interface LocateCallBack {
        void CallBack(AMapLocation aMapLocation);
    }

    private static AMapLocationClient mLocationClient;
    private static LocateCallBack mCallBack;
    private static boolean isOnceLocation = true;
    private static int mInterval = 0;

    public static void initMap(Context context) {
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(new local());
        //初始化AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //该方法默认为false。
        mLocationOption.setOnceLocation(isOnceLocation);

        //定位的间隔
        mLocationOption.setInterval(mInterval);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    //设置是否单次定位
    public static void setLocationInfo(boolean isOnce, int interval) {
        isOnceLocation = isOnce;
        mInterval = interval;
    }

    public static void actionLocation(LocateCallBack callBack) {
        //启动定位
        mLocationClient.startLocation();
        mCallBack = callBack;
    }


    static class local implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            ALog.e(aMapLocation.getAddress());
            String address = aMapLocation.getDistrict() + aMapLocation.getRoad() + aMapLocation.getAoiName();

            ALog.e(address);

            mCallBack.CallBack(aMapLocation);

            mCallBack = null;    //回调完销毁掉，避免内存益处
        }
    }
}
