package com.aglhz.yicommunity.main.door.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.OpenDoorRecordBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 门禁模块所对应的各层对象应有的接口。
 */
public interface OpenDoorRecordContract {

    interface View extends BaseContract.View {
        void responseRecord(List<OpenDoorRecordBean.DataBean> listRecord);

    }

    interface Presenter extends BaseContract.Presenter {
        void requestRecord(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<OpenDoorRecordBean> getOpenDoorRecord(Params params);
    }
}