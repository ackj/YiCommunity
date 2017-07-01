package com.aglhz.yicommunity.main.mine.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.entity.bean.HouseRightsBean;
import com.aglhz.yicommunity.entity.bean.MyHousesBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/17 0017 16:03.
 * Email: liujia95me@126.com
 */

public interface MyHousesContract {
    interface View extends BaseContract.View {
        void responseHouses(List<MyHousesBean.DataBean.AuthBuildingsBean> beas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requsetMyHouse(Params params);
    }

    interface Model extends BaseContract.Model {
        Flowable<MyHousesBean> requsetMyHouse(Params params);
    }
}
