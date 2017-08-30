package com.aglhz.yicommunity.main.door.call;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import com.aglhz.abase.common.RxManager;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.UserHelper;
import com.sipphone.sdk.BluetoothManager;
import com.sipphone.sdk.SipCoreManager;
import com.sipphone.sdk.SipCorePreferences;
import com.sipphone.sdk.SipCoreUtils;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreListenerBase;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CallActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "CallActivity";

    private ImageView micro;
    private ImageView unlock;
    private View mBottomView;
    private CallVideoFragment videoCallFragment;
    private CallAudioFragment audioCallFragment;
    private boolean isSpeakerEnabled = false, isMicMuted = false, isTransferAllowed, isAnimationDisabled;
    private boolean isVideoCallPaused = false;
    private int cameraNumber;
    private LinphoneCoreListenerBase mListener;
    private LinphoneCall mCall;
    private RxManager rxManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_call);

        if (!BluetoothManager.getInstance().isBluetoothHeadsetAvailable()) {    // true
            BluetoothManager.getInstance().initBluetooth();
        }

        isAnimationDisabled = false;
        // 设备摄像头的个数，通常由前置和后置两个摄像头
        cameraNumber = AndroidCameraConfiguration.retrieveCameras().length;
        // 构建通话过程中Native层LinphoneCore状态监听器对象，这里子关注Call的状态及加密状态是否改变
        // 因此，这里只实现了callState和callEncryptionChanged接口
        mListener = new LinphoneCoreListenerBase() {
            @Override
            public void callState(LinphoneCore lc, LinphoneCall call, State state, String message) {
                ALog.e(TAG,  state.toString());

                if (state == State.CallEnd || state == State.Error || state == State.CallReleased) {
                    finish();
                }
            }

            @Override
            public void callEncryptionChanged(LinphoneCore lc, LinphoneCall call, boolean encrypted,
                                              String authenticationToken) {
            }
        };

        LinphoneCore lc = SipCoreManager.getLcIfManagerNotDestroyedOrNull();
        if (lc != null) {    // 添加CALL状态监听器对象到Native层的LinphoneCore管理
            lc.addListener(mListener);
        }

        mBottomView = findViewById(R.id.menu);

        micro = (ImageView) findViewById(R.id.micro);
//        unlock = (ImageView) findViewById(R.id.unlock);

        findViewById(R.id.hang_up).setOnClickListener(this);
        findViewById(R.id.switchCamera).setOnClickListener(this);
        findViewById(R.id.accept_call).setOnClickListener(this);
        findViewById(R.id.video).setOnClickListener(this);
        findViewById(R.id.iv_open_door).setOnClickListener(this);


        micro.setOnClickListener(this);

        if (findViewById(R.id.fragmentContainer) != null) {

            if (SipCoreManager.getLc().getCallsNb() > 0) {    // 检查当前是否有Call, > 0
                // 当前页面显示第一个Call的状态
                LinphoneCall call = SipCoreManager.getLc().getCalls()[0];

                if (SipCoreUtils.isCallEstablished(call)) {    // 检查是否是已连接的Call对象
                    // 根据当前Call的状态，刷新UI界面信息
                }
            }

            if (savedInstanceState != null) {    // 恢复CallActiviy状态，比如由CallActivity切换到其他的界面
                // Fragment already created, no need to create it again (else it will generate a memory leak with duplicated fragments)
                isSpeakerEnabled = savedInstanceState.getBoolean("Speaker");
                isMicMuted = savedInstanceState.getBoolean("Mic");
                isVideoCallPaused = savedInstanceState.getBoolean("VideoCallPaused");
                return;
            }
            Fragment callFragment;
            // 检查当前Call的视频功能是否启用
            if (true) {    // 视频通话
                // 添加Video Fragment到Container
                callFragment = new CallVideoFragment();
                videoCallFragment = (CallVideoFragment) callFragment;
                isSpeakerEnabled = true;    // 视频通话，启用Speaker
            } else {    // 音频通话
                // 添加Audio Fragment到Container
                callFragment = new CallAudioFragment();
                audioCallFragment = (CallAudioFragment) callFragment;
            }
            // 如果蓝牙耳机连接上，route音频流到蓝牙耳机
            if (BluetoothManager.getInstance().isBluetoothHeadsetAvailable()) {
                BluetoothManager.getInstance().routeAudioToBluetooth();
            }
            // 提交Video/Audio Fragment到本Activity的FragmentManager管理
            callFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.fragmentContainer, callFragment).commitAllowingStateLoss();
        }
        registerCallDurationTimer();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hang_up:

                hangUp();
                break;

            case R.id.switchCamera:
                if (videoCallFragment != null) {
                    videoCallFragment.switchCamera();
                }
                break;
            case R.id.accept_call:
                accept();
                break;
            case R.id.video:
                enabledOrDisabledVideo(isVideoEnabled(SipCoreManager.getLc().getCurrentCall()));
                break;
            case R.id.micro:
                toggleMicro();
                break;
            case R.id.iv_open_door:

                openDoor();


                break;
        }
    }

    private void openDoor() {
        if (rxManager == null) {
            rxManager = new RxManager();
        }

        String dir = SipCoreManager.getLc().getCurrentCall().getRemoteAddress().getUserName();

        if (dir.contains("D")) {
            dir = dir.substring(1);
        }

//        ToastUtils.showToast(BaseApplication.mContext, dir);

        rxManager.add(HttpHelper.getService(ApiService.class)
                .requestOpenDoor(ApiService.requestOpenDoor, UserHelper.token, dir)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        ToastUtils.showToast(CallActivity.this, "开门成功，欢迎回家，我的主人！");

                    } else {
                        ToastUtils.showToast(CallActivity.this, baseBean.getOther().getMessage());
                    }
                }, throwable -> {
                    ToastUtils.showToast(CallActivity.this, throwable.getMessage());
                })
        );

        ALog.e("getUserName()-->" + SipCoreManager.getLc().getCurrentCall().getRemoteAddress().getUserName());

