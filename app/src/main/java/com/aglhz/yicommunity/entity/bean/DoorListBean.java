package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Created by leguang on 2017/4/22 0022.
 * Email：langmanleguang@qq.com
 */

public class DoorListBean {

    /**
     * data : [{"name":"凯宾斯基3栋1单元门口机","dir":"6-31-1","online":true,"quickopen":false,"houses":[]},{"name":"凯宾斯基3栋1单元门口机2","dir":"6-31-2","online":false,"quickopen":false,"houses":[]},{"name":"凯宾斯基3栋1单元门口机3","dir":"6-31-3","online":false,"quickopen":true,"houses":[]}]
     * other : {"code":200,"message":"获取成功","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}
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
         * message : 获取成功
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
         * name : 凯宾斯基3栋1单元门口机
         * dir : 6-31-1
         * online : true
         * quickopen : false
         * houses : []
         */
        private String name;
        private String dir;
        private boolean online;
        private boolean quickopen;
        private List<?> houses;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public boolean isOnline() {
            return online;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }

        public boolean isQuickopen() {
            return quickopen;
        }

        public void setQuickopen(boolean quickopen) {
            this.quickopen = quickopen;
        }

        public List<?> getHouses() {
            return houses;
        }

        public void setHouses(List<?> houses) {
            this.houses = houses;
        }
    }
}
