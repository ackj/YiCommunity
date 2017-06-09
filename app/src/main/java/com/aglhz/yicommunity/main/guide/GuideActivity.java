package com.aglhz.yicommunity.main.guide;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/6/8 0008 09:14.
 * Email: liujia95me@126.com
 */

public class GuideActivity extends BaseActivity {

    @BindView(R.id.image_guide_1)
    ImageView ivGuide1;
    @BindView(R.id.image_guide_2)
    ImageView ivGuide2;

    //    public static final int GUIDE_ADD_HOUSE = R.drawable.bg_guide_01;
//    public static final int GUIDE_SET_OPEN_DOOR = R.drawable.bg_guide_02_1242px_2208px;
//    public static final int GUIDE_OPEN_DOOR = R.drawable.bg_guide_03_1242px_2208px;
//    public static final int GUIDE_PARK_PAY = R.drawable.bg_guide_04_1242px_2208px;
//    public static final int GUIDE_MONTH_CAR_PAY = R.drawable.bg_guide_05_1242px_2208px;
//    public static final int GUIDE_CAR_CARD = R.drawable.bg_guide_06_1242px_2208px;
    private Unbinder unbinder;
    private int[] guides;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        unbinder = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        guides = intent.getIntArrayExtra("guide");
        for (int i = 0; i < guides.length; i++) {
            Glide.with(this).load(guides[i])
                    .preload();
        }
        setGuideFromType();
    }

    private void setGuideFromType() {

        if (currentPosition < guides.length) {
            Glide.with(this)
                    .load(guides[currentPosition])
                    .dontAnimate()
                    .into(ivGuide1);
            if (currentPosition + 1 < guides.length) {
                Glide.with(this)
                        .load(guides[currentPosition + 1])
                        .dontAnimate()
                        .into(ivGuide2);
            }
            currentPosition++;
        } else {
            finish();
        }
    }

    @OnClick(R.id.image_guide_1)
    public void onViewClicked() {
        setGuideFromType();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
