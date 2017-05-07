package com.aglhz.yicommunity.door.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.bean.SetQuickOpenBean;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 门禁模块所对应的各层对象应有的接口。
 */
public interface DoorContract {

    interface View extends BaseContract.View {
        //刷新界面，响应社区返回的数据
        void responseDoorList(DoorListBean mDoorListBean);

        //刷新界面，响应楼栋返回的数据
        void responseSave(SetQuickOpenBean mSetQuickOpenBean);

    }

    interface Presenter extends BaseContract.Presenter {
        //请求社区列表数据
        void requestDoorList(String token);

        //请求楼栋列表数据
        void requestSave(String token, String directory, String deviceName);

    }

    interface Model extends BaseContract.Model {
    }
}