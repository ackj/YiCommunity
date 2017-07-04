package com.aglhz.yicommunity.main.services.view;

import android.content.Context;
import android.view.View;

import com.aglhz.abase.mvp.view.base.BaseController;
import com.aglhz.yicommunity.R;

/**
 * Created by leguang on 2017/7/4 0022.
 * Email：langmanleguang@qq.com
 */

public class RemarkItemController extends BaseController {

    public RemarkItemController(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        return View.inflate(mContext, R.layout.item_rv_remark, null);
    }

    public void initData() {
    }

}
