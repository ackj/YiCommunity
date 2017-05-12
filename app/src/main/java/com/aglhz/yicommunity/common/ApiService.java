package com.aglhz.yicommunity.common;

import com.aglhz.yicommunity.bean.BannerBean;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.BuildingBean;
import com.aglhz.yicommunity.bean.CheckTokenBean;
import com.aglhz.yicommunity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.bean.ContactBean;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.bean.FloorBean;
import com.aglhz.yicommunity.bean.HouseRightsBean;
import com.aglhz.yicommunity.bean.MessageCenterBean;
import com.aglhz.yicommunity.bean.MyHousesBean;
import com.aglhz.yicommunity.bean.NeighbourListBean;
import com.aglhz.yicommunity.bean.NoticeBean;
import com.aglhz.yicommunity.bean.OpenDoorRecordBean;
import com.aglhz.yicommunity.bean.ParkRecordBean;
import com.aglhz.yicommunity.bean.PasswordBean;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.bean.RepairApplyBean;
import com.aglhz.yicommunity.bean.RoomBean;
import com.aglhz.yicommunity.bean.UnitBean;
import com.aglhz.yicommunity.bean.UserBean;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

import static com.aglhz.yicommunity.common.UserHelper.token;

/**
 * Created by YandZD on 2017/1/17.
 */

public interface ApiService {

    //基础路径
    String BASE_PROPERTY = "http://www.aglhz.com:8090/sub_property_ysq";   //物业
    String BASE_USER = "http://www.aglhz.com:8076/memberSYS-m";           //用户
    String BASE_PROPERTYCFG_M = "http://www.aglhz.com:8096/propertyCFG-m";

    //********************以下为Web*******************************
    String PRODUCT_INTRODUCTION = "http://www.aglhz.com/sub_property_ysq/m/html/introduction.html";

    String SERVICE_TERMS = "http://www.aglhz.com/sub_property_ysq/m/html/userAgreement.html";

    String INDENT_CENTER = "http://www.aglhz.com/sub_property_ysq/m/mall_zyg/html/newPersonCenter/newOrderCenter.html?appType=2&token=";

    String TEMP_PARKING = "http://www.aglhz.com/sub_property_ysq/m/html/banlicheka.html";

    String SUPERMARKET = "http://www.aglhz.com/mall/m/index.html?appType=2&token=";

    String WULIU_SEARCH = "http://www.aglhz.com/sub_property_ysq/m/mall_zyg/html/wuliuSearch.html?appType=2&token=";

    String JIAZHENG = "http://www.aglhz.com/sub_property_ysq/m/html/jiazheng.html";

    String WEIXIU = "http://www.aglhz.com/sub_property_ysq/m/html/weixiu.html";

    String SONGSHUI = "http://www.aglhz.com/sub_property_ysq/m/html/songshui.html";

    //********************以上为Web*******************************


    //登录验证
//    @POST("/memberSYS-m/client/checkIfTokenInvalid.do")
    String checkToken = BASE_USER + "/client/checkIfTokenInvalid.do";

    @POST
    Observable<CheckTokenBean> checkToken(@Url String url, @Query("token") String token);

    //登录
//    @POST("/memberSYS-m/client/login.do")

    String requestLogin = BASE_USER + "/client/login.do";

    @POST
    Observable<UserBean> requestLogin(@Url String url
            , @Query("sc") String sc
            , @Query("user") String user
            , @Query("pwd") String pwd);

    //登出
    @POST("/memberSYS-m/client/logout.do")
    Observable<BaseBean> requestLogout(@Query("token") String token);

    //开门
//    @POST("/sub_property_ysq/smartdoor/client/opendoor")
    String requestOpenDoor = BASE_PROPERTY + "/smartdoor/client/opendoor";

    @POST
    Observable<BaseBean> requestOpenDoor(@Url String url, @Query("token") String token, @Query("dir") String dir);

    //消息中心
    @POST("/sub_property_ysq/client/info/msgList")
    Observable<MessageCenterBean> requestMessages(@Query("token") String token);

    //社区Banner
//    @POST("/sub_property_ysq/client/info/indexadvs")
    String requestBanners = BASE_PROPERTY + "/client/info/indexadvs";

    @POST
    Observable<BannerBean> requestBanners(@Url String url);

    //物业缴费
    @POST("/sub_property_ysq/client/info/pptBillsWait.do")
    Observable<PropertyPayBean> getPropertyPay(@Query("token") String token, @Query("cmnt_c") String cmnt_c, @Query("page") int page);

