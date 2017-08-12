package com.aglhz.yicommunity.common;

import com.aglhz.yicommunity.entity.bean.AppUpdateBean;
import com.aglhz.yicommunity.entity.bean.BannerBean;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.BuildingBean;
import com.aglhz.yicommunity.entity.bean.CarCardBean;
import com.aglhz.yicommunity.entity.bean.CarCardListBean;
import com.aglhz.yicommunity.entity.bean.CardRechargeBean;
import com.aglhz.yicommunity.entity.bean.CheckTokenBean;
import com.aglhz.yicommunity.entity.bean.CommentListBean;
import com.aglhz.yicommunity.entity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.entity.bean.ComplainReplyBean;
import com.aglhz.yicommunity.entity.bean.ContactBean;
import com.aglhz.yicommunity.entity.bean.DoorListBean;
import com.aglhz.yicommunity.entity.bean.FirstLevelBean;
import com.aglhz.yicommunity.entity.bean.FloorBean;
import com.aglhz.yicommunity.entity.bean.GoodsBean;
import com.aglhz.yicommunity.entity.bean.HouseRightsBean;
import com.aglhz.yicommunity.entity.bean.MessageCenterBean;
import com.aglhz.yicommunity.entity.bean.MonthCardBillListBean;
import com.aglhz.yicommunity.entity.bean.MonthCardRuleListBean;
import com.aglhz.yicommunity.entity.bean.MyHousesBean;
import com.aglhz.yicommunity.entity.bean.NoticeBean;
import com.aglhz.yicommunity.entity.bean.OneKeyDoorBean;
import com.aglhz.yicommunity.entity.bean.OpenDoorRecordBean;
import com.aglhz.yicommunity.entity.bean.ParkOrderBean;
import com.aglhz.yicommunity.entity.bean.ParkRecordListBean;
import com.aglhz.yicommunity.entity.bean.ParkSelectBean;
import com.aglhz.yicommunity.entity.bean.PasswordBean;
import com.aglhz.yicommunity.entity.bean.PropertyPayBean;
import com.aglhz.yicommunity.entity.bean.PropertyPayDetailBean;
import com.aglhz.yicommunity.entity.bean.RemarkListBean;
import com.aglhz.yicommunity.entity.bean.RepairApplyBean;
import com.aglhz.yicommunity.entity.bean.RepairDetailBean;
import com.aglhz.yicommunity.entity.bean.RepairTypesBean;
import com.aglhz.yicommunity.entity.bean.RoomBean;
import com.aglhz.yicommunity.entity.bean.ServiceDetailBean;
import com.aglhz.yicommunity.entity.bean.ServicesListBean;
import com.aglhz.yicommunity.entity.bean.ServicesTypesBean;
import com.aglhz.yicommunity.entity.bean.SipBean;
import com.aglhz.yicommunity.entity.bean.SocialityListBean;
import com.aglhz.yicommunity.entity.bean.SubCategoryBean;
import com.aglhz.yicommunity.entity.bean.UnitBean;
import com.aglhz.yicommunity.entity.bean.UnreadMessageBean;
import com.aglhz.yicommunity.entity.bean.UserBean;
import com.aglhz.yicommunity.entity.bean.UserDataBean;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by leguang on 2017/4/16 0016.
 * Email：langmanleguang@qq.com
 * <p>
 * 项目接口集合
 */

public interface ApiService {

    //*************以下基础路径*******************
    String BASE_PROPERTY = Constants.BASE_PROPERTY;   //物业
    String BASE_USER = Constants.BASE_USER;           //用户
    String BASE_PROPERTYCFG_M = Constants.BASE_PROPERTYCFG_M;

    //*************以上基础路径*******************


    //********************以下为Web*******************************
    String PRODUCT_INTRODUCTION = "http://www.aglhz.com/sub_property_ysq/m/html/introduction.html";

    String SERVICE_TERMS = "http://www.aglhz.com/sub_property_ysq/m/html/userAgreement.html";

    String INDENT_CENTER = "http://www.aglhz.com/sub_property_ysq/m/mall_zyg/html/newPersonCenter/newOrderCenter.html?appType=2&token=";

    String TEMP_PARKING = "http://www.aglhz.com/sub_property_ysq/m/html/banlicheka.html";

    //生活超市
    String SUPERMARKET = "http://www.aglhz.com/mall/m/index.html?appType=2&payFrom=2&token=%1&lng=%2&lat=%3";

    String WULIU_SEARCH = "http://www.aglhz.com/sub_property_ysq/m/mall_zyg/html/wuliuSearch.html?appType=2&token=";

    String JIAZHENG = "http://www.aglhz.com/sub_property_ysq/m/html/jiazheng.html";

