package com.aglhz.yicommunity.preview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
            PhotoView mPhotoView = (PhotoView) LayoutInflater.from(App.mContext).inflate(R.layout.item_preview, null, false);
            mPhotoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    onBackPressed();
                }

                @Override
                public void onOutsidePhotoTap() {//点击照片边缘触发对出效果
                    onBackPressed();
                }
            });
            mPhotoView.setMaximumScale(100f);
            String picPath = picsList.get(position);
            mPhotoView.setOnLongClickListener(v -> {
                new AlertDialog.Builder(PreviewActivity.this)
                        .setItems(new String[]{"保存图片"}, (dialog, which) -> {
                            //网络访问
                            dialog.dismiss();
                            Observable.create(o -> {
                                Bitmap bitmap = null;
                                try {
                                    bitmap = Glide.with(PreviewActivity.this)
                                            .load(picPath)
                                            .asBitmap() //必须
                                            .centerCrop()
                                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                            .get();
                                } catch (InterruptedException e) {
                                    o.onError(e);
                                } catch (ExecutionException e) {
                                    o.onError(e);
                                }
                                o.onNext(bitmap);
                            }).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(o -> {
                                        savePic2Local((Bitmap) o);
                                    });
                        })
                        .show();
                return false;
            });

            Glide.with(App.mContext)
                    .load(picPath)
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

    private void savePic2Local(Bitmap bitmap) {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/亿社区";
        File localFile = new File(filePath);
        if (!localFile.exists()) {
            localFile.mkdir();
        }
        File finalImageFile = new File(localFile, System.currentTimeMillis() + ".jpg");
        if (finalImageFile.exists()) {
            finalImageFile.delete();
        }
        try {
            finalImageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(finalImageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (bitmap == null) {
            Toast.makeText(this, "图片不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        try {
            fos.flush();
            fos.close();
            Toast.makeText(this, "图片保存在：" + finalImageFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //广播通知刷新系统相册
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(finalImageFile);
        intent.setData(uri);
        sendBroadcast(intent);
    }
}
