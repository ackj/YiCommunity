package com.aglhz.yicommunity.mine.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.MyHousesBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Author: LiuJia on 2017/5/17 0017 16:03.
 * Email: liujia95me@126.com
 */

public interface MineHouseContract {
    interface View extends BaseContract.View {
        void responseHouseList(List<MyHousesBean.DataBean.AuthBuildingsBean> beas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requsetMyHouse(Params params);
    }

    interface Model extends BaseContract.Model {
        Flowable<MyHousesBean> requsetMyHouse(Params params);
    }
}
