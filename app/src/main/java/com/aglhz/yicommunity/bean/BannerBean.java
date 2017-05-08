package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/7 0007 16:07.
 * Email: liujia95me@126.com
 */

public class BannerBean extends BaseBean {

    /**
     * data : {"advs":[{"cover":"http://aglhzysq.image.alimmdn.com/indexCfg/adv1/20170504102553892824.jpg@300h_600w_1e_1c","des":"智慧亿社区-欢迎画面","fid":"fc6fa9de-1008-44d7-9a05-e8042e26b28d","ftype":1,"link":"http://aglhzysq.image.alimmdn.com/indexCfg/adv1/20170504102553892824.jpg","title":"智慧亿社区-欢迎画面"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<AdvsBean> advs;

        public List<AdvsBean> getAdvs() {
            return advs;
        }

        public void setAdvs(List<AdvsBean> advs) {
            this.advs = advs;
        }

        public static class AdvsBean {
            /**
             * cover : http://aglhzysq.image.alimmdn.com/indexCfg/adv1/20170504102553892824.jpg@300h_600w_1e_1c
             * des : 智慧亿社区-欢迎画面
             * fid : fc6fa9de-1008-44d7-9a05-e8042e26b28d
             * ftype : 1
             * link : http://aglhzysq.image.alimmdn.com/indexCfg/adv1/20170504102553892824.jpg
             * title : 智慧亿社区-欢迎画面
             */

            private String cover;
            private String des;
            private String fid;
            private int ftype;
            private String link;
            private String title;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public int getFtype() {
                return ftype;
            }

            public void setFtype(int ftype) {
                this.ftype = ftype;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
