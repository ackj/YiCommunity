package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/7 0007 17:30.
 * Email: liujia95me@126.com
 */

public class NoticeBean extends BaseBean{


    /**
     * data : {"noticeList":[{"fid":"8bcb610a-6de5-49f2-a725-316776f18f3e","title":"消杀通知"},{"fid":"d6fcb972-ff58-45d8-99ae-9216dc39251c","title":"临时停水通知"},{"fid":"2dc229ff-7557-4ba7-b548-dcf8e47f07d6","title":"青年榜样习近平"},{"fid":"85bbccb4-d36c-4c1b-be87-a3e147ceb5d2","title":"钥匙招领"},{"fid":"10de4983-f73b-4fde-b522-8c326e6356b6","title":"写好新世纪海上丝绸之路新篇章"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<NoticeListBean> noticeList;

        public List<NoticeListBean> getNoticeList() {
            return noticeList;
        }

        public void setNoticeList(List<NoticeListBean> noticeList) {
            this.noticeList = noticeList;
        }

        public static class NoticeListBean {
            /**
             * fid : 8bcb610a-6de5-49f2-a725-316776f18f3e
             * title : 消杀通知
             */

            private String fid;
            private String title;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
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
