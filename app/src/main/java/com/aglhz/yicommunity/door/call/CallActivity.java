package com.aglhz.yicommunity.door.call;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.aglhz.yicommunity.R;
import com.sipphone.sdk.BluetoothManager;
import com.sipphone.sdk.SipCoreManager;
import com.sipphone.sdk.SipCoreUtils;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreListenerBase;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration;

public class CallActivity extends Activity implements View.OnClickListener {
    public static final String TAG = "CallActivity";

    private final static int SECONDS_BEFORE_HIDING_CONTROLS = 4000;
    private final static int SECONDS_BEFORE_DENYING_CALL_UPDATE = 30000;

    public static CallActivity instance;

    private Handler mControlsHandler = new Handler();
    private Runnable mControls;
    private ImageView switchCamera;
    private RelativeLayout mActiveCallHeader, sideMenuContent, avatar_layout;
    private ImageView pause, hangUp, acceptCall, dialer, video, micro, speaker, options, addCall, transfer, conference, conferenceStatus, contactPicture;
    private ImageView unlock;
    private ImageView audioRoute, routeSpeaker, routeEarpiece, routeBluetooth, menu, chat;
    private LinearLayout mNoCurrentCall, callInfo, mCallPaused;
    private ProgressBar videoProgress;
    private View mBottomView;

    private CallVideoFragment videoCallFragment;
    private boolean isSpeakerEnabled = false, isMicMuted = false, isTransferAllowed, isAnimationDisabled;
    private LinearLayout mControlsLayout;
    private int cameraNumber;
    private Animation slideOutLeftToRight, slideInRightToLeft, slideInBottomToTop, slideInTopToBottom, slideOutBottomToTop, slideOutTopToBottom;
    private CountDownTimer timer;
    private boolean isVideoCallPaused = false;
    private CallAudioFragment audioCallFragment;
    private LinearLayout callsList, conferenceList;
    private LayoutInflater inflater;
    private ViewGroup container;
    private boolean isConferenceRunning = false;
    private LinphoneCoreListenerBase mListener;
    private DrawerLayout sideMenu;

    public static CallActivity instance() {
        return instance;
    }

    public static boolean isInstanciated() {
        return instance != null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;


//        RxBus.get().post(new CallingEvent());



        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_call);
        // 默认定义为true，即isTransferAllowed = true
        // 蓝牙耳机检测
        if (!BluetoothManager.getInstance().isBluetoothHeadsetAvailable()) {    // true
            BluetoothManager.getInstance().initBluetooth();
        }

        // 界面切换动画，默认是false，即isAnimationDisabled = false;
//		isAnimationDisabled = getApplicationContext().getResources().getBoolean(R.bool.disable_animations) || !LinphonePreferences.instance().areAnimationsEnabled();
        isAnimationDisabled = false;
        // 设备摄像头的个数，通常由前置和后置两个摄像头
        cameraNumber = AndroidCameraConfiguration.retrieveCameras().length;
        // 构建通话过程中Native层LinphoneCore状态监听器对象，这里子关注Call的状态及加密状态是否改变
        // 因此，这里只实现了callState和callEncryptionChanged接口
        mListener = new LinphoneCoreListenerBase() {
            @Override
            public void callState(LinphoneCore lc, LinphoneCall call, State state, String message) {

            }

            @Override
            public void callEncryptionChanged(LinphoneCore lc, LinphoneCall call, boolean encrypted,
                                              String authenticationToken) {
            }
        };
        mBottomView = findViewById(R.id.menu);
        videoProgress = (ProgressBar) findViewById(R.id.video_in_progress);
        videoProgress.setVisibility(View.GONE);

        micro = (ImageView) findViewById(R.id.micro);
        unlock = (ImageView) findViewById(R.id.unlock);

        findViewById(R.id.hang_up).setOnClickListener(this);
        findViewById(R.id.switchCamera).setOnClickListener(this);
        findViewById(R.id.accept_call).setOnClickListener(this);
        findViewById(R.id.video).setOnClickListener(this);

        micro.setOnClickListener(this);
        unlock.setOnClickListener(this);

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




//        RxBus.get().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hang_up:

//                hangUp(null);
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
            case R.id.unlock:
                SipCoreManager.getLc().sendDtmf('#');
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        hangUp(null);
    }

    public void displayVideoCall(boolean display) {
        if (mBottomView.getVisibility() == View.GONE) {
            mBottomView.setVisibility(View.VISIBLE);
        } else {
            mBottomView.setVisibility(View.GONE);
        }
    }

//    @Subscribe
//    public void hangUp(HardWareCallEnd event) {
//        LinphoneCore lc = SipCoreManager.getLc();
//        LinphoneCall currentCall = lc.getCurrentCall();
//
//        if (currentCall != null) {
//            lc.terminateCall(currentCall);
//        } else if (lc.isInConference()) {
//            lc.terminateConference();
//        } else {
//            lc.terminateAllCalls();
//        }
//        finish();
//    }

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
            videoProgress.setVisibility(View.VISIBLE);
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
            micro.setImageResource(R.drawable.ic_micro_selected);
        } else {
            micro.setImageResource(R.drawable.ic_micro_default);
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
}
