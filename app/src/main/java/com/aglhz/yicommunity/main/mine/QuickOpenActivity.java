package com.aglhz.yicommunity.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.aglhz.abase.cache.SPCache;
import com.aglhz.abase.common.RxManager;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.aglhz.yicommunity.common.UserHelper.clear;


/**
 * Created by leguang on 2017/4/29 0029.
 * Email：langmanleguang@qq.com
 */

public class QuickOpenActivity extends BaseActivity {
    private ViewGroup rootView;
    private RxManager rxManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = (ViewGroup) findViewById(android.R.id.content).getRootView();
        UserHelper.init();

        rxManager = new RxManager();
        rxManager.add(HttpHelper.getService(ApiService.class).requestOpenDoor(ApiService.requestOpenDoor, UserHelper.token, UserHelper.dir)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    exit();
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        DialogHelper.successSnackbar(rootView, "开门成功，欢迎回家，我的主人！");
                    } else {
                        DialogHelper.successSnackbar(rootView, baseBean.getOther().getMessage());
                    }
                }, throwable -> {
                    exit();
                    DialogHelper.errorSnackbar(rootView, "网络异常，请重试！");
                })
        );


        /**
         * 考虑重新登录问题，因为当token失效后要登录。注意登录最好搞记住密码，用户只要直接点击登录就最好。不过密码也最好有个失效，这样ACache框架就有价值。
         */

        /**
         * 考虑失败后，有一个可以重试的按钮。否则就尴尬了，
         * 得不停的等下面的1500ms。当然这个也有点鸡肋，如果能做到在桌面顶部弹框提示的话，就不需要Activity做支撑了。
         * 所以也不需要有一个界面了，不然就违背了快速这一初衷，本来打开失败就应该不停的点图标。
         */
    }

    private void exit() {
        rootView.postDelayed(this::finish, 2000);
    }

    @Override
    protected void onDestroy() {
        if (rxManager != null) {
            rxManager.clear();
        }
        super.onDestroy();
    }
}
