package com.aglhz.yicommunity.main.mine.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.UserDataBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.mine.contract.UserDataContract;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块的Model层内容。
 */

public class UserDataModel extends BaseModel implements UserDataContract.Model {
    private final String TAG = UserDataModel.class.getSimpleName();


    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<UserDataBean> updatePortrait(Params params) {
        // 创建 RequestBody，用于封装构建RequestBody
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), params.file);
        builder.addFormDataPart("file", params.file.getName(), requestBody);

        return HttpHelper.getService(ApiService.class)
                .updatePortrait(ApiService.updatePortrait,
                params.token, builder.build())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> updateUserData(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("field", params.field);
        builder.addFormDataPart("val", params.val);
        return HttpHelper.getService(ApiService.class)
                .updateUserData(ApiService.updateUserData, builder.build())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseBean> updatePassword(Params params) {
        return HttpHelper.getService(ApiService.class)
                .updatePassword(ApiService.updatePassword,
                        params.token,
                        params.pwd0,
                        params.pwd1,
                        params.pwd2)
                .subscribeOn(Schedulers.io());
    }
}