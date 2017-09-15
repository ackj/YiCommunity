package com.aglhz.yicommunity.common;


import java.io.File;
import java.util.List;

/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    private static final String TAG = Params.class.getSimpleName();
    public static String token;
    public static String cmnt_c = "";
    public String sc = "AglhzYsq";
    public String dir = "";
    public String des = "";
    public String contact = "";
    public String user = "";
    public String field = "";
    public String val = "";
    public String pwd = "";
    public String name = "";
    public String phoneNo = "";
    public String content = "";
    public String pwd0 = "";
    public String pwd1 = "";
    public String pwd2 = "";
    public String province = "";
    public String city = "";
    public String county = "";
    public String bdg_c = "";
    public String bdg_u_c = "";
    public String bdg_f_c = "";
    public String bdg_f_h_c = "";
    public String idCard = "";
    public String fid = "";
    public String url = "";
    public String ofid = "";
    public String mfid = "";
    public String picode = "";
    public String directory;
    public String deviceName;
    public String account;
    public String verifyCode;
    public String verifyType;
    public String password1;
    public String password2;
    public String price;
    public String startPlace;
    public String endPlace;
    public String outTime;
    public String currentPositionLat;
    public String billFids;     //多个时用“,”分隔。 现在已经改成ofids
    public String currentPositionLng;
    public String positionAddress;
    public String secondCategoryId;//二级菜单的id
    public String keywords;
    public String carNo;
    public String monthName;
    public String authFid;
    public String commentFid;//某条社区评论点评的id。
    public String id;
    public String searchStartTime;
    public String searchEndTime;
    public String commodityFid;//上门服务商品ID
    public String replyContent;//上门服务商品ID
    public boolean isProprietor = true;
    public int residentType;//居民类型：1=业主、2=家属、3=租客
    public boolean single;
    public boolean timeable;
    public boolean summerable;
    public int positionType;
    public int type;//1.支付宝;2.微信
    public int pageSize = Constants.PAGE_SIZE;
    public int status;
    public int timeset;
    public int maxTimes;
    public int page = 1;
    public int monthCount;//月数值：1、6、12
    public int carpoolType;
    public int startLevel;//点评星级
    public int payType;  //1.支付宝;2.微信
    public int appType;//android 2就好
    public File file;
    public List<File> files;
    public String repairType;//报修类型代号
    public String parkCardFid;//要修改的某免费卡的Fid
    public String parkPlaceFid;//某停车场Fid
    public String regionKeywords;//区域关键字
    public String complaintFid;//投诉回复时所需参数
    //在房屋权限页面中，由于请求权限的接口要用到上一页传进来的名称为fid的参数，
    // 而设置权限接口也要用到fid这个参数，但是传的请求权限接口中的一个叫rfid的字段的值，
    // 也就是说同一个页面两个接口都要一个叫fid的参数，但是值不一样，所以用rfid进行区分。
    public String rfid;
    public String powerCode;//设置某项权限
    public String otype;//业务支付类型 pptbill-物业账单缴费、pktmp-临时停车缴费、pkmcd-停车月卡充值缴费
    public String ofids; //多个时用“,”分隔。
    public int topnum = 1;
    public boolean isCleanAll;//是否删除全部
    public String directories;//设备编号，用逗号分开
    public String certificateType = "1";//证件类型（1=身份证，2=护照）
    public int identityType;//住户类型（1=业主，2=家属，3=租客）
    public String cmnt_c_en;//闲置交换和左邻右里的社区切换
    public String roomDir ; //6-31-1-1


    private Params() {
    }

    public static Params getInstance() {
        Params params = new Params();
        params.token = UserHelper.token;
        params.cmnt_c = UserHelper.communityCode;
        return params;
    }

}