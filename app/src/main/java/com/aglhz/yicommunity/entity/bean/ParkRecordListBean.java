package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/8 0008 21:16.
 * Email: liujia95me@126.com
 */

public class ParkRecordListBean extends BaseBean{

    /**
     * data : {"parkRecordList":[]}
     * other : {"code":200,"message":"data success","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        private List<PackRecordBean> parkRecordList;

        public List<PackRecordBean> getParkRecordList() {
            return parkRecordList;
        }

        public void setParkRecordList(List<PackRecordBean> parkRecordList) {
            this.parkRecordList = parkRecordList;
        }
    }

    public static class PackRecordBean{

        /**
         * carNo : string
         * costMoney : 0
         * createTime : string
         * fid : string
         * inTime : string
         * outTime : string
         * parkPlaceFid : string
         * parkPlaceName : string
         * parkType : string
         * totalCostTime : string
         */

        private String carNo;
        private int costMoney;
        private String createTime;
        private String fid;
        private String inTime;
        private String outTime;
        private String parkPlaceFid;
        private String parkPlaceName;
        private String parkType;
        private String totalCostTime;

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public int getCostMoney() {
            return costMoney;
        }

        public void setCostMoney(int costMoney) {
            this.costMoney = costMoney;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getInTime() {
            return inTime;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

        public String getParkPlaceFid() {
            return parkPlaceFid;
        }

        public void setParkPlaceFid(String parkPlaceFid) {
            this.parkPlaceFid = parkPlaceFid;
        }

        public String getParkPlaceName() {
            return parkPlaceName;
        }

        public void setParkPlaceName(String parkPlaceName) {
            this.parkPlaceName = parkPlaceName;
        }

        public String getParkType() {
            return parkType;
        }

        public void setParkType(String parkType) {
            this.parkType = parkType;
        }

        public String getTotalCostTime() {
            return totalCostTime;
        }

        public void setTotalCostTime(String totalCostTime) {
            this.totalCostTime = totalCostTime;
        }
    }
}
