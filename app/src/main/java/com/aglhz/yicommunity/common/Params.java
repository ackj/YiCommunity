package com.aglhz.yicommunity.common;

/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    public String sc = "AglhzYsq";
    public String token;
    public String cmnt_c;
    public String status;

    public String user;
    public String pwd;

    public static Params getInstance() {
        Params params = new Params();
        params.cmnt_c = UserHelper.token;
        return params;
    }
}
