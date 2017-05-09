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
    public String cmnt_c = "";
    public String status = "";
    public String dir = "";
    public String des = "";
    public String contact = "";
    public int page = 0;
    public String user = "";
    public String field = "";
    public String file = "";
    public String val = "";
    public String pwd = "";
    public int pageSize;
    public List<File> files;
    public String name;
    public String phoneNo;
    public String content;
    public int type;

    public boolean single;

    public String directory;
    public String deviceName;

    public String account;
    public String verifyCode;
    public String verifyType;
    public String password1;
    public String password2;

    private Params() {
    }

    public static Params getInstance() {
        Params params = new Params();
        params.token = UserHelper.token;
        return params;
    }
}
