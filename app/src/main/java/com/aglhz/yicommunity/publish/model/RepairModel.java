package com.aglhz.yicommunity.publish.model;

import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.publish.contract.RepairContract;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author: LiuJia on 2017/5/9 0009 10:35.
 * Email: liujia95me@126.com
 */

public class RepairModel extends BaseModel implements RepairContract.Model {

    @Override
    public void start(Object request) {

    }

//    @Override
//    public Observable<BaseBean> postRepair(Params params) {
//        String url = "http://www.aglhz.com:8090/sub_property_ysq/client/repair";
//
//        MultipartBody.Builder builder = new MultipartBody.Builder();
//        builder.addFormDataPart ("token", params.token)
//                .addFormDataPart("cmnt_c", params.cmnt_c)
//                .addFormDataPart("contact", params.contact)
//                .addFormDataPart("des", params.des)
//                .addFormDataPart("type", params.type + "")
//                .addFormDataPart("single", params.single + "")
//                .addFormDataPart("name", params.name);
//        for (File file : params.files) {
//            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
//            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
//            builder.addFormDataPart("file", file.getName(), requestBody);
//        }
//
//        builder.setType(MultipartBody.FORM);
//        MultipartBody multipartBody = builder.build();
//        return HttpHelper.getService(ApiService.class).postRepair(url, multipartBody)
//                .subscribeOn(Schedulers.io());
////        return HttpHelper.getService(ApiService.class).postRepair(url,params.token,
////                params.cmnt_c, params.contact, params.des, params.name, params.single, params.type,body)
////                .subscribeOn(Schedulers.io());
//
//    }

    @Override
    public Observable<BaseBean> postRepair(Params params) {
        String url = "http://www.aglhz.com:8090/sub_property_ysq/client/repair";
        List<MultipartBody.Part> parts = new ArrayList<>();

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("token", params.token)
                .addFormDataPart("cmnt_c", params.cmnt_c)
                .addFormDataPart("contact", params.contact)
                .addFormDataPart("des", params.des)
                .addFormDataPart("type", params.type + "")
                .addFormDataPart("name", params.name)
                .build();

//        parts.add(MultipartBody.Part.create(requestBody));

        filesToMultipartBodyParts(parts, params.files);
        return HttpHelper.getService(ApiService.class).postRepair(url,
                params.token,
                params.cmnt_c, params.contact, params.des, params.name, params.single, params.type,parts)
                .subscribeOn(Schedulers.io());
    }

    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<MultipartBody.Part> parts, List<File> files) {
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }
}
