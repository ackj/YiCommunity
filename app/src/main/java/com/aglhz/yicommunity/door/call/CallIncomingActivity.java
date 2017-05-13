package com.aglhz.yicommunity.door.call;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.yicommunity.R;
import com.sipphone.sdk.Contact;
import com.sipphone.sdk.ContactsManager;
import com.sipphone.sdk.SipCoreManager;
import com.sipphone.sdk.SipCorePreferences;
import com.sipphone.sdk.SipCoreUtils;

import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreListenerBase;
import org.linphone.mediastream.Log;

import java.util.List;

public class CallIncomingActivity extends Activity {
    private static final String DEBUG_TAG = "CallIncomingActivity";
    private static CallIncomingActivity instance;

    private TextView name, number;
    private ImageView contactPicture, accept, decline;
    private LinphoneCall mCall;
    private LinphoneCoreListenerBase mListener;
    private LinearLayout acceptUnlock;
    private LinearLayout declineUnlock;
    private boolean isActive;
    private float answerX;
    private float declineX;

    public static CallIncomingActivity instance() {
        return instance;
    }

    public static boolean isInstanciated() {
        return instance != null;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(DEBUG_TAG + " onCreate called.");
        // ????????????????
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.fragment_call_incoming);

        contactPicture = (ImageView) findViewById(R.id.contact_picture);

        // set this flag so this activity will stay in front of the keyguard
        int flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
        getWindow().addFlags(flags);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            isActive = pm.isInteractive();
        } else {
            isActive = pm.isScreenOn();
        }

        final int screenWidth = getResources().getDisplayMetrics().widthPixels;

        // ??????????????????????????????????????????
        accept = (ImageView) findViewById(R.id.accept);
        decline = (ImageView) findViewById(R.id.decline);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isActive) {    // ????????????????????????????
                    answer();
                }
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isActive) {    // ??????????????????????????
                    decline();
                }
            }
        });
        // call ????????
        mListener = new LinphoneCoreListenerBase() {
            @Override
            public void callState(LinphoneCore lc, LinphoneCall call, State state, String message) {
                Log.e("CallIncomingActivity state " + state);
                if (call == mCall && State.CallEnd == state) {    // ?????Call???End
                    finish();
                }
                if (state == State.StreamsRunning) {    // StreamsRunning????????Speaker????
                    // The following should not be needed except some devices need it (e.g. Galaxy S).
                    SipCoreManager.getLc().enableSpeaker(SipCoreManager.getLc().isSpeakerEnabled());
                }
            }
        };
        instance = this;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(DEBUG_TAG + " onResume called.");
        instance = this;
        LinphoneCore lc = SipCoreManager.getLcIfManagerNotDestroyedOrNull();
        if (lc != null) {    // ??????????????????
            lc.addListener(mListener);
        }
        // Only one call ringing at a time is allowed
        // ????????????????????
        if (SipCoreManager.getLcIfManagerNotDestroyedOrNull() != null) {
            List<LinphoneCall> calls = SipCoreUtils.getLinphoneCalls(SipCoreManager.getLc());
            for (LinphoneCall call : calls) {
                if (State.IncomingReceived == call.getState()) {
                    mCall = call;
                    break;
                }
            }
        }

        if (mCall == null) {
            Log.e("Couldn't find incoming call");
            finish();
            return;
        }
        LinphoneAddress address = mCall.getRemoteAddress();
        // ??????????????????????????????????????????????TextView??
        Contact contact = ContactsManager.getInstance().findContactWithAddress(getContentResolver(), address);
        if (contact != null) {
            SipCoreUtils.setImagePictureFromUri(this, contactPicture, contact.getPhotoUri(), contact.getThumbnailUri());
            name.setText(contact.getName());
        } else {
            name.setText(SipCoreUtils.getAddressDisplayName(address));
        }
        number.setText(address.asStringUriOnly());
    }

    @Override
    protected void onPause() {
        LinphoneCore lc = SipCoreManager.getLcIfManagerNotDestroyedOrNull();
        if (lc != null) {
            lc.removeListener(mListener);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        return super.onKeyDown(keyCode, event);
    }

    // ???
    private void decline() {
        SipCoreManager.getLc().terminateCall(mCall);
        finish();
    }

    // ????
    private void answer() {
        LinphoneCallParams params = SipCoreManager.getLc().createCallParams(mCall);

        if (!SipCoreManager.getInstance().acceptCallWithParams(mCall, params)) {
            // the above method takes care of Samsung Galaxy S
        } else {
//			if (!SPhoneHome.isInstanciated()) {
//				return;
//			}
            // ?????????????????????????????????????????????
            final LinphoneCallParams remoteParams = mCall.getRemoteParams();
            if (remoteParams != null && remoteParams.getVideoEnabled() &&
                    SipCorePreferences.instance().shouldAutomaticallyAcceptVideoRequests()) {
                // ??????????????
//				SPhoneHome.instance().startVideoActivity(mCall);
                startActivity(new Intent(CallIncomingActivity.this, CallActivity.class));
            }
        }
    }


}
