package com.aglhz.yicommunity.common.bean;

/**
 * 用户基础信息
 * <p>
 * Created by YandZD on 2017/1/11.
 */

public class UserBean extends BaseBean {

    private MemberInfoBean data;

    public MemberInfoBean getData() {
        return data;
    }

    public void setData(MemberInfoBean data) {
        this.data = data;
    }

    public static class MemberInfoBean {
        /**
         * id : ea50ec1a-c300-4ca8-a560-e04342ac1ab8
         * mobile : 158****7739
         * email :
         * sex : 1
         * qq :
         * nickName : vxsxz0142
         * face : http://aglhzmall.image.alimmdn.com/member/20161220115815571766.png
         * point : 0
         * money : 0
         * token : tk_5c98b48c-9662-4d9c-9464-73af0bf5f83a
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
