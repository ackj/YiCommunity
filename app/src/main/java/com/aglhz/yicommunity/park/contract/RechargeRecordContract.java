package com.aglhz.yicommunity.park.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.MonthCardBillListBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/27 0027 15:31.
 * Email: liujia95me@126.com
 */

public interface RechargeRecordContract {

    interface View extends BaseContract.View {
        void responseRechargeRecord(List<MonthCardBillListBean.DataBean.MonthCardBillBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestMonthCardBillList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<MonthCardBillListBean> requsetMonthCardBillList(Params params);
    }

}
