package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/8 0008 21:16.
 * Email: liujia95me@126.com
 */

public class ParkRecordBean extends BaseBean{

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
        private List<?> parkRecordList;

        public List<?> getParkRecordList() {
            return parkRecordList;
        }

        public void setParkRecordList(List<?> parkRecordList) {
            this.parkRecordList = parkRecordList;
        }
    }
}
