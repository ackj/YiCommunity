package com.aglhz.yicommunity.main.publish.model;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.model.base.BaseModel;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.IconBean;
import com.aglhz.yicommunity.bean.RepairTypesBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.publish.contract.PublishContract;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author: LiuJia on 2017/5/9 0009 10:35.
 * Email: liujia95me@126.com
 */

public class RepairModel extends BaseModel implements PublishContract.Model {

    @Override
    public void start(Object request) {

    }

    @Override
    public Observable<BaseBean> requestSubmit(Params params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("token", params.token);
        builder.addFormDataPart("cmnt_c", params.cmnt_c);
        builder.addFormDataPart("contact", params.contact);
        builder.addFormDataPart("des", params.des);
        builder.addFormDataPart("name", params.name);
        builder.addFormDataPart("single", params.single + "");
        builder.addFormDataPart("type", params.repairType);
        builder.addFormDataPart("ofid", params.ofid);

        ALog.d("PublishNeighbourModel", "上传=============");

        if (params.files != null && params.files.size() > 0) {
            for (File f : params.files) {
                ALog.d("PublishNeighbourModel", "上传图片：" + f.getAbsolutePath());
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), f);
                builder.addFormDataPart("file", f.getName(), requestBody);
            }
        }
        return HttpHelper.getService(ApiService.class).postRepair(ApiService.postRepair,
                builder.build())
                .subscribeOn(Schedulers.io());
    }

    public Single<List<IconBean>> requestHouses(Params params) {
        return HttpHelper.getService(ApiService.class)
                .requestMyhouses(ApiService.requestMyhouses, params.token, params.cmnt_c)
                .map(myHousesBean -> myHousesBean.getData().getAuthBuildings())
                .flatMap(Flowable::fromIterable)
                .map(bean -> new IconBean(R.drawable.ic_my_house_red_140px, bean.getAddress(), bean.getFid()))
                .toList()
                .subscribeOn(Schedulers.io());
    }

    public Observable<RepairTypesBean> requestRepairTypes(Params params){
        return HttpHelper.getService(ApiService.class)
                .requestRepairTypes(ApiService.requestRepairTypes,params.type,params.cmnt_c)
                .subscribeOn(Schedulers.io());
    }
}
