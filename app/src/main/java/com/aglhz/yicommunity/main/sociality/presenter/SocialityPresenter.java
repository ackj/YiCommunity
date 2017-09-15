package com.aglhz.yicommunity.main.sociality.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.sociality.contract.SocialityContract;
import com.aglhz.yicommunity.main.sociality.model.SocialityModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/9/5 0005 17:35.
 * Email: liujia95me@126.com
 */

public class SocialityPresenter extends BasePresenter<SocialityContract.View,SocialityContract.Model> implements SocialityContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public SocialityPresenter(SocialityContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected SocialityContract.Model createModel() {
        return new SocialityModel();
    }

    @Override
    public void requestCommunitys(Params params) {
        mRxManager.add(mModel.requestCommunitys(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(communityBean -> {
                    if (communityBean.getOther().getCode() == 200) {
                        getView().responseCommunitys(communityBean.getData().getCommunityInfoList());
                    } else {
                        getView().error(communityBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
