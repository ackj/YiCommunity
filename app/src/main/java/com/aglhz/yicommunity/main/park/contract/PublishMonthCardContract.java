package com.aglhz.yicommunity.main.park.contract;

import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.yicommunity.entity.bean.BaseBean;
import com.aglhz.yicommunity.entity.bean.CarCardBean;
import com.aglhz.yicommunity.entity.bean.CardRechargeBean;
import com.aglhz.yicommunity.entity.bean.MonthCardRuleBean;
import com.aglhz.yicommunity.entity.bean.MonthCardRuleListBean;
import com.aglhz.yicommunity.common.Params;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Author: LiuJia on 2017/6/2 0002 09:23.
 * Email: liujia95me@126.com
 */

public interface PublishMonthCardContract {

    interface View extends BaseContract.View {
        void responseRuleList(List<MonthCardRuleBean> datas);

        void responseSubmitSuccess(BaseBean bean);

        void responseCardRecharge(CardRechargeBean.DataBean bean);

        void responseCardPay(CarCardBean.DataBean bean);

        void responseALiPay(String order);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestSubmitMonthCard(Params params);

        void requestMonthCardRule(Params params);

        void requestCardPay(Params params);

        void requestCardRecharge(Params params);

        void requestCarCardOrder(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseBean> requestSubmitMonthCard(Params params);

        Observable<MonthCardRuleListBean> requestMonthCardRule(Params params);

        Observable<CarCardBean> requestCardPay(Params params);

        Observable<CardRechargeBean> requestCardRecharge(Params params);

        Observable<ResponseBody> requestCarCardOrder(Params params);

    }
}
