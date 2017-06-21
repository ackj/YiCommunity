package com.aglhz.yicommunity.main.park.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.entity.bean.ParkOrderBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/6/1 0001 09:17.
 * Email: liujia95me@126.com
 */

public interface ParkOrderContract {

    interface View extends BaseContract.View {
        void responseParkOrder(ParkOrderBean.DataBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestParkOrder(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<ParkOrderBean> requestParkOrder(Params params);
    }

}
