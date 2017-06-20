package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Created by YandZD on 2017/2/23.
 */

public class MyHousesBean extends BaseBean {


    /**
     * data : {"authBuildings":[{"address":"水北新村1期1栋1单元6层6房","addressPre":"","b_code":"SBXC1Q-glx-00001-001-001-006-06","b_name":"6房","c_code":"SBXC1Q-glx-00001","c_name":"水北新村1期","fid":"b18bc613-52a6-45c8-808d-304b31211a40","isOwner":1,"members":[{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170601170523419288.jpg","isOwner":1,"mname":"刘嘉","name":""},{"fid":"4fc724ec-9c5f-422e-ad5b-20d7d7810cdd","icon":"http://aglhzmall.image.alimmdn.com/member/20170525142643985471.jpg","isOwner":0,"mname":"我看看","name":""}],"o_name":"刘嘉"},{"address":"水北新村1期1栋1单元1层5房","addressPre":"","b_code":"SBXC1Q-glx-00001-001-001-001-05","b_name":"5房","c_code":"SBXC1Q-glx-00001","c_name":"水北新村1期","fid":"31f7dd7e-9fac-443b-81b5-991e28c202be","isOwner":1,"members":[{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170601170523419288.jpg","isOwner":1,"mname":"刘嘉","name":""}],"o_name":"刘嘉"},{"address":"水北新村1期1栋1单元1层4房","addressPre":"","b_code":"SBXC1Q-glx-00001-001-001-001-04","b_name":"4房","c_code":"SBXC1Q-glx-00001","c_name":"水北新村1期","fid":"053bb878-9ef0-4c33-b63c-f5ccb8f684f0","isOwner":1,"members":[{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170601170523419288.jpg","isOwner":1,"mname":"刘嘉","name":""},{"fid":"4fc724ec-9c5f-422e-ad5b-20d7d7810cdd","icon":"http://aglhzmall.image.alimmdn.com/member/20170525142643985471.jpg","isOwner":0,"mname":"我看看","name":""}],"o_name":"刘嘉"},{"address":"凯宾斯基3栋1单元2层8房","addressPre":"","b_code":"KBSJ-agl-00005-003-001-002-08","b_name":"8房","c_code":"KBSJ-agl-00005","c_name":"凯宾斯基","fid":"bc041e7d-1890-483e-b320-8b02a79fc6a2","isOwner":0,"members":[{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170601170523419288.jpg","isOwner":1,"mname":"刘嘉","name":""}],"o_name":"刘佳"},{"address":"凯宾斯基3栋1单元30层8房","addressPre":"","b_code":"KBSJ-agl-00005-003-001-030-08","b_name":"8房","c_code":"KBSJ-agl-00005","c_name":"凯宾斯基","fid":"6207e273-416a-4967-8d18-f9c25b0cea52","isOwner":0,"members":[{"fid":"4fc724ec-9c5f-422e-ad5b-20d7d7810cdd","icon":"http://aglhzmall.image.alimmdn.com/member/20170525142643985471.jpg","isOwner":0,"mname":"我看看","name":""},{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170601170523419288.jpg","isOwner":1,"mname":"刘嘉","name":""}],"o_name":"刘嘉"}]}
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
             * address : 水北新村1期1栋1单元6层6房
             * addressPre :
             * b_code : SBXC1Q-glx-00001-001-001-006-06
             * b_name : 6房
             * c_code : SBXC1Q-glx-00001
             * c_name : 水北新村1期
             * fid : b18bc613-52a6-45c8-808d-304b31211a40
             * isOwner : 1
             * members : [{"fid":"a40c120a-4107-4af8-ac25-f999ebce5363","icon":"http://aglhzmall.image.alimmdn.com/member/20170601170523419288.jpg","isOwner":1,"mname":"刘嘉","name":""},{"fid":"4fc724ec-9c5f-422e-ad5b-20d7d7810cdd","icon":"http://aglhzmall.image.alimmdn.com/member/20170525142643985471.jpg","isOwner":0,"mname":"我看看","name":""}]
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
                 * fid : a40c120a-4107-4af8-ac25-f999ebce5363
                 * icon : http://aglhzmall.image.alimmdn.com/member/20170601170523419288.jpg
                 * isOwner : 1
                 * mname : 刘嘉
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