//        SipCoreManager.getLc().sendDtmf('#');//不推荐使用这个API，因为后续会改，同时没有开门记录。
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hangUp();

        if (rxManager != null) {
            rxManager.clear();
        }

        LinphoneCore lc = SipCoreManager.getLcIfManagerNotDestroyedOrNull();
        if (lc != null) {
            lc.removeListener(mListener);
        }
    }

    public void displayVideoCall(boolean display) {
        if (mBottomView.getVisibility() == View.GONE) {
            mBottomView.setVisibility(View.VISIBLE);
        } else {
            mBottomView.setVisibility(View.GONE);
        }
    }

    public void hangUp() {
        LinphoneCore lc = SipCoreManager.getLc();
        LinphoneCall currentCall = lc.getCurrentCall();

        if (currentCall != null) {
            lc.terminateCall(currentCall);
        } else if (lc.isInConference()) {
            lc.terminateConference();
        } else {
            lc.terminateAllCalls();
        }
        finish();
    }

    private void accept() {
        LinphoneCore lCore = SipCoreManager.getLc();
        LinphoneCall currentCall = lCore.getCurrentCall();
        if (currentCall != null) {
            try {
                lCore.acceptCall(currentCall);
            } catch (LinphoneCoreException e) {
                e.printStackTrace();
            }
        }
    }

    private void enabledOrDisabledVideo(final boolean isVideoEnabled) {
        final LinphoneCall call = SipCoreManager.getLc().getCurrentCall();
        if (call == null) {
            return;
        }

        if (isVideoEnabled) {    // 如果当前是视频通话，点击之后切换到非视频通话
            LinphoneCallParams params = call.getCurrentParamsCopy();
            params.setVideoEnabled(false);
            SipCoreManager.getLc().updateCall(call, params);
        } else {    // 如果当前是非视频通话，点击后切换到视频通话
            // 检查是否低带宽使能视频通话
            if (!call.getRemoteParams().isLowBandwidthEnabled()) {
                SipCoreManager.getInstance().addVideo();
            }
        }
    }

    private void toggleMicro() {
        // MIC使能切换
        LinphoneCore lc = SipCoreManager.getLc();
        isMicMuted = !isMicMuted;
        lc.muteMic(isMicMuted);
        if (isMicMuted) {
            micro.setImageResource(R.drawable.ic_not_mute_white_240px);
        } else {
            micro.setImageResource(R.drawable.ic_mute_white_240px);
        }
    }

    private void registerCallDurationTimer() {
        Chronometer timer;
        // 设置CallView中的持续时间信息
        timer = (Chronometer) findViewById(R.id.current_call_timer);

        if (timer == null) {
            throw new IllegalArgumentException("no callee_duration view found");
        }
        // 启动持续时间Chronometer对象
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }


    private boolean isVideoEnabled(LinphoneCall call) {
        if (call != null) {
            return call.getCurrentParamsCopy().getVideoEnabled();
        }
        return false;
    }

    // 接听
    private void answer() {
        Fragment callFragment;

        if (mCall == null) {
            finish();
            return;

        }

        LinphoneCallParams params = SipCoreManager.getLc().createCallParams(mCall);

        if (!SipCoreManager.getInstance().acceptCallWithParams(mCall, params)) {
            // the above method takes care of Samsung Galaxy S
            Toast.makeText(this, "接听电话时发生错误", Toast.LENGTH_LONG).show();
        } else {
            // 根据远端是否提供视频参数信息，选择启动视频通话或音频通话
            final LinphoneCallParams remoteParams = mCall.getRemoteParams();
            if (remoteParams != null && remoteParams.getVideoEnabled() &&
                    SipCorePreferences.instance().shouldAutomaticallyAcceptVideoRequests()) {

                callFragment = new CallVideoFragment();
                videoCallFragment = (CallVideoFragment) callFragment;

            } else {
                // 启动音频通话界面
                callFragment = new CallAudioFragment();
                audioCallFragment = (CallAudioFragment) callFragment;
            }
            callFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.fragmentContainer, callFragment).commitAllowingStateLoss();

        }
    }

    @Override
    public void onBackPressedSupport() {
        //禁止返回
    }
}
