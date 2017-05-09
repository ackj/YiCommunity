package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Created by leguang on 2017/4/27 0027.
 * Email：langmanleguang@qq.com
 */

public class HouseRightsBean {

    /**
     * data : [{"authority":[{"code":"RemoteOpen","name":"远程开门","status":1},{"code":"PasswordOpen","name":"密码开门","status":1},{"code":"RemoteWatch","name":"远程监视","status":1}],"member":{"fid":"29fe6b51-fd94-4255-b724-9bc089b2c2bb","icon":"http://aglhzmall.image.alimmdn.com/member/20170221182754496317.png","isOwner":0,"mname":"ujnyu4202","name":"李芾颙"}}]
     * other : {"code":200,"currpage":0,"first":"","forward":"","message":"data success","next":"","refresh":"","time":""}
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

    public static class DataBean {
        /**
         * authority : [{"code":"RemoteOpen","name":"远程开门","status":1},{"code":"PasswordOpen","name":"密码开门","status":1},{"code":"RemoteWatch","name":"远程监视","status":1}]
         * member : {"fid":"29fe6b51-fd94-4255-b724-9bc089b2c2bb","icon":"http://aglhzmall.image.alimmdn.com/member/20170221182754496317.png","isOwner":0,"mname":"ujnyu4202","name":"李芾颙"}
         */

        private MemberBean member;
        private List<AuthorityBean> authority;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public List<AuthorityBean> getAuthority() {
            return authority;
        }

        public void setAuthority(List<AuthorityBean> authority) {
            this.authority = authority;
        }

        public static class MemberBean {
            /**
             * fid : 29fe6b51-fd94-4255-b724-9bc089b2c2bb
             * icon : http://aglhzmall.image.alimmdn.com/member/20170221182754496317.png
             * isOwner : 0
             * mname : ujnyu4202
             * name : 李芾颙
             */

            private String fid;
            private String icon;
            private int isOwner;
            private String mname;
            private String name;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getIsOwner() {
                return isOwner;
            }

            public void setIsOwner(int isOwner) {
                this.isOwner = isOwner;
            }

            public String getMname() {
                return mname;
            }

            public void setMname(String mname) {
                this.mname = mname;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class AuthorityBean {
            /**
             * code : RemoteOpen
             * name : 远程开门
             * status : 1
             */

            private String code;
            private String name;
            private int status;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
