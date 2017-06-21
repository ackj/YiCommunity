package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/22 0022 14:35.
 * Email: liujia95me@126.com
 */

public class GoodsBean extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : null
         * orderItemId : null
         * returnOrderMark : -1
         * returnOrderId : null
         * name : 烟雾探测器GS-WSD02
         * pictureURL : http://aglhzmall.image.alimmdn.com/goods/20170418161110400144.jpg@150h_150w_1e_1c
         * price : 80.00
         * introduction : 型号：GS-WSD02
         * goodsImgCover : http://aglhzmall.image.alimmdn.com/default/400X200.jpg
         * memberSeller : false
         * commerceType : B2CType
         * link : http://www.aglhz.com/mall/m/html/photo.html?goodsId=00c2fa00-5447-4cc0-8a2c-b7ea16297822
         * productId : null
         * number : 0
         * specList : []
         * shopId : null
         * goodsId : 00c2fa00-5447-4cc0-8a2c-b7ea16297822
         */

        private Object id;
        private Object orderItemId;
        private int returnOrderMark;
        private Object returnOrderId;
        private String name;
        private String pictureURL;
        private String price;
        private String introduction;
        private String goodsImgCover;
        private boolean memberSeller;
        private String commerceType;
        private String link;
        private Object productId;
        private int number;
        private Object shopId;
        private String goodsId;
        private List<?> specList;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(Object orderItemId) {
            this.orderItemId = orderItemId;
        }

        public int getReturnOrderMark() {
            return returnOrderMark;
        }

        public void setReturnOrderMark(int returnOrderMark) {
            this.returnOrderMark = returnOrderMark;
        }

        public Object getReturnOrderId() {
            return returnOrderId;
        }

        public void setReturnOrderId(Object returnOrderId) {
            this.returnOrderId = returnOrderId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPictureURL() {
            return pictureURL;
        }

        public void setPictureURL(String pictureURL) {
            this.pictureURL = pictureURL;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getGoodsImgCover() {
            return goodsImgCover;
        }

        public void setGoodsImgCover(String goodsImgCover) {
            this.goodsImgCover = goodsImgCover;
        }

        public boolean isMemberSeller() {
            return memberSeller;
        }

        public void setMemberSeller(boolean memberSeller) {
            this.memberSeller = memberSeller;
        }

        public String getCommerceType() {
            return commerceType;
        }

        public void setCommerceType(String commerceType) {
            this.commerceType = commerceType;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Object getProductId() {
            return productId;
        }

        public void setProductId(Object productId) {
            this.productId = productId;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public Object getShopId() {
            return shopId;
        }

        public void setShopId(Object shopId) {
            this.shopId = shopId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public List<?> getSpecList() {
            return specList;
        }

        public void setSpecList(List<?> specList) {
            this.specList = specList;
        }
    }
}
