package com.aglhz.yicommunity.main.mine.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.UserDataBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 邻里模块所对应的各层对象应有的接口。
 */
public interface UserDataContract {

    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter {
        void requestChangePortrait(Params params);

        void requestUpdateUserData(Params params);

        void requestUpdatePassword(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<UserDataBean> requestChangePortrait(Params params);

        Observable<BaseBean> requestUpdateUserData(Params params);

        Observable<BaseBean> requestUpdatePassword(Params params);

    }
}