    //反馈
    @POST("/sub_property_ysq/client/feedback.do")
    Observable<BaseBean> feedback(@Query("token") String token, @Query("cmnt_c") String cmnt_c, @Query("des") String des, @Query("contact") String contact);

    //更新用户信息
    @POST("/memberSYS-m/client/ updateMemberFieldByToken.do")
    Observable<BaseBean> updateUserData(@Query("token") String token, @Query("field") String field, @Query("val") String val);


    //修改密码
    @POST("/memberSYS-m/client/updateMemberPwd.do")
    Observable<BaseBean> updatePassword(@Query("token") String token, @Query("pwd0") String pwd0, @Query("pwd1") String pwd1, @Query("pwd2") String pwd2);


    //更新头像
    @Multipart
    @POST("/memberSYS-m/client/uploadHeader2.do")
    Observable<BaseBean> updatePortrait(@Query("token") String token, @Part("file") RequestBody file);

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

    //****************以下获取小区，楼栋，单元，楼层，房间等**********************************

    //请求小区列表

    String requestCommunitys = BASE_PROPERTYCFG_M + "/client/communityList.do";

    //    @POST("/propertyCFG-m/client/communityList.do")
    @POST
    Observable<CommunitySelectBean> requestCommunitys(@Url String url, @Query("sc") String sc
            , @Query("page") String page
            , @Query("pageSize") String pageSize
            , @Query("province") String province
            , @Query("city") String city
            , @Query("county") String county);

    //请求楼栋列表
    String requestBuildings = BASE_PROPERTYCFG_M + "/client/buildingList.do";


    //    @POST("/propertyCFG-m/client/buildingList.do")
    @POST
    Observable<BuildingBean> requestBuildings(@Url String url, @Query("sc") String sc
            , @Query("cmnt_c") String cmnt_c);

    //请求单元列表
    String requestUnits = BASE_PROPERTYCFG_M + "/client/buildingUnitList.do";


    //    @POST("/propertyCFG-m/client/buildingUnitList.do")
    @POST
    Observable<UnitBean> requestUnits(@Url String url, @Query("sc") String sc
            , @Query("cmnt_c") String cmnt_c
            , @Query("bdg_c") String bdg_c);

    //请求楼层列表
    String requestFloors = BASE_PROPERTYCFG_M + "/client/floorList.do";


    //    @POST("/propertyCFG-m/client/floorList.do")
    @POST
    Observable<FloorBean> requestFloors(@Url String url, @Query("sc") String sc
            , @Query("cmnt_c") String cmnt_c
            , @Query("bdg_c") String bdg_c
            , @Query("bdg_u_c") String bdg_u_c);

    //请求房间列表
    String requestRooms = BASE_PROPERTYCFG_M + "/client/houseList.do";


    //    @POST("/propertyCFG-m/client/houseList.do")
    @POST
    Observable<RoomBean> requestRooms(@Url String url, @Query("sc") String sc
            , @Query("cmnt_c") String cmnt_c
            , @Query("bdg_c") String bdg_c
            , @Query("bdg_u_c") String bdg_u_c
            , @Query("bdg_f_c") String bdg_f_c);


    //业主申请
    String ownerApply = BASE_PROPERTY + "/client/ownerApply.do";

    //申请成员
    String fmApply = BASE_PROPERTY + "/client/fmApply";

    @POST
    Observable<BaseBean> requestApply(@Url String url
            , @Query("token") String token
            , @Query("cmnt_c") String cmnt_c
            , @Query("bdg_c") String bdg_c
            , @Query("bdg_u_c") String bdg_u_c
            , @Query("bdg_f_c") String bdg_f_c
            , @Query("bdg_f_h_c") String bdg_f_h_c
            , @Query("applyName") String applyName
            , @Query("idNO") String idNO);


    //****************获取小区，楼栋，单元，楼层，房间等**********************************


    //***************根据社区代号得到物业联系方式***********************
    String CONTACT = BASE_PROPERTY + "/property/contact-property/to-client/contact-info";

    //    @POST("/sub_property_ysq/property/contact-property/to-client/contact-info")
    @POST
    Observable<ContactBean> requestContact(@Url String url, @Query("token") String token, @Query("cmnt_c") String cmnt_c);
    //***************根据社区代号得到物业联系方式***********************


