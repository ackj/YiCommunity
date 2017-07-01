package com.aglhz.yicommunity.main.message.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.MessageCenterBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 邻里模块所对应的各层对象应有的接口。
 */
public interface MessageCenterContract {

    interface View extends BaseContract.View {
        void responseReadSuccess(BaseBean bean);

        void responseDeleteSuccess(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestMessageRead(Params params);

        void requestDeleteMessage(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<MessageCenterBean> requestMessages(Params params);

        Observable<BaseBean> requestMessageRead(Params params);

        Observable<BaseBean> requsetDeleteMessage(Params params);
    }
}