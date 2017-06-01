package com.aglhz.yicommunity.main.publish.model;


import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.publish.contract.PublishContract;

import java.io.File;

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

public class ComplainModel extends BaseModel implements PublishContract.Model {
    private final String TAG = ComplainModel.class.getSimpleName();

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> post(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("cmnt_c", params.cmnt_c);
        builder.addFormDataPart("name", params.name);
        builder.addFormDataPart("phoneNo", params.phoneNo);
        builder.addFormDataPart("content", params.content);
        builder.addFormDataPart("type", params.type + "");
        ALog.d("PublishNeighbourModel", "上传=============");
        if (params.files != null && params.files.size() > 0) {
            for (File f : params.files) {
                ALog.d("PublishNeighbourModel", "上传图片：" + f.getAbsolutePath());
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), f);
                builder.addFormDataPart("file", f.getName(), requestBody);
            }
        }
        return HttpHelper.getService(ApiService.class).postComplain(ApiService.requestComplain,
                builder.build())
                .subscribeOn(Schedulers.io());
    }


}