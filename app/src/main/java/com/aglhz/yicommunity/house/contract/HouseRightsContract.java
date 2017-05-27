package com.aglhz.yicommunity.house.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.HouseRightsBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 房屋模块所对应的各层对象应有的接口。
 */
public interface HouseRightsContract {

    interface View extends BaseContract.View {

        void responseRights(HouseRightsBean mHouseRights);

        void responseUpdateRights(BaseBean mBaseBean);

        void responseDelete(BaseBean mBaseBean);

    }

    interface Presenter extends BaseContract.Presenter {

        void requestRights(Params params);

        void requestUpdateRights(Params params);

        void requestDelete(Params params);

    }

    interface Model extends BaseContract.Model {

        Observable<HouseRightsBean> requestRights(Params params);

        Observable<BaseBean> requestUpdateRights(Params params);

        Observable<BaseBean> requestDelete(Params params);
    }
}