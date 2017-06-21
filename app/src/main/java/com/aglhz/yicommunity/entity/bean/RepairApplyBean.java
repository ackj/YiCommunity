package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/8 0008 21:27.
 * Email: liujia95me@126.com
 */

public class RepairApplyBean extends BaseBean {


    /**
     * data : {"repairApplys":[{"ctime":"2017-05-08","des":"简历","fid":"18ccae3a-7d47-4ab9-b7c3-58cd12c114cc","pics":[],"replys":[],"single":false,"status":0,"type":"电梯"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RepairApplysBean> repairApplys;

        public List<RepairApplysBean> getRepairApplys() {
            return repairApplys;
        }

        public void setRepairApplys(List<RepairApplysBean> repairApplys) {
            this.repairApplys = repairApplys;
        }

        public static class RepairApplysBean {
            /**
             * ctime : 2017-05-08
             * des : 简历
             * fid : 18ccae3a-7d47-4ab9-b7c3-58cd12c114cc
             * pics : []
             * replys : []
             * single : false
             * status : 0
             * type : 电梯
             */

            private String ctime;
            private String des;
            private String fid;
            private boolean single;
            private int status;
            private String type;
            private List<?> pics;
            private List<?> replys;

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public boolean isSingle() {
                return single;
            }

            public void setSingle(boolean single) {
                this.single = single;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<?> getPics() {
                return pics;
            }

            public void setPics(List<?> pics) {
                this.pics = pics;
            }

            public List<?> getReplys() {
                return replys;
            }

            public void setReplys(List<?> replys) {
                this.replys = replys;
            }
        }
    }
}
