package com.aglhz.yicommunity.entity.bean;

public class BaseBean {

    /**
     * other : {"code":500,"message":"手机号 不能为空","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}
     */

    private OtherBean other;

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public static class OtherBean {
        /**
         * code : 500
         * message : 手机号 不能为空
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

        @Override
        public String toString() {
            return "OtherBean{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    ", time='" + time + '\'' +
                    ", currpage=" + currpage +
                    ", next='" + next + '\'' +
                    ", forward='" + forward + '\'' +
                    ", refresh='" + refresh + '\'' +
                    ", first='" + first + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "other=" + other +
                '}';
    }
}
