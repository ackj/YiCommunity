package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/6/30 0030 16:08.
 * Email: liujia95me@126.com
 * 服务商品详情
 */

public class ServiceDetailBean extends BaseBean{

    /**
     * data : {"commodityDesc":"按时收费 服务有保障","commodityPrice":"¥70:00","commodityMerit":"1、专业培训\r\n2、高效服务\r\n3、贴心回访","commodityServiceFlow":"1、沙发、餐桌、茶几等开关清洁除尘\r\n2、油烟机表面、储油斗、台面炊具、厨房内瓷砖墙面、电饭煲、电冰箱表面、微波炉台面以及洗碗池的清洗\r\n3、床头柜、梳妆台、衣柜、地面、门、垃圾桶等清洁打扫\r\n4、整理洗漱用品、洗脸台、马桶、莲蓬头、水管、水龙头、浴房墙砖、地砖以及更换垃圾袋","duration":"房屋面积：90平米 参考时长：2-3小时房屋面积：90-140平米参考时长：3-4小时","commodityTitle":"家庭保洁2小时","coverageArea":"惠州市、惠城区","commodityUrl":"http://aglhzysq.image.alimmdn.com/services/commodity/20170703075026673898.jpg","merchantIconUrl":"http://aglhzysq.image.alimmdn.com/services/merchant/icon/20170702111327632659.jpg","merchantName":"惠州居家服务有限公司","address":"惠州市惠城区江北佳兆业大厦15楼1509","businessHours":"9:00--22:00","contactWay":"17688312334","merchantAge":"3","serviceScopes":"家庭保洁、家居维修、送水上门","merchantScene":[{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326347594.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326463636.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326544736.jpg"}],"merchantLicense":[{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170702111326626701.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170702111327139374.jpg"}],"commorityComment":[{"fid":"2c4fef07-1828-4aa4-be04-b87b24d931c2","startLevel":2,"content":"屠龙默默看见了流量监控","createTime":"2017-07-06 14:06:49","commentReplyCount":1,"member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"},"commentReplyList":[{"fid":"282d9b69-68f5-4ff0-b3f0-61b3030ab509","content":" 监控","createTime":"2017-07-07 10:27:58","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}}]},{"fid":"b4b992de-8445-44a6-a596-56a8539624b2","startLevel":3,"content":"唾沫默默哦旅进旅退www","createTime":"2017-07-06 14:05:59","commentReplyCount":8,"member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"},"commentReplyList":[{"fid":"8ab8dc03-92f3-4c22-adf2-86999c2926a9","content":"555","createTime":"2017-07-06 14:06:29","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}},{"fid":"c5f3cd86-5e24-4b01-888b-b564f80566dd","content":"55","createTime":"2017-07-06 14:06:26","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}},{"fid":"90fa945f-e05b-4d89-90bd-e3ba0136ebd2","content":"55","createTime":"2017-07-06 14:06:25","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}},{"fid":"d3be5b32-964c-4dee-8102-13675e7e70ce","content":"55","createTime":"2017-07-06 14:06:25","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}},{"fid":"247ffab5-7d59-4992-ab22-99339a3fbdb1","content":"25","createTime":"2017-07-06 14:06:23","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}}]},{"fid":"369a0cb1-21e1-4feb-adf4-41048df473e5","startLevel":1,"content":"lajilajilaji","createTime":"2017-07-06 14:04:18","commentReplyCount":0,"member":{"memberNickName":"刘嘉","avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg"},"commentReplyList":[]}]}
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
         * commodityDesc : 按时收费 服务有保障
         * commodityPrice : ¥70:00
         * commodityMerit : 1、专业培训
         2、高效服务
         3、贴心回访
         * commodityServiceFlow : 1、沙发、餐桌、茶几等开关清洁除尘
         2、油烟机表面、储油斗、台面炊具、厨房内瓷砖墙面、电饭煲、电冰箱表面、微波炉台面以及洗碗池的清洗
         3、床头柜、梳妆台、衣柜、地面、门、垃圾桶等清洁打扫
         4、整理洗漱用品、洗脸台、马桶、莲蓬头、水管、水龙头、浴房墙砖、地砖以及更换垃圾袋
         * duration : 房屋面积：90平米 参考时长：2-3小时房屋面积：90-140平米参考时长：3-4小时
         * commodityTitle : 家庭保洁2小时
         * coverageArea : 惠州市、惠城区
         * commodityUrl : http://aglhzysq.image.alimmdn.com/services/commodity/20170703075026673898.jpg
         * merchantIconUrl : http://aglhzysq.image.alimmdn.com/services/merchant/icon/20170702111327632659.jpg
         * merchantName : 惠州居家服务有限公司
         * address : 惠州市惠城区江北佳兆业大厦15楼1509
         * businessHours : 9:00--22:00
         * contactWay : 17688312334
         * merchantAge : 3
         * serviceScopes : 家庭保洁、家居维修、送水上门
         * merchantScene : [{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326347594.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326463636.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326544736.jpg"}]
         * merchantLicense : [{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170702111326626701.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170702111327139374.jpg"}]
         * commorityComment : [{"fid":"2c4fef07-1828-4aa4-be04-b87b24d931c2","startLevel":2,"content":"屠龙默默看见了流量监控","createTime":"2017-07-06 14:06:49","commentReplyCount":1,"member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"},"commentReplyList":[{"fid":"282d9b69-68f5-4ff0-b3f0-61b3030ab509","content":" 监控","createTime":"2017-07-07 10:27:58","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}}]},{"fid":"b4b992de-8445-44a6-a596-56a8539624b2","startLevel":3,"content":"唾沫默默哦旅进旅退www","createTime":"2017-07-06 14:05:59","commentReplyCount":8,"member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"},"commentReplyList":[{"fid":"8ab8dc03-92f3-4c22-adf2-86999c2926a9","content":"555","createTime":"2017-07-06 14:06:29","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}},{"fid":"c5f3cd86-5e24-4b01-888b-b564f80566dd","content":"55","createTime":"2017-07-06 14:06:26","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}},{"fid":"90fa945f-e05b-4d89-90bd-e3ba0136ebd2","content":"55","createTime":"2017-07-06 14:06:25","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}},{"fid":"d3be5b32-964c-4dee-8102-13675e7e70ce","content":"55","createTime":"2017-07-06 14:06:25","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}},{"fid":"247ffab5-7d59-4992-ab22-99339a3fbdb1","content":"25","createTime":"2017-07-06 14:06:23","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}}]},{"fid":"369a0cb1-21e1-4feb-adf4-41048df473e5","startLevel":1,"content":"lajilajilaji","createTime":"2017-07-06 14:04:18","commentReplyCount":0,"member":{"memberNickName":"刘嘉","avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg"},"commentReplyList":[]}]
         */

