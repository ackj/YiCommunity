package com.aglhz.yicommunity.common;

import com.aglhz.yicommunity.bean.BannerBean;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.CheckTokenBean;
import com.aglhz.yicommunity.bean.NoticeBean;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by YandZD on 2017/1/17.
 */

public interface ApiService {

    String PRODUCT_INTRODUCTION = "http://www.aglhz.com/sub_property_ysq/m/html/introduction.html";

    String SERVICE_TERMS = "http://www.aglhz.com/sub_property_ysq/m/html/userAgreement.html";

    String INDENT_CENTER = "http://www.aglhz.com/mall/m/html/newPersonCenter/newOrderCenter.html?appType=2&token=";

    //登录验证
    @POST("/memberSYS-m/client/checkIfTokenInvalid.do")
    Observable<CheckTokenBean> checkToken(@Query("token") String token);

    //登录
    @POST("/memberSYS-m/client/login.do")
    Observable<UserBean> login(@Query("sc") String sc, @Query("user") String user, @Query("pwd") String pwd);

    //开门
    @POST("/sub_property_ysq/smartdoor/client/opendoor")
    Observable<BaseBean> openDoor(@Query("token") String token, @Query("dir") String dir);

    //Banner
    @POST("/sub_property_ysq/client/info/indexadvs")
    Observable<BannerBean> getBanners();

    @POST("/sub_property_ysq/client/info/noticeList")
    Observable<NoticeBean> getNotice(@Query("token") String token,@Query("cmnt_c")String cmnt_c,@Query("topnum")int topnum);

    @POST("/sub_property_ysq/client/info/pptBillsWait.do")
    Observable<PropertyPayBean> getPropertyPay(@Query("token") String token,@Query("cmnt_c")String cmnt_c, @Query("page")int page);
}
