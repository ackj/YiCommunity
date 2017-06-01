package com.aglhz.yicommunity.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author: LiuJia on 2017/5/23 0023 14:49.
 * Email: liujia95me@126.com
 */

public class ParkSelectBean extends BaseBean {

    /**
     * data : {"parkPlaceList":[{"address":"凯宾斯基测试地址","communityFid":"KBSJ-agl-00005","communityName":"惠州市 凯宾斯基","fid":"5b9a90d0-a117-4033-b13c-0a6fa58a68ad","name":"测试停车场1","regionInfo":"惠城区广东省惠州市"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ParkPlaceListBean> parkPlaceList;

        public List<ParkPlaceListBean> getParkPlaceList() {
            return parkPlaceList;
        }

        public void setParkPlaceList(List<ParkPlaceListBean> parkPlaceList) {
            this.parkPlaceList = parkPlaceList;
        }

        public static class ParkPlaceListBean implements Serializable {
            /**
             * address : 凯宾斯基测试地址
             * communityFid : KBSJ-agl-00005
             * communityName : 惠州市 凯宾斯基
             * fid : 5b9a90d0-a117-4033-b13c-0a6fa58a68ad
             * name : 测试停车场1
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
