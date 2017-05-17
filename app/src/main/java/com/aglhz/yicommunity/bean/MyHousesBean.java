package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Created by YandZD on 2017/2/23.
 */

public class MyHousesBean extends BaseBean {


    /**
     * data : {"authBuildings":[{"address":"广东省惠州市惠城区凯宾斯基3栋1单元8层5房","b_code":"KBSJ-agl-00005-003-001-008-05","b_name":"5房","c_code":"KBSJ-agl-00005","c_name":"凯宾斯基","fid":"d325d4d4-8de7-4cce-b0c4-f39c5b8af00f","members":[{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170425090419463319.jpg","isOwner":1,"mname":"rnzbr8008","name":""}],"o_name":"张三"},{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层6房","b_code":"KBSJ-agl-00005-003-001-016-06","b_name":"6房","c_code":"KBSJ-agl-00005","c_name":"凯宾斯基","fid":"ebd4cb41-0500-4254-a1be-45cee1054930","members":[],"o_name":"刘嘉"},{"address":"广东省惠州市惠城区凯宾斯基3栋1单元1层1房","b_code":"KBSJ-agl-00005-003-001-001-01","b_name":"1房","c_code":"KBSJ-agl-00005","c_name":"凯宾斯基","fid":"0b93e5a9-f3dd-42bc-a69c-cd43e803b9c6","members":[],"o_name":"刘佳1房"}]}
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

        public static class AuthBuildingsBean {
            /**
             * address : 广东省惠州市惠城区凯宾斯基3栋1单元8层5房
             * b_code : KBSJ-agl-00005-003-001-008-05
             * b_name : 5房
             * c_code : KBSJ-agl-00005
             * c_name : 凯宾斯基
             * fid : d325d4d4-8de7-4cce-b0c4-f39c5b8af00f
             * members : [{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170425090419463319.jpg","isOwner":1,"mname":"rnzbr8008","name":""}]
             * o_name : 张三
             */

            private String address;
            private String b_code;
            private String b_name;
            private String c_code;
            private String c_name;
            private String fid;
            private String o_name;
            private List<MembersBean> members;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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
                 * fid : a40c120a-4107-4af8-ac25-f999ebce5363
                 * icon : http://aglhzmall.image.alimmdn.com/member/20170425090419463319.jpg
                 * isOwner : 1
                 * mname : rnzbr8008
                 * name :
                 */

                private String fid;
                private String icon;
                private int isOwner;
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

                public int getIsOwner() {
                    return isOwner;
                }

                public void setIsOwner(int isOwner) {
                    this.isOwner = isOwner;
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
