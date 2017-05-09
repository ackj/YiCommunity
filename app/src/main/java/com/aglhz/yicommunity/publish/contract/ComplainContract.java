package com.aglhz.yicommunity.publish.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 邻里模块所对应的各层对象应有的接口。
 */
public interface ComplainContract {

    interface View extends BaseContract.View {
        void responseComplain(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestComplain(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> postCmplain(Params params);
    }
}