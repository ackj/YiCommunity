package com.aglhz.yicommunity.main.sociality.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.SocialityListBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 邻里模块所对应的各层对象应有的接口。
 */
public interface SocialityContract {

    interface View extends BaseContract.View {
        void responseSuccess(List<SocialityListBean.DataBean.MomentsListBean> datas);

        void removeSuccess(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestNeighbourList(Params params);

        void requestExchangeList(Params params);

        void requestCarpoolList(Params params);

        void requestMyNeihbourList(Params params);

        void requestMyExchangeList(Params params);

        void requestMyCarpoolList(Params params);

        void removeMyCarpool(Params params);

        void removeMyExchange(Params params);

        void removeMyNeighbour(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<SocialityListBean> requestNeighbourList(Params params);

        Observable<SocialityListBean> getExchangeList(Params params);

        Observable<SocialityListBean> getCarpoolList(Params params);

        Observable<SocialityListBean> getMyNeihbourList(Params params);

        Observable<SocialityListBean> getMyExchangeList(Params params);

        Observable<SocialityListBean> getMyCarpoolList(Params params);

        Observable<BaseBean> removeMyCarpool(Params params);

        Observable<BaseBean> removeMyExchange(Params params);

        Observable<BaseBean> removeMyNeighbour(Params params);

    }
}