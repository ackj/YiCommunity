package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Created by leguang on 2017/4/19 0019.
 * Email：langmanleguang@qq.com
 */

public class UnitBean {

    /**
     * data : {"buildingUnits":[{"code":"KBSJ-agl-00005-003-001","name":"1单元","floor":30,"position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元"}}],"community":{"code":"KBSJ-agl-00005","dir":"6","name":"凯宾斯基","position":{"province":"广东省","city":"惠州市","county":"惠城区","address":"广东省惠州市惠城区凯宾斯基"}}}
     * other : {"code":200,"message":"data success","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}
     */

    private DataBean data;
    private OtherBean other;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public static class DataBean {
        /**
         * buildingUnits : [{"code":"KBSJ-agl-00005-003-001","name":"1单元","floor":30,"position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元"}}]
         * community : {"code":"KBSJ-agl-00005","dir":"6","name":"凯宾斯基","position":{"province":"广东省","city":"惠州市","county":"惠城区","address":"广东省惠州市惠城区凯宾斯基"}}
         */

        private CommunityBean community;
        private List<BuildingUnitsBean> buildingUnits;

        public CommunityBean getCommunity() {
            return community;
        }

        public void setCommunity(CommunityBean community) {
            this.community = community;
        }

        public List<BuildingUnitsBean> getBuildingUnits() {
            return buildingUnits;
        }

        public void setBuildingUnits(List<BuildingUnitsBean> buildingUnits) {
            this.buildingUnits = buildingUnits;
        }

        public static class CommunityBean {
            /**
             * code : KBSJ-agl-00005
             * dir : 6
             * name : 凯宾斯基
             * position : {"province":"广东省","city":"惠州市","county":"惠城区","address":"广东省惠州市惠城区凯宾斯基"}
             */

            private String code;
            private String dir;
            private String name;
            private PositionBean position;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
                 * address : 广东省惠州市惠城区凯宾斯基
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

        public static class BuildingUnitsBean {
            /**
             * code : KBSJ-agl-00005-003-001
             * name : 1单元
             * floor : 30
             * position : {"address":"广东省惠州市惠城区凯宾斯基3栋1单元"}
             */

            private String code;
            private String name;
            private int floor;
            private PositionBeanX position;

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

            public int getFloor() {
                return floor;
            }

            public void setFloor(int floor) {
                this.floor = floor;
            }

            public PositionBeanX getPosition() {
                return position;
            }

            public void setPosition(PositionBeanX position) {
                this.position = position;
            }

            public static class PositionBeanX {
                /**
                 * address : 广东省惠州市惠城区凯宾斯基3栋1单元
                 */

                private String address;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }
            }
        }
    }

    public static class OtherBean {
        /**
         * code : 200
         * message : data success
         * time :
         * currpage : 0
         * next :
         * forward :
         * refresh :
         * first :
         */

        private int code;
        private String message;
        private String time;
        private int currpage;
        private String next;
        private String forward;
        private String refresh;
        private String first;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getCurrpage() {
            return currpage;
        }

        public void setCurrpage(int currpage) {
            this.currpage = currpage;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }
    }
}
