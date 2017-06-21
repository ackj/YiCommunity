package com.aglhz.yicommunity.main.propery.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.entity.bean.NoticeBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/9 0009 22:33.
 * Email: liujia95me@126.com
 */

public interface NoticeListContract {

    interface View extends BaseContract.View {
        void responseNotices(List<NoticeBean.DataBean.NoticeListBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestNotices(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<NoticeBean> requestNotices(Params params);
    }
}
