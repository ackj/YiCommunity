package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/6/30 0030 16:08.
 * Email: liujia95me@126.com
 * 服务商品详情
 */

public class ServicesCommodityDetailBean extends BaseBean{


    /**
     * data : {"address":"惠州市惠城区江北佳兆业中心","businessHours":"9:00--22:00","commodityDesc":"商品描述","commodityMerit":"1:商品优点","commodityPrice":"¥1800:00","commodityServiceFlow":"1:服务流程","commodityTitle":"家庭保洁2小时","commodityUrl":"http://aglhzysq.image.alimmdn.com/services/commodity/20170630203144928689.png","contactWay":"13589091567","coverageArea":"1:覆盖区域","duration":"1:时长参考","merchantAge":"4","merchantIconUrl":"http://aglhzysq.image.alimmdn.com/services/merchant/icon/20170630210010028523.png","merchantLicense":[{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630210009659634.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630210009803193.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630210009910869.jpg"}],"merchantName":"惠州保洁有限公司","merchantScene":[{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630210009319928.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630210009423233.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630210009527758.jpg"}],"serviceScopes":"家庭保洁"}
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
         * address : 惠州市惠城区江北佳兆业中心
         * businessHours : 9:00--22:00
         * commodityDesc : 商品描述
         * commodityMerit : 1:商品优点
         * commodityPrice : ¥1800:00
         * commodityServiceFlow : 1:服务流程
         * commodityTitle : 家庭保洁2小时
         * commodityUrl : http://aglhzysq.image.alimmdn.com/services/commodity/20170630203144928689.png
         * contactWay : 13589091567
         * coverageArea : 1:覆盖区域
         * duration : 1:时长参考
         * merchantAge : 4
         * merchantIconUrl : http://aglhzysq.image.alimmdn.com/services/merchant/icon/20170630210010028523.png
         * merchantLicense : [{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630210009659634.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630210009803193.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630210009910869.jpg"}]
         * merchantName : 惠州保洁有限公司
         * merchantScene : [{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630210009319928.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630210009423233.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630210009527758.jpg"}]
         * serviceScopes : 家庭保洁
         */

        private String address;
        private String businessHours;
        private String commodityDesc;
        private String commodityMerit;
        private String commodityPrice;
        private String commodityServiceFlow;
        private String commodityTitle;
        private String commodityUrl;
        private String contactWay;
        private String coverageArea;
        private String duration;
        private String merchantAge;
        private String merchantIconUrl;
        private String merchantName;
        private String serviceScopes;
        private List<MerchantLicenseBean> merchantLicense;
        private List<MerchantSceneBean> merchantScene;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusinessHours() {
            return businessHours;
        }

        public void setBusinessHours(String businessHours) {
            this.businessHours = businessHours;
        }

        public String getCommodityDesc() {
            return commodityDesc;
        }

        public void setCommodityDesc(String commodityDesc) {
            this.commodityDesc = commodityDesc;
        }

        public String getCommodityMerit() {
            return commodityMerit;
        }

        public void setCommodityMerit(String commodityMerit) {
            this.commodityMerit = commodityMerit;
        }

        public String getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(String commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public String getCommodityServiceFlow() {
            return commodityServiceFlow;
        }

        public void setCommodityServiceFlow(String commodityServiceFlow) {
            this.commodityServiceFlow = commodityServiceFlow;
        }

        public String getCommodityTitle() {
            return commodityTitle;
        }

        public void setCommodityTitle(String commodityTitle) {
            this.commodityTitle = commodityTitle;
        }

        public String getCommodityUrl() {
            return commodityUrl;
        }

        public void setCommodityUrl(String commodityUrl) {
            this.commodityUrl = commodityUrl;
        }

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public String getCoverageArea() {
            return coverageArea;
        }

        public void setCoverageArea(String coverageArea) {
            this.coverageArea = coverageArea;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getMerchantAge() {
            return merchantAge;
        }

        public void setMerchantAge(String merchantAge) {
            this.merchantAge = merchantAge;
        }

        public String getMerchantIconUrl() {
            return merchantIconUrl;
        }

        public void setMerchantIconUrl(String merchantIconUrl) {
            this.merchantIconUrl = merchantIconUrl;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getServiceScopes() {
            return serviceScopes;
        }

        public void setServiceScopes(String serviceScopes) {
            this.serviceScopes = serviceScopes;
        }

        public List<MerchantLicenseBean> getMerchantLicense() {
            return merchantLicense;
        }

        public void setMerchantLicense(List<MerchantLicenseBean> merchantLicense) {
            this.merchantLicense = merchantLicense;
        }

        public List<MerchantSceneBean> getMerchantScene() {
            return merchantScene;
        }

        public void setMerchantScene(List<MerchantSceneBean> merchantScene) {
            this.merchantScene = merchantScene;
        }

        public static class MerchantLicenseBean {
            /**
             * url : http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630210009659634.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class MerchantSceneBean {
            /**
             * url : http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630210009319928.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
