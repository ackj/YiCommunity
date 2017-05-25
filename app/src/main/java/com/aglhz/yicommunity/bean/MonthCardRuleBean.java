package com.aglhz.yicommunity.bean;

/**
 * Author: LiuJia on 2017/5/25 0025 16:58.
 * Email: liujia95me@126.com
 */

public class MonthCardRuleBean {
    /**
     * endDate : string
     * fid : string
     * money : 0
     * monthCount : 0
     * name : string
     * remark : string
     * sequenceNum : 0
     * startDate : string
     */

    private String endDate;
    private String fid;
    private int money;
    private int monthCount;
    private String name;
    private String remark;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
