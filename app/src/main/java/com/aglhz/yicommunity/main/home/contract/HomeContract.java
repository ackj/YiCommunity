package com.aglhz.yicommunity.main.home.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BannerBean;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.FirstLevelBean;
import com.aglhz.yicommunity.entity.bean.OneKeyDoorBean;
import com.aglhz.yicommunity.entity.bean.ServicesTypesBean;
import com.aglhz.yicommunity.entity.bean.SubCategoryBean;

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

        void responseServiceClassifyList(List<ServicesTypesBean.DataBean.ClassifyListBean> classifys);

        void responseOneKeyOpenDoorDeviceList(List<OneKeyDoorBean.DataBean.ItemListBean> doorList);

        //智慧商城一级列表
        void responseFirstLevel(List<FirstLevelBean.DataBean> datas);

        //智慧商城二级列表
        void responseSubCategoryList(List<SubCategoryBean.DataBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestBanners(Params params);

        void requestHomeNotices(Params params);

        void requestOpenDoor();

        void requestServiceTypes(Params params);

        void requestOneKeyOpenDoorDeviceList(Params params);

        //智慧商城一级列表
        void requestFirstLevel(Params params);

        //智慧商城二级列表
        void requestSubCategoryList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BannerBean> requestBanners(Params params);

        Single<List<String>> requestHomeNotices(Params params);

        Observable<BaseBean> requestOpenDoor(Params params);

        Observable<ServicesTypesBean> requestServiceTypes(Params params);

        Observable<OneKeyDoorBean> requestOneKeyOpenDoorDeviceList(Params params);

        //智慧商城一级列表
        Observable<FirstLevelBean> requestFirstLevel(Params params);

        //智慧商城二级列表
        Observable<SubCategoryBean> requestSubCategoryList(Params params);
    }
}