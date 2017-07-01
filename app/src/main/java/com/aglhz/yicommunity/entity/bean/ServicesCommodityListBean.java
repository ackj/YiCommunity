package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/6/30 0030 16:06.
 * Email: liujia95me@126.com
 * 服务商品列表
 */

public class ServicesCommodityListBean extends BaseBean{


    /**
     * data : {"dataList":[{"commodityDesc":"商品描述","commodityPrice":"¥1800:00","commodityTitle":"家庭保洁2小时","commodityUrl":"http://aglhzysq.image.alimmdn.com/services/commodity/20170630203144928689.png","coverageArea":"1:覆盖区域","fid":"36d7c380-967b-4135-a363-72e403771f25"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<DataListBean> dataList;

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * commodityDesc : 商品描述
             * commodityPrice : ¥1800:00
             * commodityTitle : 家庭保洁2小时
             * commodityUrl : http://aglhzysq.image.alimmdn.com/services/commodity/20170630203144928689.png
             * coverageArea : 1:覆盖区域
             * fid : 36d7c380-967b-4135-a363-72e403771f25
             */

            private String commodityDesc;
            private String commodityPrice;
            private String commodityTitle;
            private String commodityUrl;
            private String coverageArea;
            private String fid;

            public String getCommodityDesc() {
                return commodityDesc;
            }

            public void setCommodityDesc(String commodityDesc) {
                this.commodityDesc = commodityDesc;
            }

            public String getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(String commodityPrice) {
                this.commodityPrice = commodityPrice;
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

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }
        }
    }
}
