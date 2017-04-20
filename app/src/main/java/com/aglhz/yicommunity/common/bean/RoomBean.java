package com.aglhz.yicommunity.common.bean;

import java.util.List;

/**
 * Created by leguang on 2017/4/19 0019.
 * Email：langmanleguang@qq.com
 */

public class RoomBean {
    /**
     * data : {"floor":{"code":"KBSJ-agl-00005-003-001-016","name":"16层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层"}},"houses":[{"code":"KBSJ-agl-00005-003-001-016-01","name":"1房","houseDir":"6-31-16-1","houseNO":"1601","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层1房"}},{"code":"KBSJ-agl-00005-003-001-016-02","name":"2房","houseDir":"6-31-16-2","houseNO":"1602","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层2房"}},{"code":"KBSJ-agl-00005-003-001-016-03","name":"3房","houseDir":"6-31-16-3","houseNO":"1603","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层3房"}},{"code":"KBSJ-agl-00005-003-001-016-04","name":"4房","houseDir":"6-31-16-4","houseNO":"1604","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层4房"}},{"code":"KBSJ-agl-00005-003-001-016-05","name":"5房","houseDir":"6-31-16-5","houseNO":"1605","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层5房"}},{"code":"KBSJ-agl-00005-003-001-016-06","name":"6房","houseDir":"6-31-16-6","houseNO":"1606","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层6房"}},{"code":"KBSJ-agl-00005-003-001-016-07","name":"7房","houseDir":"6-31-16-7","houseNO":"1607","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层7房"}},{"code":"KBSJ-agl-00005-003-001-016-08","name":"8房","houseDir":"6-31-16-8","houseNO":"1608","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层8房"}}]}
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
         * floor : {"code":"KBSJ-agl-00005-003-001-016","name":"16层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层"}}
         * houses : [{"code":"KBSJ-agl-00005-003-001-016-01","name":"1房","houseDir":"6-31-16-1","houseNO":"1601","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层1房"}},{"code":"KBSJ-agl-00005-003-001-016-02","name":"2房","houseDir":"6-31-16-2","houseNO":"1602","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层2房"}},{"code":"KBSJ-agl-00005-003-001-016-03","name":"3房","houseDir":"6-31-16-3","houseNO":"1603","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层3房"}},{"code":"KBSJ-agl-00005-003-001-016-04","name":"4房","houseDir":"6-31-16-4","houseNO":"1604","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层4房"}},{"code":"KBSJ-agl-00005-003-001-016-05","name":"5房","houseDir":"6-31-16-5","houseNO":"1605","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层5房"}},{"code":"KBSJ-agl-00005-003-001-016-06","name":"6房","houseDir":"6-31-16-6","houseNO":"1606","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层6房"}},{"code":"KBSJ-agl-00005-003-001-016-07","name":"7房","houseDir":"6-31-16-7","houseNO":"1607","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层7房"}},{"code":"KBSJ-agl-00005-003-001-016-08","name":"8房","houseDir":"6-31-16-8","houseNO":"1608","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层8房"}}]
         */

        private FloorBean floor;
        private List<HousesBean> houses;

        public FloorBean getFloor() {
            return floor;
        }

        public void setFloor(FloorBean floor) {
            this.floor = floor;
        }

        public List<HousesBean> getHouses() {
            return houses;
        }

        public void setHouses(List<HousesBean> houses) {
            this.houses = houses;
        }

        public static class FloorBean {
            /**
             * code : KBSJ-agl-00005-003-001-016
             * name : 16层
             * position : {"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层"}
             */

            private String code;
            private String name;
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

            public PositionBean getPosition() {
                return position;
            }

            public void setPosition(PositionBean position) {
                this.position = position;
            }

            public static class PositionBean {
                /**
                 * address : 广东省惠州市惠城区凯宾斯基3栋1单元16层
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

        public static class HousesBean {
            /**
             * code : KBSJ-agl-00005-003-001-016-01
             * name : 1房
             * houseDir : 6-31-16-1
             * houseNO : 1601
             * position : {"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层1房"}
             */

            private String code;
            private String name;
            private String houseDir;
            private String houseNO;
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

            public String getHouseDir() {
                return houseDir;
            }

            public void setHouseDir(String houseDir) {
                this.houseDir = houseDir;
            }

            public String getHouseNO() {
                return houseNO;
            }

            public void setHouseNO(String houseNO) {
                this.houseNO = houseNO;
            }

            public PositionBeanX getPosition() {
                return position;
            }

            public void setPosition(PositionBeanX position) {
                this.position = position;
            }

            public static class PositionBeanX {
                /**
                 * address : 广东省惠州市惠城区凯宾斯基3栋1单元16层1房
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
