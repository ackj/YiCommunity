package com.aglhz.yicommunity.common;

import java.io.File;
import java.util.List;

/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    public String sc = "AglhzYsq";
    public String token = "";
    public int status;
    public String dir = "";
    public String des = "";
    public String contact = "";
    public int page = 0;
    public String user = "";
    public String field = "";
    public String file = "";
    public String val = "";
    public String pwd = "";
    public int pageSize = 20;
    public List<File> files;
    public String name = "";
    public String phoneNo = "";
    public String content = "";
    public int type;
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
    public boolean isProprietor = true;
    public String idCard = "";
    public String fid = "";
    public String url = "";
    public String mfid = "";
    public String picode = "";

    public boolean single;

    public String directory;
    public String deviceName;

    public String account;
    public String verifyCode;
    public String verifyType;
    public String password1;
    public String password2;
    public boolean summerable;
    public boolean timeable;


    private Params() {
    }

    public static Params getInstance() {
        Params params = new Params();
        params.token = UserHelper.token;
        return params;
    }
}
