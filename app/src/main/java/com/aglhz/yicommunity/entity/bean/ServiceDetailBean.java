package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/6/30 0030 16:08.
 * Email: liujia95me@126.com
 * 服务商品详情
 */

public class ServiceDetailBean extends BaseBean{

    /**
     * data : {"commorityComment":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"19a01a7c-77e3-4d53-aa27-5f2bc25263a2","createTime":"2017-07-05 19:38:54","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"what ï¼\u009fï¼\u009fï¼\u009f","fid":"de8097b2-2198-477d-80f0-f12b10e447c9","createTime":"2017-07-05 19:44:29"}]},{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"19a01a7c-77e3-4d53-aa27-5f2bc25263a2","createTime":"2017-07-05 19:38:54","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"what ï¼\u009fï¼\u009fï¼\u009f","fid":"de8097b2-2198-477d-80f0-f12b10e447c9","createTime":"2017-07-05 19:44:29"}]},{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"19a01a7c-77e3-4d53-aa27-5f2bc25263a2","createTime":"2017-07-05 19:38:54","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"what ï¼\u009fï¼\u009fï¼\u009f","fid":"de8097b2-2198-477d-80f0-f12b10e447c9","createTime":"2017-07-05 19:44:29"}]}],"merchantLicense":[{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170702111326626701.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170702111327139374.jpg"}],"contactWay":"17688312334","merchantScene":[{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326347594.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326463636.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326544736.jpg"}],"commodityPrice":"¥70:00","commodityUrl":"http://aglhzysq.image.alimmdn.com/services/commodity/20170703075026673898.jpg","merchantAge":"2","duration":"房屋面积：90平米    参考时长：2-3小时房屋面积：90-140平米参考时长：3-4小时","commodityMerit":"1、专业培训\r\n2、高效服务\r\n3、贴心回访","commodityTitle":"家庭保洁2小时","address":"惠州市惠城区江北佳兆业大厦15楼1509","businessHours":"9:00--22:00","merchantIconUrl":"http://aglhzysq.image.alimmdn.com/services/merchant/icon/20170702111327632659.jpg","merchantName":"惠州居家服务有限公司","commodityDesc":"按时收费 服务有保障","serviceScopes":"家庭保洁、家居维修、送水上门","commodityServiceFlow":"1、沙发、餐桌、茶几等开关清洁除尘\r\n2、油烟机表面、储油斗、台面炊具、厨房内瓷砖墙面、电饭煲、电冰箱表面、微波炉台面以及洗碗池的清洗\r\n3、床头柜、梳妆台、衣柜、地面、门、垃圾桶等清洁打扫\r\n4、整理洗漱用品、洗脸台、马桶、莲蓬头、水管、水龙头、浴房墙砖、地砖以及更换垃圾袋","coverageArea":"惠州市、惠城区"}
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
         * commorityComment : [{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"19a01a7c-77e3-4d53-aa27-5f2bc25263a2","createTime":"2017-07-05 19:38:54","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"what ï¼\u009fï¼\u009fï¼\u009f","fid":"de8097b2-2198-477d-80f0-f12b10e447c9","createTime":"2017-07-05 19:44:29"}]},{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"19a01a7c-77e3-4d53-aa27-5f2bc25263a2","createTime":"2017-07-05 19:38:54","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"what ï¼\u009fï¼\u009fï¼\u009f","fid":"de8097b2-2198-477d-80f0-f12b10e447c9","createTime":"2017-07-05 19:44:29"}]},{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"19a01a7c-77e3-4d53-aa27-5f2bc25263a2","createTime":"2017-07-05 19:38:54","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"what ï¼\u009fï¼\u009fï¼\u009f","fid":"de8097b2-2198-477d-80f0-f12b10e447c9","createTime":"2017-07-05 19:44:29"}]}]
         * merchantLicense : [{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170702111326626701.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/license/20170702111327139374.jpg"}]
         * contactWay : 17688312334
         * merchantScene : [{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326347594.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326463636.jpg"},{"url":"http://aglhzysq.image.alimmdn.com/services/merchant/scene/20170702111326544736.jpg"}]
         * commodityPrice : ¥70:00
         * commodityUrl : http://aglhzysq.image.alimmdn.com/services/commodity/20170703075026673898.jpg
         * merchantAge : 2
         * duration : 房屋面积：90平米    参考时长：2-3小时房屋面积：90-140平米参考时长：3-4小时
         * commodityMerit : 1、专业培训
         2、高效服务
         3、贴心回访
         * commodityTitle : 家庭保洁2小时
         * address : 惠州市惠城区江北佳兆业大厦15楼1509
         * businessHours : 9:00--22:00
         * merchantIconUrl : http://aglhzysq.image.alimmdn.com/services/merchant/icon/20170702111327632659.jpg
         * merchantName : 惠州居家服务有限公司
         * commodityDesc : 按时收费 服务有保障
         * serviceScopes : 家庭保洁、家居维修、送水上门
         * commodityServiceFlow : 1、沙发、餐桌、茶几等开关清洁除尘
         2、油烟机表面、储油斗、台面炊具、厨房内瓷砖墙面、电饭煲、电冰箱表面、微波炉台面以及洗碗池的清洗
         3、床头柜、梳妆台、衣柜、地面、门、垃圾桶等清洁打扫
         4、整理洗漱用品、洗脸台、马桶、莲蓬头、水管、水龙头、浴房墙砖、地砖以及更换垃圾袋
         * coverageArea : 惠州市、惠城区
         */

