package com.aglhz.yicommunity.picker.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.picker.contract.CityPickerContract;
import com.aglhz.yicommunity.picker.model.CommunityPickerModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/8 0008 11:36.
 * Email: liujia95me@126.com
 */

public class CityPickerPresenter extends BasePresenter<CityPickerContract.View,CityPickerContract.Model> implements CityPickerContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public CityPickerPresenter(CityPickerContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected CityPickerContract.Model createModel() {
        return new CommunityPickerModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestCommunitys(Params params) {
        ALog.d("requestCommunityList --------------");
        mRxManager.add(mModel.requestCommunitys(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(communitySelectBean -> {
                    ALog.d("requestCommunityList 2222222");
                    if (communitySelectBean.getOther().getCode() == 200) {
                        ALog.d("requestCommunityList 4444444");
                        getView().responseCommunitys(communitySelectBean.getData().getCommunities());
                    } else {
                        ALog.d("requestCommunityList 3333333");
                        getView().error(communitySelectBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
