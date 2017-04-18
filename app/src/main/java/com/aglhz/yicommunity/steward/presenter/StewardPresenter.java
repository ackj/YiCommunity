package com.aglhz.yicommunity.steward.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.abase.network.http.HttpClient;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.common.BeanCallback;
import com.aglhz.yicommunity.common.ServiceApi;
import com.aglhz.yicommunity.common.bean.IconBean;
import com.aglhz.yicommunity.common.bean.MyHourseBean;
import com.aglhz.yicommunity.steward.contract.StewardContract;
import com.aglhz.yicommunity.steward.model.StewardModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责管家模块Presenter层内容。
 */

public class StewardPresenter extends BasePresenter<StewardContract.View, StewardContract.Model> implements StewardContract.Presenter {
    private final String TAG = StewardPresenter.class.getSimpleName();

    public StewardPresenter(StewardContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected StewardContract.Model createModel() {
        return new StewardModel();
    }

    @Override
    public void start() {
        ALog.e("NeighbourPresenter::start");
//        mModel.start();


        getMyHouse();


    }

    private void getMyHouse() {
        Map params = BaseParams.getTokenMap();
        HttpClient.post(ServiceApi.authBdgs, params, new BeanCallback<MyHourseBean>() {
            @Override
            public void onError(String errMsg) {
                if (isViewAttached()) {
                    getView().error(new Exception());
                }
            }

            @Override
            public void onSuccess(MyHourseBean bean) {
                if (isViewAttached()) {
                    List<IconBean> listIcons = new ArrayList<IconBean>();
                    if (bean != null && !bean.getData().getAuthBuildings().isEmpty()) {
                        for (MyHourseBean.DataBean.AuthBuildingsBean authBuildingsBean : bean.getData().getAuthBuildings()) {
                            listIcons.add(new IconBean(R.drawable.ic_my_house_red_140px, authBuildingsBean.getB_name()));
                        }
                    }
                    listIcons.add(new IconBean(R.drawable.ic_add_house_red_140px, "添加房屋"));
                    getView().responseHouses(listIcons);
                }

            }
        });
    }


}