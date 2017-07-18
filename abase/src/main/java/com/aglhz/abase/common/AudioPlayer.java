package com.aglhz.abase.common;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.aglhz.abase.R;
import com.aglhz.abase.log.ALog;


/**
 * Author：leguang on 2016/11/29 0029 14:15
 * Email：langmanleguang@qq.com
 */
public class AudioPlayer {
    private static final String TAG = AudioPlayer.class.getSimpleName();
    private static AudioPlayer INSTANCE;
    private SoundPool mSoundPool;
    private static int streamID;
    public static final int REFRESH = 1;
    public static final int CLICK = 2;
    public static final int KNOCK = 3;
    public static final int OPEN = 4;

    private AudioPlayer() {
    }

    private AudioPlayer(Context context) {
        if (mSoundPool == null) {
            mSoundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
            // 初始化声音
            mSoundPool.load(context, R.raw.refresh, 1);// 1
            mSoundPool.load(context, R.raw.click, 1);// 1
            mSoundPool.load(context, R.raw.knock, 1);// 1
            mSoundPool.load(context, R.raw.open, 1);// 1
        }
    }

    /**
     * 初始化
     */
    public static AudioPlayer getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AudioPlayer(context);
        }
        return INSTANCE;
    }

    /**
     * 播放声音
     *
     * @param soundID
     */

    public void play(int soundID) {
        if (mSoundPool == null) {
            throw new Error("Please initialize first!");
        }
        streamID = mSoundPool.play(soundID, 1, 1, 0, 0, 1);
        ALog.e("streamID-->" + streamID);
    }

    public void play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate) {
        if (mSoundPool == null) {
            throw new Error("Please initialize first!");
        }
        streamID = mSoundPool.play(soundID, leftVolume, rightVolume, priority, loop, rate);
    }

    public void stop() {
        if (mSoundPool == null) {
            throw new Error("Please initialize first!");
        }

        ALog.e("streamID-->" + streamID);
        mSoundPool.stop(streamID);
    }

    public void clear() {
        if (mSoundPool != null) {
            mSoundPool.release();
            mSoundPool = null;
        }
    }
}
