package com.aglhz.abase.common;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.aglhz.abase.R;


/**
 * Author：leguang on 2016/11/29 0029 14:15
 * Email：langmanleguang@qq.com
 */
public class AudioPlayer {
    private static final String TAG = AudioPlayer.class.getSimpleName();
    private static AudioPlayer INSTANCE;
    private SoundPool mSoundPool;

    private AudioPlayer() {
    }

    private AudioPlayer(Context context) {
        if (mSoundPool == null) {
            mSoundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
            // 初始化声音
            mSoundPool.load(context, R.raw.refresh, 1);// 1
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
        mSoundPool.play(soundID, 1, 1, 0, 0, 1);
    }

    public void clear() {
        if (mSoundPool != null) {
            mSoundPool = null;
        }
    }
}