    String WEIXIU = "http://www.aglhz.com/sub_property_ysq/m/html/weixiu.html";

    String SONGSHUI = "http://www.aglhz.com/sub_property_ysq/m/html/songshui.html";

    //智能设备
    String SMART_DEVICE = "http://www.aglhz.com/sub_property_ysq/m/html/zhinengshebei.html";

    //我的地址
    String MY_ADDRESS = "http://www.aglhz.com/sub_property_ysq/m/mall_zyg/html/addressee_list.html?appType=2&token=";

    //物业详情
    String requestNoticeDetail = "http://www.aglhz.com/sub_property_ysq/m/html/noticeDetail.html?fid=";

    //闲置交换用户协议
    String AGREEMENT_EXCHANGE = "http://www.aglhz.com/sub_property_ysq/m/html/xianzhijiaohuanUserAgreement.html";
    //拼车服务用户协议
    String AGREEMENT_CARPOOL = "http://www.aglhz.com/sub_property_ysq/m/html/pincheUserAgreement.html";

    String REPORT_URL = "http://www.aglhz.com/sub_property_ysq/m/html/report.html?token=%1$s&infoType=%2$s&infoFid=%3$s&appType=2";

    String REPORT_SERVICE_URL = "http://www.aglhz.com/sub_property_ysq/m/html/report.html?token=%1$s&infoFid=%2$s&appType=2";

    String BUSINESS_LICENSE_URL = "http://www.aglhz.com/sub_property_ysq/wx_public/html/merchantLicense.html?fid=";

    //********************以上为Web*******************************

    //********************以下为更新App接口*******************************
    String requestAppUpdatae = BASE_PROPERTY + "/app/version/to-client/show-lastest-version";

    @POST
    Observable<AppUpdateBean> requestAppUpdatae(@Url String url);

    //********************以上为更新App接口*******************************


    //登录验证
//    @POST("/memberSYS-m/client/checkIfTokenInvalid.do")
    String requestCheckToken = BASE_USER + "/client/checkIfTokenInvalid.do";

    @POST
    Observable<CheckTokenBean> requestCheckToken(@Url String url, @Query("token") String token);

    //登录
    //@POST("/memberSYS-m/client/login.do")

    String requestLogin = BASE_USER + "/client/login.do";

    @POST
    Observable<UserBean> requestLogin(@Url String url
            , @Query("sc") String sc
            , @Query("user") String user
            , @Query("pwd") String pwd);

    //登出
    String requestLogout = BASE_USER + "/client/logout.do";

    //    @POST("/memberSYS-m/client/logout.do")
    @POST
    Observable<BaseBean> requestLogout(
            @Url String url,
            @Query("token") String token);

    //开门
//    @POST("/sub_property_ysq/smartdoor/client/opendoor")
    String requestOpenDoor = BASE_PROPERTY + "/smartdoor/client/opendoor";

    @POST
    Observable<BaseBean> requestOpenDoor(@Url String url, @Query("token") String token, @Query("dir") String dir);

    //消息中心
    String requestMessages = BASE_PROPERTY + "/client/info/msgList";

    //    @POST("/sub_property_ysq/client/info/msgList")
    @POST
    Observable<MessageCenterBean> requestMessages(@Url String url,
                                                  @Query("token") String token,
                                                  @Query("pageSize") String pageSize,
                                                  @Query("page") String page);

    //消息已读
    String requestMessageRead = BASE_PROPERTY + "/client/msgread";

    @POST
    Observable<BaseBean> requestMessageRead(@Url String url, @Query("token") String token, @Query("fid") String fid);


    //消息删除
    String requestDeleteMessages = BASE_PROPERTY + "/client/delete-member-messages";

    @POST
    Observable<BaseBean> requestDeleteMessages(@Url String url,
                                               @Query("token") String token,
                                               @Query("isCleanAll") boolean isCleanAll,
                                               @Query("messageFids") String messageFids);

    //社区Banner
    //@POST("/sub_property_ysq/client/info/indexadvs")
    String requestBanners = BASE_PROPERTY + "/client/info/indexadvs";

    @POST
    Observable<BannerBean> requestBanners(@Url String url,
                                          @Query("cmnt_c") String cmnt_c);

    //反馈
    String requestSubmitFeedback = BASE_PROPERTY + "/client/feedback.do";

    //    @POST/*("/sub_property_ysq/client/feedback.do")*/
    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestSubmitFeedback(@Url String url,
                                               @Field("token") String token,
                                               @Field("cmnt_c") String cmnt_c,
                                               @Field("des") String des,
                                               @Field("contact") String contact);

    //更新用户信息
    //@POST("/memberSYS-m/client/updateMemberFieldByToken.do")
    String requsetUpdateUserData = BASE_USER + "/client/updateMemberFieldByToken.do";

