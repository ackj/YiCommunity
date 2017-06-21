package com.aglhz.yicommunity.main.smarthome.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.entity.bean.FirstLevelBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/22 0022 10:22.
 * Email: liujia95me@126.com
 */

public interface GoodsCategoryContract {

    interface View extends BaseContract.View {
        void responseFirstLevel(List<FirstLevelBean.DataBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestFirstLevel(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<FirstLevelBean> requestFirstLevel(Params params);
    }

}
