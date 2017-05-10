package com.aglhz.yicommunity.publish.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.luban.Luban;
import com.aglhz.yicommunity.common.luban.OnCompressListener;
import com.aglhz.yicommunity.publish.contract.RepairContract;
import com.aglhz.yicommunity.publish.model.RepairModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: LiuJia on 2017/5/9 0009 10:36.
 * Email: liujia95me@126.com
 */

public class RepairPresenter extends BasePresenter<RepairContract.View, RepairContract.Model> implements RepairContract.Presenter {

    private static final String TAG = RepairPresenter.class.getSimpleName();

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public RepairPresenter(RepairContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected RepairContract.Model createModel() {
        return new RepairModel();
    }

    @Override
    public void start(Object request) {

    }

    @Override
    public void postRepair(Params params) {
        totalCount = params.files.size();
        params.cmnt_c = "KBSJ-agl-00005";
        compress(params);

    }

    private List<File> compressFile = new ArrayList<>();
    int totalCount = 0;

    //压缩图片
    private void compress(Params params) {
        for (int i = 0; i < params.files.size(); i++) {
            ALog.e(TAG, "--- 压缩前的图片路径：" + params.files.get(i).getAbsolutePath());
            ALog.e(TAG, "--- 压缩前的图片大小：" + params.files.get(i).length());
            Luban.get(BaseApplication.mContext)
                    .load(params.files.get(i))
                    .putGear(Luban.THIRD_GEAR)
                    .setFilename(System.currentTimeMillis() + "")
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                            Toast.makeText(BaseApplication.mContext, "I'm start", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(File file) {
                            ALog.e(TAG, "压缩后的图片路径：" + file.getAbsolutePath());
                            ALog.e(TAG, "压缩后的图片大小：" + file.length());
                            synchronized (RepairPresenter.class) {
                                compressFile.add(file);
                                if (compressFile.size() == totalCount) {
                                    ALog.e(TAG,"开始上传了！！");
                                    beginPost(params);
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    }).launch();
        }
    }

    private void beginPost(Params params) {
        params.files = compressFile;
        mRxManager.add(mModel.postRepair(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    if (baseBean.getOther().getCode() == 200) {
                        ALog.e(TAG,"上传成功了！！");
                        getView().responseRepair(baseBean);
                    } else {
                        ALog.e(TAG,"上传失败了！！");
                        getView().error(baseBean.getOther().getMessage());
                    }
                }, this::error));
    }
}
