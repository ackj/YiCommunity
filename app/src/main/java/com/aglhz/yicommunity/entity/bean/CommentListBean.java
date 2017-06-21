package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/11 0011 16:22.
 * Email: liujia95me@126.com
 */

public class CommentListBean extends BaseBean {


    /**
     * data : {"commentList":[{"content":" 来看看","createTime":"2017-05-11 16:19:53","fid":"29c9f8cf-3a5b-48d5-8c31-b113bf83a68b","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}},{"content":"拖","createTime":"2017-05-11 16:19:48","fid":"38106def-a8b0-4c98-bc55-59207ad8ca50","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}},{"content":"@1223 tkl","createTime":"2017-05-11 16:19:43","fid":"96fc289e-0162-430e-872b-447aab63240a","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}},{"content":"看看","createTime":"2017-05-11 16:19:35","fid":"3358ecc4-f5be-4c0d-bd83-866be0f21a57","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}},{"content":"天空","createTime":"2017-05-11 16:19:31","fid":"61c32526-ce54-49e3-8690-149c43a69618","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}},{"content":"看看","createTime":"2017-05-11 16:19:26","fid":"da73f742-decc-4f4c-ad56-9fe6921ab06c","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}},{"content":"看看","createTime":"2017-05-11 16:19:17","fid":"f58be0ca-d329-422d-85d6-b8ea25312451","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}},{"content":"看看","createTime":"2017-05-11 16:19:14","fid":"0e826986-4794-458f-9959-e2141903c553","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}},{"content":"@1223 ","createTime":"2017-05-11 16:19:11","fid":"190cd977-618f-4e7e-b5a7-ba11e7161843","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}},{"content":"垃圾","createTime":"2017-05-11 16:19:04","fid":"b290d092-2fc5-4c80-9904-e3b8fe8107c2","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170413150426133939.png","memberNickName":"1223"}}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CommentBean> commentList;

        public List<CommentBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentBean> commentList) {
            this.commentList = commentList;
        }
    }
}
