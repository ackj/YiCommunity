package com.aglhz.abase.network.http;

import com.aglhz.abase.log.ALog;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class LoggingInterceptor implements Interceptor {
    private static final String TAG = LoggingInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        long t1 = System.nanoTime();

        Buffer buffer = new Buffer();

        Buffer buffer1;

        if (request.body() != null)
            request.body().writeTo(buffer);

        ALog.e(String.format("Sending request %s on %s%n%sRequest Params: %s",
                request.url(), chain.connection(), request.headers(), buffer.clone().readUtf8()));
        buffer.close();

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();


        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE);
        buffer = source.buffer().clone();
        buffer1 = source.buffer().clone();

        ALog.e(String.format("Received response for %s%nin %.1fms%n%sResponse Json: %s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers(),
                buffer.readUtf8()));


        ALog.json("json::" + buffer1.readUtf8());


        //***********下一是用拦截器检测登录状态，如果是非登录状态，则发送事件登录（如果能拿到Application也能跳转）****************
        String jsonString = buffer1.readUtf8();
        ALog.e("jsonString::" + jsonString);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString).optJSONObject("other");
            String code = jsonObject.optString("code");
            if ("123".equals(code)) {
                ALog.e("111111111111111111111");
                EventBus.getDefault().post(new LoginInterceptor());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //***************************************************************************************************************


        buffer.close();

        return response;
    }
}
