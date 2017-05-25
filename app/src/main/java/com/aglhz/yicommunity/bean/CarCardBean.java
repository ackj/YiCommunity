package com.aglhz.yicommunity.bean;

/**
 * Author: LiuJia on 2017/5/25 0025 15:16.
 * Email: liujia95me@126.com
 */

public class CarCardBean extends BaseBean {

    /**
     * data : {"carNo":"粤S55555","costMoney":100,"customerName":"张三","endTime":"2017-06-25","monthCount":1,"monthName":"一个月","parkCardFid":"3f37f389-acb5-4c16-9524-4408061460cf","parkPlaceFid":"2c4361a2-450c-4cc7-a268-b2762d6501be","parkPlaceName":"凯宾斯基停车场","phoneNo":"133333333","startTime":"2017-05-25"}
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
         * carNo : 粤S55555
         * costMoney : 100.0
         * customerName : 张三
         * endTime : 2017-06-25
         * monthCount : 1
         * monthName : 一个月
         * parkCardFid : 3f37f389-acb5-4c16-9524-4408061460cf
         * parkPlaceFid : 2c4361a2-450c-4cc7-a268-b2762d6501be
         * parkPlaceName : 凯宾斯基停车场
         * phoneNo : 133333333
         * startTime : 2017-05-25
         */

        private String carNo;
        private double costMoney;
        private String customerName;
        private String endTime;
        private int monthCount;
        private String monthName;
        private String parkCardFid;
        private String parkPlaceFid;
        private String parkPlaceName;
        private String phoneNo;
        private String startTime;

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

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getMonthCount() {
            return monthCount;
        }

        public void setMonthCount(int monthCount) {
            this.monthCount = monthCount;
        }

        public String getMonthName() {
            return monthName;
        }

        public void setMonthName(String monthName) {
            this.monthName = monthName;
        }

        public String getParkCardFid() {
            return parkCardFid;
        }

        public void setParkCardFid(String parkCardFid) {
            this.parkCardFid = parkCardFid;
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

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
    }
}
