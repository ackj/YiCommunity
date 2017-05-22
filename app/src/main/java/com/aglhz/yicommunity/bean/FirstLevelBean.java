package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/22 0022 09:49.
 * Email: liujia95me@126.com
 */

public class FirstLevelBean extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * id : 37085bd7-e28a-4471-98f6-2809ad5998cd
         * image : http://aglhzmall.image.alimmdn.com/category/20161201111412700443.jpg@248h_330w_1e_1c
         * link : http://www.aglhz.com/mall/m/html/productListForm.html?id=37085bd7-e28a-4471-98f6-2809ad5998cd
         * name : 美味零食
         */
        private String id;
        private String image;
        private String link;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
