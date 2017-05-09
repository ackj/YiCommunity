package com.aglhz.yicommunity.picker.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/8 0008 11:33.
 * Email: liujia95me@126.com
 */

public interface CityPickerContract {

    interface Model extends BaseContract.Model{
        Observable<CommunitySelectBean> getCommunityList(Params params);
    }

    interface Presenter extends BaseContract.Presenter{
        void requestCommunityList();
    }

    interface View extends BaseContract.View{
        void responseCommunityList(List<CommunitySelectBean.DataBean.CommunitiesBean> bean);
    }
}
