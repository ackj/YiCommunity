package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/19 0019 09:39.
 * Email: liujia95me@126.com
 */

public class RepairDetailBean extends BaseBean {

    /**
     * data : {"contact":"13531688695","ctime":"2017-05-14","des":"电梯坏了","fid":"69386ab3-fb42-4a1d-a45d-3b6bd0a6b106","house":"","name":"哈哈","pics":["http://aglhzysq.image.alimmdn.com/repairApply/20170514172650370810.jpg"],"replys":[],"single":false,"status":0,"type":""}
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
         * contact : 13531688695
         * ctime : 2017-05-14
         * des : 电梯坏了
         * fid : 69386ab3-fb42-4a1d-a45d-3b6bd0a6b106
         * house :
         * name : 哈哈
         * pics : ["http://aglhzysq.image.alimmdn.com/repairApply/20170514172650370810.jpg"]
         * replys : []
         * single : false
         * status : 0
         * type :
         */

        private String contact;
        private String ctime;
        private String des;
        private String fid;
        private String house;
        private String name;
        private boolean single;
        private int status;
        private String type;
        private List<String> pics;
        private List<ReplysBean> replys;

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

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

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }

        public List<ReplysBean> getReplys() {
            return replys;
        }

        public void setReplys(List<ReplysBean> replys) {
            this.replys = replys;
        }


    }

    public static class ReplysBean{
        private String ctime;
        private String des;

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
    }
}
