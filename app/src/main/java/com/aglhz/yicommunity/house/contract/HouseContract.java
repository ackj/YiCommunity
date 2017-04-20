package com.aglhz.yicommunity.house.contract;


import com.aglhz.abase.mvp.contract.base.BaseContract;
import com.aglhz.abase.widget.selector.ISelectAble;
import com.aglhz.yicommunity.common.bean.BaseBean;

import java.util.List;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 房屋模块所对应的各层对象应有的接口。
 */
public interface HouseContract {

    interface View extends BaseContract.View {
        //刷新界面，响应社区返回的数据
        void responseCommunity(List<ISelectAble> communities);

        //刷新界面，响应楼栋返回的数据
        void responseBuilding(List<ISelectAble> buildings);

        //刷新界面，响应单元返回的数据
        void responseUnit(List<ISelectAble> units);

        //刷新界面，响应楼层返回的数据
        void responseFloor(List<ISelectAble> floors);

        //刷新界面，响应房间返回的数据
        void responseRoom(List<ISelectAble> rooms);


        //刷新界面，响应房间返回的数据
        void responseApply(BaseBean mBaseBean);
    }

    interface Presenter extends BaseContract.Presenter {
        //请求社区列表数据
        void requestCommunity(String province, String city, String county);

        //请求楼栋列表数据
        void requestBuilding(String communityCode);

        //请求楼栋列表数据
        void requestUnit(String communityCode, String buildingCode);

        //请求楼层列表数据
        void requestFloor(String communityCode, String buildingCode, String unitCode);

        //请求房间列表数据
        void requestRoom(String communityCode, String buildingCode, String unitCode, String floorCode);

        //请求房间列表数据
        void requestApply(boolean isProprietor, String cmnt_c, String bdg_c, String bdg_u_c, String bdg_f_c, String bdg_f_h_c, String applyName, String idNO);
    }

    interface Model extends BaseContract.Model {
    }
}