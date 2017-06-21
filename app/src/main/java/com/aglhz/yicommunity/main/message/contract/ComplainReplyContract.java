package com.aglhz.yicommunity.main.message.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.entity.bean.ComplainReplyBean;
import com.aglhz.yicommunity.common.Params;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/18 0018 18:00.
 * Email: liujia95me@126.com
 */

public interface ComplainReplyContract {

    interface View extends BaseContract.View {
    }

    interface Presenter extends BaseContract.Presenter {
    }

    interface Model extends BaseContract.Model {
        Observable<ComplainReplyBean> requestComplainReplies(Params params);
    }
}
