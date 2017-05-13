package com.aglhz.yicommunity.door.call;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.aglhz.yicommunity.R;

public class CallAudioFragment extends Fragment {
    private static final String DEBUG_TAG = "CallAudioFragment";
    private CallActivity incallActvityInstance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call_audio, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        incallActvityInstance = (CallActivity) activity;

//		if (incallActvityInstance != null) {
//			incallActvityInstance.bindAudioFragment(this);
//		}
    }

    @Override
    public void onStart() {
        super.onStart();

        // Just to be sure we have incall controls
//		if (incallActvityInstance != null) {
//			incallActvityInstance.removeCallbacks();
//		}
    }

    class SwipeGestureDetector implements OnTouchListener {
        static final int MIN_DISTANCE = 100;
        private float downX, upX;
        private boolean lock;

        private SwipeListener listener;

        public SwipeGestureDetector(SwipeListener swipeListener) {
            super();
            listener = swipeListener;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lock = false;
                    downX = event.getX();
                    return true;

                case MotionEvent.ACTION_MOVE:
                    if (lock) {
                        return false;
                    }
                    upX = event.getX();

                    float deltaX = downX - upX;

                    if (Math.abs(deltaX) > MIN_DISTANCE) {
                        lock = true;
                        if (deltaX < 0) {
                            listener.onLeftToRightSwipe();
                            return true;
                        }
                        if (deltaX > 0) {
                            listener.onRightToLeftSwipe();
                            return true;
                        }
                    }
                    break;
            }
            return false;
        }
    }

    interface SwipeListener {
        void onRightToLeftSwipe();

        void onLeftToRightSwipe();
    }
}
