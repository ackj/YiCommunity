package com.aglhz.yicommunity.common;

import com.aglhz.yicommunity.bean.BannerBean;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.CheckTokenBean;
import com.aglhz.yicommunity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.bean.MessageCenterBean;
import com.aglhz.yicommunity.bean.NoticeBean;
import com.aglhz.yicommunity.bean.ParkRecordBean;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.bean.RepairApplyBean;
import com.aglhz.yicommunity.bean.UserBean;

import java.io.File;
import java.util.List;

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

    //登出
    @POST("/memberSYS-m/client/logout.do")
    Observable<BaseBean> logout(@Query("token") String token);

    //开门
    @POST("/sub_property_ysq/smartdoor/client/opendoor")
    Observable<BaseBean> openDoor(@Query("token") String token, @Query("dir") String dir);

    //消息中心
    @POST("/sub_property_ysq/client/info/msgList")
    Observable<MessageCenterBean> requestMessages(@Query("token") String token);

    //社区Banner
    @POST("/sub_property_ysq/client/info/indexadvs")
    Observable<BannerBean> getBanners();

    //社区通知列表
    @POST("/sub_property_ysq/client/info/noticeList")
    Observable<NoticeBean> getNotice(@Query("token") String token, @Query("cmnt_c") String cmnt_c, @Query("topnum") int topnum);

    //物业缴费
    @POST("/sub_property_ysq/client/info/pptBillsWait.do")
    Observable<PropertyPayBean> getPropertyPay(@Query("token") String token, @Query("cmnt_c") String cmnt_c, @Query("page") int page);

    //社区地址列表
    @POST("/propertyCFG-m/client/communityList.do")
    Observable<CommunitySelectBean> getCommunityList(@Query("sc") String sc, @Query("page") int page, @Query("pageSize") int pageSize);

    //提交管理投诉
    @POST("/sub_property_ysq/property/complaint/from-client/complaint-create")
    Observable<BaseBean> postComplain(@Query("token") String token,
                                      @Query("cmnt_c") String cmnt_c,
                                      @Query("name") String name,
                                      @Query("phoneNo") String phoneNo,
                                      @Query("content") String content,
                                      @Query("type") int type,
                                      @Query("file") List<File> files);

    //停车记录
    @POST("/sub_property_ysq/park/record/to-client/record-list")
    Observable<ParkRecordBean> getParkRecord(@Query("token") String token);

    //物业报修列表
    @POST("/sub_property_ysq/client/info/repairApplyList")
    Observable<RepairApplyBean> getRepairApply(@Query("token") String token);

}
