package com.aglhz.yicommunity.common;

import com.aglhz.yicommunity.bean.BannerBean;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.BuildingBean;
import com.aglhz.yicommunity.bean.CarCardBean;
import com.aglhz.yicommunity.bean.CarCardListBean;
import com.aglhz.yicommunity.bean.CardRechargeBean;
import com.aglhz.yicommunity.bean.CheckTokenBean;
import com.aglhz.yicommunity.bean.CommentListBean;
import com.aglhz.yicommunity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.bean.ContactBean;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.bean.FirstLevelBean;
import com.aglhz.yicommunity.bean.FloorBean;
import com.aglhz.yicommunity.bean.GoodsBean;
import com.aglhz.yicommunity.bean.HouseRightsBean;
import com.aglhz.yicommunity.bean.MessageCenterBean;
import com.aglhz.yicommunity.bean.MonthCardRuleListBean;
import com.aglhz.yicommunity.bean.MyHousesBean;
import com.aglhz.yicommunity.bean.NeighbourListBean;
import com.aglhz.yicommunity.bean.NoticeBean;
import com.aglhz.yicommunity.bean.OpenDoorRecordBean;
import com.aglhz.yicommunity.bean.ParkRecordListBean;
import com.aglhz.yicommunity.bean.ParkSelectBean;
import com.aglhz.yicommunity.bean.PasswordBean;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.bean.RepairApplyBean;
import com.aglhz.yicommunity.bean.RepairDetailBean;
import com.aglhz.yicommunity.bean.RoomBean;
import com.aglhz.yicommunity.bean.SipBean;
import com.aglhz.yicommunity.bean.SubCategoryBean;
import com.aglhz.yicommunity.bean.UnitBean;
import com.aglhz.yicommunity.bean.UserBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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
//    String BASE_PROPERTYCFG_M = "http://192.168.250.108:8080/propertyCFG-m";  //调试之用

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
    String requestMessages = BASE_PROPERTY + "/client/info/msgList";

    //    @POST("/sub_property_ysq/client/info/msgList")
    @POST
    Observable<MessageCenterBean> requestMessages(@Url String url,
                                                  @Query("token") String token,
                                                  @Query("pageSize") String pageSize,
                                                  @Query("page") String page);

    //社区Banner
    //@POST("/sub_property_ysq/client/info/indexadvs")
    String requestBanners = BASE_PROPERTY + "/client/info/indexadvs";

    @POST
    Observable<BannerBean> requestBanners(@Url String url);

    //反馈
    String feedback = BASE_PROPERTY + "/client/feedback.do";

    @POST/*("/sub_property_ysq/client/feedback.do")*/
    Observable<BaseBean> feedback(@Url String url, @Query("token") String token, @Query("cmnt_c") String cmnt_c, @Query("des") String des, @Query("contact") String contact);

    //更新用户信息
    //@POST("/memberSYS-m/client/updateMemberFieldByToken.do")
    String updateUserData = BASE_USER + "/client/updateMemberFieldByToken.do";

    @POST
    Observable<BaseBean> updateUserData(@Url String url, @Body MultipartBody file);


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
    String updatePortrait = BASE_USER + "/client/uploadHeader2.do";

    @POST
    Observable<BaseBean> updatePortrait(@Url String url, @Query("token") String token, @Body MultipartBody file);

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
    String appointOpenDoor = BASE_PROPERTY + "/smartdoor/client/opendoor";

    @POST/*("/sub_property_ysq/smartdoor/client/opendoor")*/
    Observable<BaseBean> appointOpenDoor(@Url String url, @Query("token") String token, @Query("dir") String dir);

    //密码开门
    String requestPassword = BASE_PROPERTY + "/smartdoor/client/temppwd";

    @POST/*("/sub_property_ysq/smartdoor/client/temppwd")*/
    Observable<PasswordBean> requestPassword(@Url String url,
                                             @Query("token") String token,
                                             @Query("dir") String dir);

    //设置快速开门
    String postQuickOpenDoor = BASE_PROPERTY + "/smartdoor/client/qdos";

    @POST/*("/sub_property_ysq/smartdoor/client/qdos")*/
    Observable<BaseBean> postQuickOpenDoor(@Url String url, @Query("token") String token, @Query("directory") String directory, @Query("deviceName") String deviceName);

    //获取开门记录
    String requestOpenDoorRecord = BASE_PROPERTY + "/smartdoor/info/dooropenlog";

    @POST
    Observable<OpenDoorRecordBean> requestOpenDoorRecord(@Url String url, @Query("token") String token);

    //注册
    String register = BASE_USER + "/client/register.do";

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
    Flowable<MyHousesBean> requestMyhouses(@Url String url,
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
                                          @Query("page") String page,
                                          @Query("pageSize") String pageSize,
                                          @Query("summerable") boolean summerable,
                                          @Query("timeable") boolean timeable);

    //物业详情
    String requestNoticeDetail = BASE_PROPERTY + "/client/info/noticeDet?fid=";

    //获取物业公告
    @POST("/sub_property_ysq/client/info/noticeList")
    Observable<NoticeBean> getNoticeList(@Query("token") String token,
                                         @Query("cmnt_c") String cmnt_c,
                                         @Query("summerable") boolean summerable,
                                         @Query("timeable") boolean timeable,
                                         @Query("page") int page,
                                         @Query("pageSize") int pageSize);

    //获取邻里圈列表
    String requestNeighbourList = BASE_PROPERTY + "/neighbor/moments/to-client/moments-list";

    @POST
    Observable<NeighbourListBean> requestNeighbourList(@Url String url,
                                                       @Query("token") String token,
                                                       @Query("cmnt_c") String cmnt_c,
                                                       @Query("page") int page,
                                                       @Query("pageSize") int pageSize);

    //获取闲置交换
    String requestExchangeList = BASE_PROPERTY + "/neighbor/exchange/to-client/exchange-list";

    @POST
    Observable<NeighbourListBean> requestExchangeList(@Url String url, @Query("page") int page, @Query("pageSize") int pageSize);

    //获取拼车服务
    String requestCarpoolList = BASE_PROPERTY + "/neighbor/carpool/to-client/carpool-list/";

    @POST
    Observable<NeighbourListBean> requestCarpoolList(@Url String url,
                                                     @Query("token") String token,
                                                     @Query("cmnt_c") String cmnt_c,
                                                     @Query("currentPositionLat") String currentPositionLat,
                                                     @Query("currentPositionLng") String currentPositionLng,
                                                     @Query("page") int page,
                                                     @Query("pageSize") int pageSize);

    //获取我发布的闲置交换
    String requestMyExchangeList = BASE_PROPERTY + "/neighbor/exchange/to-client/exchange-mine-list";

    @POST/*("sub_property_ysq/neighbor/exchange/to-client/exchange-mine-list")*/
    Observable<NeighbourListBean> requestMyExchangeList(@Url String url, @Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);

    String requestMyNeighbourList = BASE_PROPERTY + "/neighbor/moments/to-client/moments-mine-list";

    //获取我发布的邻里圈列表
    @POST
    Observable<NeighbourListBean> requestMyNeighbourList(@Url String url, @Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);

    String requestMyCarpoolList = BASE_PROPERTY + "/neighbor/carpool/to-client/carpool-mine-list";

    //获取我发布的拼车服务列表
    @POST
    Observable<NeighbourListBean> requestMyCarpoolList(@Url String url, @Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);


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
    Observable<BaseBean> postNeighbourMessage(@Url String url, @Body MultipartBody file);

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
                                          @Query("token") String token,
                                          @Query("type") int type,
                                          @Query("billFids") String billFids);

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
    String postMothCarPay = BASE_PROPERTY + "/park/card/from-client/month-card-create";

    @POST
    Observable<BaseBean> postMothCarPay(@Url String url, @Body MultipartBody body);

    //业主办理免费卡
    String postOwnerCard = BASE_PROPERTY + "/park/card/from-client/owner-card-create";

    @POST
    Observable<BaseBean> postOwnerCard(@Url String url, @Body MultipartBody body);

    //按区域搜索停车场
    String requestParkList = BASE_PROPERTY + "/park/place/to-client/search-park-list";

    @POST
    Observable<ParkSelectBean> requestParkList(@Url String url, @Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);

    //我的车卡列表
    String requestCarCardList = BASE_PROPERTY + "/park/card/to-client/card-list";

    @POST
    Observable<CarCardListBean> requestCarCardList(@Url String url, @Query("token") String token, @Query("page") int page, @Query("pageSize") int pageSize);

    //月卡计费规则列表
    String requestMonthCardRuleList = BASE_PROPERTY + "/park/card/rule/to-client/rule-list";

    @POST
    Observable<MonthCardRuleListBean> requestMonthCardRuleList(@Url String url, @Query("token") String token, @Query("parkPlaceFid") String fid);

    //删除车卡
    String deleteCarCard = BASE_PROPERTY + "/park/card/from-client/card-delete";

    @POST
    Observable<BaseBean> deleteCarCard(@Url String url, @Query("token") String token, @Query("parkCardFids") String fid);

    //车卡管理里某月卡审核通过后的缴费页
    String requestCardPay = BASE_PROPERTY + "/park/card/to-client/card-pay";

    @POST
    Observable<CarCardBean> requestCardPay(@Url String url, @Query("token") String token, @Query("parkCardFid") String fid);

    //车卡管理里某月卡充值页
    String requestCardRecharge = BASE_PROPERTY + "/park/card/to-client/card-recharge";

    @POST
    Observable<CardRechargeBean> requestCardRecharge(@Url String url, @Query("token") String token, @Query("parkCardFid") String fid);

    //-------------- 未对接的接口 -----------------

    //停车记录
    String requestParkRecord = BASE_PROPERTY + "/park/record/to-client/record-list";

    @POST
    Observable<ParkRecordListBean> requestParkRecord(@Url String url, @Query("token") String authToken, @Query("page") int page, @Query("pageSize") int pageSize);

    //车卡管理里某免费卡的修改页
    String modifyOwnerCard = BASE_PROPERTY + "/park/card/to-client/owner-card-modify";

    @POST
    Observable<BaseBean> modifyOwnerCard(@Url String url, @Query("token") String token, @Query("parkCardFid") String fid);

    //实时查询某停车场的车位信息
    String searchParkSpace = BASE_PROPERTY + "/park/space/to-client/search-park-space";

    @POST
    Observable<BaseBean> searchParkSpace(@Url String url, @Query("parkPlaceFid") String fid);

    //某车临时停车的缴费账单
    String requestPayBill = BASE_PROPERTY + "/park/temporary/to-client/pay-bill";

    @POST
    Observable<BaseBean> requestPayBill(@Url String url, @Query("token") String token, @Query("parkPlaceFid") String fid, @Query("carNo") String carNo);

}
