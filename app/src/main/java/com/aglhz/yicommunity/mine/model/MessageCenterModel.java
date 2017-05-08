package com.aglhz.yicommunity.mine.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.MessageCenterBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.mine.contract.MessageCenterContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class MessageCenterModel extends BaseModel implements MessageCenterContract.Model {
    private final String TAG = MessageCenterModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }


    @Override
    public Observable<MessageCenterBean> requestMessages(Params params) {
        return HttpHelper.getService(ApiService.class).requestMessages(params.token)
                .subscribeOn(Schedulers.io());
    }
}