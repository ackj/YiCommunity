package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/27 0027 15:16.
 * Email: liujia95me@126.com
 */

public class MonthCardBillListBean extends BaseBean {

    /**
     * data : {"monthCardBillList":[{"billCode":"201705261747123453","carNo":"粤S55555","endTime":"2017-06-26","money":100,"monthCount":1,"parkPlace":{"address":"凯宾斯基负二层停车场","communityFid":"KBSJ-agl-00005","communityName":"惠州市 凯宾斯基","fid":"2c4361a2-450c-4cc7-a268-b2762d6501be","name":"凯宾斯基停车场","regionInfo":"惠城区广东省惠州市"},"payTime":"2017-05-26","startTime":"2017-05-26"},{"billCode":"201705241736124312","carNo":"粤B88888","endTime":"2017-06-24","money":100,"monthCount":1,"parkPlace":{"address":"凯宾斯基负二层停车场","communityFid":"KBSJ-agl-00005","communityName":"惠州市 凯宾斯基","fid":"2c4361a2-450c-4cc7-a268-b2762d6501be","name":"凯宾斯基停车场","regionInfo":"惠城区广东省惠州市"},"payTime":"2017-05-24","startTime":"2017-05-24"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MonthCardBillBean> monthCardBillList;

        public List<MonthCardBillBean> getMonthCardBillList() {
            return monthCardBillList;
        }

        public void setMonthCardBillList(List<MonthCardBillBean> monthCardBillList) {
            this.monthCardBillList = monthCardBillList;
        }

        public static class MonthCardBillBean {
            /**
             * billCode : 201705261747123453
             * carNo : 粤S55555
             * endTime : 2017-06-26
             * money : 100.0
             * monthCount : 1
             * parkPlace : {"address":"凯宾斯基负二层停车场","communityFid":"KBSJ-agl-00005","communityName":"惠州市 凯宾斯基","fid":"2c4361a2-450c-4cc7-a268-b2762d6501be","name":"凯宾斯基停车场","regionInfo":"惠城区广东省惠州市"}
             * payTime : 2017-05-26
             * startTime : 2017-05-26
             */

            private String billCode;
            private String carNo;
            private String endTime;
            private double money;
            private int monthCount;
            private ParkPlaceBean parkPlace;
            private String payTime;
            private String startTime;

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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
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

            public ParkPlaceBean getParkPlace() {
                return parkPlace;
            }

            public void setParkPlace(ParkPlaceBean parkPlace) {
                this.parkPlace = parkPlace;
            }

            public String getPayTime() {
                return payTime;
            }

            public void setPayTime(String payTime) {
                this.payTime = payTime;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public static class ParkPlaceBean {
                /**
                 * address : 凯宾斯基负二层停车场
                 * communityFid : KBSJ-agl-00005
                 * communityName : 惠州市 凯宾斯基
                 * fid : 2c4361a2-450c-4cc7-a268-b2762d6501be
                 * name : 凯宾斯基停车场
                 * regionInfo : 惠城区广东省惠州市
                 */

                private String address;
                private String communityFid;
                private String communityName;
                private String fid;
                private String name;
                private String regionInfo;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCommunityFid() {
                    return communityFid;
                }

                public void setCommunityFid(String communityFid) {
                    this.communityFid = communityFid;
                }

                public String getCommunityName() {
                    return communityName;
                }

                public void setCommunityName(String communityName) {
                    this.communityName = communityName;
                }

                public String getFid() {
                    return fid;
                }

                public void setFid(String fid) {
                    this.fid = fid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getRegionInfo() {
                    return regionInfo;
                }

                public void setRegionInfo(String regionInfo) {
                    this.regionInfo = regionInfo;
                }
            }
        }
    }
}
