package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/7/28 0028 20:18.
 * Email: liujia95me@126.com
 */

public class OneKeyDoorBean extends BaseBean{


    /**
     * data : {"itemCount":2,"itemList":[{"cmnt":"KLJY-v85-00004","dir":"43-1","name":"克拉家园门口机","online":true},{"cmnt":"KLJY-v85-00004","dir":"43-101-1","name":"克拉家园1栋1单元门口机","online":false}]}
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
         * itemCount : 2
         * itemList : [{"cmnt":"KLJY-v85-00004","dir":"43-1","name":"克拉家园门口机","online":true},{"cmnt":"KLJY-v85-00004","dir":"43-101-1","name":"克拉家园1栋1单元门口机","online":false}]
         */

        private int itemCount;
        private List<ItemListBean> itemList;

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public static class ItemListBean {
            /**
             * cmnt : KLJY-v85-00004
             * dir : 43-1
             * name : 克拉家园门口机
             * online : true
             */

            private String cmnt;
            private String dir;
            private String name;
            private boolean online;

            public String getCmnt() {
                return cmnt;
            }

            public void setCmnt(String cmnt) {
                this.cmnt = cmnt;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isOnline() {
                return online;
            }

            public void setOnline(boolean online) {
                this.online = online;
            }
        }
    }
}
