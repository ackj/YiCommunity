package com.aglhz.yicommunity.main.door.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.DoorListBean;
import com.aglhz.yicommunity.entity.bean.PasswordBean;

import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 门禁模块所对应的各层对象应有的接口。
 */
public interface PasswordOpenDoorContract {

    interface View extends BaseContract.View {
        //刷新界面，响应社区返回的数据
        void responseDoors(DoorListBean mDoorListBean);

        void responsePassword(PasswordBean mPasswordBean);

    }

    interface Presenter extends BaseContract.Presenter {
        //请求社区列表数据
        void requestDoors(Params params);

        void requestPassword(Params params);
    }

    interface Model extends BaseContract.Model {

        Observable<DoorListBean> requestDoors(Params params);

        Observable<PasswordBean> getPassword(Params params);
    }
}