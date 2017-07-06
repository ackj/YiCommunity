package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Created by leguang on 2017/7/5 0005.
 * Email：langmanleguang@qq.com
 */

public class RemarkListBean extends BaseBean{

    /**
     * data : {"commentList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"é\u009d\u009eå¸¸ç»\u0099å\u008a\u009b","fid":"3c19e525-090f-435f-a823-129f151b8aba","merchantName":"惠州居家服务有限公司","createTime":"2017-07-05 19:36:58","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"1b441f3d-b85d-4dc2-9027-9c7c051d7747","createTime":"2017-07-05 19:38:40"}]},{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"é\u009d\u009eå¸¸ç»\u0099å\u008a\u009b","fid":"3c19e525-090f-435f-a823-129f151b8aba","merchantName":"惠州居家服务有限公司","createTime":"2017-07-05 19:36:58","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"1b441f3d-b85d-4dc2-9027-9c7c051d7747","createTime":"2017-07-05 19:38:40"}]},{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"é\u009d\u009eå¸¸ç»\u0099å\u008a\u009b","fid":"3c19e525-090f-435f-a823-129f151b8aba","merchantName":"惠州居家服务有限公司","createTime":"2017-07-05 19:36:58","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"1b441f3d-b85d-4dc2-9027-9c7c051d7747","createTime":"2017-07-05 19:38:40"}]},{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"é\u009d\u009eå¸¸ç»\u0099å\u008a\u009b","fid":"3c19e525-090f-435f-a823-129f151b8aba","merchantName":"惠州居家服务有限公司","createTime":"2017-07-05 19:36:58","commentReplyList":[{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"1b441f3d-b85d-4dc2-9027-9c7c051d7747","createTime":"2017-07-05 19:38:40"}]}]}
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
             * member : {"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"}
             * content : éå¸¸ç»å
             * fid : 3c19e525-090f-435f-a823-129f151b8aba
             * merchantName : 惠州居家服务有限公司
             * createTime : 2017-07-05 19:36:58
             * commentReplyList : [{"member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170626164302534559.jpeg","memberNickName":"刘嘉"},"content":"hello","fid":"1b441f3d-b85d-4dc2-9027-9c7c051d7747","createTime":"2017-07-05 19:38:40"}]
             */

            private MemberBean member;
            private String content;
            private String fid;
            private String merchantName;
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

            public String getMerchantName() {
                return merchantName;
            }

            public void setMerchantName(String merchantName) {
                this.merchantName = merchantName;
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
                 * content : hello
                 * fid : 1b441f3d-b85d-4dc2-9027-9c7c051d7747
                 * createTime : 2017-07-05 19:38:40
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
    }
}