        private String contactWay;
        private String commodityPrice;
        private String commodityUrl;
        private String merchantAge;
        private String duration;
        private String commodityMerit;
        private String commodityTitle;
        private String address;
        private String businessHours;
        private String merchantIconUrl;
        private String merchantName;
        private String commodityDesc;
        private String serviceScopes;
        private String commodityServiceFlow;
        private String coverageArea;
        private List<CommorityCommentBean> commorityComment;
        private List<MerchantLicenseBean> merchantLicense;
        private List<MerchantSceneBean> merchantScene;

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public String getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(String commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public String getCommodityUrl() {
            return commodityUrl;
        }

        public void setCommodityUrl(String commodityUrl) {
            this.commodityUrl = commodityUrl;
        }

        public String getMerchantAge() {
            return merchantAge;
        }

        public void setMerchantAge(String merchantAge) {
            this.merchantAge = merchantAge;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getCommodityMerit() {
            return commodityMerit;
        }

        public void setCommodityMerit(String commodityMerit) {
            this.commodityMerit = commodityMerit;
        }

        public String getCommodityTitle() {
            return commodityTitle;
        }

        public void setCommodityTitle(String commodityTitle) {
            this.commodityTitle = commodityTitle;
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

        public String getCommodityDesc() {
            return commodityDesc;
        }

        public void setCommodityDesc(String commodityDesc) {
            this.commodityDesc = commodityDesc;
        }

        public String getServiceScopes() {
            return serviceScopes;
        }

        public void setServiceScopes(String serviceScopes) {
            this.serviceScopes = serviceScopes;
        }

        public String getCommodityServiceFlow() {
            return commodityServiceFlow;
        }

        public void setCommodityServiceFlow(String commodityServiceFlow) {
            this.commodityServiceFlow = commodityServiceFlow;
        }

        public String getCoverageArea() {
            return coverageArea;
        }

        public void setCoverageArea(String coverageArea) {
            this.coverageArea = coverageArea;
        }

        public List<CommorityCommentBean> getCommorityComment() {
            return commorityComment;
        }

        public void setCommorityComment(List<CommorityCommentBean> commorityComment) {
            this.commorityComment = commorityComment;
        }

        public List<MerchantLicenseBean> getMerchantLicense() {
            return merchantLicense;
        }

        public void setMerchantLicense(List<MerchantLicenseBean> merchantLicense) {
            this.merchantLicense = merchantLicense;
        }

        public List<MerchantSceneBean> getMerchantScene() {
            return merchantScene;
        }

        public void setMerchantScene(List<MerchantSceneBean> merchantScene) {
            this.merchantScene = merchantScene;
        }

        public static class CommorityCommentBean {
            /**
             * member : {"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"}
             * content : hello
             * fid : 19a01a7c-77e3-4d53-aa27-5f2bc25263a2
             * createTime : 2017-07-05 19:38:54
             * commentReplyList : [{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"what ï¼\u009fï¼\u009fï¼\u009f","fid":"de8097b2-2198-477d-80f0-f12b10e447c9","createTime":"2017-07-05 19:44:29"}]
             */

            private MemberBean member;
            private String content;
            private String fid;
            private String createTime;
            private List<CommentReplyListBean> commentReplyList;

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public List<CommentReplyListBean> getCommentReplyList() {
                return commentReplyList;
            }

            public void setCommentReplyList(List<CommentReplyListBean> commentReplyList) {
                this.commentReplyList = commentReplyList;
            }

            public static class MemberBean {
                /**
                 * avator : http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg
                 * memberNickName : 刘嘉
                 */

                private String avator;
                private String memberNickName;

                public String getAvator() {
                    return avator;
                }

                public void setAvator(String avator) {
                    this.avator = avator;
                }

                public String getMemberNickName() {
                    return memberNickName;
                }

                public void setMemberNickName(String memberNickName) {
                    this.memberNickName = memberNickName;
                }
            }

            public static class CommentReplyListBean {
                /**
                 * member : {"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"}
                 * content : what ï¼ï¼ï¼
                 * fid : de8097b2-2198-477d-80f0-f12b10e447c9
                 * createTime : 2017-07-05 19:44:29
                 */

                private MemberBeanX member;
                private String content;
                private String fid;
                private String createTime;

                public MemberBeanX getMember() {
                    return member;
                }

                public void setMember(MemberBeanX member) {
                    this.member = member;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getFid() {
                    return fid;
                }

                public void setFid(String fid) {
                    this.fid = fid;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public static class MemberBeanX {
                    /**
                     * avator : http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg
                     * memberNickName : 刘嘉
                     */

                    private String avator;
                    private String memberNickName;

                    public String getAvator() {
                        return avator;
                    }

                    public void setAvator(String avator) {
                        this.avator = avator;
                    }

                    public String getMemberNickName() {
                        return memberNickName;
                    }

                    public void setMemberNickName(String memberNickName) {
                        this.memberNickName = memberNickName;
                    }
                }
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
    }
}
