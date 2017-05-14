package com.aglhz.yicommunity.mypublish.presenter;


import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.ServiceApi;
import com.aglhz.yicommunity.mypublish.contract.UnusedExchangeContract;


/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责邻里模块Presenter层内容。
 */

public class UnusedExchangePresenter extends BasePresenter<UnusedExchangeContract.View, UnusedExchangeContract.Model> implements UnusedExchangeContract.Presenter {
    private final String TAG = UnusedExchangePresenter.class.getSimpleName();

    //请求第几页
    private String goodsChangeUrl;

    public UnusedExchangePresenter(UnusedExchangeContract.View mView) {
        super(mView);
        initGoodsChangeUrl();
    }


    //邻里圈第一次赋值接口地址
    private void initGoodsChangeUrl() {
        goodsChangeUrl = ServiceApi.PROPERTY_CODE + "/neighbor/exchange/to-client/exchange-list?page=1&pageSize=10";
    }

    @Override
    public void start(Object request) {

    }


//    @Override
//    public boolean nextGoodsChange() {
//        if (goodsChangeUrl.isEmpty()) {
//            return false;
//        }
//        HttpClient.post(goodsChangeUrl, params, new BeanCallback<GoodsChangeListBean>() {
//            @Override
//            public void onError(String errMsg) {
//                getView().onNetErr(errMsg);
//            }
//
//            @Override
//            public void onSuccess(GoodsChangeListBean bean) {
//                if (bean.getOther().getCode() == 200) {
//                    goodsChangeUrl = bean.getOther().getNext();
//                    getView().nextGoodsChangeResult(bean.getData().getExchangeList());
//                }
//            }
//        });
//        return true;
//    }

//    @Override
//    public void refreshGoodsChange() {
//        initGoodsChangeUrl();
//        HttpClient.post(goodsChangeUrl, params, new BeanCallback<GoodsChangeListBean>() {
//            @Override
//            public void onError(String errMsg) {
//                getView().onNetErr(errMsg);
//            }
//
//            @Override
//            public void onSuccess(GoodsChangeListBean bean) {
//                if (bean.getOther().getCode() == 200) {
//                    goodsChangeUrl = bean.getOther().getNext();
//                    getView().refreshGoodsChangeResult(bean.getData().getExchangeList());
//                }
//            }
//        });
//    }
}