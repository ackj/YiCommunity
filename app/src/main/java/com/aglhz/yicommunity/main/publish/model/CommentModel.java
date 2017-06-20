package com.aglhz.yicommunity.main.publish.model;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.CommentListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.publish.contract.CommentContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * Author: LiuJia on 2017/5/11 0011 16:36.
 * Email: liujia95me@126.com
 */

public class CommentModel extends BaseModel implements CommentContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<CommentListBean> requestExchangeCommentList(Params params) {
        ALog.e("page::" + params.page);

        return HttpHelper.getService(ApiService.class)
                .getExchangeComments(ApiService.getExchangeComments, params.fid, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CommentListBean> requestCarpoolCommentList(Params params) {
        ALog.e("page::" + params.page);

        return HttpHelper.getService(ApiService.class)
                .requestCarpoolComments(ApiService.requestCarpoolComments, params.fid, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CommentListBean> requestNeighbourCommentList(Params params) {
        ALog.e("page::" + params.page);


        return HttpHelper.getService(ApiService.class)
                .requestNeighbourComments(ApiService.requestNeighbourComments, params.fid, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestSubmitExchangeComment(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("exchangeFid", params.fid);
        builder.addFormDataPart("content", params.content);

        return HttpHelper.getService(ApiService.class)
                .requestSubmitExchangeComment(ApiService.requestSubmitExchangeComment, builder.build())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestSubmitCarpoolComment(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("carpoolFid", params.fid);
        builder.addFormDataPart("content", params.content);
        return HttpHelper.getService(ApiService.class)
                .requestSubmitCarpoolComment(ApiService.requestSubmitCarpoolComment, builder.build())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> requestSubmitNeighbourComment(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("momentsFid", params.fid);
        builder.addFormDataPart("content", params.content);
        return HttpHelper.getService(ApiService.class)
                .requestSubmitNeighbourComment(ApiService.requestSubmitNeighbourComment, builder.build())
                .subscribeOn(Schedulers.io());
    }
}
