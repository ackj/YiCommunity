package com.aglhz.yicommunity.bean;

/**
 * Author: LiuJia on 2017/6/1 0001 10:02.
 * Email: liujia95me@126.com
 */

public class ParkOrderBean extends BaseBean {


    /**
     * data : {"billCode":"201706010956315140178","carNo":"YK22222","costMoney":5,"createTime":"2017-06-01 09:56","inTime":"2017-06-01 07:24","outTime":"2017-06-01 09:24","parkPlaceFid":"2c4361a2-450c-4cc7-a268-b2762d6501be","parkPlaceName":"凯宾斯基停车场","totalCostTime":"2小时"}
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
         * billCode : 201706010956315140178
         * carNo : YK22222
         * costMoney : 5.0
         * createTime : 2017-06-01 09:56
         * inTime : 2017-06-01 07:24
         * outTime : 2017-06-01 09:24
         * parkPlaceFid : 2c4361a2-450c-4cc7-a268-b2762d6501be
         * parkPlaceName : 凯宾斯基停车场
         * totalCostTime : 2小时
         */

        private String billCode;
        private String carNo;
        private double costMoney;
        private String createTime;
        private String inTime;
        private String outTime;
        private String parkPlaceFid;
        private String parkPlaceName;
        private String totalCostTime;

        public String getBillCode() {
            return billCode;
        }

        public void setBillCode(String billCode) {
            this.billCode = billCode;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public double getCostMoney() {
            return costMoney;
        }

        public void setCostMoney(double costMoney) {
            this.costMoney = costMoney;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getTotalCostTime() {
            return totalCostTime;
        }

        public void setTotalCostTime(String totalCostTime) {
            this.totalCostTime = totalCostTime;
        }
    }
}
