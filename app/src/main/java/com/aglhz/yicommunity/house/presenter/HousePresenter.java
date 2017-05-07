package com.aglhz.yicommunity.house.presenter;

import android.support.annotation.NonNull;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.presenter.base.BasePresenter;
import com.aglhz.yicommunity.common.BaseParams;
import com.aglhz.yicommunity.common.ServiceApi;
import com.aglhz.yicommunity.house.contract.HouseContract;
import com.aglhz.yicommunity.house.model.HouseModel;

import java.util.Map;

/**
 * Author：leguang on 2016/10/9 0009 10:35
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块Presenter层内容。
 */

public class HousePresenter extends BasePresenter<HouseContract.View, HouseContract.Model> implements HouseContract.Presenter {
    private final String TAG = HousePresenter.class.getSimpleName();

    public HousePresenter(HouseContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected HouseContract.Model createModel() {
        return new HouseModel();
    }


    @Override
    public void requestCommunity(String province, String city, String county) {
        Map params = BaseParams.getScMap();
        params.put("page", 1);
        params.put("pageSize", 10000);
        params.put("province", province);
        params.put("city", city);
        params.put("county", county);
//        HttpHelper.post(ServiceApi.communityList, params, new BeanCallback<CommunitySelectBean>() {
//            @Override
//            public void onError(String errMsg) {
//
//            }
//
//            @Override
//            public void onSuccess(final CommunitySelectBean bean) {
//                if (bean.getOther().getCode() == 200) {
//                    if (bean.getData() != null && isViewAttached()) {
//                        ArrayList<ISelectAble> communities = new ArrayList<>();
//
//                        if (bean.getData().getCommunities().size() > 0) {
//                            for (int i = 0; i < bean.getData().getCommunities().size(); i++) {
//                                final int finalI = i;
//                                communities.add(new ISelectAble() {
//                                    @Override
//                                    public String getName() {
//                                        return bean.getData().getCommunities().get(finalI).getName();
//                                    }
//
//                                    @Override
//                                    public int getId() {
//                                        return finalI;
//                                    }
//
//                                    @Override
//                                    public Object getArg() {
//                                        return bean.getData().getCommunities().get(finalI).getCode();
//                                    }
//                                });
//                            }
//                        } else {
//                            communities.add(new ISelectAble() {
//                                @Override
//                                public String getName() {
//                                    return "";
//                                }
//
//                                @Override
//                                public int getId() {
//                                    return 0;
//                                }
//
//                                @Override
//                                public Object getArg() {
//                                    return this;
//                                }
//                            });
//                        }
//                        getView().responseCommunity(communities);
//                    }
//                }
//            }
//        });
    }

    @Override
    public void requestBuilding(String communityCode) {
        Map params = BaseParams.getScMap();
        params.put("cmnt_c", communityCode);
//        HttpHelper.post(ServiceApi.BUILDING_LIST, params, new BeanCallback<BuildingBean>() {
//            @Override
//            public void onError(String errMsg) {
//
//                if (isViewAttached()) {
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(final BuildingBean bean) {
//
//                if (bean.getOther().getCode() == 200) {
//                    if (bean.getData() != null && isViewAttached()) {
//                        ArrayList<ISelectAble> buildings = new ArrayList<>();
//
//                        if (bean.getData().getBuildings().size() > 0) {
//                            for (int i = 0; i < bean.getData().getBuildings().size(); i++) {
//                                final int finalI = i;
//                                buildings.add(new ISelectAble() {
//                                    @Override
//                                    public String getName() {
//                                        return bean.getData().getBuildings().get(finalI).getName();
//                                    }
//
//                                    @Override
//                                    public int getId() {
//                                        return finalI;
//                                    }
//
//                                    @Override
//                                    public Object getArg() {
//                                        return bean.getData().getBuildings().get(finalI).getCode();
//                                    }
//                                });
//                            }
//                        } else {
//                            buildings.add(new ISelectAble() {
//                                @Override
//                                public String getName() {
//                                    return "";
//                                }
//
//                                @Override
//                                public int getId() {
//                                    return 0;
//                                }
//
//                                @Override
//                                public Object getArg() {
//                                    return this;
//                                }
//                            });
//                        }
//                        getView().responseBuilding(buildings);
//                    }
//                }
//            }
//        });
    }

    @Override
    public void requestUnit(String communityCode, String buildingCode) {
        Map params = BaseParams.getScMap();
        params.put("cmnt_c", communityCode);
        params.put("bdg_c", buildingCode);

//
//        HttpHelper.post(ServiceApi.UNIT_LIST, params, new BeanCallback<UnitBean>() {
//            @Override
//            public void onError(String errMsg) {
//
//                if (isViewAttached()) {
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(final UnitBean bean) {
//
//                if (bean.getOther().getCode() == 200) {
//                    if (bean.getData() != null && isViewAttached()) {
//                        ArrayList<ISelectAble> units = new ArrayList<>();
//
//                        if (bean.getData().getBuildingUnits().size() > 0) {
//                            for (int i = 0; i < bean.getData().getBuildingUnits().size(); i++) {
//                                final int finalI = i;
//                                units.add(new ISelectAble() {
//                                    @Override
//                                    public String getName() {
//                                        return bean.getData().getBuildingUnits().get(finalI).getName();
//                                    }
//
//                                    @Override
//                                    public int getId() {
//                                        return finalI;
//                                    }
//
//                                    @Override
//                                    public Object getArg() {
//                                        return bean.getData().getBuildingUnits().get(finalI).getCode();
//                                    }
//                                });
//                            }
//                        } else {
//                            units.add(new ISelectAble() {
//                                @Override
//                                public String getName() {
//                                    return "";
//                                }
//
//                                @Override
//                                public int getId() {
//                                    return 0;
//                                }
//
//                                @Override
//                                public Object getArg() {
//                                    return this;
//                                }
//                            });
//                        }
//                        getView().responseUnit(units);
//                    }
//                }
//            }
//        });
    }

    @Override
    public void requestFloor(String communityCode, String buildingCode, String unitCode) {
        Map params = BaseParams.getScMap();
        params.put("cmnt_c", communityCode);
        params.put("bdg_c", buildingCode);
        params.put("bdg_u_c", unitCode);


//        HttpHelper.post(ServiceApi.FLOOR_LIST, params, new BeanCallback<FloorBean>() {
//            @Override
//            public void onError(String errMsg) {
//
//                if (isViewAttached()) {
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(final FloorBean bean) {
//
//                if (bean.getOther().getCode() == 200) {
//                    if (bean.getData() != null && isViewAttached()) {
//                        ArrayList<ISelectAble> floors = new ArrayList<>();
//
//                        if (bean.getData().getFloors().size() > 0) {
//                            for (int i = 0; i < bean.getData().getFloors().size(); i++) {
//                                final int finalI = i;
//                                floors.add(new ISelectAble() {
//                                    @Override
//                                    public String getName() {
//                                        return bean.getData().getFloors().get(finalI).getName();
//                                    }
//
//                                    @Override
//                                    public int getId() {
//                                        return finalI;
//                                    }
//
//                                    @Override
//                                    public Object getArg() {
//                                        return bean.getData().getFloors().get(finalI).getCode();
//                                    }
//                                });
//                            }
//                        } else {
//                            floors.add(new ISelectAble() {
//                                @Override
//                                public String getName() {
//                                    return "";
//                                }
//
//                                @Override
//                                public int getId() {
//                                    return 0;
//                                }
//
//                                @Override
//                                public Object getArg() {
//                                    return this;
//                                }
//                            });
//                        }
//                        getView().responseFloor(floors);
//                    }
//                }
//            }
//        });
    }

    @Override
    public void requestRoom(String communityCode, String buildingCode, String unitCode, String floorCode) {
        Map params = BaseParams.getScMap();
        params.put("cmnt_c", communityCode);
        params.put("bdg_c", buildingCode);
        params.put("bdg_u_c", unitCode);
        params.put("bdg_f_c", floorCode);


//        HttpHelper.post(ServiceApi.ROOM_LIST, params, new BeanCallback<RoomBean>() {
//            @Override
//            public void onError(String errMsg) {
//
//                if (isViewAttached()) {
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(final RoomBean bean) {
//
//                if (bean.getOther().getCode() == 200) {
//                    if (bean.getData() != null && isViewAttached()) {
//                        ArrayList<ISelectAble> rooms = new ArrayList<>();
//
//                        if (bean.getData().getHouses().size() > 0) {
//                            for (int i = 0; i < bean.getData().getHouses().size(); i++) {
//                                final int finalI = i;
//                                rooms.add(new ISelectAble() {
//                                    @Override
//                                    public String getName() {
//                                        return bean.getData().getHouses().get(finalI).getName();
//                                    }
//
//                                    @Override
//                                    public int getId() {
//                                        return finalI;
//                                    }
//
//                                    @Override
//                                    public Object getArg() {
//                                        return bean.getData().getHouses().get(finalI).getCode();
//                                    }
//                                });
//                            }
//                        } else {
//                            rooms.add(new ISelectAble() {
//                                @Override
//                                public String getName() {
//                                    return "";
//                                }
//
//                                @Override
//                                public int getId() {
//                                    return 0;
//                                }
//
//                                @Override
//                                public Object getArg() {
//                                    return this;
//                                }
//                            });
//                        }
//                        getView().responseRoom(rooms);
//                    }
//                }
//            }
//        });
    }

    @Override
    public void requestApply(boolean isProprietor, String cmnt_c, final String bdg_c, String bdg_u_c, String bdg_f_c, String bdg_f_h_c, String applyName, String idNO) {
        Map<String, Object> param = BaseParams.getTokenMap();
        param.put("cmnt_c", cmnt_c);
        param.put("bdg_c", bdg_c);
        param.put("bdg_u_c", bdg_u_c);
        param.put("bdg_f_c", bdg_f_c);
        param.put("bdg_f_h_c", bdg_f_h_c);
        param.put("applyName", applyName);
        param.put("idNO", idNO);


        ALog.e(param.get("cmnt_c"));
        ALog.e(param.get("bdg_c"));
        ALog.e(param.get("bdg_u_c"));
        ALog.e(param.get("bdg_f_c"));
        ALog.e(param.get("bdg_f_h_c"));
        ALog.e(param.get("applyName"));
        ALog.e(param.get("idNO"));
        ALog.e(ServiceApi.apply(isProprietor));
//
//        HttpHelper.post(ServiceApi.apply(isProprietor), param, new BeanCallback<BaseBean>() {
//            @Override
//            public void onError(String errMsg) {
//
//                if (isViewAttached()) {
//                    getView().error(null);
//                }
//            }
//
//            @Override
//            public void onSuccess(BaseBean mBaseBean) {
//                if (mBaseBean.getOther().getCode() == 200) {
//
//                    ALog.a("申请成功");
//                    if (isViewAttached()) {
//                        getView().responseApply(mBaseBean);
//                    }
//                }
//            }
//        });
    }

    @Override
    public void start(Object request) {

    }
}