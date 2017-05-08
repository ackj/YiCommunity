package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/7 0007 20:53.
 * Email: liujia95me@126.com
 */

public class PropertyPayBean extends BaseBean{
    /**
     * data : {"totalAmt":"120.02","obpptBills":[{"fid":"6fef1eb8-966e-409e-a276-69c3d8e7e683","bCode":"201701251915539169187","bName":"物业账单11月","dtFrom":"2016-11-01","dtTo":"2016-11-30","totalAmt":"0.01","status":2,"payType":1,"no":"huangyk2017","ctime":"2017-01-25 19:15:54","pptBillDets":[{"iCode":"WY-DT-002","iName":"电梯费用","des":"电梯费用","iTotalAmt":"0.0"},{"iCode":"WY-GL-001","iName":"物业管理费","des":"物业管理费","iTotalAmt":"0.01"},{"iCode":"WY-GGZM-003","iName":"公共照明","des":"公共照明","iTotalAmt":"0.0"},{"iCode":"WY-GTSD-005","iName":"公摊水电","des":"公摊水电","iTotalAmt":"0.0"},{"iCode":"WY-BJ-004","iName":"保洁费","des":"保洁费","iTotalAmt":"0.0"}],"buildingInfo":{"o_name":"黄远科","c_name":"东湖花园1期","c_code":"YSQ-DHHY-01-00002","b_name":"1单元1层1001","b_code":"YSQ-DHHY-01-001U-001-001-00001"}},{"fid":"77d8ba23-792e-4632-8f83-a08451c02e3d","bCode":"201701241758535405413","bName":"物业账单12月","dtFrom":"2016-12-01","dtTo":"2016-12-31","totalAmt":"0.01","status":2,"payType":1,"no":"wuweiyuan2017","ctime":"2017-01-24 17:58:54","pptBillDets":[{"iCode":"WY-GTSD-005","iName":"公摊水电","des":"公摊水电","iTotalAmt":"0.0"},{"iCode":"WY-DT-002","iName":"电梯费用","des":"电梯费用","iTotalAmt":"0.0"},{"iCode":"WY-BJ-004","iName":"保洁费","des":"保洁费","iTotalAmt":"0.0"},{"iCode":"WY-GGZM-003","iName":"公共照明","des":"公共照明","iTotalAmt":"0.0"},{"iCode":"WY-GL-001","iName":"物业管理费","des":"物业管理费","iTotalAmt":"0.01"}],"buildingInfo":{"o_name":"吴伟沅","c_name":"水北新村1期","c_code":"YSQ-SBXC-01-00001","b_name":"1单元2层2012","b_code":"YSQ-SBXC-01-001U-002-012-00012"}},{"fid":"1","bCode":"SBXC-01-000000001","bName":"物业费8月到9月","dtFrom":"2017-01-01","dtTo":"2017-01-31","totalAmt":"120.00","status":2,"payType":0,"no":"wdj123","ctime":"2017-01-23 10:45:25","pptBillDets":[{"iCode":"WY-DT-002","iName":"电梯费用","des":"电梯费用","iTotalAmt":"20.0"},{"iCode":"WY-GTSD-005","iName":"公摊水电","des":"公摊水电","iTotalAmt":"20.0"},{"iCode":"WY-GL-001","iName":"物业管理费","des":"物业管理费","iTotalAmt":"30.0"},{"iCode":"WY-BJ-004","iName":"保洁费","des":"保洁费","iTotalAmt":"25.0"},{"iCode":"WY-GGZM-003","iName":"公共照明","des":"公共照明","iTotalAmt":"25.0"}],"buildingInfo":{"o_name":"吴三","c_name":"水北新村1期","c_code":"YSQ-SBXC-01-00001","b_name":"1单元1层1001","b_code":"YSQ-SBXC-01-001U-001-1001-00001"}}]}
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
         * totalAmt : 120.02
         * obpptBills : [{"fid":"6fef1eb8-966e-409e-a276-69c3d8e7e683","bCode":"201701251915539169187","bName":"物业账单11月","dtFrom":"2016-11-01","dtTo":"2016-11-30","totalAmt":"0.01","status":2,"payType":1,"no":"huangyk2017","ctime":"2017-01-25 19:15:54","pptBillDets":[{"iCode":"WY-DT-002","iName":"电梯费用","des":"电梯费用","iTotalAmt":"0.0"},{"iCode":"WY-GL-001","iName":"物业管理费","des":"物业管理费","iTotalAmt":"0.01"},{"iCode":"WY-GGZM-003","iName":"公共照明","des":"公共照明","iTotalAmt":"0.0"},{"iCode":"WY-GTSD-005","iName":"公摊水电","des":"公摊水电","iTotalAmt":"0.0"},{"iCode":"WY-BJ-004","iName":"保洁费","des":"保洁费","iTotalAmt":"0.0"}],"buildingInfo":{"o_name":"黄远科","c_name":"东湖花园1期","c_code":"YSQ-DHHY-01-00002","b_name":"1单元1层1001","b_code":"YSQ-DHHY-01-001U-001-001-00001"}},{"fid":"77d8ba23-792e-4632-8f83-a08451c02e3d","bCode":"201701241758535405413","bName":"物业账单12月","dtFrom":"2016-12-01","dtTo":"2016-12-31","totalAmt":"0.01","status":2,"payType":1,"no":"wuweiyuan2017","ctime":"2017-01-24 17:58:54","pptBillDets":[{"iCode":"WY-GTSD-005","iName":"公摊水电","des":"公摊水电","iTotalAmt":"0.0"},{"iCode":"WY-DT-002","iName":"电梯费用","des":"电梯费用","iTotalAmt":"0.0"},{"iCode":"WY-BJ-004","iName":"保洁费","des":"保洁费","iTotalAmt":"0.0"},{"iCode":"WY-GGZM-003","iName":"公共照明","des":"公共照明","iTotalAmt":"0.0"},{"iCode":"WY-GL-001","iName":"物业管理费","des":"物业管理费","iTotalAmt":"0.01"}],"buildingInfo":{"o_name":"吴伟沅","c_name":"水北新村1期","c_code":"YSQ-SBXC-01-00001","b_name":"1单元2层2012","b_code":"YSQ-SBXC-01-001U-002-012-00012"}},{"fid":"1","bCode":"SBXC-01-000000001","bName":"物业费8月到9月","dtFrom":"2017-01-01","dtTo":"2017-01-31","totalAmt":"120.00","status":2,"payType":0,"no":"wdj123","ctime":"2017-01-23 10:45:25","pptBillDets":[{"iCode":"WY-DT-002","iName":"电梯费用","des":"电梯费用","iTotalAmt":"20.0"},{"iCode":"WY-GTSD-005","iName":"公摊水电","des":"公摊水电","iTotalAmt":"20.0"},{"iCode":"WY-GL-001","iName":"物业管理费","des":"物业管理费","iTotalAmt":"30.0"},{"iCode":"WY-BJ-004","iName":"保洁费","des":"保洁费","iTotalAmt":"25.0"},{"iCode":"WY-GGZM-003","iName":"公共照明","des":"公共照明","iTotalAmt":"25.0"}],"buildingInfo":{"o_name":"吴三","c_name":"水北新村1期","c_code":"YSQ-SBXC-01-00001","b_name":"1单元1层1001","b_code":"YSQ-SBXC-01-001U-001-1001-00001"}}]
         */

