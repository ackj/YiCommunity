package com.aglhz.yicommunity.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Window;

import com.aglhz.abase.common.AudioPlayer;
import com.aglhz.yicommunity.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Author: LiuJia on 2017/7/17 0017 11:06.
 * Email: liujia95me@126.com
 */

public class OpenDoorDialog extends Dialog {

    private static final String TAG = OpenDoorDialog.class.getSimpleName();

    private final GifImageView gifImageView;
    private Context context;

    public OpenDoorDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_open_door);
        gifImageView = (GifImageView) findViewById(R.id.iv_gif);
        AudioPlayer.getInstance(context).play(AudioPlayer.KNOCK, 1, 1, 0, -1, 1F);
        try {
            GifDrawable gifFromAssets = new GifDrawable(context.getAssets(), "monkey_open_door_200px.gif");
            gifImageView.setImageDrawable(gifFromAssets);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    public void setError() {
        try {
            GifDrawable gifFromAssets = new GifDrawable(context.getAssets(), "open_door_error_200px.gif");
            gifImageView.setImageDrawable(gifFromAssets);
            gifImageView.postDelayed(() -> {
                gifImageView.setImageDrawable(null);
                dismiss();
            }, 1500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSuccess() {
        AudioPlayer.getInstance(context).stop();
        AudioPlayer.getInstance(context).play(AudioPlayer.OPEN);
        try {
            GifDrawable gifFromAssets = new GifDrawable(context.getAssets(), "open_door_success_200px.gif");
            gifImageView.setImageDrawable(gifFromAssets);
            gifImageView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gifImageView.setImageDrawable(null);
                    dismiss();
                }
            }, 1500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
