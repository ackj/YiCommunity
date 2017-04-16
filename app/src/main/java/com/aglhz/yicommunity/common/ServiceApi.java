package com.aglhz.yicommunity.common;

/**
 * Created by YandZD on 2017/1/17.
 */

public class ServiceApi {

    //sub_mall_zyg   memberSYS-m

    public final static String HEADAPI = "http://www.aglhz.com:8076/memberSYS-m";           //用户
    public final static String HEADAPI_PROPERTY = "http://www.aglhz.com:8090/sub_property_ysq";   //物业
    public final static String PROPERTYCFG_M = "http://www.aglhz.com:8096/propertyCFG-m";
    public final static String MSG_INFO = "http://www.aglhz.com/sub_property_ysq"; //物业消息中心
//    public final static String PROPERTY_CODE = "http://www.aglhz.com:8089/property_code";


    //test
//    public final static String  HEADAPI_PROPERTY="http://192.168.2.241:8080/property_code";
//    public final static String PROPERTY_CODE = "http://192.168.2.241:8080/property_code";
    public final static String PROPERTY_CODE = HEADAPI_PROPERTY;


//    public final static String PROPERTY_CODE = HEADAPI_PROPERTY;

    public final static String register = HEADAPI + "/client/register.do";

    public final static String forgetPassword = HEADAPI + "/client/renewMemberPwd.do";

    public final static String login = HEADAPI + "/client/login.do";

    public final static String verifyCode = HEADAPI + "/client/validCode.do";

    public final static String uploadHead = HEADAPI + "/client/uploadHeader2.do";

    public final static String userInfo = HEADAPI + "/client/memberInfo.do";

    public final static String logout = HEADAPI + "/client/logout.do";

    //修改信息 sex-性别； nickName-昵称；   sign-签名；
    public final static String modifyInfo = HEADAPI + "/client/updateMemberFieldByToken.do";

    //修改密码
    public final static String modifyPassword = HEADAPI + "/client/updateMemberPwd.do";

    //检查token是否有效
    public final static String checkIfTokenInvalid = HEADAPI + "/client/checkIfTokenInvalid.do";

    //反馈
    public final static String feedback = HEADAPI_PROPERTY + "/client/feedback.do";

    //获取社区列表
    public final static String communityList = PROPERTYCFG_M + "/client/communityList.do";

    //业主申请
    public final static String ownerApply = HEADAPI_PROPERTY + "/client/ownerApply.do";

    //获取我的房屋
    public final static String authBdgs = HEADAPI_PROPERTY + "/client/info/authBdgs.do";

    //获取房屋成员
    public final static String authBdgMembers = HEADAPI_PROPERTY + "/client/info/authBdgMembers";

    //待缴费账单
    public final static String pptBillsWait = HEADAPI_PROPERTY + "/client/info/pptBillsWait.do";

    //已缴费账单
    public final static String pptBillsFinished = HEADAPI_PROPERTY + "/client/info/pptBillsFinished.do";

    //物业账单详情
    public final static String propertyBillDet = HEADAPI_PROPERTY + "/client/info/pptBillDet";

    //物业报修类型
    public final static String rpTypes = HEADAPI_PROPERTY + "/client/info/rpTypes.do";

    //我的报修列表
    public final static String repairApplyList = HEADAPI_PROPERTY + "/client/info/repairApplyList";

    //支付宝 物业缴费
    public final static String aliPayPropertyExpense = HEADAPI_PROPERTY + "/client/pay/generatePayJSON";

    //提交报修表单
    public final static String repair = HEADAPI_PROPERTY + "/client/repair";

    //申请成员
    public final static String fmApply = HEADAPI_PROPERTY + "/client/fmApply";

    //申请租客
    public final static String renterApply = HEADAPI_PROPERTY + "/client/renterApply";


    //物业报修详情
    public final static String repairDet = HEADAPI_PROPERTY + "/client/info/repairDet";

    //闲置交换 所有的数据 列表
    public final static String exchangeList = PROPERTY_CODE + "/neighbor/exchange/to-client/exchange-list";

    //邻里圈 所有数据 列表
    public final static String momentsList = PROPERTY_CODE + "/neighbor/moments/to-client/moments-list";

