package com.aglhz.yicommunity.properypay.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.PropertyPayBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/7 0007 21:40.
 * Email: liujia95me@126.com
 */

public interface PropertyPayContract {
    interface View extends BaseContract.View {
        void responsePropertyPay(PropertyPayBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestPropertyPay();
    }

    interface Model extends BaseContract.Model {
        Observable<PropertyPayBean> getPropertyPay(Params params);
    }
}
