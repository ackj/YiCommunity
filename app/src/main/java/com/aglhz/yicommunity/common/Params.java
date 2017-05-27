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
    public String billFids;
    public String currentPositionLng;
    public String positionAddress;
    public String secondCategoryId;//二级菜单的id
    public String keywords;
    public String carNo;
    public String monthName;
    public String authFid;
    public String id;
    public String searchStartTime;
    public String searchEndTime;
    public boolean isProprietor = true;
    public boolean single;
    public boolean timeable;
    public boolean summerable;
    public int positionType;
    public int type;
    public int pageSize = Constants.PAGE_SIZE;
    public int status;
    public int page = 1;
    public int monthCount;//月数值：1、6、12
    public int carpoolType;
    public int payType;  //1.支付宝;2.微信
    public int appType;//android 2就好
    public File file;
    public List<File> files;
    public String repairType;//报修类型代号
    public String parkCardFid;//要修改的某免费卡的Fid
    public String parkPlaceFid;//某停车场Fid

    private Params() {
    }

    public static Params getInstance() {
        Params params = new Params();
        params.token = UserHelper.token;
        params.cmnt_c = UserHelper.communityCode;
        return params;
    }

}