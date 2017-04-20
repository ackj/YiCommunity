package com.aglhz.yicommunity.common;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.common.bean.BaseBean;
import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by YandZD on 2017/1/17.
 */

public abstract class BeanCallback<T> extends AbsCallback<T> {

    //不是UI线程
    @Override
    public T convertSuccess(Response response) throws Exception {
        String s = StringConvert.create().convertSuccess(response);
        ALog.e(s);
        response.close();
        Gson gson = new Gson();
        T bean = gson.fromJson(s, getBeanType());

        return bean;
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        e.printStackTrace();
        super.onError(call, response, e);
        onError("网络错误，请检查您的网络");
    }

    @Override
    public void onSuccess(T bean, Call call, Response response) {
        Gson gson = new Gson();
        String resStr = gson.toJson(bean);
        ALog.e(resStr);

        BaseBean resBean = gson.fromJson(resStr, BaseBean.class);
        int code = resBean.getOther().getCode();

        if (code != 200) {
            errorTask(resBean);
        } else {
            onSuccess(resStr);
            onSuccess(bean);
        }
    }

    private void errorTask(BaseBean bean) {
        //123为登录失效
        if (bean.getOther().getCode() == 123) {
//            UserInfoHelper.cleanInfo();
        }
        onError(((BaseBean) bean).getOther().getMessage());
    }

    public abstract void onError(String errMsg);

    abstract public void onSuccess(T Bean);

    public void onSuccess(String content) {

    }

    private Class<T> getBeanType() {
        Class c = this.getClass();
        Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            return (Class<T>) p[0];
        }
        return null;
    }
}
