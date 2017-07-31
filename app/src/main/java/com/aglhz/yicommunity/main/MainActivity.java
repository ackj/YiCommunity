package com.aglhz.yicommunity.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.DoorManager;
import com.aglhz.yicommunity.common.ShakeHelper;
import com.aglhz.yicommunity.main.door.call.CallActivity;
import com.aglhz.yicommunity.main.view.MainFragment;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore;

import cn.itsite.multiselector.MultiSelectorDialog;
import cn.itsite.multiselector.MultiSelectorInterface;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 主页模块的容器，负责装载一个MainFragment和一些需要在这个容器里初始化的东西。
 */

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Handler handler;
    private ShakeHelper shakeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, MainFragment.newInstance());
        }
        handler = new Handler();
        setCallListener();

//        shakeHelper = new ShakeHelper(this);
//        shakeHelper.Start();
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }

    public void setCallListener() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DoorManager.getInstance().setCallListener(new DoorManager.LinphoneCallBack() {
                    @Override
                    public void callState(LinphoneCore lc, LinphoneCall call, LinphoneCall.State state, String message) {
                        if (state == LinphoneCall.State.OutgoingInit || state == LinphoneCall.State.OutgoingProgress) {
                            startActivity(new Intent(MainActivity.this, CallActivity.class));
                        }
                    }
                });
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (shakeHelper != null) {
            shakeHelper.Stop();
        }
    }
}
