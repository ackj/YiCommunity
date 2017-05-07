package com.aglhz.yicommunity.steward.model;


import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.yicommunity.steward.contract.StewardContract;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责管家模块的Model层内容。
 */

public class StewardModel extends BaseModel implements StewardContract.Model {
    private final String TAG = StewardModel.class.getSimpleName();

    @Override
    public void start(Object request) {
        ALog.e("start");
    }
}