        private String commodityDesc;
        private String commodityPrice;
        private String commodityMerit;
        private String commodityServiceFlow;
        private String duration;
        private String commodityTitle;
        private String coverageArea;
        private String commodityUrl;
        private String merchantIconUrl;
        private String merchantName;
        private String address;
        private String businessHours;
        private String contactWay;
        private String merchantAge;
        private String serviceScopes;
        private List<MerchantSceneBean> merchantScene;
        private List<MerchantLicenseBean> merchantLicense;
        private List<CommorityCommentBean> commorityComment;

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

        public String getCommodityMerit() {
            return commodityMerit;
        }

        public void setCommodityMerit(String commodityMerit) {
            this.commodityMerit = commodityMerit;
        }

        public String getCommodityServiceFlow() {
            return commodityServiceFlow;
        }

        public void setCommodityServiceFlow(String commodityServiceFlow) {
            this.commodityServiceFlow = commodityServiceFlow;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
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

        public String getCommodityUrl() {
            return commodityUrl;
        }

        public void setCommodityUrl(String commodityUrl) {
            this.commodityUrl = commodityUrl;
        }

        public String getMerchantIconUrl() {
            return merchantIconUrl;
        }

        public void setMerchantIconUrl(String merchantIconUrl) {
            this.merchantIconUrl = merchantIconUrl;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusinessHours() {
            return businessHours;
        }

        public void setBusinessHours(String businessHours) {
            this.businessHours = businessHours;
        }

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public String getMerchantAge() {
            return merchantAge;
        }

        public void setMerchantAge(String merchantAge) {
            this.merchantAge = merchantAge;
        }

        public String getServiceScopes() {
            return serviceScopes;
        }

        public void setServiceScopes(String serviceScopes) {
            this.serviceScopes = serviceScopes;
        }

        public List<MerchantSceneBean> getMerchantScene() {
            return merchantScene;
        }

        public void setMerchantScene(List<MerchantSceneBean> merchantScene) {
            this.merchantScene = merchantScene;
        }

        public List<MerchantLicenseBean> getMerchantLicense() {
            return merchantLicense;
        }

        public void setMerchantLicense(List<MerchantLicenseBean> merchantLicense) {
            this.merchantLicense = merchantLicense;
        }

        public List<CommorityCommentBean> getCommorityComment() {
            return commorityComment;
        }

        public void setCommorityComment(List<CommorityCommentBean> commorityComment) {
            this.commorityComment = commorityComment;
        }

        public static class MerchantSceneBean {
            /**
             * url : http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326347594.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class MerchantLicenseBean {
            /**
             * url : http://aglhzysq.image.alimmdn.com/services/merchant/license/20170702111326626701.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class CommorityCommentBean {
            /**
             * fid : 2c4fef07-1828-4aa4-be04-b87b24d931c2
             * startLevel : 2
             * content : 屠龙默默看见了流量监控
             * createTime : 2017-07-06 14:06:49
             * commentReplyCount : 1
             * member : {"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}
             * commentReplyList : [{"fid":"282d9b69-68f5-4ff0-b3f0-61b3030ab509","content":" 监控","createTime":"2017-07-07 10:27:58","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}}]
             */

            private String fid;
            private int startLevel;
            private String content;
            private String createTime;
            private int commentReplyCount;
            private MemberBean member;
            private List<CommentReplyListBean> commentReplyList;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public int getStartLevel() {
                return startLevel;
            }

            public void setStartLevel(int startLevel) {
                this.startLevel = startLevel;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getCommentReplyCount() {
                return commentReplyCount;
            }

            public void setCommentReplyCount(int commentReplyCount) {
                this.commentReplyCount = commentReplyCount;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public List<CommentReplyListBean> getCommentReplyList() {
                return commentReplyList;
            }

            public void setCommentReplyList(List<CommentReplyListBean> commentReplyList) {
                this.commentReplyList = commentReplyList;
            }

            public static class MemberBean {
                /**
                 * memberNickName : 柏勇
                 * avator : http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg
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

            public static class CommentReplyListBean {
                /**
                 * fid : 282d9b69-68f5-4ff0-b3f0-61b3030ab509
                 * content :  监控
                 * createTime : 2017-07-07 10:27:58
                 * member : {"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}
                 */

                private String fid;
                private String content;
                private String createTime;
                private MemberBeanX member;

                public String getFid() {
                    return fid;
                }

                public void setFid(String fid) {
                    this.fid = fid;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public MemberBeanX getMember() {
                    return member;
                }

                public void setMember(MemberBeanX member) {
                    this.member = member;
                }

                public static class MemberBeanX {
                    /**
                     * memberNickName : 柏勇
                     * avator : http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg
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
            }
        }
    }
}