    @POST("/sub_property_ysq/client/repair")
    Observable<BaseBean> postRepair(@Query("token") String token,
                                    @Query("cmnt_c") String cmnt_c,
                                    @Query("contact") String contact,
                                    @Query("des") String des,
                                    @Query("name") String name,
                                    @Query("single") boolean single,
                                    @Query("file") List<File> files,
                                    @Query("type") int type);

    //获取门禁列表
    @POST("/sub_property_ysq/smartdoor/info/doormchs")
    Observable<DoorListBean> getDoorList(@Query("token") String token);

    //指定开门
    @POST("/sub_property_ysq/smartdoor/client/opendoor")
    Observable<BaseBean> appointOpenDoor(@Query("token") String token, @Query("dir") String dir);

    //密码开门
    @POST("/sub_property_ysq/smartdoor/client/temppwd")
    Observable<PasswordBean> getPassword(@Query("token") String token, @Query("dir") String dir);

    //设置快速开门
    @POST("/sub_property_ysq/smartdoor/client/qdos")
    Observable<BaseBean> setQuickOpenDoor(@Query("token") String token, @Query("directory") String directory, @Query("deviceName") String deviceName);

    //获取开门记录
    @POST("/sub_property_ysq/smartdoor/info/dooropenlog")
    Observable<OpenDoorRecordBean> getOpenDoorRecord(@Query("token") String token);

    //注册
    @POST("/memberSYS-m/client/register.do")
    Observable<BaseBean> register(@Query("sc") String sc, @Query("account") String account, @Query("code") String code, @Query("Password1") String password1, @Query("Password2") String password2);

    //找回密码
    @POST("/memberSYS-m/client/renewMemberPwd.do")
    Observable<BaseBean> forgetPwd();

    //获取验证码
    @POST("/memberSYS-m/client/validCode.do")
    Observable<BaseBean> getVerifyCode(@Query("sc") String sc, @Query("phone") String phone, @Query("type") String type);


    //***********以下房屋权限系列接口********************************

    //获取我的房屋
//    @POST("/sub_property_ysq/client/info/authBdgs.do")

    String requestMyhouses = BASE_PROPERTY + "/client/info/authBdgs.do";

    @POST
    Flowable<MyHousesBean> requestMyhouses(
            @Url String url,
            @Query("token") String token,
            @Query("cmnt_c") String cmnt_c);


    String UPDATE_RIGHTS_MYSELF = BASE_PROPERTY + "/smartdoor/client/powerset";

    String UPDATE_RIGHTS_OTHER = BASE_PROPERTY + "/smartdoor/client/enmemberpower";

    String requestRights = BASE_PROPERTY + "/smartdoor/info/authBdgMemAcsPow";


    @POST
    Observable<HouseRightsBean> requestRights(@Url String url
            , @Query("token") String token
            , @Query("fid") String fid);

    @POST
    Observable<BaseBean> requestUpdateRights(@Url String url
            , @Query("token") String token
            , @Query("mfid") String mfid
            , @Query("fid") String fid
            , @Query("picode") String picode
            , @Query("status") int status);

    @POST
    Observable<BaseBean> requestDelete(@Url String url
            , @Query("token") String token
            , @Query("mfid") String mfid
            , @Query("fid") String fid);

    //获取物业公告列表
//    @POST("/sub_property_ysq/client/info/noticeList")
    String requestNotices = BASE_PROPERTY + "/client/info/noticeList";

    @POST
    Observable<NoticeBean> requestNotices(@Url String url,
                                          @Query("token") String token,
                                          @Query("cmnt_c") String cmnt_c,
                                          @Query("summerable") boolean summerable,
                                          @Query("timeable") boolean timeable);

    //物业详情
    String requestNoticeDetail = BASE_PROPERTY + "/m/html/noticeDetail.html?fid=";


    //获取邻里圈列表
    @POST("/sub_property_ysq/neighbor/moments/to-client/moments-list")
    Observable<NeighbourListBean> getNeighbourList(@Query("page") int page, @Query("pageSize") int pageSize);


    //首页获取公告列表
    //    @POST("/sub_property_ysq/client/info/noticeTop")
    String requestHomeNotices = BASE_PROPERTY + "/client/info/noticeTop";

    @POST
    Observable<NoticeBean> requestHomeNotices(@Url String url,
                                              @Query("token") String token,
                                              @Query("cmnt_c") String cmnt_c);

}
