package com.aglhz.yicommunity.main.publish.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/9 0009 10:30.
 * Email: liujia95me@126.com
 */

public interface PublishContract {

    interface View extends BaseContract.View {
        void responseSuccess(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void post(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> post(Params params);
    }

}
