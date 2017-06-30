package com.aglhz.yicommunity.main.home.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BannerBean;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.ServicesClassifyListBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 管家模块所对应的各层对象应有的接口。
 */
public interface HomeContract {

    interface View extends BaseContract.View {
        void responseBanners(List<BannerBean.DataBean.AdvsBean> banners);

        void responseHomeNotices(List<String> notices);

        void responseOpenDoor();

        void responseServiceClassifyList(List<ServicesClassifyListBean.DataBean.ClassifyListBean> classifys);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestBanners();

        void requestHomeNotices();

        void requestOpenDoor();

        void requestServiceClassifyList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BannerBean> requestBanners();

        Single<List<String>> requestHomeNotices(Params params);

        Observable<BaseBean> requestOpenDoor(Params params);

        Observable<ServicesClassifyListBean> requestServiceClassifyList(Params params);

    }
}