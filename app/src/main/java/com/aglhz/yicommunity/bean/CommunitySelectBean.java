package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Created by YandZD on 2017/2/16.
 */

public class CommunitySelectBean extends BaseBean{


    /**
     * data : {"communities":[{"code":"YSQ-SBXC-01-00001","name":"水北新村1期","area":"1001","buildings":22,"position":{"province":"广东省","city":"惠州市","county":"惠城区","address":"广东惠州江北水北新村1期"}},{"code":"YSQ-DHHY-01-00002","name":"东湖花园1期","area":"2001","buildings":33,"position":{"province":"广东省","city":"惠州市","county":"惠城区","address":"广东惠州东平东湖花园1期"}},{"code":"YSQ-HLCT-01-00003","name":"海伦春天1期","area":"3000","buildings":34,"position":{"province":"广东省","city":"惠州市","county":"惠城区","address":"广东惠州海伦春天1期"}}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CommunitiesBean> communities;

        public List<CommunitiesBean> getCommunities() {
            return communities;
        }

        public void setCommunities(List<CommunitiesBean> communities) {
            this.communities = communities;
        }

        public static class CommunitiesBean {
            /**
             * code : YSQ-SBXC-01-00001
             * name : 水北新村1期
             * area : 1001
             * buildings : 22
             * position : {"province":"广东省","city":"惠州市","county":"惠城区","address":"广东惠州江北水北新村1期"}
             */

            private String code;
            private String name;
            private String area;
            private int buildings;
            private PositionBean position;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getBuildings() {
                return buildings;
            }

            public void setBuildings(int buildings) {
                this.buildings = buildings;
            }

            public PositionBean getPosition() {
                return position;
            }

            public void setPosition(PositionBean position) {
                this.position = position;
            }

            public static class PositionBean {
                /**
                 * province : 广东省
                 * city : 惠州市
                 * county : 惠城区
                 * address : 广东惠州江北水北新村1期
                 */

                private String province;
                private String city;
                private String county;
                private String address;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }
            }
        }
    }
}
