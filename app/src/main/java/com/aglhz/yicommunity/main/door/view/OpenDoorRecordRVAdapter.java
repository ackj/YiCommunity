package com.aglhz.yicommunity.main.door.view;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.OpenDoorWay;
import com.aglhz.yicommunity.entity.bean.OpenDoorRecordBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author: LiuJia on 2017/4/21 11:11.
 * Email: liujia95me@126.com
 */
public class OpenDoorRecordRVAdapter extends BaseRecyclerViewAdapter<OpenDoorRecordBean.DataBean, BaseViewHolder> {


    public OpenDoorRecordRVAdapter() {
        super(R.layout.item_opendoor_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenDoorRecordBean.DataBean item) {
        String unlockway;
        switch (item.getAccessWay()) {
            case OpenDoorWay.ADAW_CALL://通话开锁
                unlockway = "通话";
                break;
            case OpenDoorWay.ADAW_MONITOR://监视开锁
                unlockway = "监视";
                break;
            case OpenDoorWay.ADAW_CARD://刷卡开锁
                unlockway = "刷卡";
                break;
            case OpenDoorWay.ADAW_PASSWORD://密码开锁
                unlockway = "密码";
                break;
            case OpenDoorWay.ADAW_NOTIFICATION://通知开锁
                unlockway = "通知";
                break;
            case OpenDoorWay.ADAW_CALL_CENTER://中心机通话开锁
                unlockway = "中心机通话";
                break;
            case OpenDoorWay.ADAW_CALL_INDOOR://室内机通话开锁
                unlockway = "室内机通话";
                break;
            case OpenDoorWay.ADAW_CALL_APPS://移动App通话开锁
                unlockway = "App通话";
                break;
            case OpenDoorWay.ADAW_CALL_PHONE://手机通话开锁
                unlockway = "手机通话";
                break;
            case OpenDoorWay.ADAW_CALL_TELEPHONE://固定电话通话开
                unlockway = "固定电话通话";
                break;
            case OpenDoorWay.ADAW_CALL_GW://网关通话开锁
                unlockway = "网关通话";
                break;
            case OpenDoorWay.ADAW_MONITOR_CENTER://中心监视开锁
                unlockway = "中心监视";
                break;
            case OpenDoorWay.ADAW_MONITOR_INDOOR://室内机监视开锁
                unlockway = "室内机监视";
                break;
            case OpenDoorWay.ADAW_CARD_ICID://ID卡刷卡开锁
                unlockway = "ID卡刷卡";
                break;
            case OpenDoorWay.ADAW_CARD_IDENTITY://身份证刷卡开锁
                unlockway = "身份证刷卡";
                break;
            case OpenDoorWay.ADAW_CARD_REDIDENCE://居住证刷卡开锁
                unlockway = "居住证刷卡";
                break;
            case OpenDoorWay.ADAW_CARD_CITIZEN://市民卡刷卡开锁
                unlockway = "市民卡刷卡";
                break;
            case OpenDoorWay.ADAW_CARD_QR_CODE://二维码刷卡开锁
                unlockway = "二维码刷卡";
                break;
            case OpenDoorWay.ADAW_PASSWORD_PUBLIC://公共密码开锁
                unlockway = "公共密码";
                break;
            case OpenDoorWay.ADAW_CARD_PRIVATE://私有密码开锁
                unlockway = "私有密码";
                break;
            case OpenDoorWay.ADAW_PASSWORD_DURESS://胁迫密码开锁
                unlockway = "胁迫密码";
                break;
            case OpenDoorWay.ADAW_APP_NOTIFIFY://移动APP钥匙包开锁
                unlockway = "APP钥匙包";
                break;
            case OpenDoorWay.ADAW_PASSWORD_TEMP://临时密码开锁
                unlockway = "临时密码";
                break;
            default:
                unlockway = "其他";
                break;
        }

        helper.setText(R.id.tv_door_name, item.getDeviceName())
                .setText(R.id.tv_unlock_way, unlockway)
                .setText(R.id.tv_date, item.getAccessTime())
                .setText(R.id.tv_opendoor_man, item.getUserName());
    }
}
