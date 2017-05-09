package com.aglhz.yicommunity.steward.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.ContactBean;
import com.aglhz.yicommunity.bean.IconBean;
import com.aglhz.yicommunity.bean.SipBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 管家模块所对应的各层对象应有的接口。
 */
public interface StewardContract {

    interface View extends BaseContract.View {

        void responseHouses(List<IconBean> listIcons);

        void responseContact(List<String> phoneList);

        void responseGetSip(SipBean mSipBean);

    }

    interface Presenter extends BaseContract.Presenter {
        void requestContact(String cmnt_c);

        void requestGetSip(String token);

    }

    interface Model extends BaseContract.Model {
        Observable<ContactBean> getContact(Params params);

    }
}