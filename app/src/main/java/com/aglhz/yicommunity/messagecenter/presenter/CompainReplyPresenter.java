package com.aglhz.yicommunity.messagecenter.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.messagecenter.contract.CompainsReplyContract;
import com.aglhz.yicommunity.messagecenter.model.CompainsReplyModel;

/**
 * Author: LiuJia on 2017/5/18 0018 18:04.
 * Email: liujia95me@126.com
 */

public class CompainReplyPresenter extends BasePresenter<CompainsReplyContract.View, CompainsReplyContract.Model> implements CompainsReplyContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public CompainReplyPresenter(CompainsReplyContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected CompainsReplyContract.Model createModel() {
        return new CompainsReplyModel();
    }

    @Override
    public void start(Object request) {

    }
}