    //邻里活动 所有数据 列表
    public final static String activityList = PROPERTY_CODE + "/neighbor/activity/to-client/activity-list";

    //发布邻里圈消息
    public final static String momentsCreate = PROPERTY_CODE + "/neighbor/moments/from-client/moments-create";

    //发布邻里活动
    public final static String activityCreate = PROPERTY_CODE + "/neighbor/activity/from-client/activity-create";

    //发布物品交换
    public final static String exchangeCreate = PROPERTY_CODE + "/neighbor/exchange/from-client/exchange-create";

    //发布拼车
    public final static String carpoolCreate = PROPERTY_CODE + "/neighbor/carpool/from-client/carpool-create";

    //在邻里圈删除"我"的信息
    public final static String momentsDelete = PROPERTY_CODE + "/neighbor/moments/from-client/moments-delete";

    //在拼车删除"我"的信息
    public final static String carpoolDelete = PROPERTY_CODE + "/neighbor/carpool/from-client/carpool-delete";

    //在邻里活动删除"我"的信息
    public final static String activityDelete = PROPERTY_CODE + "/neighbor/activity/from-client/activity-delete";

    //在闲置交换删除"我"的信息
    public final static String exchangeDelete = PROPERTY_CODE + "/neighbor/exchange/from-client/exchange-delete";

    //首页热门邻里圈
    public final static String momentsNewestList = PROPERTY_CODE + "/neighbor/moments/to-client/moments-newest-list";

    //社区轮播图
    public final static String indexadvs = HEADAPI_PROPERTY + "/client/info/indexadvs";

    //邻里轮播图
    public final static String neighboradvs = HEADAPI_PROPERTY + "/client/info/neighboradvs";

    //邻里活动 评论列表
    public final static String activityCommentList = PROPERTY_CODE + "/neighbor/activity/to-client/activity-comment-list";

    //闲置交换 评论列表
    public final static String exchangeCommentList = PROPERTY_CODE + "/neighbor/exchange/to-client/exchange-comment-list";

    //邻里圈 评论列表
    public final static String momentsCommentList = PROPERTY_CODE + "/neighbor/moments/to-client/moments-comment-list";

    //拼车 评论列表
    public final static String carpoolCommentList = PROPERTY_CODE + "/neighbor/carpool/to-client/carpool-comment-list";

    //邻里活动 发表评论
    public final static String activityCommentCreate = PROPERTY_CODE + "/neighbor/activity/from-client/activity-comment-create";

    //闲置交换 发表评论
    public final static String exchangeCommentCreate = PROPERTY_CODE + "/neighbor/exchange/from-client/exchange-comment-create";

    //邻里圈 发表评论
    public final static String momentsCommentCreate = PROPERTY_CODE + "/neighbor/moments/from-client/moments-comment-create";

    //拼车 发表评论
    public final static String carpoolCommentCreate = PROPERTY_CODE + "/neighbor/carpool/from-client/carpool-comment-create";

    //拼车 找车主
    public final static String findCarOwner = PROPERTY_CODE + "/neighbor/carpool/to-client/carpool-list/1";

    //拼车 找乘客
    public final static String findPassenger = PROPERTY_CODE + "/neighbor/carpool/to-client/carpool-list/2";

    //拼车  "我的"
    public final static String carpoolMineList = PROPERTY_CODE + "/neighbor/carpool/to-client/carpool-mine-list";

    //消息中心列表
    public final static String noticeList = HEADAPI_PROPERTY + "/client/info/noticeList";

    //首页土特产
    public final static String RecommendGoodsTagList = "http://www.aglhz.com/mall/member/home/getRecommendGoodsTagList.do?appType=2&token=";

    //消息中心
    public final static String msgList = HEADAPI_PROPERTY + "/client/info/msgList";

    //认证
    public final static String authApprove = HEADAPI_PROPERTY + "/client/authApprove";

    //审核结果
    public final static String authApplyDet = HEADAPI_PROPERTY + "/client/info/authApplyDet";

    //物业公告
    public final static String noticeDet = HEADAPI_PROPERTY + "/client/info/noticeDet";
}
