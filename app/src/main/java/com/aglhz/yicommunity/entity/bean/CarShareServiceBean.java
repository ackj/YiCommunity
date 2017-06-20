package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * 拼车服务列表bean
 * Created by YandZD on 2017/2/20.
 */

public class CarShareServiceBean extends BaseBean {


    /**
     * data : {"carpoolList":[{"fid":"8ef1d41b-51b0-419f-9b31-d1add73acdd6","member":{"memberNickName":"吴","avator":"http://aglhzmall.image.alimmdn.com/member/20170213094931080885.png"},"communityName":"未定义","type":1,"startPlace":"惠州市","endPlace":"北京市","setOutTime":"2017-02-20 17:58","content":"我有车","positionType":1,"publishPositionLat":"23.112434","publishPositionLng":"114.419590","publishPositionAddress":"惠州市 云山西路","commentCount":0,"createTime":"2017-02-17 18:00:16","pics":[{"type":1,"url":"http://aglhzysq.image.alimmdn.com/carpool/20170217180016360228.png"}]}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CarpoolListBean> carpoolList;

        public List<CarpoolListBean> getCarpoolList() {
            return carpoolList;
        }

        public void setCarpoolList(List<CarpoolListBean> carpoolList) {
            this.carpoolList = carpoolList;
        }

        public static class CarpoolListBean {
            /**
             * fid : 8ef1d41b-51b0-419f-9b31-d1add73acdd6
             * member : {"memberNickName":"吴","avator":"http://aglhzmall.image.alimmdn.com/member/20170213094931080885.png"}
             * communityName : 未定义
             * type : 1
             * startPlace : 惠州市
             * endPlace : 北京市
             * setOutTime : 2017-02-20 17:58
             * content : 我有车
             * positionType : 1
             * publishPositionLat : 23.112434
             * publishPositionLng : 114.419590
             * publishPositionAddress : 惠州市 云山西路
             * commentCount : 0
             * createTime : 2017-02-17 18:00:16
             * pics : [{"type":1,"url":"http://aglhzysq.image.alimmdn.com/carpool/20170217180016360228.png"}]
             */

            private String fid;
            private MemberBean member;
            private String communityName;
            private int type;
            private String startPlace;
            private String endPlace;
            private String setOutTime;
            private String content;
            private int positionType;
            private String publishPositionLat;
            private String publishPositionLng;
            private String publishPositionAddress;
            private String commentCount;
            private String createTime;
            private List<PicsBean> pics;
//            private List<MomentsCommentBean.DataBean.CommentListBean> commentList;
//
//            public List<MomentsCommentBean.DataBean.CommentListBean> getCommentList() {
//                return commentList;
//            }
//
//            public void setCommentList(List<MomentsCommentBean.DataBean.CommentListBean> commentList) {
//                this.commentList = commentList;
//            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public String getCommunityName() {
                return communityName;
            }

            public void setCommunityName(String communityName) {
                this.communityName = communityName;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getStartPlace() {
                return startPlace;
            }

            public void setStartPlace(String startPlace) {
                this.startPlace = startPlace;
            }

            public String getEndPlace() {
                return endPlace;
            }

            public void setEndPlace(String endPlace) {
                this.endPlace = endPlace;
            }

            public String getSetOutTime() {
                return setOutTime;
            }

            public void setSetOutTime(String setOutTime) {
                this.setOutTime = setOutTime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getPositionType() {
                return positionType;
            }

            public void setPositionType(int positionType) {
                this.positionType = positionType;
            }

            public String getPublishPositionLat() {
                return publishPositionLat;
            }

            public void setPublishPositionLat(String publishPositionLat) {
                this.publishPositionLat = publishPositionLat;
            }

            public String getPublishPositionLng() {
                return publishPositionLng;
            }

            public void setPublishPositionLng(String publishPositionLng) {
                this.publishPositionLng = publishPositionLng;
            }

            public String getPublishPositionAddress() {
                return publishPositionAddress;
            }

            public void setPublishPositionAddress(String publishPositionAddress) {
                this.publishPositionAddress = publishPositionAddress;
            }

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public List<PicsBean> getPics() {
                return pics;
            }

            public void setPics(List<PicsBean> pics) {
                this.pics = pics;
            }

            public static class MemberBean {
                /**
                 * memberNickName : 吴
                 * avator : http://aglhzmall.image.alimmdn.com/member/20170213094931080885.png
                 */

                private String memberNickName;
                private String avator;

                public String getMemberNickName() {
                    return memberNickName;
                }

                public void setMemberNickName(String memberNickName) {
                    this.memberNickName = memberNickName;
                }

                public String getAvator() {
                    return avator;
                }

                public void setAvator(String avator) {
                    this.avator = avator;
                }
            }

            public static class PicsBean {
                /**
                 * type : 1
                 * url : http://aglhzysq.image.alimmdn.com/carpool/20170217180016360228.png
                 */

                private int type;
                private String url;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
