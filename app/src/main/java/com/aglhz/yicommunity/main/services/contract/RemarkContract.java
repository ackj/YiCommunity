package com.aglhz.yicommunity.main.services.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.RemarkListBean;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 社区服务所对应的各层对象应有的接口。
 */
public interface RemarkContract {

    interface View extends BaseContract.View {
    }

    interface Presenter extends BaseContract.Presenter {
    }

    interface Model extends BaseContract.Model {
        Observable<RemarkListBean> requestRemarkList(Params params);
    }
}