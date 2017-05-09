package com.aglhz.yicommunity.house;

import java.util.List;

/**
 * Author: LiuJia on 2017/4/20 9:40.
 * Email: liujia95me@126.com
 */
public class MemberBean {

    public MemberBean(String name, int avatar, String role, List<Permission> permissions, boolean isSelect) {
        this.name = name;
        this.avatar = avatar;
        this.role = role;
        this.permissions = permissions;
        this.isSelect = isSelect;
    }

    public String name;
    public int avatar;
    public String role; //角色：是业主还是成员
    public List<Permission> permissions; //权限
    public boolean isSelect;//是否选中

    public static class Permission {
        public Permission(String desc, boolean toggle) {
            this.desc = desc;
            this.toggle = toggle;
        }
        public String desc;     //权限描述
        public boolean toggle;  //开关
    }

}
