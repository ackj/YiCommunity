package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/9/5 0005 17:24.
 * Email: liujia95me@126.com
 */

public class CommunityBean extends BaseBean{


    /**
     * data : {"communityInfoList":[{"communityFid":"KLJY-v85-00004","communityName":"广东省惠州市惠城区克拉家园"},{"communityFid":"KBSJ-agl-00005","communityName":"广东省惠州市惠城区凯宾斯基"},{"communityFid":"DHHY1Q-cgi-00002","communityName":"广东省惠州东平东湖花园1期"},{"communityFid":"YSQ-HLCT-01-00003","communityName":"广东省惠州海伦春天1期"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CommunityInfoListBean> communityInfoList;

        public List<CommunityInfoListBean> getCommunityInfoList() {
            return communityInfoList;
        }

        public void setCommunityInfoList(List<CommunityInfoListBean> communityInfoList) {
            this.communityInfoList = communityInfoList;
        }

        public static class CommunityInfoListBean {
            /**
             * communityFid : KLJY-v85-00004
             * communityName : 广东省惠州市惠城区克拉家园
             */

            private String communityFid;
            private String communityName;

            public String getCommunityFid() {
                return communityFid;
            }

            public void setCommunityFid(String communityFid) {
                this.communityFid = communityFid;
            }

            public String getCommunityName() {
                return communityName;
            }

            public void setCommunityName(String communityName) {
                this.communityName = communityName;
            }
        }
    }
}
