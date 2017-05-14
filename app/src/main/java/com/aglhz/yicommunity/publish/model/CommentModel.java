package com.aglhz.yicommunity.publish.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.CommentListBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.CommentContract;

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
        return HttpHelper.getService(ApiService.class)
                .getExchangeComments(ApiService.getExchangeComments, params.fid, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CommentListBean> requestCarpoolCommentList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .getCarpoolComments(ApiService.getCarpoolComments, params.fid, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CommentListBean> requestNeighbourCommentList(Params params) {
        return HttpHelper.getService(ApiService.class)
                .getNeighbourComments(ApiService.getNeighbourComments, params.fid, params.page, params.pageSize)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> postExchangeComment(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("exchangeFid", params.fid);
        builder.addFormDataPart("content", params.content);

        return HttpHelper.getService(ApiService.class)
                .postExchangeComment(ApiService.postExchangeComment, builder.build())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> postCarpoolComment(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("carpoolFid", params.fid);
        builder.addFormDataPart("content", params.content);
        return HttpHelper.getService(ApiService.class)
                .postCarpoolComment(ApiService.postCarpoolComment,builder.build())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> postNeighbourComment(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("momentsFid", params.fid);
        builder.addFormDataPart("content", params.content);
        return HttpHelper.getService(ApiService.class)
                .postNeighbourComment(ApiService.postNeighbourComment, builder.build())
                .subscribeOn(Schedulers.io());
    }
}
