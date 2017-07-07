package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Created by leguang on 2017/7/5 0005.
 * Email：langmanleguang@qq.com
 */

public class RemarkListBean extends BaseBean{

    /**
     * data : {"commentList":[{"fid":"bdfb52aa-2dbc-44a1-b596-8cfaf47a662e","startLevel":5,"content":"1包健康最重要下午我","createTime":"2017-07-06 11:01:09","merchantName":"惠州居家服务有限公司","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"},"commentReplyList":[{"fid":"e5f8004c-b818-4208-b774-0b0c09a04575","content":"1册饿","createTime":"2017-07-06 11:01:27","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}}]},{"fid":"ccaeae10-ad87-4934-8fd5-c98fe647cbb9","startLevel":5,"content":"旅途我现在用默默哦我摸摸哦耶","createTime":"2017-07-06 09:10:22","merchantName":"惠州居家服务有限公司","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"},"commentReplyList":[{"fid":"e7ba1028-e2cf-46f6-8302-687649f553b6","content":"123","createTime":"2017-07-06 11:00:48","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}}]},{"fid":"febae405-fd6a-48d1-b247-cd0b4b6601a2","startLevel":5,"content":"JJ咯哦哦哦哦哦哦哦哦哦哦哦哦哦","createTime":"2017-07-06 09:09:59","merchantName":"惠州居家服务有限公司","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"},"commentReplyList":[]},{"fid":"3ca843a2-5b06-4047-a5a1-91846d58384c","startLevel":5,"content":"hello fack ......","createTime":"2017-07-05 19:39:23","merchantName":"惠州居家服务有限公司","member":{"memberNickName":"刘嘉","avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg"},"commentReplyList":[{"fid":"de8097b2-2198-477d-80f0-f12b10e447c9","content":"222222222","createTime":"2017-07-05 19:44:29","member":{"memberNickName":"刘嘉","avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg"}}]},{"fid":"76b33bb5-69ae-4dfe-9926-a2df7650e319","startLevel":5,"content":"hello fack","createTime":"2017-07-05 19:39:14","merchantName":"惠州居家服务有限公司","member":{"memberNickName":"刘嘉","avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg"},"commentReplyList":[]},{"fid":"19a01a7c-77e3-4d53-aa27-5f2bc25263a2","startLevel":5,"content":"hello","createTime":"2017-07-05 19:38:54","merchantName":"惠州居家服务有限公司","member":{"memberNickName":"刘嘉","avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg"},"commentReplyList":[{"fid":"1681eb8a-00f6-4dba-8242-b82cf0d7ce30","content":"hhhhhhh","createTime":"2017-07-05 21:10:04","member":{"memberNickName":"刘嘉","avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg"}}]},{"fid":"3c19e525-090f-435f-a823-129f151b8aba","startLevel":5,"content":"111111111\u009b","createTime":"2017-07-05 19:36:58","merchantName":"惠州居家服务有限公司","member":{"memberNickName":"刘嘉","avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg"},"commentReplyList":[{"fid":"1b441f3d-b85d-4dc2-9027-9c7c051d7747","content":"hello","createTime":"2017-07-05 19:38:40","member":{"memberNickName":"刘嘉","avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg"}}]}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CommentListBean> commentList;

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public static class CommentListBean {
            /**
             * fid : bdfb52aa-2dbc-44a1-b596-8cfaf47a662e
             * startLevel : 5
             * content : 1包健康最重要下午我
             * createTime : 2017-07-06 11:01:09
             * merchantName : 惠州居家服务有限公司
             * member : {"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}
             * commentReplyList : [{"fid":"e5f8004c-b818-4208-b774-0b0c09a04575","content":"1册饿","createTime":"2017-07-06 11:01:27","member":{"memberNickName":"柏勇","avator":"http://aglhzmall.image.alimmdn.com/member/20170509115013937894.jpg"}}]
             */

            private String fid;
            private int startLevel;
            private String content;
            private String createTime;
            private String merchantName;
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

            public String getMerchantName() {
                return merchantName;
            }

            public void setMerchantName(String merchantName) {
                this.merchantName = merchantName;
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
                 * fid : e5f8004c-b818-4208-b774-0b0c09a04575
                 * content : 1册饿
                 * createTime : 2017-07-06 11:01:27
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
