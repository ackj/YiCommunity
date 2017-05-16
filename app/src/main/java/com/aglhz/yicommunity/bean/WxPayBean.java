package com.aglhz.yicommunity.bean;

/**
 * Created by YandZD on 2017/3/2.
 */

public class WxPayBean extends BaseBean {

    /**
     * data : {"appid":"wx160fff7b6ed86ef7","timestamp":"1488433821","noncestr":"cd63a988f91f41998d7a7eadf66cbe79","sign":"8D7410CE2501592656FEB7D9B5505863","partnerid":"1439365802","prepayid":"wx20170302135021dec3c88b630163226216"}
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
         * appid : wx160fff7b6ed86ef7
         * timestamp : 1488433821
         * noncestr : cd63a988f91f41998d7a7eadf66cbe79
         * sign : 8D7410CE2501592656FEB7D9B5505863
         * partnerid : 1439365802
         * prepayid : wx20170302135021dec3c88b630163226216
         */

        private String appid;
        private String timestamp;
        private String noncestr;
        private String sign;
        private String partnerid;
        private String prepayid;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }
    }
}
