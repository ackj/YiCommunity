package com.aglhz.yicommunity.common.bean;

import java.util.List;

/**
 * Created by leguang on 2017/4/19 0019.
 * Email：langmanleguang@qq.com
 */

public class FloorBean {


    /**
     * data : {"building":{"code":"KBSJ-agl-00005-003","name":"3栋","position":{"address":"广东省惠州市惠城区凯宾斯基3栋"}},"floors":[{"code":"KBSJ-agl-00005-003-001-001","name":"1层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元1层"}},{"code":"KBSJ-agl-00005-003-001-002","name":"2层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元2层"}},{"code":"KBSJ-agl-00005-003-001-003","name":"3层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元3层"}},{"code":"KBSJ-agl-00005-003-001-004","name":"4层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元4层"}},{"code":"KBSJ-agl-00005-003-001-005","name":"5层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元5层"}},{"code":"KBSJ-agl-00005-003-001-006","name":"6层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元6层"}},{"code":"KBSJ-agl-00005-003-001-007","name":"7层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元7层"}},{"code":"KBSJ-agl-00005-003-001-008","name":"8层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元8层"}},{"code":"KBSJ-agl-00005-003-001-009","name":"9层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元9层"}},{"code":"KBSJ-agl-00005-003-001-010","name":"10层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元10层"}},{"code":"KBSJ-agl-00005-003-001-011","name":"11层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元11层"}},{"code":"KBSJ-agl-00005-003-001-012","name":"12层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元12层"}},{"code":"KBSJ-agl-00005-003-001-013","name":"13层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元13层"}},{"code":"KBSJ-agl-00005-003-001-014","name":"14层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元14层"}},{"code":"KBSJ-agl-00005-003-001-015","name":"15层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元15层"}},{"code":"KBSJ-agl-00005-003-001-016","name":"16层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层"}},{"code":"KBSJ-agl-00005-003-001-017","name":"17层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元17层"}},{"code":"KBSJ-agl-00005-003-001-018","name":"18层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元18层"}},{"code":"KBSJ-agl-00005-003-001-019","name":"19层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元19层"}},{"code":"KBSJ-agl-00005-003-001-020","name":"20层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元20层"}},{"code":"KBSJ-agl-00005-003-001-021","name":"21层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元21层"}},{"code":"KBSJ-agl-00005-003-001-022","name":"22层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元22层"}},{"code":"KBSJ-agl-00005-003-001-023","name":"23层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元23层"}},{"code":"KBSJ-agl-00005-003-001-024","name":"24层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元24层"}},{"code":"KBSJ-agl-00005-003-001-025","name":"25层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元25层"}},{"code":"KBSJ-agl-00005-003-001-026","name":"26层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元26层"}},{"code":"KBSJ-agl-00005-003-001-027","name":"27层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元27层"}},{"code":"KBSJ-agl-00005-003-001-028","name":"28层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元28层"}},{"code":"KBSJ-agl-00005-003-001-029","name":"29层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元29层"}},{"code":"KBSJ-agl-00005-003-001-030","name":"30层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元30层"}}]}
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
         * building : {"code":"KBSJ-agl-00005-003","name":"3栋","position":{"address":"广东省惠州市惠城区凯宾斯基3栋"}}
         * floors : [{"code":"KBSJ-agl-00005-003-001-001","name":"1层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元1层"}},{"code":"KBSJ-agl-00005-003-001-002","name":"2层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元2层"}},{"code":"KBSJ-agl-00005-003-001-003","name":"3层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元3层"}},{"code":"KBSJ-agl-00005-003-001-004","name":"4层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元4层"}},{"code":"KBSJ-agl-00005-003-001-005","name":"5层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元5层"}},{"code":"KBSJ-agl-00005-003-001-006","name":"6层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元6层"}},{"code":"KBSJ-agl-00005-003-001-007","name":"7层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元7层"}},{"code":"KBSJ-agl-00005-003-001-008","name":"8层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元8层"}},{"code":"KBSJ-agl-00005-003-001-009","name":"9层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元9层"}},{"code":"KBSJ-agl-00005-003-001-010","name":"10层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元10层"}},{"code":"KBSJ-agl-00005-003-001-011","name":"11层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元11层"}},{"code":"KBSJ-agl-00005-003-001-012","name":"12层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元12层"}},{"code":"KBSJ-agl-00005-003-001-013","name":"13层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元13层"}},{"code":"KBSJ-agl-00005-003-001-014","name":"14层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元14层"}},{"code":"KBSJ-agl-00005-003-001-015","name":"15层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元15层"}},{"code":"KBSJ-agl-00005-003-001-016","name":"16层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元16层"}},{"code":"KBSJ-agl-00005-003-001-017","name":"17层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元17层"}},{"code":"KBSJ-agl-00005-003-001-018","name":"18层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元18层"}},{"code":"KBSJ-agl-00005-003-001-019","name":"19层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元19层"}},{"code":"KBSJ-agl-00005-003-001-020","name":"20层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元20层"}},{"code":"KBSJ-agl-00005-003-001-021","name":"21层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元21层"}},{"code":"KBSJ-agl-00005-003-001-022","name":"22层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元22层"}},{"code":"KBSJ-agl-00005-003-001-023","name":"23层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元23层"}},{"code":"KBSJ-agl-00005-003-001-024","name":"24层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元24层"}},{"code":"KBSJ-agl-00005-003-001-025","name":"25层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元25层"}},{"code":"KBSJ-agl-00005-003-001-026","name":"26层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元26层"}},{"code":"KBSJ-agl-00005-003-001-027","name":"27层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元27层"}},{"code":"KBSJ-agl-00005-003-001-028","name":"28层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元28层"}},{"code":"KBSJ-agl-00005-003-001-029","name":"29层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元29层"}},{"code":"KBSJ-agl-00005-003-001-030","name":"30层","position":{"address":"广东省惠州市惠城区凯宾斯基3栋1单元30层"}}]
         */

        private BuildingBean building;
        private List<FloorsBean> floors;

        public BuildingBean getBuilding() {
            return building;
        }

        public void setBuilding(BuildingBean building) {
            this.building = building;
        }

        public List<FloorsBean> getFloors() {
            return floors;
        }

        public void setFloors(List<FloorsBean> floors) {
            this.floors = floors;
        }

        public static class BuildingBean {
            /**
             * code : KBSJ-agl-00005-003
             * name : 3栋
             * position : {"address":"广东省惠州市惠城区凯宾斯基3栋"}
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
                 * address : 广东省惠州市惠城区凯宾斯基3栋
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

        public static class FloorsBean {
            /**
             * code : KBSJ-agl-00005-003-001-001
             * name : 1层
             * position : {"address":"广东省惠州市惠城区凯宾斯基3栋1单元1层"}
             */

            private String code;
            private String name;
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

            public PositionBeanX getPosition() {
                return position;
            }

            public void setPosition(PositionBeanX position) {
                this.position = position;
            }

            public static class PositionBeanX {
                /**
                 * address : 广东省惠州市惠城区凯宾斯基3栋1单元1层
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
