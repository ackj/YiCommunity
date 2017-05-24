package com.aglhz.yicommunity.park.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.CarCardListBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 *
 * 邻里模块所对应的各层对象应有的接口。
 */
public interface CarCardContract {

    interface View extends BaseContract.View {
        void responseCarCardList(List<CarCardListBean.DataBean.CardListBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestCarCardList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<CarCardListBean> requestCarCardList(Params params);
    }
}