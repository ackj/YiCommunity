package com.aglhz.yicommunity.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.DoorManager;
import com.aglhz.yicommunity.main.door.call.CallActivity;
import com.aglhz.yicommunity.main.view.MainFragment;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 主页模块的容器，负责装载一个MainFragment和一些需要在这个容器里初始化的东西。
 */

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, MainFragment.newInstance());
        }
        handler = new Handler();
        setCallListener();

    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }

    public void setCallListener() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DoorManager.getInstance().addCallListener(new DoorManager.LinphoneCallBack() {
                    @Override
                    public void callState(LinphoneCore lc, LinphoneCall call, LinphoneCall.State state, String message) {
                        ALog.e("state-->" + state + "-----" + "message-->" + message);


                        String tenantCode = call.getRemoteParams().getCustomHeader(
                                "X-TenantCode");
                        String deviceNumber = call.getRemoteParams().getCustomHeader(
                                "X-DeviceNumber");
                        String callPicture = call.getRemoteParams().getCustomHeader(
                                "X-CallPicture");

                        ALog.e(tenantCode);
                        ALog.e(deviceNumber);
                        ALog.e(callPicture);

                        if (state == LinphoneCall.State.OutgoingInit || state == LinphoneCall.State.OutgoingProgress) {
                            startActivity(new Intent(MainActivity.this, CallActivity.class));
                        }
                    }
                });
            }
        }, 3000);
    }
}
