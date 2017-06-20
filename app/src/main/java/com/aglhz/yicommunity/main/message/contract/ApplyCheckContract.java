package com.aglhz.yicommunity.main.message.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/26 0026 16:57.
 * Email: liujia95me@126.com
 */

public interface ApplyCheckContract {

    interface View extends BaseContract.View {
        void responseApplySuccess(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestApplyCheck(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestApplyCheck(Params params);
    }

}