    @POST
    Observable<BaseBean> requsetUpdateUserData(@Url String url, @Body MultipartBody file);


    //修改密码
//    @POST("/memberSYS-m/client/updateMemberPwd.do")
    String requestUpdatePassword = BASE_USER + "/client/updateMemberPwd.do";

    @POST
    Observable<BaseBean> requestUpdatePassword(@Url String url,
                                               @Query("token") String token,
                                               @Query("pwd0") String pwd0,
                                               @Query("pwd1") String pwd1,
                                               @Query("pwd2") String pwd2);

    //更新头像
    String requestUpdatePortrait = BASE_USER + "/client/uploadHeader2.do";

    @POST
    Observable<UserDataBean> requestUpdatePortrait(@Url String url, @Query("token") String token, @Body MultipartBody file);

    //物业报修列表
    String requestRepairApply = BASE_PROPERTY + "/client/info/repairApplyList";

    @POST
    Observable<RepairApplyBean> requestRepairApply(@Url String url,
                                                   @Query("token") String token,
                                                   @Query("cmnt_c") String cmnt_c,
                                                   @Query("pageSize") String pageSize,
                                                   @Query("page") String page);

    //物业报修详情
    String requestRepairDetail = BASE_PROPERTY + "/client/info/repairDet";

    @POST
    Observable<RepairDetailBean> requestRepairDetail(@Url String url, @Query("token") String token, @Query("fid") String fid);


    //报修类型列表
    String requestRepairTypes = BASE_PROPERTY + "/client/info/rpTypes";

    @POST
    Observable<RepairTypesBean> requestRepairTypes(@Url String url, @Query("type") int type, @Query("cmnt_c") String cmnt_c);


    //****************以下获取小区，楼栋，单元，楼层，房间等**********************************

    //请求小区列表
    String requestCommunitys = BASE_PROPERTYCFG_M + "/client/communityList.do";

    //    @POST("/propertyCFG-m/client/communityList.do")
    @FormUrlEncoded
    @POST
    Observable<CommunitySelectBean> requestCommunitys(
            @Url String url
            , @Field("sc") String sc
            , @Field("page") String page
            , @Field("pageSize") String pageSize
            , @Field("province") String province
            , @Field("city") String city
            , @Field("county") String county);

//    @FormUrlEncoded
//    @POST
//    Observable<CommunitySelectBean> requestCommunitys(
//            @Url String url,
//            @FieldMap() Map<String, String> params);

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


    //房屋成员认证审核
    String requestAuthApprove = BASE_PROPERTY + "/client/authApprove";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestAuthApprove(@Url String url,
                                            @Field("token") String token,
                                            @Field("fid") String fid,
                                            @Field("authfid") String authFid,
                                            @Field("status") int status);

    //业主申请
//    String ownerApply = BASE_PROPERTY + "/client/ownerApply.do";
    String ownerApply = BASE_PROPERTY + "/client/apply-to-owner";

    //申请租客
//    String fmApply = BASE_PROPERTY + "/client/fmApply";
    String fmApply = BASE_PROPERTY+"/client/apply-to-tenant";

    //申请家属
//    String relativeApply = BASE_PROPERTY+"/client/renterApply";
    String relativeApply =BASE_PROPERTY+"/client/apply-to-family-member";

    @POST
    Observable<BaseBean> requestApply(@Url String url
            , @Body MultipartBody file);


    //****************获取小区，楼栋，单元，楼层，房间等**********************************


    //***************根据社区代号得到物业联系方式***********************
    String CONTACT = BASE_PROPERTY + "/property/contact-property/to-client/contact-info";

    //    @POST("/sub_property_ysq/property/contact-property/to-client/contact-info")
    @POST
    Observable<ContactBean> requestContact(@Url String url, @Query("token") String token, @Query("cmnt_c") String cmnt_c);
    //***************根据社区代号得到物业联系方式***********************


    //    @POST("/sub_property_ysq/client/repair")
    String postRepair = BASE_PROPERTY + "/client/repair";

    @POST
    Observable<BaseBean> postRepair(@Url String url, @Body MultipartBody file);

    //提交管理投诉
    String requestComplain = BASE_PROPERTY + "/property/complaint/from-client/complaint-create";

    @POST
    Observable<BaseBean> postComplain(@Url String url, @Body MultipartBody file);

    //获取门禁列表
//    @POST("/sub_property_ysq/smartdoor/info/doormchs")

    //    String requestDoors = BASE_PROPERTY + "/smartdoor/info/doormchs";
    String requestDoors = BASE_PROPERTY + "/smartdoor/info/cmnt-device-list ";

