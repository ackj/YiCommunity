package com.aglhz.yicommunity.entity.bean;


import java.util.List;

/**
 * Author: LiuJia on 2017/5/7 0007 20:53.
 * Email: liujia95me@126.com
 */

public class PropertyPayDetailBean extends BaseBean{

    /**
     * data : {"fid":"eb81b16e-4002-4006-b377-8aca57853f4a","bCode":"201705311156533953502","bName":"测试201705311153","dtFrom":"2017-04-01","dtTo":"2017-04-30","totalAmt":"0.07","status":2,"payType":2,"no":"","ctime":"2017-05-31 11:56:53","pptBillDets":[{"iCode":"GGWXF","iName":"公共维修费","des":"公共维修费","iTotalAmt":"0.01"},{"iCode":"WY-BJ-004","iName":"保洁费","des":"保洁费","iTotalAmt":"0.01"},{"iCode":"WY-LJCL-006","iName":"垃圾处理费","des":"垃圾处理费","iTotalAmt":"0.01"},{"iCode":"WY-GTSD-005","iName":"公摊水电","des":"公摊水电","iTotalAmt":"0.01"},{"iCode":"WY-DT-002","iName":"电梯费用","des":"电梯费用","iTotalAmt":"0.01"},{"iCode":"WY-GGZM-003","iName":"公共照明","des":"公共照明","iTotalAmt":"0.01"},{"iCode":"WY-GL-001","iName":"物业管理费","des":"物业管理费","iTotalAmt":"0.01"}],"buildingInfo":{"fid":"eb81b16e-4002-4006-b377-8aca57853f4a","o_name":"李勇","c_name":"凯宾斯基","c_code":"KBSJ-agl-00005","b_name":"8房","b_code":"KBSJ-agl-00005-003-001-030-08","addressPre":"","address":"凯宾斯基3栋1单元30层8房","isOwner":0,"members":[]}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fid : eb81b16e-4002-4006-b377-8aca57853f4a
         * bCode : 201705311156533953502
         * bName : 测试201705311153
         * dtFrom : 2017-04-01
         * dtTo : 2017-04-30
         * totalAmt : 0.07
         * status : 2
         * payType : 2
         * no :
         * ctime : 2017-05-31 11:56:53
         * pptBillDets : [{"iCode":"GGWXF","iName":"公共维修费","des":"公共维修费","iTotalAmt":"0.01"},{"iCode":"WY-BJ-004","iName":"保洁费","des":"保洁费","iTotalAmt":"0.01"},{"iCode":"WY-LJCL-006","iName":"垃圾处理费","des":"垃圾处理费","iTotalAmt":"0.01"},{"iCode":"WY-GTSD-005","iName":"公摊水电","des":"公摊水电","iTotalAmt":"0.01"},{"iCode":"WY-DT-002","iName":"电梯费用","des":"电梯费用","iTotalAmt":"0.01"},{"iCode":"WY-GGZM-003","iName":"公共照明","des":"公共照明","iTotalAmt":"0.01"},{"iCode":"WY-GL-001","iName":"物业管理费","des":"物业管理费","iTotalAmt":"0.01"}]
         * buildingInfo : {"fid":"eb81b16e-4002-4006-b377-8aca57853f4a","o_name":"李勇","c_name":"凯宾斯基","c_code":"KBSJ-agl-00005","b_name":"8房","b_code":"KBSJ-agl-00005-003-001-030-08","addressPre":"","address":"凯宾斯基3栋1单元30层8房","isOwner":0,"members":[]}
         */

        private String fid;
        private String bCode;
        private String bName;
        private String dtFrom;
        private String dtTo;
        private String totalAmt;
        private int status;
        private int payType;
        private String no;
        private String ctime;
        private BuildingInfoBean buildingInfo;
        private List<PptBillDetsBean> pptBillDets;

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getBCode() {
            return bCode;
        }

        public void setBCode(String bCode) {
            this.bCode = bCode;
        }

        public String getBName() {
            return bName;
        }

        public void setBName(String bName) {
            this.bName = bName;
        }

        public String getDtFrom() {
            return dtFrom;
        }

        public void setDtFrom(String dtFrom) {
            this.dtFrom = dtFrom;
        }

        public String getDtTo() {
            return dtTo;
        }

        public void setDtTo(String dtTo) {
            this.dtTo = dtTo;
        }

        public String getTotalAmt() {
            return totalAmt;
        }

        public void setTotalAmt(String totalAmt) {
            this.totalAmt = totalAmt;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public BuildingInfoBean getBuildingInfo() {
            return buildingInfo;
        }

        public void setBuildingInfo(BuildingInfoBean buildingInfo) {
            this.buildingInfo = buildingInfo;
        }

        public List<PptBillDetsBean> getPptBillDets() {
            return pptBillDets;
        }

        public void setPptBillDets(List<PptBillDetsBean> pptBillDets) {
            this.pptBillDets = pptBillDets;
        }

        public static class BuildingInfoBean {
            /**
             * fid : eb81b16e-4002-4006-b377-8aca57853f4a
             * o_name : 李勇
             * c_name : 凯宾斯基
             * c_code : KBSJ-agl-00005
             * b_name : 8房
             * b_code : KBSJ-agl-00005-003-001-030-08
             * addressPre :
             * address : 凯宾斯基3栋1单元30层8房
             * isOwner : 0
             * members : []
             */

            private String fid;
            private String o_name;
            private String c_name;
            private String c_code;
            private String b_name;
            private String b_code;
            private String addressPre;
            private String address;
            private int isOwner;
            private List<?> members;

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

            public String getAddressPre() {
                return addressPre;
            }

            public void setAddressPre(String addressPre) {
                this.addressPre = addressPre;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getIsOwner() {
                return isOwner;
            }

            public void setIsOwner(int isOwner) {
                this.isOwner = isOwner;
            }

            public List<?> getMembers() {
                return members;
            }

            public void setMembers(List<?> members) {
                this.members = members;
            }
        }

        public static class PptBillDetsBean {
            /**
             * iCode : GGWXF
             * iName : 公共维修费
             * des : 公共维修费
             * iTotalAmt : 0.01
             */

            private String iCode;
            private String iName;
            private String des;
            private String iTotalAmt;

            public String getICode() {
                return iCode;
            }

            public void setICode(String iCode) {
                this.iCode = iCode;
            }

            public String getIName() {
                return iName;
            }

            public void setIName(String iName) {
                this.iName = iName;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getITotalAmt() {
                return iTotalAmt;
            }

            public void setITotalAmt(String iTotalAmt) {
                this.iTotalAmt = iTotalAmt;
            }
        }
    }
}
