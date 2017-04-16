package com.aglhz.abase.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.R;

public class MoreTextView extends LinearLayout {
    private static final String TAG = MoreTextView.class.getSimpleName();

    protected TextView contentView;
    protected TextView expandView;

    protected int textColor;
    protected float textSize;
    protected int maxLine;
    protected String text;

    public int defaultTextColor = Color.BLACK;
    public int defaultTextSize = 12;
    public int defaultLine = 3;

    public MoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initalize();
        initWithAttrs(context, attrs);
        bindListener();
    }

    protected void initWithAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MoreTextStyle);
        try {
            int textColor = ta.getColor(R.styleable.MoreTextStyle_textColor, defaultTextColor);
            textSize = ta.getDimensionPixelSize(R.styleable.MoreTextStyle_textSize, defaultTextSize);
            maxLine = ta.getInt(R.styleable.MoreTextStyle_maxLine, defaultLine);
            text = ta.getString(R.styleable.MoreTextStyle_text);
            bindTextView(textColor, textSize, maxLine, text);
        } finally {
            ta.recycle();
        }
    }

    protected void initalize() {
        setOrientation(VERTICAL);
        setGravity(Gravity.LEFT);
        contentView = new TextView(getContext());
        addView(contentView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        expandView = new TextView(getContext());
//        int padding = dip2px(getContext(), 5);
//        expandView.setPadding(0, padding, 0, 0);
        expandView.setText("全文");
        expandView.setTextColor(Color.parseColor("#E95D5A"));
        LayoutParams llp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        llp.setMargins(0, 30, 0, 0);
        expandView.setLayoutParams(llp);
        addView(expandView);
    }

    protected void bindTextView(int color, float size, final int line, String text) {
        contentView.setTextColor(color);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        contentView.setText(text);
        contentView.setHeight(contentView.getLineHeight() * line);
        post(new Runnable() {

            @Override
            public void run() {
                expandView.setVisibility(contentView.getLineCount() > line ? View.VISIBLE : View.GONE);

            }
        });
    }

    protected void bindListener() {
        setOnClickListener(new OnClickListener() {
            boolean isExpand;

            @Override
            public void onClick(View v) {
                if (!expandView.isShown()) {
                    return;
                }


                isExpand = !isExpand;
                contentView.clearAnimation();
                final int deltaValue;
                final int startValue = contentView.getHeight();
                int durationMillis = 350;
                if (isExpand) {
                    deltaValue = contentView.getLineHeight() * contentView.getLineCount() - startValue;
                    expandView.setText("收起");
                } else {
                    deltaValue = contentView.getLineHeight() * maxLine - startValue;
                    expandView.setText("全文");
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        contentView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }

                };
                animation.setDuration(durationMillis);
                contentView.startAnimation(animation);
            }
        });
    }

    public TextView getTextView() {
        return contentView;
    }

    public void setText(CharSequence charSequence) {
        contentView.setText(charSequence);
        expandView.setVisibility(contentView.getLineCount() > maxLine ? View.VISIBLE : View.GONE);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
