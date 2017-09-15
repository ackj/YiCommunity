package com.aglhz.abase.mvp.model.base;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.contract.base.BaseContract;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 所有Model类的基类，负责模型层的内容，包括数据获取和处理以及部分业务逻辑代码。
 */
public abstract class BaseModel implements BaseContract.Model {
    private final String TAG = BaseModel.class.getSimpleName();

    /**
     * 生命周期开始。
     */
    @Override
    public void start(Object request) {
        ALog.e(TAG, "start");
    }

    /**
     * 生命周期结束，用于控制Model的资源释放。
     */
    @Override
    public void clear() {

    }
}