    @POST
    Observable<DoorListBean> requestDoors(@Url String url,
                                          @Query("token") String token,
                                          @Query("cmnt_c") String cmnt_c);

    //指定开门
    String requestAppointOpenDoor = BASE_PROPERTY + "/smartdoor/client/opendoor";

    @POST/*("/sub_property_ysq/smartdoor/client/opendoor")*/
    Observable<BaseBean> requestAppointOpenDoor(@Url String url, @Query("token") String token, @Query("dir") String dir);

    //密码开门
    String requestPassword = BASE_PROPERTY + "/smartdoor/client/temppwd";

    @POST/*("/sub_property_ysq/smartdoor/client/temppwd")*/
    Observable<PasswordBean> requestPassword(@Url String url,
                                             @Query("token") String token,
                                             @Query("dir") String dir,
                                             @Query("timeset") int timeset,
                                             @Query("maxTimes") int maxTimes);

    //设置快速开门
    String requestSetQuickOpenDoor = BASE_PROPERTY + "/smartdoor/client/qdos";

    @POST/*("/sub_property_ysq/smartdoor/client/qdos")*/
    Observable<BaseBean> requestSetQuickOpenDoor(@Url String url, @Query("token") String token, @Query("directory") String directory, @Query("deviceName") String deviceName);

    // 设置一键开门
    String requestResetOneKeyOpenDoor = BASE_PROPERTY + "/smartdoor/client/one-key-open-door-setting";

    @POST
    Observable<BaseBean> requestResetOneKeyOpenDoor(@Url String url, @Query("token") String token, @Query("cmnt_c") String cmnt_c, @Query("directories") String directories);

    //获取一键开门列表
    String requestOneKeyOpenDoorDeviceList = BASE_PROPERTY + "/smartdoor/info/one-key-open-door-device-list";

    @POST
    Observable<OneKeyDoorBean> requestOneKeyOpenDoorDeviceList(@Url String url, @Query("token") String token, @Query("cmnt_c") String cmnt_c);

    //获取开门记录
    String requestOpenDoorRecord = BASE_PROPERTY + "/smartdoor/info/dooropenlog";

    @POST
    Observable<OpenDoorRecordBean> requestOpenDoorRecord(@Url String url, @Query("token") String token);

    //注册
    String requestRegister = BASE_USER + "/client/register.do";

    @POST
    Observable<BaseBean> requestRegister(@Url String url, @Query("sc") String sc, @Query("account") String account, @Query("code") String code, @Query("Password1") String password1, @Query("Password2") String password2);

    //重置密码
    String requestResetPassword = BASE_USER + "/client/renewMemberPwd.do";

    @POST
    Observable<BaseBean> requestResetPassword(@Url String url, @Query("sc") String sc, @Query("account") String account, @Query("code") String code, @Query("pwd1") String password1, @Query("pwd2") String password2);

    //获取验证码
    String requestVerifyCode = BASE_USER + "/client/validCode.do";

    @POST
    Observable<BaseBean> requestVerifyCode(@Url String url, @Query("sc") String sc, @Query("phone") String phone, @Query("type") String type);


    //***********以下房屋权限系列接口********************************

    //获取我的房屋
//    @POST("/sub_property_ysq/client/info/authBdgs.do")

//    String requestMyhouses = BASE_PROPERTY + "/client/info/authBdgs.do";
    String requestMyhouses = BASE_PROPERTY + "/client/info/my-house-list";

    //查询用户名下某小区的房屋
    @POST
    Flowable<MyHousesBean> requestMyhouses(@Url String url,
                                           @Query("token") String token,
                                           @Query("cmnt_c") String cmnt_c);

    //查询用户名下所有的房屋
    @POST
    Flowable<MyHousesBean> requestMyhouses(@Url String url,
                                           @Query("token") String token);

    //对自己设置权限
    String UPDATE_RIGHTS_MYSELF = BASE_PROPERTY + "/smartdoor/client/powerset";

    //对成员设置权限
    String UPDATE_RIGHTS_OTHER = BASE_PROPERTY + "/smartdoor/client/enmemberpower";

    //房屋成员及其门禁权限信息
//    String requestRights = BASE_PROPERTY + "/smartdoor/info/authBdgMemAcsPow";
    String requestRights = BASE_PROPERTY + "/smartdoor/info/inhabitant-house-and-power";

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

    String requestCheckPermission = BASE_PROPERTY + "/smartdoor/client/checkAcsPower";

    @POST
    Observable<BaseBean> requestCheckPermission(@Url String url
            , @Query("token") String token
            , @Query("dir") String dir
            , @Query("powerCode") String powerCode);


//    String requestDeleteMember = BASE_PROPERTY + "/client/unauthMember";
    String requestDeleteMember = BASE_PROPERTY + "/client/unauthMember";

