package com.aglhz.yicommunity.main.publish.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.luban.Luban;
import com.aglhz.yicommunity.main.publish.contract.PublishContract;
import com.aglhz.yicommunity.main.publish.view.RepairFragment;
import com.aglhz.yicommunity.main.publish.model.RepairModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: LiuJia on 2017/5/9 0009 10:36.
 * Email: liujia95me@126.com
 */

public class RepairPresenter extends BasePresenter<PublishContract.View, PublishContract.Model> implements PublishContract.Presenter {

    private static final String TAG = RepairPresenter.class.getSimpleName();

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public RepairPresenter(PublishContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected PublishContract.Model createModel() {
        return new RepairModel();
    }

    @Override
    public void start(Object request) {

    }

    //请求我的房屋
    public void requestMyhouse(Params params) {
        mRxManager.add(((RepairModel) mModel).requestHouses(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(beanList -> {
                    if (isViewAttached ()){
                        ((RepairFragment) getView()).responseMyHouse(beanList);
                    }
                }, this::error));
    }

    public void requestRepairTypes(Params params) {
        mRxManager.add(((RepairModel) mModel).requestRepairTypes(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(beanList -> {
                    if (isViewAttached()) {
                        ((RepairFragment) getView()).responseRepairTypes(beanList.getData().getTypes());
                    }
                }, this::error));

    }

    @Override
    public void requestSubmit(Params params) {
        if (params.type == 1) {
            compress(params);
        } else {
            beginPost(params);
        }
    }

    public void compress(Params params) {
        for (int i = 0; i < params.files.size(); i++) {
            ALog.d(TAG, params.files.get(i).getAbsoluteFile() + " --- length----" + params.files.get(i).length());
        }
        Luban.get(App.mContext)
                .load(params.files)
                .putGear(Luban.THIRD_GEAR)
                .asList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        files -> {
                            ALog.e(Thread.currentThread().getName());
                            for (int i = 0; i < files.size(); i++) {
                                ALog.d(TAG, files.get(i).getAbsoluteFile() + " --- length----" + files.get(i).length());
                            }
                            params.files = files;
                            beginPost(params);
                        });
    }

    private void beginPost(Params params) {
        mRxManager.add(mModel.requestSubmit(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        ALog.e(TAG, "上传成功了！！");
                        getView().responseSuccess(baseBean);
                    } else {
                        ALog.e(TAG, "上传失败了！！");
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
