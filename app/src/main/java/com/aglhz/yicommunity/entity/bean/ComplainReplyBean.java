package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Created by leguang on 2017/6/1 0001.
 * Email：langmanleguang@qq.com
 */

public class ComplainReplyBean {

    /**
     * data : {"fid":"2666e8bb-c818-4c67-8b52-996c5ecfbf7c","name":"李勇","phoneNo":"180****8888","content":"嗯","createTime":"2017-06-01 11:01:14","pics":[],"replyList":[{"fid":"038f9c92-be8e-48b5-bde7-f46659a3a170","content":"agweqawegqaweg","createTime":"2017-06-01 11:01:55"}]}
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
         * fid : 2666e8bb-c818-4c67-8b52-996c5ecfbf7c
         * name : 李勇
         * phoneNo : 180****8888
         * content : 嗯
         * createTime : 2017-06-01 11:01:14
         * pics : []
         * replyList : [{"fid":"038f9c92-be8e-48b5-bde7-f46659a3a170","content":"agweqawegqaweg","createTime":"2017-06-01 11:01:55"}]
         */

        private String fid;
        private String name;
        private String phoneNo;
        private String content;
        private String createTime;
        private List<?> pics;
        private List<ReplyListBean> replyList;

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

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<?> getPics() {
            return pics;
        }

        public void setPics(List<?> pics) {
            this.pics = pics;
        }

        public List<ReplyListBean> getReplyList() {
            return replyList;
        }

        public void setReplyList(List<ReplyListBean> replyList) {
            this.replyList = replyList;
        }

        public static class ReplyListBean {
            /**
             * fid : 038f9c92-be8e-48b5-bde7-f46659a3a170
             * content : agweqawegqaweg
             * createTime : 2017-06-01 11:01:55
             */

            private String fid;
            private String content;
            private String createTime;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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
