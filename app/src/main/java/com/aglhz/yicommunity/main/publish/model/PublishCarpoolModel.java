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
 * Author: LiuJia on 2017/5/12 0012 14:57.
 * Email: liujia95me@126.com
 */

public class PublishCarpoolModel extends BaseModel implements PublishContract.Model {
    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> post(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("cmnt_c", params.cmnt_c);
        builder.addFormDataPart("carpoolType", params.carpoolType + "");
        builder.addFormDataPart("startPlace", params.startPlace);
        builder.addFormDataPart("endPlace", params.endPlace);
        builder.addFormDataPart("setOutTime", params.outTime);
        builder.addFormDataPart("content", params.content);
        builder.addFormDataPart("publishPositionLat", params.currentPositionLat);
        builder.addFormDataPart("publishPositionLng", params.currentPositionLng);
        builder.addFormDataPart("publishPositionAddress", params.positionAddress);
        builder.addFormDataPart("positionType", params.positionType + "");
        builder.addFormDataPart("type", params.type + "");

        ALog.d("PublishNeighbourModel", "上传=============");

        if (params.files != null && params.files.size() > 0) {
            for (File f : params.files) {
                ALog.d("PublishNeighbourModel", "上传图片：" + f.getAbsolutePath());
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), f);
                builder.addFormDataPart("file", f.getName(), requestBody);
            }
        }
        return HttpHelper.getService(ApiService.class)
                .postCarpoolMessage(ApiService.postCarpoolMessage,
                        builder.build())
                .subscribeOn(Schedulers.io());
    }
}
