package com.aglhz.yicommunity.preview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Author: LiuJia on 2017/5/10 0010 14:40.
 * Email: liujia95me@126.com
 */

public class PreviewActivity extends BaseActivity {
    private static final String TAG = PreviewActivity.class.getSimpleName();
    @BindView(R.id.viewpager_activity_preview)
    ViewPager mViewPager;
    private Unbinder unbinder;
    private ArrayList<String> picsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        unbinder = ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle picsBundle = intent.getBundleExtra("pics");
        int position = intent.getIntExtra("position", 0);
        picsList = picsBundle.getStringArrayList("picsList");
        for (int i = 0; i < picsList.size(); i++) {
            ALog.e(TAG, picsList.get(i));
        }

        mViewPager.setAdapter(new PreviewAdapter());
        mViewPager.setCurrentItem(position);
    }

    private void initListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mViewPager != null) {
            mViewPager.setAdapter(null);
        }
    }

    class PreviewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return picsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView mPhotoView = (PhotoView) LayoutInflater.from(BaseApplication.mContext).inflate(R.layout.item_preview, null, false);
            mPhotoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                }

                @Override
                public void onOutsidePhotoTap() {//点击照片边缘触发对出效果
                    onBackPressed();
                }
            });

            Glide.with(BaseApplication.mContext)
                    .load(picsList.get(position))
                    .error(R.drawable.ic_default_img_120px)
                    .placeholder(R.drawable.ic_default_img_120px)
                    .into(mPhotoView);
            container.addView(mPhotoView);
            return mPhotoView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
