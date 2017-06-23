package com.aglhz.yicommunity.login.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/6/23 0023 19:18.
 * Email: liujia95me@126.com
 */

public interface ResetPasswordContract {

    interface View extends BaseContract.View {
        void reponseResetSuccess(BaseBean baseBean);
        void responseVerfyCodeSuccess(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestReset(Params params);
        void requestVerifyCode(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestReset(Params params);
        Observable<BaseBean> requestVerifyCode(Params params);
    }

}
