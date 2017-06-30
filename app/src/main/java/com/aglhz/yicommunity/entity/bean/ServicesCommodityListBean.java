package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/6/30 0030 16:06.
 * Email: liujia95me@126.com
 * 服务商品列表
 */

public class ServicesCommodityListBean extends BaseBean{

    /**
     * data : {"dataList":[{"commodityDesc":"按时收费   服务有保障","commodityPrice":"¥800:00","commodityTitle":"家庭保健2小时","coverageArea":"全惠州市内各个区域","fid":"0980e896-8894-42eb-9c66-f335f16cafbe","pics":[]}]}
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
             * commodityDesc : 按时收费   服务有保障
             * commodityPrice : ¥800:00
             * commodityTitle : 家庭保健2小时
             * coverageArea : 全惠州市内各个区域
             * fid : 0980e896-8894-42eb-9c66-f335f16cafbe
             * pics : []
             */

            private String commodityDesc;
            private String commodityPrice;
            private String commodityTitle;
            private String coverageArea;
            private String fid;
            private List<?> pics;

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

            public List<?> getPics() {
                return pics;
            }

            public void setPics(List<?> pics) {
                this.pics = pics;
            }
        }
    }
}
