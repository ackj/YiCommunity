package com.aglhz.abase.network.http;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class LoginInterceptor implements Interceptor {
    private static final String TAG = LoginInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String jsonString = response.body().string();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString).optJSONObject("other");
            String code = jsonObject.optString("code");
            if ("123".equals(code)) {
                EventBus.getDefault().post(new LoginInterceptor());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }
}
