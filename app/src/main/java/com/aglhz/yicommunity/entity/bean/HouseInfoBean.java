package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/9/15 0015 10:23.
 * Email: liujia95me@126.com
 */

public class HouseInfoBean extends BaseBean{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * familyNumber : 015007620563
         * houseName : 凯宾斯基3栋1单元14层5房
         * roomDir : 6-31-14-5
         */

        private String familyNumber;
        private String houseName;
        private String roomDir;

        public String getFamilyNumber() {
            return familyNumber;
        }

        public void setFamilyNumber(String familyNumber) {
            this.familyNumber = familyNumber;
        }

        public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getRoomDir() {
            return roomDir;
        }

        public void setRoomDir(String roomDir) {
            this.roomDir = roomDir;
        }
    }
}
