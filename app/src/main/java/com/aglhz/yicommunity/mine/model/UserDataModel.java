package com.aglhz.yicommunity.mine.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.mine.contract.FeedbackContract;
import com.aglhz.yicommunity.mine.contract.UserDataContract;

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
    public Observable<BaseBean> updatePortrait(Params params) {
//        // 创建 RequestBody，用于封装构建RequestBody
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//// MultipartBody.Part  和后端约定好Key，这里的partName是用image
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
//
//// 添加描述
//        String descriptionString = "hello, 这是文件描述";
//        RequestBody description =
//                RequestBody.create(
//                        MediaType.parse("multipart/form-data"), descriptionString);
//
//        return HttpHelper.getService(ApiService.class).updatePortrait(params.token, params., params.val)
//                .subscribeOn(Schedulers.io());
        return null;
    }

    @Override
    public Observable<BaseBean> updateUserData(Params params) {
        return HttpHelper.getService(ApiService.class).updateUserData(params.token, params.field, params.val)
                .subscribeOn(Schedulers.io());
    }
}