    @POST
    Observable<BaseBean> requestDeleteMember(@Url String url
            , @Query("token") String token
            , @Query("fid") String fid
            , @Query("identityType") int identityType
            , @Query("mfid") String mfid);

    //***********以上房屋权限系列接口********************************


    //获取物业公告列表
//    @POST("/sub_property_ysq/client/info/noticeList")
    String requestNotices = BASE_PROPERTY + "/client/info/noticeList";

    @POST
    Observable<NoticeBean> requestNotices(@Url String url,
                                          @Query("token") String token,
                                          @Query("cmnt_c") String cmnt_c,
                                          @Query("page") String page,
                                          @Query("pageSize") String pageSize,
                                          @Query("summerable") boolean summerable,
                                          @Query("timeable") boolean timeable);

//    //获取物业公告
//    @POST("/sub_property_ysq/client/info/noticeList")
//    Observable<NoticeBean> getNoticeList(@Query("token") String token,
//                                         @Query("cmnt_c") String cmnt_c,
//                                         @Query("summerable") boolean summerable,
//                                         @Query("timeable") boolean timeable,
//                                         @Query("page") int page,
//                                         @Query("pageSize") int pageSize);

    //获取邻里圈列表
    String requestNeighbourList = BASE_PROPERTY + "/neighbor/moments/to-client/moments-list";

    @POST
    Observable<SocialityListBean> requestNeighbourList(@Url String url,
                                                       @Query("token") String token,
                                                       @Query("cmnt_c") String cmnt_c,
                                                       @Query("page") int page,
                                                       @Query("pageSize") int pageSize);

    //获取闲置交换
    String requestExchangeList = BASE_PROPERTY + "/neighbor/exchange/to-client/exchange-list";

    @POST
    Observable<SocialityListBean> requestExchangeList(@Url String url, @Query("page") int page, @Query("pageSize") int pageSize);

    //获取拼车服务
    String requestCarpoolList = BASE_PROPERTY + "/neighbor/carpool/to-client/carpool-list/";

    @POST
    Observable<SocialityListBean> requestCarpoolList(@Url String url,
                                                     @Query("token") String token,
                                                     @Query("cmnt_c") String cmnt_c,
                                                     @Query("currentPositionLat") String currentPositionLat,
                                                     @Query("currentPositionLng") String currentPositionLng,
                                                     @Query("page") int page,
                                                     @Query("pageSize") int pageSize);

    //获取我发布的闲置交换
    String requestMyExchangeList = BASE_PROPERTY + "/neighbor/exchange/to-client/exchange-mine-list";

    @POST/*("sub_property_ysq/neighbor/exchange/to-client/exchange-mine-list")*/
    Observable<SocialityListBean> requestMyExchangeList(@Url String url, @Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);

    String requestMyNeighbourList = BASE_PROPERTY + "/neighbor/moments/to-client/moments-mine-list";

    //获取我发布的邻里圈列表
    @POST
    Observable<SocialityListBean> requestMyNeighbourList(@Url String url, @Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);

    String requestMyCarpoolList = BASE_PROPERTY + "/neighbor/carpool/to-client/carpool-mine-list";

    //获取我发布的拼车服务列表
    @POST
    Observable<SocialityListBean> requestMyCarpoolList(@Url String url, @Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);


    //首页获取公告列表
    //    @POST("/sub_property_ysq/client/info/noticeTop")
    String requestHomeNotices = BASE_PROPERTY + "/client/info/noticeTop";

    @POST
    Flowable<NoticeBean> requestHomeNotices(@Url String url,
                                            @Query("token") String token,
                                            @Query("cmnt_c") String cmnt_c,
                                            @Query("topnum") int topnum);


    //获取闲置交换的评论
    String getExchangeComments = BASE_PROPERTY + "/neighbor/exchange/to-client/exchange-comment-list";

    @POST
    Observable<CommentListBean> getExchangeComments(@Url String url, @Query("exchangeFid") String fid, @Query("page") int page, @Query("pageSize") int pageSize);

    //获取Sip账号，用户拨打门禁的电话。
    String requestSip = BASE_PROPERTY + "/smartdoor/client/cngsip";

    @POST
    Observable<SipBean> requestSip(@Url String url, @Query("token") String token);

    //获取拼车服务的评论
    String requestCarpoolComments = BASE_PROPERTY + "/neighbor/carpool/to-client/carpool-comment-list";

    @POST
    Observable<CommentListBean> requestCarpoolComments(@Url String url, @Query("carpoolFid") String fid, @Query("page") int page, @Query("pageSize") int pageSize);

    //获取左邻右里的评论
    String requestNeighbourComments = BASE_PROPERTY + "/neighbor/moments/to-client/moments-comment-list";

