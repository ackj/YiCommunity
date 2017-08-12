package com.aglhz.yicommunity.entity.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YandZD on 2017/2/23.
 */

public class MyHousesBean extends BaseBean {


    /**
     * data : {"authBuildings":[{"address":"凯宾斯基3栋1单元30层8房","addressPre":"","b_code":"KBSJ-agl-00005-003-001-030-08","b_name":"8房","c_code":"KBSJ-agl-00005","c_name":"凯宾斯基","fid":"74fe7634-83ad-499c-b775-adeafd0dbe8e","isOwner":0,"members":[{"fid":"4fc724ec-9c5f-422e-ad5b-20d7d7810cdd","icon":"http://aglhzmall.image.alimmdn.com/member/20170804113107729314.jpg","identityType":1,"mname":"我看看","name":"李勇"},{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","identityType":2,"mname":"刘嘉","name":"刘嘉"},{"fid":"511aed9d-fe60-480d-b84a-3ba8cb838078","icon":"http://aglhzmall.image.alimmdn.com/member/20170728180258803342.jpg","identityType":2,"mname":"有意思","name":"刘嘉"},{"fid":"8a4dc5ae-9f87-426e-9923-11bbe7dcc170","icon":"http://aglhzmall.image.alimmdn.com/member/20170623165300450847.png","identityType":3,"mname":"最帅最帅的高诚龙哥","name":"NJL"},{"fid":"6b371bdd-f7c8-4e63-b887-ce5e30d24fa7","icon":"http://aglhzmall.image.alimmdn.com/member/20170428105040047152.jpg","identityType":3,"mname":"鱼非鱼","name":"基哥"}],"o_name":"刘嘉"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<AuthBuildingsBean> authBuildings;

        public List<AuthBuildingsBean> getAuthBuildings() {
            return authBuildings;
        }

        public void setAuthBuildings(List<AuthBuildingsBean> authBuildings) {
            this.authBuildings = authBuildings;
        }

        public static class AuthBuildingsBean implements Serializable{
            /**
             * address : 凯宾斯基3栋1单元30层8房
             * addressPre :
             * b_code : KBSJ-agl-00005-003-001-030-08
             * b_name : 8房
             * c_code : KBSJ-agl-00005
             * c_name : 凯宾斯基
             * fid : 74fe7634-83ad-499c-b775-adeafd0dbe8e
             * isOwner : 0
             * members : [{"fid":"4fc724ec-9c5f-422e-ad5b-20d7d7810cdd","icon":"http://aglhzmall.image.alimmdn.com/member/20170804113107729314.jpg","identityType":1,"mname":"我看看","name":"李勇"},{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","identityType":2,"mname":"刘嘉","name":"刘嘉"},{"fid":"511aed9d-fe60-480d-b84a-3ba8cb838078","icon":"http://aglhzmall.image.alimmdn.com/member/20170728180258803342.jpg","identityType":2,"mname":"有意思","name":"刘嘉"},{"fid":"8a4dc5ae-9f87-426e-9923-11bbe7dcc170","icon":"http://aglhzmall.image.alimmdn.com/member/20170623165300450847.png","identityType":3,"mname":"最帅最帅的高诚龙哥","name":"NJL"},{"fid":"6b371bdd-f7c8-4e63-b887-ce5e30d24fa7","icon":"http://aglhzmall.image.alimmdn.com/member/20170428105040047152.jpg","identityType":3,"mname":"鱼非鱼","name":"基哥"}]
             * o_name : 刘嘉
             */

            private String address;
            private String addressPre;
            private String b_code;
            private String b_name;
            private String c_code;
            private String c_name;
            private String fid;
            private int isOwner;
            private String o_name;
            private List<MembersBean> members;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAddressPre() {
                return addressPre;
            }

            public void setAddressPre(String addressPre) {
                this.addressPre = addressPre;
            }

            public String getB_code() {
                return b_code;
            }

            public void setB_code(String b_code) {
                this.b_code = b_code;
            }

            public String getB_name() {
                return b_name;
            }

            public void setB_name(String b_name) {
                this.b_name = b_name;
            }

            public String getC_code() {
                return c_code;
            }

            public void setC_code(String c_code) {
                this.c_code = c_code;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public int getIsOwner() {
                return isOwner;
            }

            public void setIsOwner(int isOwner) {
                this.isOwner = isOwner;
            }

            public String getO_name() {
                return o_name;
            }

            public void setO_name(String o_name) {
                this.o_name = o_name;
            }

            public List<MembersBean> getMembers() {
                return members;
            }

            public void setMembers(List<MembersBean> members) {
                this.members = members;
            }

            public static class MembersBean {
                /**
                 * fid : 4fc724ec-9c5f-422e-ad5b-20d7d7810cdd
                 * icon : http://aglhzmall.image.alimmdn.com/member/20170804113107729314.jpg
                 * identityType : 1
                 * mname : 我看看
                 * name : 李勇
                 */

                private String fid;
                private String icon;
                private int identityType;
                private String mname;
                private String name;

                public String getFid() {
                    return fid;
                }

                public void setFid(String fid) {
                    this.fid = fid;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public int getIdentityType() {
                    return identityType;
                }

                public void setIdentityType(int identityType) {
                    this.identityType = identityType;
                }

                public String getMname() {
                    return mname;
                }

                public void setMname(String mname) {
                    this.mname = mname;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
