package com.aglhz.yicommunity.steward.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.ContactBean;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.bean.IconBean;
import com.aglhz.yicommunity.bean.MyHousesBean;
import com.aglhz.yicommunity.bean.SipBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 管家模块所对应的各层对象应有的接口。
 */
public interface StewardContract {

    interface View extends BaseContract.View {

        void responseHouses(List<IconBean> listIcons);

        void responseContact(String[] arrayPhones);

        void responseDoors(DoorListBean mDoorListBean);

    }

    interface Presenter extends BaseContract.Presenter {
        void requestContact(Params params);

        void requestDoors(Params params);

    }

    interface Model extends BaseContract.Model {
        Observable<ContactBean> requestContact(Params params);

        Single<List<IconBean>> requestHouses(Params params);

        Observable<DoorListBean> requestDoors(Params params);
    }
}