    @POST
    Observable<CommentListBean> requestNeighbourComments(@Url String url, @Query("momentsFid") String fid, @Query("page") int page, @Query("pageSize") int pageSize);

    //发送 邻里圈评论
    String requestSubmitNeighbourComment = BASE_PROPERTY + "/neighbor/moments/from-client/moments-comment-create";

    @POST
    Observable<BaseBean> requestSubmitNeighbourComment(@Url String url, @Body MultipartBody file);

    //发送 闲置交换评论
    String requestSubmitExchangeComment = BASE_PROPERTY + "/neighbor/exchange/from-client/exchange-comment-create";

    @POST
    Observable<BaseBean> requestSubmitExchangeComment(@Url String url, @Body MultipartBody file);

    //发送 拼车服务评论
    String requestSubmitCarpoolComment = BASE_PROPERTY + "/neighbor/carpool/from-client/carpool-comment-create";

    @POST
    Observable<BaseBean> requestSubmitCarpoolComment(@Url String url, @Body MultipartBody file);

    //发送拼车服务信息
    String requestSubmitExchange = BASE_PROPERTY + "/neighbor/exchange/from-client/exchange-create";

    @POST
    Observable<BaseBean> requestSubmitExchange(@Url String url,
                                               @Body MultipartBody file
                                               //附件类型（1=图片, 大小不能超过300K，2=视频，大小不能超过10M）
    );


    //发送拼车服务信息
    String requestSubmitCarpool = BASE_PROPERTY + "/neighbor/carpool/from-client/carpool-create";

    @POST
    Observable<BaseBean> requestSubmitCarpool(@Url String url,
                                              @Body MultipartBody file);//附件类型（1=图片,大小不能超过300K，2=视频，大小不能超过10M）

    //发送邻里圈信息
    String requestSubmitNeighbour = BASE_PROPERTY + "/neighbor/moments/from-client/moments-create";

    @POST
    Observable<BaseBean> requestSubmitNeighbour(@Url String url, @Body MultipartBody file);

    //移除左邻右里我发布的信息
    String requestRemoveNeighbour = BASE_PROPERTY + "/neighbor/moments/from-client/moments-delete";

    @POST
    Observable<BaseBean> requestRemoveNeighbour(@Url String url, @Query("token") String token, @Query("momentsFids") String fid);

    //移除闲置交换我发布的信息
    String requestRemoveExchange = BASE_PROPERTY + "/neighbor/exchange/from-client/exchange-delete";

    @POST
    Observable<BaseBean> requestRemoveExchange(@Url String url, @Query("token") String token, @Query("exchangeFids") String fid);

    //移除拼车服务我发布的信息
    String requestRemoveCarpool = BASE_PROPERTY + "/neighbor/carpool/from-client/carpool-delete";

    @POST
    Observable<BaseBean> requestRemoveCarpool(@Url String url, @Query("token") String token, @Query("carpoolFids") String fid);


    //******************************以下为物业部分****************************
    //待缴费账单
    String requestPropertyNotPay = BASE_PROPERTY + "/client/info/pptBillsWait.do";

    //物业缴费
//    @POST("/sub_property_ysq/client/info/pptBillsWait.do")
    @POST
    Observable<PropertyPayBean> requestPropertyNotPay(@Url String url,
                                                      @Query("token") String token,
                                                      @Query("cmnt_c") String cmnt_c,
                                                      @Query("page") int page);

    //已缴费账单
    String requestPropertyPayed = BASE_PROPERTY + "/client/info/pptBillsFinished.do";

    @POST
    Observable<PropertyPayBean> requestPropertyPayed(@Url String url,
                                                     @Query("token") String token,
                                                     @Query("cmnt_c") String cmnt_c,
                                                     @Query("page") int page);

    //物业账单详情
    String requestPropertyPayDetail = BASE_PROPERTY + "/client/info/pptBillDet";

    @POST
    Observable<PropertyPayDetailBean> requestPropertyPayDetail(@Url String url,
                                                               @Query("token") String token,
                                                               @Query("fid") String fid);

    //微信、支付宝等第三方支付物业缴费订单 type为1.支付宝;2.微信
//    String requestOrder = BASE_PROPERTY + "/client/pay/generatePayJSON";  改版后的接口也改了，各类支付改成统一接口
    String requestOrder = BASE_PROPERTY + "/pay/client/generatePayJSON";

    @POST
    Observable<ResponseBody> requestOrder(@Url String url,
                                          @Query("otype") String otype,
                                          @Query("type") int type,
                                          @Query("ofids") String ofids);

    //友盟用户登记接口
    String requestUMeng = BASE_USER + "/client/logUMengParams.do";

