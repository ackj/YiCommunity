package com.aglhz.yicommunity.common;


import java.io.File;
import java.util.List;

/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    private static final String TAG = Params.class.getSimpleName();
    private static Params INSTANCE;
    public static String token;
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
    public String cmnt_c = "";
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
    public boolean isProprietor = true;
    public boolean single;
    public boolean timeable;
    public boolean summerable;
    public int positionType;
    public int type;
    public int pageSize = Constants.PAGE_SIZE;
    public int status;
    public int page = 1;
    public int carpoolType;
    public File file;
    public List<File> files;
    public int payType;  //1.支付宝;2.微信
    public String id;
    public int appType;//android 2就好
    public String secondCategoryId;//二级菜单的id
    public String keywords;
    public String carNo;
    public String monthName;
    public int monthCount;//月数值：1、6、12
    public String authFid;


    private Params() {
    }

    public static Params getInstance() {
        if (INSTANCE == null) {
            synchronized (Params.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Params();
                }
            }
        }
        init();
        return INSTANCE;
    }

    private static void init() {
        token = UserHelper.token;
    }

    @Override
    public String toString() {
        return "Params{" +
                "sc='" + sc + '\'' +
                ", dir='" + dir + '\'' +
                ", des='" + des + '\'' +
                ", contact='" + contact + '\'' +
                ", user='" + user + '\'' +
                ", field='" + field + '\'' +
                ", val='" + val + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", content='" + content + '\'' +
                ", pwd0='" + pwd0 + '\'' +
                ", pwd1='" + pwd1 + '\'' +
                ", pwd2='" + pwd2 + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", cmnt_c='" + cmnt_c + '\'' +
                ", bdg_c='" + bdg_c + '\'' +
                ", bdg_u_c='" + bdg_u_c + '\'' +
                ", bdg_f_c='" + bdg_f_c + '\'' +
                ", bdg_f_h_c='" + bdg_f_h_c + '\'' +
                ", idCard='" + idCard + '\'' +
                ", fid='" + fid + '\'' +
                ", url='" + url + '\'' +
                ", mfid='" + mfid + '\'' +
                ", picode='" + picode + '\'' +
                ", directory='" + directory + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", account='" + account + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", verifyType='" + verifyType + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", price='" + price + '\'' +
                ", startPlace='" + startPlace + '\'' +
                ", endPlace='" + endPlace + '\'' +
                ", outTime='" + outTime + '\'' +
                ", currentPositionLat='" + currentPositionLat + '\'' +
                ", billFids='" + billFids + '\'' +
                ", currentPositionLng='" + currentPositionLng + '\'' +
                ", positionAddress='" + positionAddress + '\'' +
                ", isProprietor=" + isProprietor +
                ", single=" + single +
                ", timeable=" + timeable +
                ", summerable=" + summerable +
                ", positionType=" + positionType +
                ", type=" + type +
                ", pageSize=" + pageSize +
                ", status=" + status +
                ", page=" + page +
                ", carpoolType=" + carpoolType +
                ", file=" + file +
                ", files=" + files +
                ", payType=" + payType +
                ", id='" + id + '\'' +
                ", appType=" + appType +
                '}';
    }
}