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
 * 引导页
 */
public class GuideActivity extends BaseActivity {

    @BindView(R.id.image_guide_1)
    ImageView ivGuide1;
    @BindView(R.id.image_guide_2)
    ImageView ivGuide2;

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

    @Override
    public boolean swipeBackPriority() {
        return false;
    }

    private void initData() {
        Intent intent = getIntent();
        guides = intent.getIntArrayExtra("guide");
        for (int i = 0; i < guides.length; i++) {
            Glide.with(this).load(guides[i])
                    .preload();
        }
        loadGuides();
    }

    /**
     * 加载指引页
     * 指引页是以两层ImageView实现的，目的是为了让两张以上引导页切换时自然过渡
     */
    private void loadGuides() {
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
        loadGuides();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
