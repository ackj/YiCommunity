package com.aglhz.yicommunity.about.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.about.contract.FeedbackContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class FeedbackModel extends BaseModel implements FeedbackContract.Model {
    private final String TAG = FeedbackModel.class.getSimpleName();


    @Override
    public Observable<BaseBean> submitFeedback(Params params) {
        return HttpHelper.getService(ApiService.class).feedback(ApiService.feedback,params.token, params.cmnt_c, params.des, params.contact)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void start(Object request) {

    }
}