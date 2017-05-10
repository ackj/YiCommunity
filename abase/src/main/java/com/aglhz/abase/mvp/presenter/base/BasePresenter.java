package com.aglhz.abase.mvp.presenter.base;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.aglhz.abase.common.RxManager;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Author：leguang on 2016/10/9 0009 10:31
 * Email：langmanleguang@qq.com
 * <p>
 * 所有Presenter类的基类，负责调度View层和Model层的交互。
 */
public abstract class BasePresenter<V extends BaseContract.View, M extends BaseContract.Model> {
    private final String TAG = BasePresenter.class.getSimpleName();

    public Reference<V> mViewReference;
    public M mModel;
    //每一套mvp应该拥有一个独立的RxManager
    public RxManager mRxManager = new RxManager();

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public BasePresenter(V mView) {
        setView(mView);
        mModel = createModel();
    }

    public void setView(V view) {
        mViewReference = new WeakReference<V>(view);
    }

    public V getView() {
        return mViewReference == null ? null : mViewReference.get();
    }

    @UiThread
    public boolean isViewAttached() {
        return mViewReference != null && mViewReference.get() != null;
    }

    @NonNull
    protected M createModel() {
        return null;
    }

    public void setModel(@NonNull M model) {
        this.mModel = model;
    }

    @UiThread
    public void clear() {
        ALog.e(TAG + "clear()");

        //优先释放Model层对象，避免内存泄露
        if (mModel != null) {
            mModel.clear();
            mModel = null;
        }

        if (mRxManager != null) {
            mRxManager.clear();
        }

        //释放View层对象，避免内存泄露
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    public void error(Throwable throwable) {

        if (!isViewAttached()) {
            return;
        }
        if (throwable == null) {
            getView().error("数据异常");
            return;
        }
        if (throwable instanceof ConnectException) {
            getView().error("网络异常");
        } else if (throwable instanceof HttpException) {
            getView().error("服务器异常");
        } else if (throwable instanceof SocketTimeoutException) {
            getView().error("连接超时");
        } else if (throwable instanceof JSONException) {
            getView().error("解析异常");
        } else {
            getView().error("数据异常");
        }
        throwable.printStackTrace();
        ALog.e(TAG,throwable);
    }
}
