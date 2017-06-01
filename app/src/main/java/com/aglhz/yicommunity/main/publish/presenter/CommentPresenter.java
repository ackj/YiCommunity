package com.aglhz.yicommunity.main.publish.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.publish.contract.CommentContract;
import com.aglhz.yicommunity.main.publish.model.CommentModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/11 0011 16:46.
 * Email: liujia95me@126.com
 */

public class CommentPresenter extends BasePresenter<CommentContract.View, CommentContract.Model> implements CommentContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public CommentPresenter(CommentContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected CommentContract.Model createModel() {
        return new CommentModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestExchangeCommentList(Params params) {
        mRxManager.add(mModel.requestExchangeCommentList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentListBean -> {
                    if (commentListBean.getOther().getCode() == 200) {
                        getView().responseCommentList(commentListBean.getData().getCommentList());
                    } else {
                        getView().error(commentListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestCarpoolCommentList(Params params) {
        mRxManager.add(mModel.requestCarpoolCommentList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentListBean -> {
                    if (commentListBean.getOther().getCode() == 200) {
                        getView().responseCommentList(commentListBean.getData().getCommentList());
                    } else {
                        getView().error(commentListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void requestNeighbourCommentList(Params params) {
        mRxManager.add(mModel.requestNeighbourCommentList(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentListBean -> {
                    if (commentListBean.getOther().getCode() == 200) {
                        getView().responseCommentList(commentListBean.getData().getCommentList());
                    } else {
                        getView().error(commentListBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void postExchangeComment(Params params) {
        mRxManager.add(mModel.postExchangeComment(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseCommentSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void postCarpoolComment(Params params) {
        mRxManager.add(mModel.postCarpoolComment(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseCommentSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }

    @Override
    public void postNeighbourComment(Params params) {
        mRxManager.add(mModel.postNeighbourComment(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().responseCommentSuccess(baseBean);
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }

}
