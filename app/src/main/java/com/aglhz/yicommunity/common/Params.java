package com.aglhz.yicommunity.common;

import java.io.File;
import java.util.List;

/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    public String sc = "AglhzYsq";
    public String token;
    public String cmnt_c;
    public String status;
    public String dir;
    public int page;
    public int pageSize;

    public String user;
    public String pwd;
    public List<File> files;
    public String name;
    public String phoneNo;
    public String content;
    public int type;

    private Params() {
    }

    public static Params getInstance() {
        Params params = new Params();
        params.token = UserHelper.token;
        return params;
    }
}
