package com.aglhz.yicommunity.main.picker.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.ParkSelectBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/23 0023 14:54.
 * Email: liujia95me@126.com
 */

public interface ParkPickerContract {

    interface View extends BaseContract.View {
        void responsePark(List<ParkSelectBean.DataBean.ParkPlaceListBean> bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestParkList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<ParkSelectBean> requestParkList(Params params);
    }
}
