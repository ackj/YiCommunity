package com.aglhz.yicommunity.bean;

/**
 * Created by YandZD on 2017/1/17.
 */

public class UserBean extends BaseBean {


    /**
     * data : {"isSuccess":true,"status":true,"memberInfo":{"id":"237ac0dc-24c3-45e1-b675-6dfb6144063e","mobile":"135****9720","email":"","sex":0,"qq":"","nickName":"uirrv0913","face":"http://aglhzmall.image.alimmdn.com/member/20170105115242891113.jpeg","point":0,"money":"0","token":"tk_e8286a11-f646-447b-8743-f87e9eb2335f","level":0,"levelName":"","grade":0,"gradeName":""}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * isSuccess : true
         * status : true
         * memberInfo : {"id":"237ac0dc-24c3-45e1-b675-6dfb6144063e","mobile":"135****9720","email":"","sex":0,"qq":"","nickName":"uirrv0913","face":"http://aglhzmall.image.alimmdn.com/member/20170105115242891113.jpeg","point":0,"money":"0","token":"tk_e8286a11-f646-447b-8743-f87e9eb2335f","level":0,"levelName":"","grade":0,"gradeName":""}
         */

        private boolean isSuccess;
        private boolean status;
        private MemberInfoBean memberInfo;

        public boolean isIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public MemberInfoBean getMemberInfo() {
            return memberInfo;
        }

        public void setMemberInfo(MemberInfoBean memberInfo) {
            this.memberInfo = memberInfo;
        }

        public static class MemberInfoBean {
            /**
             * id : 237ac0dc-24c3-45e1-b675-6dfb6144063e
             * mobile : 135****9720
             * email :
             * sex : 0
             * qq :
             * nickName : uirrv0913
             * face : http://aglhzmall.image.alimmdn.com/member/20170105115242891113.jpeg
             * point : 0
             * money : 0
             * token : tk_e8286a11-f646-447b-8743-f87e9eb2335f
             * level : 0
             * levelName :
             * grade : 0
             * gradeName :
             */

            private String id;
            private String mobile;
            private String email;
            private int sex;
            private String qq;
            private String nickName;
            private String face;
            private int point;
            private String money;
            private String token;
            private int level;
            private String levelName;
            private int grade;
            private String gradeName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public String getGradeName() {
                return gradeName;
            }

            public void setGradeName(String gradeName) {
                this.gradeName = gradeName;
            }
        }
    }
}
