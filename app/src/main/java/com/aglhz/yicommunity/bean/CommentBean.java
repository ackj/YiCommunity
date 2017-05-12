package com.aglhz.yicommunity.bean;

/**
 * Author: LiuJia on 2017/5/10 0010 09:30.
 * Email: liujia95me@126.com
 */

public class CommentBean{
    /**
     * content : 时空
     * createTime : 2017-05-10 09:24:51
     * fid : a3a37457-52ec-4943-add5-2294336a1426
     * member : {"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}
     */

    private String content;
    private String createTime;
    private String fid;
    private MemberBean member;

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

}
