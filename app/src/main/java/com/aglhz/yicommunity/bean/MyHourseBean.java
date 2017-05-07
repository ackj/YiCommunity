package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Created by YandZD on 2017/2/23.
 */

public class MyHourseBean extends BaseBean {

    /**
     * data : {"authBuildings":[{"fid":"307afbba-abf8-4308-b17c-8c6ee8523cfd","o_name":"吴伟沅","c_name":"水北新村1期","c_code":"YSQ-SBXC-01-00001","b_name":"1单元2层2012","b_code":"YSQ-SBXC-01-001U-002-012-00012"}]}
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
             * fid : 307afbba-abf8-4308-b17c-8c6ee8523cfd
             * o_name : 吴伟沅
             * c_name : 水北新村1期
             * c_code : YSQ-SBXC-01-00001
             * b_name : 1单元2层2012
             * b_code : YSQ-SBXC-01-001U-002-012-00012
             */

            private String fid;
            private String o_name;
            private String c_name;
            private String c_code;
            private String b_name;
            private String b_code;

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

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getC_code() {
                return c_code;
            }

            public void setC_code(String c_code) {
                this.c_code = c_code;
            }

            public String getB_name() {
                return b_name;
            }

            public void setB_name(String b_name) {
                this.b_name = b_name;
            }

            public String getB_code() {
                return b_code;
            }

            public void setB_code(String b_code) {
                this.b_code = b_code;
            }
        }
    }
}