    @POST
    Observable<BaseBean> requestUMeng(@Url String url,
                                      @Query("token") String token,
                                      @Query("deviceToken") String deviceToken,
                                      @Query("alias") String alias,
                                      @Query("aliasType") String aliasType);

    //************************* 智能商城模块 **************************
    //一级列表同时是判断是否是跳转一级列表还是二级列表的入口
    String requestFirstLevel = "http://www.aglhz.com/mall/member/goodscategory/firstLevelList.do";

    @POST
    Observable<FirstLevelBean> requestFirstLevel(@Url String url, @Query("keywords") String keywords);

    //二级列表
    String requestSubCategoryLevel = "http://www.aglhz.com/mall/member/goodscategory/subCategoryLevelList.do";

    @POST
    Observable<SubCategoryBean> requestSubCategoryLevel(@Url String url, @Query("token") String token, @Query("appType") int appType, @Query("id") String id);

    //三级列表
    String requestGoodsList = "http://www.aglhz.com/mall/member/goodscategory/findGoodsListByCategoryId.do";

    @POST
    Observable<GoodsBean> requestGoodsList(@Url String url, @Query("token") String token, @Query("appType") int appType, @Query("secondCategoryId") String id);

    //************************ 停车记录 *************************

    //月卡办理
    String requestSubmitMonthCard = BASE_PROPERTY + "/park/card/from-client/month-card-create";
//    String requestSubmitMonthCard = BASE_TEST_PROPERTY_CODE + "/park/card/from-client/month-card-create";


    @POST
    Observable<BaseBean> requestSubmitMonthCard(@Url String url, @Body MultipartBody body);

    //业主办理免费卡
    String requestSubmitOwnerCard = BASE_PROPERTY + "/park/card/from-client/owner-card-create";

    @POST
    Observable<BaseBean> requestSubmitOwnerCard(@Url String url, @Body MultipartBody body);

    //按区域搜索停车场
    String requestParkList = BASE_PROPERTY + "/park/place/to-client/search-park-list";

    @FormUrlEncoded
    @POST
    Observable<ParkSelectBean> requestParkList(@Url String url, @Field("token") String token, @Field("page") int page, @Field("pageSize") int pageSize, @Field("regionKeywords") String regionKeywords);

    //我的车卡列表
    String requestCarCardList = BASE_PROPERTY + "/park/card/to-client/card-list";
//    String requestCarCardList = BASE_TEST_PROPERTY_CODE + "/park/card/to-client/card-list";

    @POST
    Observable<CarCardListBean> requestCarCardList(@Url String url, @Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);

    //月卡计费规则列表
    String requestMonthCardRuleList = BASE_PROPERTY + "/park/card/rule/to-client/rule-list";

    @POST
    Observable<MonthCardRuleListBean> requestMonthCardRuleList(@Url String url, @Query("token") String token, @Query("parkPlaceFid") String fid);

    //删除车卡
    String requestDeleteCarCard = BASE_PROPERTY + "/park/card/from-client/card-delete";

    @POST
    Observable<BaseBean> requestDeleteCarCard(@Url String url, @Query("token") String token, @Query("parkCardFids") String fid);

    //车卡管理里某月卡审核通过后的缴费页
    String requestCardPay = BASE_PROPERTY + "/park/card/to-client/card-pay";

    @POST
    Observable<CarCardBean> requestCardPay(@Url String url, @Query("token") String token, @Query("parkCardFid") String fid);

    //车卡管理里某月卡充值页
    String requestCardRecharge = BASE_PROPERTY + "/park/card/to-client/card-recharge";

    @POST
    Observable<CardRechargeBean> requestCardRecharge(@Url String url, @Query("token") String token, @Query("parkCardFid") String fid);

    //停车记录
    String requestParkRecord = BASE_PROPERTY + "/park/record/to-client/record-list";

    @POST
    Observable<ParkRecordListBean> requestParkRecord(@Url String url,
                                                     @Query("token") String authToken,
                                                     @Query("page") int page,
                                                     @Query("pageSize") int pageSize,
                                                     @Query("searchStartTime") String searchStartTime,
                                                     @Query("searchEndTime") String searchEndTime);

    //"我的"模块里的未读标记
    String requestUnreadMark = BASE_PROPERTY + "/client/info/redcount";

    @POST
    Observable<UnreadMessageBean> requestUnreadMark(@Url String url, @Query("token") String token);

    //-------------- 未对接的接口 ---------------

    //某车临时停车的缴费账单
    String requestPayBill = BASE_PROPERTY + "/park/temporary/to-client/pay-bill";

    @POST
    Observable<ParkOrderBean> requestPayBill(@Url String url, @Query("token") String token, @Query("parkPlaceFid") String fid, @Query("carNo") String carNo);

