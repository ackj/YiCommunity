package com.aglhz.yicommunity.event;

import com.aglhz.yicommunity.bean.ParkSelectBean;

/**
 * Author: LiuJia on 2017/5/23 0023 15:16.
 * Email: liujia95me@126.com
 */

public class EventPark {

    public ParkSelectBean.DataBean.ParkPlaceListBean bean;

    public EventPark(ParkSelectBean.DataBean.ParkPlaceListBean bean) {
        this.bean = bean;
    }

}
