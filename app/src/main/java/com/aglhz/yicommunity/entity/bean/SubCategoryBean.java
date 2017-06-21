package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/22 0022 11:36.
 * Email: liujia95me@126.com
 */

public class SubCategoryBean extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3d48f009-a155-45ee-8730-246c887dd9be
         * name : 家庭安全系统
         * link : null
         * image : null
         * tmp : null
         * lstCategory : null
         */

        private String id;
        private String name;
        private Object link;
        private Object image;
        private Object tmp;
        private Object lstCategory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getLink() {
            return link;
        }

        public void setLink(Object link) {
            this.link = link;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public Object getTmp() {
            return tmp;
        }

        public void setTmp(Object tmp) {
            this.tmp = tmp;
        }

        public Object getLstCategory() {
            return lstCategory;
        }

        public void setLstCategory(Object lstCategory) {
            this.lstCategory = lstCategory;
        }
    }
}
