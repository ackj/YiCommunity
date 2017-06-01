package com.aglhz.yicommunity.main.propery.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.propery.contract.NoticeListContract;
import com.aglhz.yicommunity.main.propery.model.NoticeListModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/9 0009 22:34.
 * Email: liujia95me@126.com
 */

public class NoticeListPresenter extends BasePresenter<NoticeListContract.View, NoticeListContract.Model> implements NoticeListContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public NoticeListPresenter(NoticeListContract.View mView) {
        super(mView);
    }

    @Override
    public void start(Object request) {

    }

    @NonNull
    @Override
    protected NoticeListContract.Model createModel() {
        return new NoticeListModel();
    }

    @Override
    public void requestNotices(Params params) {
        mRxManager.add(mModel.requestNotices(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(noticeBean -> {
                    if (noticeBean.getOther().getCode() == 200) {
                        getView().responseNotices(noticeBean.getData().getNoticeList());
                    } else {
                        getView().error(noticeBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
