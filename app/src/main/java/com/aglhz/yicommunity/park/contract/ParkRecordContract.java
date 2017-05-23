package com.aglhz.yicommunity.park.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.ParkRecordListBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/23 0023 09:18.
 * Email: liujia95me@126.com
 */

public interface ParkRecordContract {

    interface View extends BaseContract.View {
        void responseParkRecord(List<ParkRecordListBean.PackRecordBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestParkReocrd(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<ParkRecordListBean> requestParkRecord(Params params);
    }

}
