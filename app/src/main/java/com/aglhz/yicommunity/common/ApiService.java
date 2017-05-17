package com.aglhz.yicommunity.common;

import com.aglhz.yicommunity.bean.BannerBean;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.BuildingBean;
import com.aglhz.yicommunity.bean.CheckTokenBean;
import com.aglhz.yicommunity.bean.CommentListBean;
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
import com.aglhz.yicommunity.bean.SipBean;
import com.aglhz.yicommunity.bean.UnitBean;
import com.aglhz.yicommunity.bean.UserBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

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

    //智能设备
    String SMART_DEVICE = "http://www.aglhz.com/sub_property_ysq/m/html/zhinengshebei.html";

    //我的地址
    String MY_ADDRESS = "http://www.aglhz.com/sub_property_ysq/m/mall_zyg/html/addressee_list.html?appType=2&token=";

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
    @POST("/sub_property_ysq/client/info/msgList")
    Observable<MessageCenterBean> requestMessages(@Query("token") String token);

    //社区Banner
//    @POST("/sub_property_ysq/client/info/indexadvs")
    String requestBanners = BASE_PROPERTY + "/client/info/indexadvs";

    @POST
    Observable<BannerBean> requestBanners(@Url String url);

    //反馈
    @POST("/sub_property_ysq/client/feedback.do")
    Observable<BaseBean> feedback(@Query("token") String token, @Query("cmnt_c") String cmnt_c, @Query("des") String des, @Query("contact") String contact);

    //更新用户信息
//    @POST("/memberSYS-m/client/updateMemberFieldByToken.do")
    String updateUserData = BASE_USER + "/client/updateMemberFieldByToken.do";

    @POST
    Observable<BaseBean> updateUserData(@Url String url,
                                        @Query("token") String token,
                                        @Query("field") String field,
                                        @Query("val") String val);


    //修改密码
//    @POST("/memberSYS-m/client/updateMemberPwd.do")
    String updatePassword = BASE_USER + "/client/updateMemberPwd.do";

    @POST
    Observable<BaseBean> updatePassword(@Url String url,
                                        @Query("token") String token,
                                        @Query("pwd0") String pwd0,
                                        @Query("pwd1") String pwd1,
                                        @Query("pwd2") String pwd2);


    //更新头像
    @Multipart
    @POST("/memberSYS-m/client/uploadHeader2.do")
    Observable<BaseBean> updatePortrait(@Query("token") String token, @Part("file") RequestBody file);


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


    String postComplain = BASE_PROPERTY + "/property/complaint/from-client/complaint-create";

    //提交管理投诉
    @POST
    Observable<BaseBean> postComplain(@Url String url, @Body MultipartBody file);

    //获取门禁列表