        private String totalAmt;
        private List<ObpptBillsBean> obpptBills;

        public String getTotalAmt() {
            return totalAmt;
        }

        public void setTotalAmt(String totalAmt) {
            this.totalAmt = totalAmt;
        }

        public List<ObpptBillsBean> getObpptBills() {
            return obpptBills;
        }

        public void setObpptBills(List<ObpptBillsBean> obpptBills) {
            this.obpptBills = obpptBills;
        }

        public static class ObpptBillsBean {
            /**
             * fid : 6fef1eb8-966e-409e-a276-69c3d8e7e683
             * bCode : 201701251915539169187
             * bName : 物业账单11月
             * dtFrom : 2016-11-01
             * dtTo : 2016-11-30
             * totalAmt : 0.01
             * status : 2
             * payType : 1
             * no : huangyk2017
             * ctime : 2017-01-25 19:15:54
             * pptBillDets : [{"iCode":"WY-DT-002","iName":"电梯费用","des":"电梯费用","iTotalAmt":"0.0"},{"iCode":"WY-GL-001","iName":"物业管理费","des":"物业管理费","iTotalAmt":"0.01"},{"iCode":"WY-GGZM-003","iName":"公共照明","des":"公共照明","iTotalAmt":"0.0"},{"iCode":"WY-GTSD-005","iName":"公摊水电","des":"公摊水电","iTotalAmt":"0.0"},{"iCode":"WY-BJ-004","iName":"保洁费","des":"保洁费","iTotalAmt":"0.0"}]
             * buildingInfo : {"o_name":"黄远科","c_name":"东湖花园1期","c_code":"YSQ-DHHY-01-00002","b_name":"1单元1层1001","b_code":"YSQ-DHHY-01-001U-001-001-00001"}
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
                 * o_name : 黄远科
                 * c_name : 东湖花园1期
                 * c_code : YSQ-DHHY-01-00002
                 * b_name : 1单元1层1001
                 * b_code : YSQ-DHHY-01-001U-001-001-00001
                 */

                private String o_name;
                private String c_name;
                private String c_code;
                private String b_name;
                private String b_code;

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

            public static class PptBillDetsBean {
                /**
                 * iCode : WY-DT-002
                 * iName : 电梯费用
                 * des : 电梯费用
                 * iTotalAmt : 0.0
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
}
