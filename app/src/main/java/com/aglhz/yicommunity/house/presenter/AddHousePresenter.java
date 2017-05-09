package com.aglhz.yicommunity.house.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.abase.widget.selector.ISelectAble;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.BuildingBean;
import com.aglhz.yicommunity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.bean.FloorBean;
import com.aglhz.yicommunity.bean.RoomBean;
import com.aglhz.yicommunity.bean.UnitBean;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ServiceApi;
import com.aglhz.yicommunity.house.contract.AddHouseContract;
import com.aglhz.yicommunity.house.model.AddHouseModel;

import org.apache.http.client.HttpClient;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class AddHousePresenter extends BasePresenter<AddHouseContract.View, AddHouseContract.Model> implements AddHouseContract.Presenter {
    private final String TAG = AddHousePresenter.class.getSimpleName();

    public AddHousePresenter(AddHouseContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddHouseContract.Model createModel() {
        return new AddHouseModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void requestCommunitys(Params params) {
        mRxManager.add(mModel.requestCommunitys(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().start(baseBean.getOther().getMessage());
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestBuildings(Params params) {
        mRxManager.add(mModel.requestBuildings(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().start(baseBean.getOther().getMessage());
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestUnits(Params params) {
        mRxManager.add(mModel.requestUnits(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().start(baseBean.getOther().getMessage());
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestFloors(Params params) {
        mRxManager.add(mModel.requestFloors(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().start(baseBean.getOther().getMessage());
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestRooms(Params params) {
        mRxManager.add(mModel.requestRooms(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        getView().start(baseBean.getOther().getMessage());
                    } else {
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error)
        );
    }

    @Override
    public void requestApply(Params params) {

    }
}