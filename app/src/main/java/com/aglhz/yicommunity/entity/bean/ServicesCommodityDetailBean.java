package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/6/30 0030 16:08.
 * Email: liujia95me@126.com
 * 服务商品详情
 */

public class ServicesCommodityDetailBean extends BaseBean{


    /**
     * data : {"address":"惠州市惠城区江北佳兆业中心","commodityDesc":"商品描述","commodityMerit":"商品优点.....","commodityPrice":"¥1800:00","commodityServiceFlow":"商品优点......","commodityTitle":"商品标题","commodityUrl":"http://aglhzysq.image.alimmdn.com/services/commodity/20170630174955896442.jpg","coverageArea":"覆盖区域.....","duration":"时长参考.......","merchantIconUrl":"http://aglhzysq.image.alimmdn.com/services/merchant/icon/20170630165102228620.jpg","merchantLicense":[],"merchantScene":[{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630165053565186.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630165055369329.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630165055643851.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630165056011478.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630165057808439.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630165100370108.jpg"}]}
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
         * commodityDesc : 商品描述
         * commodityMerit : 商品优点.....
         * commodityPrice : ¥1800:00
         * commodityServiceFlow : 商品优点......
         * commodityTitle : 商品标题
         * commodityUrl : http://aglhzysq.image.alimmdn.com/services/commodity/20170630174955896442.jpg
         * coverageArea : 覆盖区域.....
         * duration : 时长参考.......
         * merchantIconUrl : http://aglhzysq.image.alimmdn.com/services/merchant/icon/20170630165102228620.jpg
         * merchantLicense : []
         * merchantScene : [{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630165053565186.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630165055369329.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630165055643851.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630165056011478.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630165057808439.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170630165100370108.jpg"}]
         */

        private String address;
        private String commodityDesc;
        private String commodityMerit;
        private String commodityPrice;
        private String commodityServiceFlow;
        private String commodityTitle;
        private String commodityUrl;
        private String coverageArea;
        private String duration;
        private String merchantIconUrl;
        private List<?> merchantLicense;
        private List<MerchantSceneBean> merchantScene;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getMerchantIconUrl() {
            return merchantIconUrl;
        }

        public void setMerchantIconUrl(String merchantIconUrl) {
            this.merchantIconUrl = merchantIconUrl;
        }

        public List<?> getMerchantLicense() {
            return merchantLicense;
        }

        public void setMerchantLicense(List<?> merchantLicense) {
            this.merchantLicense = merchantLicense;
        }

        public List<MerchantSceneBean> getMerchantScene() {
            return merchantScene;
        }

        public void setMerchantScene(List<MerchantSceneBean> merchantScene) {
            this.merchantScene = merchantScene;
        }

        public static class MerchantSceneBean {
            /**
             * url : http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170630165053565186.jpg
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
