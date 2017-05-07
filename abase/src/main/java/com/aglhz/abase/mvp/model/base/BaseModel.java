package com.aglhz.abase.mvp.model.base;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 所有Model类的基类，负责模型层的内容，包括数据获取和处理以及部分业务逻辑代码。
 */
public abstract class BaseModel {
    private final String TAG = BaseModel.class.getSimpleName();

    //生命周期结束，用于控制Model的资源释放
    public void clear() {

    }
}
