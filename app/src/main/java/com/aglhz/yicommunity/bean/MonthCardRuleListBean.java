package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/24 0024 11:55.
 * Email: liujia95me@126.com
 */

public class MonthCardRuleListBean extends BaseBean{

    /**
     * data : {"monthCardRuleList":[{"endDate":"2017-06-24","fid":"1","money":250,"monthCount":1,"name":"一个月","sequenceNum":1,"startDate":"2017-05-24"},{"endDate":"2017-07-24","fid":"2","money":450,"monthCount":2,"name":"两个月","sequenceNum":2,"startDate":"2017-05-24"},{"endDate":"2017-08-24","fid":"3","money":650,"monthCount":3,"name":"三个月","sequenceNum":3,"startDate":"2017-05-24"},{"endDate":"2017-11-24","fid":"4","money":1200,"monthCount":6,"name":"半年","sequenceNum":4,"startDate":"2017-05-24"},{"endDate":"2018-05-24","fid":"5","money":2200,"monthCount":12,"name":"一年","sequenceNum":5,"startDate":"2017-05-24"},{"endDate":"2019-05-24","fid":"6","money":3600,"monthCount":24,"name":"两年","sequenceNum":6,"startDate":"2017-05-24"},{"endDate":"2020-05-24","fid":"7","money":5000,"monthCount":36,"name":"三年","sequenceNum":7,"startDate":"2017-05-24"}]}
     */
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MonthCardRuleBean> monthCardRuleList;

        public List<MonthCardRuleBean> getMonthCardRuleList() {
            return monthCardRuleList;
        }

        public void setMonthCardRuleList(List<MonthCardRuleBean> monthCardRuleList) {
            this.monthCardRuleList = monthCardRuleList;
        }

        public static class MonthCardRuleBean {

            /**
             * endDate : 2017-06-24
             * fid : 1
             * money : 250.0
             * monthCount : 1
             * name : 一个月
             * sequenceNum : 1
             * startDate : 2017-05-24
             */
            private String endDate;
            private String fid;
            private double money;
            private int monthCount;
            private String name;
            private int sequenceNum;
            private String startDate;

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public int getMonthCount() {
                return monthCount;
            }

            public void setMonthCount(int monthCount) {
                this.monthCount = monthCount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSequenceNum() {
                return sequenceNum;
            }

            public void setSequenceNum(int sequenceNum) {
                this.sequenceNum = sequenceNum;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }
        }
    }
}
