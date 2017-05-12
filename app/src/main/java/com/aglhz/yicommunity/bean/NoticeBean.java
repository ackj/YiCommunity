package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/7 0007 17:30.
 * Email: liujia95me@126.com
 */

public class NoticeBean extends BaseBean{

    /**
     * data : {"noticeList":[{"fid":"eec6cfd7-988f-4e97-954b-f3d32eae29e1","title":"小区部分停电通知","content":"停电范围：小区1栋、2栋、3栋\u2026","ctime":"2017-02-06"}]}
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
             * fid : eec6cfd7-988f-4e97-954b-f3d32eae29e1
             * title : 小区部分停电通知
             * content : 停电范围：小区1栋、2栋、3栋…
             * ctime : 2017-02-06
             */
            private String fid;
            private String title;
            private String content;
            private String ctime;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }
    }
}
