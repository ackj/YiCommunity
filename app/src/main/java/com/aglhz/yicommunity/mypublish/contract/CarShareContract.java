package com.aglhz.yicommunity.mypublish.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 邻里模块所对应的各层对象应有的接口。
 */
public interface CarShareContract {

    interface View extends BaseContract.View {
//        void responseData(List<CarpoolServiceListBean.DataBean.CarpoolListBean> list, int page);

        void addLoadingFooter();

        void addErrorFooter();

        void empty();

        void loadComplete();
    }

    interface Presenter extends BaseContract.Presenter {
        void requestData(int page);
    }

    interface Model extends BaseContract.Model {
    }
}