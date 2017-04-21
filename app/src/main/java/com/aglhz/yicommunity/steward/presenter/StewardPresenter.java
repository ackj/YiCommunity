package com.aglhz.yicommunity.steward.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.abase.network.http.HttpClient;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.common.BeanCallback;
import com.aglhz.yicommunity.common.ServiceApi;
import com.aglhz.yicommunity.common.bean.BaseBean;
import com.aglhz.yicommunity.common.bean.ContactBean;
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
        getMyHouse();
        requestContact("KBSJ-agl-00005");
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
                    getView().responseHouses(listIcons);
                }

            }
        });
    }


    @Override
    public void requestContact(String cmnt_c) {
        Map params = BaseParams.getTokenMap();
        params.put("cmnt_c", cmnt_c);
        HttpClient.post(ServiceApi.CONTACT, params, new BeanCallback<ContactBean>() {
            @Override
            public void onError(String errMsg) {
                if (isViewAttached()) {
                    getView().error(new Exception());
                }
            }

            @Override
            public void onSuccess(ContactBean bean) {
                if (isViewAttached()) {
                    List<String> listPhone = new ArrayList();
                    if (!TextUtils.isEmpty(bean.getData().getTelephoneNo())) {
                        listPhone.add("座机：" + bean.getData().getTelephoneNo());
                    }
                    if (!TextUtils.isEmpty(bean.getData().getMobileNo())) {
                        listPhone.add("手机：" + bean.getData().getMobileNo());
                    }
                    getView().responseContact(listPhone);
                }
            }
        });
    }
}