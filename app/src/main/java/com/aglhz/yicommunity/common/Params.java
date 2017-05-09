package com.aglhz.yicommunity.common;

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

    private Params() {
    }

    public static Params getInstance() {
        Params params = new Params();
        params.token = UserHelper.token;
        return params;
    }
}
