package com.aglhz.yicommunity.main.park.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/27 0027 16:54.
 * Email: liujia95me@126.com
 */

public interface PublishOwnerCardContract  {

    interface View extends BaseContract.View {
        void responseSuccess(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {

        void requestSubmitOwnerCard(Params params);

        void requestModifyOwnerCard(Params params);

    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestSubmitOwnerCard(Params params);

        Observable<BaseBean> requestModifyOwnerCard(Params params);
    }

}
