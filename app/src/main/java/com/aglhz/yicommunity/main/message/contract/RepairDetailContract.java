package com.aglhz.yicommunity.main.message.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.RepairDetailBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/19 0019 09:38.
 * Email: liujia95me@126.com
 */

public interface RepairDetailContract {

    interface View extends BaseContract.View {
        void responseRepairDetail(RepairDetailBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestRepairDetail(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<RepairDetailBean> requestRepairDetail(Params params);
    }

}
