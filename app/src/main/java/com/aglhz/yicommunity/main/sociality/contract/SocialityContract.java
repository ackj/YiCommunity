package com.aglhz.yicommunity.main.sociality.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.CommunityBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/9/5 0005 17:21.
 * Email: liujia95me@126.com
 */

public interface SocialityContract {

    interface View extends BaseContract.View {
        void responseCommunitys(List<CommunityBean.DataBean.CommunityInfoListBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestCommunitys(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<CommunityBean> requestCommunitys(Params params);
    }
}
