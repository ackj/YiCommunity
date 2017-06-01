package com.aglhz.yicommunity.main.smarthome.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.GoodsBean;
import com.aglhz.yicommunity.bean.SubCategoryBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/5/22 0022 09:06.
 * Email: liujia95me@126.com
 */

public interface SmartHomeMallContract {
    interface View extends BaseContract.View {
        void responseSubCategoryList(List<SubCategoryBean.DataBean> datas);

        void responseGoodsList(List<GoodsBean.DataBean> datas);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestSubCategoryList(Params params);

        void requestGoodsList(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<SubCategoryBean> requestSubCategoryList(Params params);

        Observable<GoodsBean> requestGoodsList(Params params);
    }
}