    //车卡管理里某免费卡的修改页
    String requestModifyOwnerCard = BASE_PROPERTY + "/park/card/from-client/owner-card-modify";
//    String requestModifyOwnerCard ="http://192.168.250.108:8080/property_code/park/card/from-client/owner-card-modify";

//    @POST
//    Observable<BaseBean> requestModifyOwnerCard(@Url String url,
//                                                @Body MultipartBody body);

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestModifyOwnerCard(@Url String url, @FieldMap() Map<String, String> map);

    //实时查询某停车场的车位信息
    String requestSearchParkSpace = BASE_PROPERTY + "/park/space/to-client/search-park-space";

    @POST
    Observable<BaseBean> requestSearchParkSpace(@Url String url, @Query("parkPlaceFid") String fid);

    //todo:（临时测试用的接口）车卡管理里列出某会员的月卡充值记录列表
    String requestRechargeRecord = BASE_PROPERTY + "/park/card/to-client/month-card-recharge-record-list";
//    String requestRechargeRecord = BASE_PROPERTY+"/park/card/to-client/month-card-recharge-record-list";

    @POST
    Observable<MonthCardBillListBean> requestRechargeRecord(@Url String url,
                                                            @Query("token") String token,
                                                            @Query("page") int page,
                                                            @Query("pageSize") int pageSize);

    //消息中心投诉回复
    String requestComplainReplies = BASE_PROPERTY + "/property/complaint/to-client/complaint-detail";

    @POST
    Observable<ComplainReplyBean> requestComplainReplies(@Url String url,
                                                         @Query("token") String token,
                                                         @Query("complaintFid") String String);


    String requestCarCardOrder = BASE_PROPERTY + "/park/card/from-client/month-card-bill-pay";

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> requestCarCardOrder(@Url String url,
                                                 @Field("token") String token,
                                                 @Field("parkCardFid") String parkCardFid,
                                                 @Field("monthName") String monthName,//预缴月数名称（例如：一个月、半年、一年等）
                                                 @Field("monthCount") int monthCount,//预缴月数值（例如：1、6、12等）
                                                 @Field("payType") int payType);//支付类型（1=支付宝支付、2=微信支付）


    //-------------------------------以下为2017.06.30添加的社区服务接口--------------------------------------------

    String requestServiceClassifyList = BASE_PROPERTY + "/services/classify/to-client/classify-list";

    @GET
    Observable<ServicesTypesBean> requestServiceClassifyList(@Url String url,
                                                             @Query("page") int page,
                                                             @Query("pageSize") int pageSize,
                                                             @Query("cmnt_c") String cmnt_c);

    String requestServiceCommodityList = BASE_PROPERTY + "/services/commodity/to-client/commodity-list";

    @GET
    Observable<ServicesListBean> requestServiceCommodityList(@Url String url,
                                                             @Query("page") int page,
                                                             @Query("pageSize") int pageSize,
                                                             @Query("classifyFid") String classifyFid);

    String requestServiceDetail = BASE_PROPERTY + "/services/commodity/to-client/commodity-details";

    @GET
    Observable<ServiceDetailBean> requestServiceDetail(@Url String url, @Query("fid") String fid);


    //社区服务评论列表接口
    String requestRemarkList = BASE_PROPERTY + "/services/commodity/to-client/commodity-comment-list";

    @GET
    Observable<RemarkListBean> requestRemarkList(@Url String url,
                                                 @Query("page") int page,
                                                 @Query("pageSize") int pageSize,
                                                 @Query("commodityFid") String classifyFid);

    //请求点评回复列表
    String requestRemarkReplyList = BASE_PROPERTY + "/services/commodity/to-client/commodity/comment-reply-list";

    @GET
    Observable<CommentListBean> requestRemarkReplyList(@Url String url,
                                                       @Query("commentFid") String commentFid,
                                                       @Query("page") int page,
                                                       @Query("pageSize") int pageSize);

    //发表点评
    String requestRemarkService = BASE_PROPERTY + "/services/commodity/from-client/commodity-comment-create";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestRemarkService(@Url String url,
                                              @Field("token") String token,
                                              @Field("commodityFid") String commodityFid,
                                              @Field("startLevel") int startLevel,
                                              @Field("content") String content);

    //发表点评回复
    String requestSubmitRemark = BASE_PROPERTY + "/services/commodity/from-client/commodity/comment-reply-create ";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestSubmitRemark(@Url String url,
                                             @Field("token") String token,
                                             @Field("commentFid") String commodityFid,
                                             @Field("replyContent") String replyContent);
    //-------------------------------以上为2017.06.30添加的社区服务接口--------------------------------------------

}

