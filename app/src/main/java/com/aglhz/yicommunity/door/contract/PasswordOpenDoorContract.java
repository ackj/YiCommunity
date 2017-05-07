package com.aglhz.yicommunity.door.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.bean.PasswordBean;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 门禁模块所对应的各层对象应有的接口。
 */
public interface PasswordOpenDoorContract {

    interface View extends BaseContract.View {
        void responsePassword(PasswordBean mPasswordBean);

    }

    interface Presenter extends BaseContract.Presenter {
        void requestPassword(String token, String dir);


    }

    interface Model extends BaseContract.Model {
    }
}