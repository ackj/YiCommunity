package com.aglhz.yicommunity.publish.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.CommentListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.CommentContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/11 0011 16:36.
 * Email: liujia95me@126.com
 */

public class CommentModel extends BaseModel implements CommentContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<CommentListBean> getCommentList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .getExchangeComments(ApiService.getExchangeComments, params.fid, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }
}
