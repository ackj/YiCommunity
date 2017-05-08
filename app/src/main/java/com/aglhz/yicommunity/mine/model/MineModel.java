package com.aglhz.yicommunity.mine.model;

import com.aglhz.abase.cache.CacheManager;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.mine.contract.MineContract;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

import static com.aglhz.abase.cache.CacheManager.getTotalCacheSize;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class MineModel extends BaseModel implements MineContract.Model {
    private final String TAG = MineModel.class.getSimpleName();


    @Override
    public Observable<BaseBean> requestLogout(Params params) {
        return HttpHelper.getService(ApiService.class).logout(params.token)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<String> requestCache() {
        return Observable.create((ObservableOnSubscribe<String>) e -> {
                    try {
                        e.onNext(CacheManager.getTotalCacheSize(BaseApplication.mContext));
                    } catch (Exception ex) {
                        e.onError(ex);
                    }
                    e.onComplete();
                }
        ).subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<String> requestClearCache() {
        return Observable.create((ObservableOnSubscribe<String>) e -> {
                    CacheManager.clearAllCache(BaseApplication.mContext);
                    try {
                        e.onNext(CacheManager.getTotalCacheSize(BaseApplication.mContext));
                    } catch (Exception ex) {
                        e.onError(ex);
                    }
                    e.onComplete();
                }
        ).subscribeOn(Schedulers.computation());
    }

    @Override
    public void start(Object request) {

    }
}