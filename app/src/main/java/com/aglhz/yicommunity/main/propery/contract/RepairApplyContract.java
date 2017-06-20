package com.aglhz.yicommunity.main.propery.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.entity.bean.RepairApplyBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/8 0008 21:28.
 * Email: liujia95me@126.com
 */
public interface RepairApplyContract {

    interface View extends BaseContract.View {
        void responseRepairApplyList(List<RepairApplyBean.DataBean.RepairApplysBean> beans);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestRepairApplyList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<RepairApplyBean> getRepairApply(Params params);
    }

}