//    @POST("/sub_property_ysq/smartdoor/info/doormchs")

    String requestDoors = BASE_PROPERTY + "/smartdoor/info/doormchs";


    @POST
    Observable<DoorListBean> requestDoors(@Url String url, @Query("token") String token);

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
    String requestOpenDoorRecord = BASE_PROPERTY + "/smartdoor/info/dooropenlog";

    @POST
    Observable<OpenDoorRecordBean> requestOpenDoorRecord(@Url String url, @Query("token") String token);

    String register = BASE_USER + "/client/register.do";

    //注册
    @POST
    Observable<BaseBean> register(@Url String url, @Query("sc") String sc, @Query("account") String account, @Query("code") String code, @Query("Password1") String password1, @Query("Password2") String password2);

    //找回密码
    @POST("/memberSYS-m/client/renewMemberPwd.do")
    Observable<BaseBean> forgetPwd();

    //获取验证码
    String requestVerifyCode = BASE_USER + "/client/validCode.do";

    @POST
    Observable<BaseBean> requestVerifyCode(@Url String url, @Query("sc") String sc, @Query("phone") String phone, @Query("type") String type);


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

    //获取物业公告
    @POST("/sub_property_ysq/client/info/noticeList")
    Observable<NoticeBean> getNoticeList(@Query("token") String token,
                                         @Query("cmnt_c") String cmnt_c,
                                         @Query("summerable") boolean summerable,
                                         @Query("timeable") boolean timeable,
                                         @Query("page") int page,
                                         @Query("pageSize") int pageSize);

    //获取邻里圈列表
    @POST("/sub_property_ysq/neighbor/moments/to-client/moments-list")
    Observable<NeighbourListBean> getNeighbourList(@Query("page") int page, @Query("pageSize") int pageSize);

    //获取闲置交换
    @POST("sub_property_ysq/neighbor/exchange/to-client/exchange-list")
    Observable<NeighbourListBean> getExchangeList(@Query("page") int page, @Query("pageSize") int pageSize);

    //获取拼车服务
    @POST("sub_property_ysq/neighbor/carpool/to-client/carpool-list/{carpoolType}")
    Observable<NeighbourListBean> getCarpoolList(@Path("carpoolType") int carpoolType,
                                                 @Query("currentPositionLat") String currentPositionLat,
                                                 @Query("currentPositionLng") String currentPositionLng,
                                                 @Query("page") int page, @Query("pageSize") int pageSize);

    //获取我发布的闲置交换
    @POST("sub_property_ysq/neighbor/exchange/to-client/exchange-mine-list")
    Observable<NeighbourListBean> getMyExchangeList(@Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);

    //获取我发布的邻里圈列表
    @POST("/sub_property_ysq/neighbor/moments/to-client/moments-mine-list")
    Observable<NeighbourListBean> getMyNeighbourList(@Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);

    //获取我发布的拼车服务列表
    @POST("/sub_property_ysq/neighbor/carpool/to-client/carpool-mine-list")
    Observable<NeighbourListBean> getMyCarpoolList(@Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);


    //首页获取公告列表
    //    @POST("/sub_property_ysq/client/info/noticeTop")
    String requestHomeNotices = BASE_PROPERTY + "/client/info/noticeTop";

    @POST
    Flowable<NoticeBean> requestHomeNotices(@Url String url,
                                            @Query("token") String token,
                                            @Query("cmnt_c") String cmnt_c);


    //获取闲置交换的评论
    String getExchangeComments = BASE_PROPERTY + "/neighbor/exchange/to-client/exchange-comment-list";

    @POST
    Observable<CommentListBean> getExchangeComments(@Url String url, @Query("exchangeFid") String fid, @Query("page") int page, @Query("pageSize") int pageSize);

    //获取Sip账号，用户拨打门禁的电话。
    String requestSip = BASE_PROPERTY + "/smartdoor/client/cngsip";

    @POST
    Observable<SipBean> requestSip(@Url String url, @Query("token") String token);

    //获取拼车服务的评论
    String getCarpoolComments = BASE_PROPERTY + "/neighbor/carpool/to-client/carpool-comment-list";

    @POST
    Observable<CommentListBean> getCarpoolComments(@Url String url, @Query("carpoolFid") String fid, @Query("page") int page, @Query("pageSize") int pageSize);

    //获取左邻右里的评论
    String getNeighbourComments = BASE_PROPERTY + "/neighbor/moments/to-client/moments-comment-list";

    @POST
    Observable<CommentListBean> getNeighbourComments(@Url String url, @Query("momentsFid") String fid, @Query("page") int page, @Query("pageSize") int pageSize);

    //发送 邻里圈评论
    String postNeighbourComment = BASE_PROPERTY + "/neighbor/moments/from-client/moments-comment-create";

    @POST
    Observable<BaseBean> postNeighbourComment(@Url String url, @Body MultipartBody file);

    //发送 闲置交换评论
    String postExchangeComment = BASE_PROPERTY + "/neighbor/exchange/from-client/exchange-comment-create";

    @POST
    Observable<BaseBean> postExchangeComment(@Url String url, @Body MultipartBody file);

    //发送 拼车服务评论
    String postCarpoolComment = BASE_PROPERTY + "/neighbor/carpool/from-client/carpool-comment-create";

    @POST
    Observable<BaseBean> postCarpoolComment(@Url String url, @Body MultipartBody file);


    //发送拼车服务信息
    String postExchangeMessage = BASE_PROPERTY + "/neighbor/exchange/from-client/exchange-create";

    @POST
    Observable<BaseBean> postExchangeMessage(@Url String url,
                                             @Body MultipartBody file
                                             //附件类型（1=图片, 大小不能超过300K，2=视频，大小不能超过10M）
    );


    //发送拼车服务信息
    String postCarpoolMessage = BASE_PROPERTY + "/neighbor/carpool/from-client/carpool-create";

    @POST
    Observable<BaseBean> postCarpoolMessage(@Url String url,
                                            @Body MultipartBody file);//附件类型（1=图片,大小不能超过300K，2=视频，大小不能超过10M）

    //发送邻里圈信息
    String postNeighbourMessage = BASE_PROPERTY + "/neighbor/moments/from-client/moments-create";

    @POST
    Observable<BaseBean> postNeighbourMessage(@Url String url,
//                                              @Query("token") String token,
//                                              @Query("cmnt_c") String cmnt_c,
//                                              @Query("content") String content,
//                                              @Query("stype") int type, //附件类型（1=图片, 大小不能超过300K，2=视频，大小不能超过10M）
                                              @Body MultipartBody file);

    //移除左邻右里我发布的信息
    String removeNeighbourMessage = BASE_PROPERTY + "/neighbor/moments/from-client/moments-delete";

    @POST
    Observable<BaseBean> removeNeighbourMessage(@Url String url, @Query("token") String token, @Query("momentsFids") String fid);

    //移除闲置交换我发布的信息
    String removeExchangeMessage = BASE_PROPERTY + "/neighbor/exchange/from-client/exchange-delete";

    @POST
    Observable<BaseBean> removeExchangeMessage(@Url String url, @Query("token") String token, @Query("exchangeFids") String fid);

    //移除闲置交换我发布的信息
    String removeCarpoolMessage = BASE_PROPERTY + "/neighbor/carpool/from-client/carpool-delete";

    @POST
    Observable<BaseBean> removeCarpoolMessage(@Url String url, @Query("token") String token, @Query("carpoolFids") String fid);


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
    Observable<PropertyPayBean> requestPropertyPayDetail(@Url String url,
                                                         @Query("token") String token,
                                                         @Query("fid") String fid);

    //微信、支付宝等第三方支付物业缴费订单 type为1.支付宝;2.微信
    String requestOrder = BASE_PROPERTY + "/client/pay/generatePayJSON";

    @POST
    Observable<ResponseBody> requestOrder(@Url String url,
                                          @Query("type") int type,
                                          @Query("billFids") String billFids);

}
