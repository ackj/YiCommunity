package com.aglhz.yicommunity.home.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 管家模块所对应的各层对象应有的接口。
 */
public interface HomeContract {

    interface View extends BaseContract.View {
//        void responseBanner(List<IndexadvsBean.DataBean.AdvsBean> bean);

    }

    interface Presenter extends BaseContract.Presenter {
        void requestBanner();

    }

    interface Model extends BaseContract.Model {
    }
}