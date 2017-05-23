package com.aglhz.yicommunity.park.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/23 0023 16:11.
 * Email: liujia95me@126.com
 */

public interface MonthCardPayContract {

    interface View extends BaseContract.View {
    }

    interface Presenter extends BaseContract.Presenter {
        void postMothCarPay(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> postMothCarPay(Params params);
    }

}
