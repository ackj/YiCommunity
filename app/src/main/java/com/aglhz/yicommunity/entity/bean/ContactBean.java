package com.aglhz.yicommunity.entity.bean;

/**
 * Created by leguang on 2017/4/21 0021.
 * Email：langmanleguang@qq.com
 */

public class ContactBean extends BaseBean {

    /**
     * data : {"fid":"5a1896b4-2458-4a4c-a240-e4bd1240e29d","mobileNo":"18676733437","telephoneNo":""}
     * other : {"code":200,"message":"data success","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}
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
         * fid : 5a1896b4-2458-4a4c-a240-e4bd1240e29d
         * mobileNo : 18676733437
         * telephoneNo :
         */

        private String fid;
        private String mobileNo;
        private String telephoneNo;

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getTelephoneNo() {
            return telephoneNo;
        }

        public void setTelephoneNo(String telephoneNo) {
            this.telephoneNo = telephoneNo;
        }
    }

}
