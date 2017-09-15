package com.aglhz.yicommunity.main.door.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Author: LiuJia on 2017/9/15 0015 11:10.
 * Email: liujia95me@126.com
 */

public interface FamilyPhoneContract {
    interface View extends BaseContract.View {
        void responseSetFamilyPhone(BaseBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestSetFamilyPhone(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestSetFamilyPhone(Params params);
    }
}
