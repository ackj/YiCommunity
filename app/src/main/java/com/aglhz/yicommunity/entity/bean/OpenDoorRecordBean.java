package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/4/21 10:33.
 * Email: liujia95me@126.com
 */
public class OpenDoorRecordBean {

    /**
     * data : [{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2016:21:34","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2016:21:20","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2015:56:05","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2010:11:47","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2016:21:34","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2016:21:20","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2015:56:05","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2010:11:47","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2016:21:34","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2016:21:20","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2015:56:05","deviceLocalDirectory":"6-31-1"},{"userName":"拉斯维加斯-奥斯托洛夫斯基-龙哥","accessWay":22,"deviceName":"凯宾斯基3栋1单元门口机","direction":2,"accessTime":"2017-04-2010:11:47","deviceLocalDirectory":"6-31-1"}]
     * other : {"code":200,"message":"success","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}
     */

    private OtherBean other;
    private List<DataBean> data;

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class OtherBean {
        /**
         * code : 200
         * message : success
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

    public static class DataBean {
        /**
         * userName : 拉斯维加斯-奥斯托洛夫斯基-龙哥
         * accessWay : 22
         * deviceName : 凯宾斯基3栋1单元门口机
         * direction : 2
         * accessTime : 2017-04-2016:21:34
         * deviceLocalDirectory : 6-31-1
         */

        private String userName;
        private int accessWay;
        private String deviceName;
        private int direction;
        private String accessTime;
        private String deviceLocalDirectory;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getAccessWay() {
            return accessWay;
        }

        public void setAccessWay(int accessWay) {
            this.accessWay = accessWay;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String getAccessTime() {
            return accessTime;
        }

        public void setAccessTime(String accessTime) {
            this.accessTime = accessTime;
        }

        public String getDeviceLocalDirectory() {
            return deviceLocalDirectory;
        }

        public void setDeviceLocalDirectory(String deviceLocalDirectory) {
            this.deviceLocalDirectory = deviceLocalDirectory;
        }
    }
}
