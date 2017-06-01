package com.aglhz.yicommunity.main.mine.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.UnreadMessageBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 邻里模块所对应的各层对象应有的接口。
 */
public interface MineContract {

    interface View extends BaseContract.View {
        //退出登录成功
        void responseLogout(String message);

        void responseCache(String message);

        void responseUnreadMark(UnreadMessageBean bean);

    }

    interface Presenter extends BaseContract.Presenter {
        //退出登录
        void requestLogout(Params params);

        void requestCache();

        void requestClearCache();

        void requestUnreadMark(Params params);

    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestLogout(Params params);

        Observable<String> requestCache();

        Observable<String> requestClearCache();

        Observable<UnreadMessageBean> requestUnreadMark(Params params);

    }
}