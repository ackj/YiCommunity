package com.aglhz.yicommunity.entity.bean;

/**
 * Created by leguang on 2017/8/1 0001.
 * Email：langmanleguang@qq.com
 */

public class AppUpdateBean {

    /**
     * data : {"appName":"亿社区20170808","description":"","fid":"5b729574-6475-4537-b29c-b6bb96f919b8","fileName":"yishequ_2.1.2.apk","isEnable":true,"isForce":true,"size":"0.00 M","time":"2017-08-08 19:32:08","url":"http://aglhzysq.image.alimmdn.com/app/version/android/yishequ_2.1.2.apk","versionCode":212,"versionName":"2.1.2"}
     * other : {"code":200,"currpage":0,"first":"","forward":"","message":"data success","next":"","refresh":"","time":""}
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
         * appName : 亿社区20170808
         * description :
         * fid : 5b729574-6475-4537-b29c-b6bb96f919b8
         * fileName : yishequ_2.1.2.apk
         * isEnable : true
         * isForce : true
         * size : 0.00 M
         * time : 2017-08-08 19:32:08
         * url : http://aglhzysq.image.alimmdn.com/app/version/android/yishequ_2.1.2.apk
         * versionCode : 212
         * versionName : 2.1.2
         */

        private String appName;
        private String description;
        private String fid;
        private String fileName;
        private boolean isEnable;
        private boolean isForce;
        private String size;
        private String time;
        private String url;
        private int versionCode;
        private String versionName;

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public boolean isIsEnable() {
            return isEnable;
        }

        public void setIsEnable(boolean isEnable) {
            this.isEnable = isEnable;
        }

        public boolean isIsForce() {
            return isForce;
        }

        public void setIsForce(boolean isForce) {
            this.isForce = isForce;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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
         * message : data success
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
