package com.aglhz.yicommunity.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.main.view.MainFragment;
import com.sipphone.sdk.SipCoreManager;
import com.sipphone.sdk.SipCorePreferences;
import com.sipphone.sdk.access.WebApiConstants;
import com.sipphone.sdk.access.WebReponse;
import com.sipphone.sdk.access.WebUserApi;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreListenerBase;
import org.linphone.core.LinphoneProxyConfig;

public class TestActivity extends BaseActivity {
    private LinphoneCoreListenerBase mListener;
    private WebUserApi mWebUserApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, new MaterialStyleFragment());
        }
    }

    private void setOutgoingListener() {
        LinphoneCoreListenerBase mListener = new LinphoneCoreListenerBase() {
            @Override
            public void registrationState(LinphoneCore lc, LinphoneProxyConfig proxy,
                                          LinphoneCore.RegistrationState state, String smessage) {
                ALog.e("registrationState：" + state.toString() + "：：" + smessage);

            }

            @Override
            public void callState(LinphoneCore lc, LinphoneCall call, LinphoneCall.State state, String message) {

                ALog.e("callState：" + state.toString() + "：：" + message);


                if (state == LinphoneCall.State.OutgoingInit ||
                        state == LinphoneCall.State.OutgoingProgress) {
                    // 启动CallOutgoingActivity
                    ALog.e("进来了");

//                    startActivity(new Intent(MainActivity.this, CallActivity.class));

//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            ALog.e("启动CallActivity");
//
//                            startActivity(new Intent(MainActivity.this, CallActivity.class));
//
//                        }
//                    }, 4000);

                }
                if (LinphoneCall.State.CallEnd == state) {    // 当前的Call状态为End
//                    RxBus.get().post(new HardWareCallEnd());
                }
            }
        };

        // 添加监听器对象到Native层的LinhoneCore监听器对象接口
        LinphoneCore lc = SipCoreManager.getLcIfManagerNotDestroyedOrNull();
        if (lc != null) {
            lc.addListener(mListener);
            SipCorePreferences.instance().setAccountOutboundProxyEnabled(0, true);
        }
    }

    public void callOutgoing() {
        ALog.e("callOutgoing");
//        setOutgoingListener();

//        final SipCoreManager.AddressType address = new AddressText(this, null);
//        address.setDisplayedName("");
//        address.setText("sip:D6-31-1@member");
//        if (SipCoreManager.isInstanciated()) {
//            ALog.e("准备拨号…………");
//
//            SipCoreManager.getInstance().newOutgoingCall(address);
//            return;
//        } else {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    ALog.e("递归进来了");
//
//                    callOutgoing();
//                }
//            }, 4000);
//        }
    }

    //注册来电监听
    private void regiestIncomingReceived() {
        mListener = new LinphoneCoreListenerBase() {
            @Override
            public void registrationState(LinphoneCore lc, LinphoneProxyConfig proxy,
                                          LinphoneCore.RegistrationState state, String smessage) {
            }

            @Override
            public void callState(LinphoneCore lc, LinphoneCall call, LinphoneCall.State state, String message) {
                Log.i("test_test", state.toString());
                if (state == LinphoneCall.State.IncomingReceived) {    // 启动CallIncomingActivity
//                    startActivity(new Intent(HomeActivity.this, CallIncomingActivity.class));
//                    Toast.makeText(MainActivity.this, "ddddddddd", Toast.LENGTH_SHORT).show();
                    answer(call);
                }
            }
        };

        // 添加监听器对象到Native层的LinhoneCore监听器对象接口
        new Thread() {
            public void run() {
                while (true) {
                    LinphoneCore lc = SipCoreManager.getLcIfManagerNotDestroyedOrNull();
                    if (lc != null) {
                        lc.addListener(mListener);
                        return;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
    }

    private void answer(LinphoneCall mCall) {

        LinphoneCallParams params = SipCoreManager.getLc().createCallParams(mCall);

        if (!SipCoreManager.getInstance().acceptCallWithParams(mCall, params)) {
            // the above method takes care of Samsung Galaxy S
        } else {
//			if (!SPhoneHome.isInstanciated()) {
//				return;
//			}
//            startActivity(new Intent(HomeActivity.this, CallActivity.class));
            // 根据远端是否提供视频参数信息，选择启动视频通话或音频通话
            final LinphoneCallParams remoteParams = mCall.getRemoteParams();
            if (remoteParams != null && remoteParams.getVideoEnabled() &&
                    SipCorePreferences.instance().shouldAutomaticallyAcceptVideoRequests()) {
                // 启动视频通话界面
//				SPhoneHome.instance().startVideoActivity(mCall);
//                startActivity(new Intent(MainActivity.this, CallActivity.class));
//                startActivity(new Intent(HomeActivity.this, CallActivity.class));
            }
        }
    }
}

