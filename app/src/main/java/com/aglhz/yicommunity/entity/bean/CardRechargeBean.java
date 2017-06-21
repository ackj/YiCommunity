package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/25 0025 15:51.
 * Email: liujia95me@126.com
 */

public class CardRechargeBean extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * carNo : string
         * costMoney : 0
         * customerName : string
         * endTime : string
         * monthCardRuleList : [{"endDate":"string","fid":"string","money":0,"monthCount":0,"name":"string","remark":"string","sequenceNum":0,"startDate":"string"}]
         * monthCount : 0
         * monthName : string
         * parkCardFid : string
         * parkPlaceFid : string
         * parkPlaceName : string
         * phoneNo : string
         * startTime : string
         */

        private String carNo;
        private int costMoney;
        private String customerName;
        private String endTime;
        private int monthCount;
        private String monthName;
        private String parkCardFid;
        private String parkPlaceFid;
        private String parkPlaceName;
        private String phoneNo;
        private String startTime;
        private List<MonthCardRuleBean> monthCardRuleList;

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

        public List<MonthCardRuleBean> getMonthCardRuleList() {
            return monthCardRuleList;
        }

        public void setMonthCardRuleList(List<MonthCardRuleBean> monthCardRuleList) {
            this.monthCardRuleList = monthCardRuleList;
        }
    }

}
