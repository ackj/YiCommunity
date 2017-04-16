package com.aglhz.abase.network.http;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YandZD on 2017/1/17.
 */

public class HttpClient {

    private HttpClient() {

    }

    public static <T> void post(String url, AbsCallback<T> callBack) {
        post(url, new HashMap<String, Object>(), callBack);
    }

    public static <T> void post(String url, Map<String, Object> params, AbsCallback<T> callBack) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        PostRequest postRequest = OkGo.post(url);
        postRequest = getPostRequest(postRequest, params);
        postRequest.execute(callBack);
    }

    public static <T> void get(String url, Map<String, Object> params, AbsCallback<T> callBack) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        GetRequest getRequest = OkGo.get(url);

        getRequest = getGetRequest(getRequest, params);

        getRequest.execute(callBack);
    }

    public static <T> void uploadFile(String url, Map<String, Object> params, AbsCallback<T> callBack) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        PostRequest postRequest = OkGo.post(url);
        postRequest = getPostRequest(postRequest, params);
        postRequest.execute(callBack);
    }

    private static PostRequest getPostRequest(PostRequest postRequest, Map<String, Object> params) {
        for (String key : params.keySet()) {
            Object value = params.get(key);
            if (value == null) continue;

            if (value instanceof File) {
                postRequest.params(key, (File) value);
            } else if (value instanceof List) {
                postRequest.addFileParams(key, (List<File>) value);
            } else {
                postRequest.params(key, value.toString());
            }

        }
        return postRequest;
    }

    private static GetRequest getGetRequest(GetRequest getRequest, Map<String, Object> params) {
        for (String key : params.keySet()) {
            Object value = params.get(key);
            getRequest.params(key, (String) value);
        }
        return getRequest;
    }
}
