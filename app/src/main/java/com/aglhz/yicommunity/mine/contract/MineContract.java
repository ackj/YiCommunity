package com.aglhz.yicommunity.mine.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 邻里模块所对应的各层对象应有的接口。
 */
public interface MineContract {

    interface View extends BaseContract.View {
        //退出登录成功
        void logoutSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        //退出登录
        void logout();
    }

    interface Model extends BaseContract.Model {
    }
}