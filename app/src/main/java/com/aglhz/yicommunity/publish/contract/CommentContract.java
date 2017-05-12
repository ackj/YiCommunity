package com.aglhz.yicommunity.publish.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.CommentBean;
import com.aglhz.yicommunity.bean.CommentListBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/11 0011 16:34.
 * Email: liujia95me@126.com
 */

public interface CommentContract {

    interface View extends BaseContract.View {
        void responseCommentList(List<CommentBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestCommentList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<CommentListBean> getCommentList(Params params);
    }

}
