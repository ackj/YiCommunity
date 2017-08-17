package com.aglhz.yicommunity.launch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.aglhz.abase.cache.SPCache;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.main.MainActivity;
import com.bumptech.glide.Glide;

/**
 * Author: LiuJia on 2017/8/17 0017 14:23.
 * Email: liujia95me@126.com
 */

public class WelcomeActivity extends BaseActivity {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    private ViewPager viewPager;

    private int[] welcomeBg = {
            R.drawable.yindao300ye1242px2208px,
            R.drawable.yindao301ye1242px2208px,
            R.drawable.yindao303ye1242px2208px};
    private View pointer;
    private ImageView imageView1;
    private ImageView imageView2;
    private Button btnBegin;

    private int imageView1StartX, imageView2StartX, pointerStartX, btnStartX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        imageView1 = (ImageView) findViewById(R.id.imageview1);
        imageView2 = (ImageView) findViewById(R.id.imageview2);
        btnBegin = (Button) findViewById(R.id.btn_begin);
        pointer = findViewById(R.id.view_pointer);
        initData();
        initListener();
    }

    private void initData() {
        pointerStartX = DensityUtils.getDisplayWidth(this) / 2
                - DensityUtils.dp2px(this, 50);
        imageView1StartX = -DensityUtils.getDisplayWidth(this);
        imageView2StartX = -DensityUtils.getDisplayWidth(this) * 2;
        btnStartX = DensityUtils.getDisplayWidth(this) * 2
                + DensityUtils.getDisplayWidth(this) / 2
                - DensityUtils.dp2px(this, 50);
        ALog.e(TAG,"pointerStartX:"+pointerStartX);
        imageView1.setX(imageView1StartX);
        imageView2.setX(imageView2StartX);
        btnBegin.setX(btnStartX);
        pointer.setX(pointerStartX);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return welcomeBg.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = new ImageView(WelcomeActivity.this);
                Glide.with(WelcomeActivity.this)
                        .load(welcomeBg[position])
                        .into(iv);
                container.addView(iv);
                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int x = (position * DensityUtils.getDisplayWidth(WelcomeActivity.this)) + positionOffsetPixels;
                imageView1.setX(imageView1StartX + x);
                imageView2.setX(imageView2StartX + x);
                btnBegin.setX(btnStartX - x);
                int pointerX = (int) (DensityUtils.dp2px(WelcomeActivity.this, 44) * positionOffset
                        + DensityUtils.dp2px(WelcomeActivity.this, 44) * position);
                pointer.setX(pointerStartX + pointerX);
            }

            @Override
            public void onPageSelected(int position) {
                ALog.e(TAG, "onPageSelected:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPCache.put(WelcomeActivity.this, Constants.SP_KEY_WELCOME,true);
                go2Main();
            }
        });
    }

    private void go2Main() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(0, 0);
        //此处之所以延迟退出是因为立即退出在小米手机上会有一个退出跳转动画，而我不想要这个垂直退出的跳转动画。
        new Handler().postDelayed(() -> finish(), 1000);
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }

}
