package com.aglhz.yicommunity.bean;

/**
 * Created by YandZD on 2016/11/21.
 */

public class ALiPayBean {


    /**
     * data : {"body":"订单号:20161123162142539704","_input_charset":"utf-8","appId":"2016042001318970","privateKey":"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK1sFURCx6B/LAUsHBDHyF1cdtRfHEE1geUsFCC6E9WoXPEb9IhorU9PSFkO65YsTbS7vptOsfUys/q5N43luQ9SLj2nG45V23Aw3dOr0NgjvKb+QURb3+6O/09gRR4ZglK8BjuXcG+eUJ9KowrA/mMLOosVS/dxGjn7I3O8KzxRAgMBAAECgYEAqbhfT8CzMaWD5UP364w50PTO7qRuOHsvNqNex3CUPMNawjSDqoQXKMkEmvP5J60QF1KuBbyMeNU4QqXFcr9hzblIC+9zHXZzKJ/RpKhYqG3Gj/GJ0G/yo/tXHT+0H48itwt6IjWiaIUqiQDdF86Iav+c6+mus6pdorgOpLUWJ2ECQQDh+ihc68qJSU/J6WWs4yV2WKd8QE7MZCx8QN/DG55tvgGbL+nuTkO75qafBSQEwLJLVH8e2I3CCN2whNmf8y6NAkEAxHZye5yfWfep0F/aJYtCVXTNjyLrCDoNU7gqyoHAWbf7l4lZpwHxvVBQYzyKYko/zRADnkdLaKufoKUEYM/F1QJBAK9EJLYdM1NThwzldzSmYFHv5Mcb4YHpWA9vCZ37997J9dj1SozIKwS7mhPQGuWMxzxh5s53y0rpMXhNMbU7GakCQQC0IAEw0lXLz+Vy9h71IywLW88H+DTTUx5a7tTU3Ie1+piCxFxdYcv5DcTWZ3BdU8QRSCh5GtbJvejaCdHszTKNAkBH6BbHQXdtPbT++rMVllPybDjimRrtPj2IasVGqbftJmlJUZSh12vgJ8o84aURxnTiHXmMyCdLXfzXMXYl/gHm","subject":"订单号:20161123162142539704","total_fee":"3.5","service":"alipay.wap.create.direct.pay.by.user","notify_url":"http://www.aglhz.com/mall/member/aliPay/aliPayOrderBackNotify.do","partner":"2088221680833656","seller_id":"apple_yunshan@163.com","out_trade_no":"20161123162142539704","payment_type":"1","show_url":"http://www.aglhz.com/mall/member/aliPay/aliPayShowURLNotify.do","return_url":"http://www.aglhz.com/mall/member/aliPay/aliPayReturnURLNotify.do"}
     * other : {"code":200,"message":"","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}
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
         * body : 订单号:20161123162142539704
         * _input_charset : utf-8
         * appId : 2016042001318970
         * privateKey : MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK1sFURCx6B/LAUsHBDHyF1cdtRfHEE1geUsFCC6E9WoXPEb9IhorU9PSFkO65YsTbS7vptOsfUys/q5N43luQ9SLj2nG45V23Aw3dOr0NgjvKb+QURb3+6O/09gRR4ZglK8BjuXcG+eUJ9KowrA/mMLOosVS/dxGjn7I3O8KzxRAgMBAAECgYEAqbhfT8CzMaWD5UP364w50PTO7qRuOHsvNqNex3CUPMNawjSDqoQXKMkEmvP5J60QF1KuBbyMeNU4QqXFcr9hzblIC+9zHXZzKJ/RpKhYqG3Gj/GJ0G/yo/tXHT+0H48itwt6IjWiaIUqiQDdF86Iav+c6+mus6pdorgOpLUWJ2ECQQDh+ihc68qJSU/J6WWs4yV2WKd8QE7MZCx8QN/DG55tvgGbL+nuTkO75qafBSQEwLJLVH8e2I3CCN2whNmf8y6NAkEAxHZye5yfWfep0F/aJYtCVXTNjyLrCDoNU7gqyoHAWbf7l4lZpwHxvVBQYzyKYko/zRADnkdLaKufoKUEYM/F1QJBAK9EJLYdM1NThwzldzSmYFHv5Mcb4YHpWA9vCZ37997J9dj1SozIKwS7mhPQGuWMxzxh5s53y0rpMXhNMbU7GakCQQC0IAEw0lXLz+Vy9h71IywLW88H+DTTUx5a7tTU3Ie1+piCxFxdYcv5DcTWZ3BdU8QRSCh5GtbJvejaCdHszTKNAkBH6BbHQXdtPbT++rMVllPybDjimRrtPj2IasVGqbftJmlJUZSh12vgJ8o84aURxnTiHXmMyCdLXfzXMXYl/gHm
         * subject : 订单号:20161123162142539704
         * total_fee : 3.5
         * service : alipay.wap.create.direct.pay.by.user
         * notify_url : http://www.aglhz.com/mall/member/aliPay/aliPayOrderBackNotify.do
         * partner : 2088221680833656
         * seller_id : apple_yunshan@163.com
         * out_trade_no : 20161123162142539704
         * payment_type : 1
         * show_url : http://www.aglhz.com/mall/member/aliPay/aliPayShowURLNotify.do
         * return_url : http://www.aglhz.com/mall/member/aliPay/aliPayReturnURLNotify.do
         */

        private String body;
        private String _input_charset;
        private String appId;
        private String privateKey;
        private String subject;
        private String total_fee;
        private String service;
        private String notify_url;
        private String partner;
        private String seller_id;
        private String out_trade_no;
        private String payment_type;
        private String show_url;
        private String return_url;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String get_input_charset() {
            return _input_charset;
        }

        public void set_input_charset(String _input_charset) {
            this._input_charset = _input_charset;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getNotify_url() {
            return notify_url;
        }

        public void setNotify_url(String notify_url) {
            this.notify_url = notify_url;
        }

        public String getPartner() {
            return partner;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public String getShow_url() {
            return show_url;
        }

        public void setShow_url(String show_url) {
            this.show_url = show_url;
        }

        public String getReturn_url() {
            return return_url;
        }

        public void setReturn_url(String return_url) {
            this.return_url = return_url;
        }
    }

    public static class OtherBean {
        /**
         * code : 200
         * message :
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

