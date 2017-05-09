package com.aglhz.yicommunity.house.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.HouseRightsBean;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 房屋模块所对应的各层对象应有的接口。
 */
public interface HouseRightsContract {

    interface View extends BaseContract.View {
        void responseRights(HouseRightsBean mHouseRights);

        void responseUpdateRights(BaseBean mBaseBean);

        void responseDelete(BaseBean mBaseBean);

    }

    interface Presenter extends BaseContract.Presenter {
        void requestRights(String token, String fid);

        void requestUpdateRights(String url, String mfid, String fid, String picode, int status);

        void requestDelete(String token, String fid, String mfid);

    }

    interface Model extends BaseContract.Model {
    }
}