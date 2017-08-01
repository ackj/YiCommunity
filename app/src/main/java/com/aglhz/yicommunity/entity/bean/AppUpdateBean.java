package com.aglhz.yicommunity.entity.bean;

/**
 * Created by leguang on 2017/8/1 0001.
 * Email：langmanleguang@qq.com
 */

public class AppUpdateBean {

    /**
     * data : {"appName":"yishequ","isForce":0,"time":"2017-5-25 10:33:21","size":"2173270","description":"有重要更新升级","url":"dataFile/apk/20170801.apk","versionCode":214,"versionName":"2.1.4"}
     * other : {"code":200,"currpage":0,"first":"","forward":"","message":"获取成功","next":"","refresh":"","time":""}
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
         * appName : yishequ
         * isForce : 0
         * time : 2017-5-25 10:33:21
         * size : 2173270
         * description : 有重要更新升级
         * url : dataFile/apk/20170801.apk
         * versionCode : 214
         * versionName : 2.1.4
         */

        private String appName;
        private int isForce;
        private String time;
        private String size;
        private String description;
        private String url;
        private int versionCode;
        private String versionName;

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public int getIsForce() {
            return isForce;
        }

        public void setIsForce(int isForce) {
            this.isForce = isForce;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }

    public static class OtherBean {
        /**
         * code : 200
         * currpage : 0
         * first :
         * forward :
         * message : 获取成功
         * next :
         * refresh :
         * time :
         */

        private int code;
        private int currpage;
        private String first;
        private String forward;
        private String message;
        private String next;
        private String refresh;
        private String time;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getCurrpage() {
            return currpage;
        }

        public void setCurrpage(int currpage) {
            this.currpage = currpage;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
