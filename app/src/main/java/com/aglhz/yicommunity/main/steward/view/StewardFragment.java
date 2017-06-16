package com.aglhz.yicommunity.main.steward.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.DoorListBean;
import com.aglhz.yicommunity.bean.IconBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.DoorManager;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.login.LoginActivity;
import com.aglhz.yicommunity.main.MainActivity;
import com.aglhz.yicommunity.main.door.DoorActivity;
import com.aglhz.yicommunity.main.house.HouseActivity;
import com.aglhz.yicommunity.main.park.ParkActivity;
import com.aglhz.yicommunity.main.picker.PickerActivity;
import com.aglhz.yicommunity.main.publish.PropertyActivity;
import com.aglhz.yicommunity.main.smarthome.view.GoodsCategoryFragment;
import com.aglhz.yicommunity.main.steward.contract.StewardContract;
import com.aglhz.yicommunity.main.steward.presenter.StewardPresenter;
import com.aglhz.yicommunity.qrcode.ScanQRCodeActivity;
import com.aglhz.yicommunity.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author：leguang on 2017/4/13 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 负责管家模块的View层内容。
 * 此类不能用懒加载，因为在未进入此页面，PtrFrameLayout尚未加载的完全的时候，
 * 由于切换小区，这个页面的EventBus会调用下拉刷新，导致再进入此页面后，下拉刷新无法归为，基本瘫痪。
 */
public class StewardFragment extends BaseFragment<StewardContract.Presenter> implements StewardContract.View {
    private static final String TAG = StewardFragment.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_my_house)
    RecyclerView rvMyHouse;
    @BindView(R.id.rv_smart_home)
    RecyclerView rvSmartHome;
    @BindView(R.id.rv_smart_door)
    RecyclerView rvSmartDoor;
    @BindView(R.id.rv_smart_park)
    RecyclerView rvSmartPark;
    @BindView(R.id.rv_property_service)
    RecyclerView rvPropertyService;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.sv_steward_fragment)
    ScrollView svSteward;

    private StewardRVAdapter myHouseAdapter;
    private StewardRVAdapter smartHomeAdapter;
    private StewardRVAdapter smartDoorAdapter;
    private StewardRVAdapter smartParkAdapter;
    private StewardRVAdapter propertyServiceAdapter;
    private List<IconBean> listMyhouses;
    private Params params = Params.getInstance();
    private final static int SELECT_COMMUNIT = 100;   //选择社区
    private Unbinder unbinder;

    public static StewardFragment newInstance() {
        return new StewardFragment();
    }

    @NonNull
    @Override
    protected StewardContract.Presenter createPresenter() {
        return new StewardPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steward, container, false);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar(toolbar);
        initPtrFrameLayout(ptrFrameLayout, svSteward);
        initData();
        setListener();
    }

    private void initData() {
        //我的房屋卡片
        rvMyHouse.setLayoutManager(new GridLayoutManager(_mActivity, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;//禁止RecyclerView的滑动，避免嵌套ScorllView时滑动卡顿。
            }
        });
        rvMyHouse.setAdapter(myHouseAdapter = new StewardRVAdapter());
        listMyhouses = new ArrayList<IconBean>();
        listMyhouses.add(new IconBean(R.drawable.ic_add_house_red_140px, "添加房屋", ""));
        myHouseAdapter.setNewData(listMyhouses);
        //智能家居卡片
        rvSmartHome.setLayoutManager(new GridLayoutManager(_mActivity, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvSmartHome.setAdapter(smartHomeAdapter = new StewardRVAdapter());
        List<IconBean> listSmartHome = new ArrayList<IconBean>();
        listSmartHome.add(new IconBean(R.drawable.ic_smart_device_blue_140px, "智能设备", ""));
        listSmartHome.add(new IconBean(R.drawable.ic_smart_store_blue_140px, "智能设备商城", ""));
        listSmartHome.add(new IconBean(R.drawable.ic_add_smart_blue_140px, "添加主机", ""));
        smartHomeAdapter.setNewData(listSmartHome);

        //智慧门禁卡片
        rvSmartDoor.setLayoutManager(new GridLayoutManager(_mActivity, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvSmartDoor.setAdapter(smartDoorAdapter = new StewardRVAdapter());
        List<IconBean> listSmartDoor = new ArrayList<IconBean>();
        listSmartDoor.add(new IconBean(R.drawable.ic_key_green_140px_140px, "设置开门", ""));
        listSmartDoor.add(new IconBean(R.drawable.ic_open_door_green_140px, "指定开门", ""));
        listSmartDoor.add(new IconBean(R.drawable.ic_password_open_door_green_140px, "密码开门", ""));
        listSmartDoor.add(new IconBean(R.drawable.ic_call_door_green_140px, "门禁监控", ""));
        listSmartDoor.add(new IconBean(R.drawable.ic_open_recording_green_140px, "开门记录", ""));
        smartDoorAdapter.setNewData(listSmartDoor);

        //智慧停车卡片
        rvSmartPark.setLayoutManager(new GridLayoutManager(_mActivity, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvSmartPark.setAdapter(smartParkAdapter = new StewardRVAdapter());
        List<IconBean> listSmartPark = new ArrayList<IconBean>();
        listSmartPark.add(new IconBean(R.drawable.ic_car_card_200px, "我的车卡", ""));
        listSmartPark.add(new IconBean(R.drawable.ic_stop_record_140px, "停车记录", ""));
        listSmartPark.add(new IconBean(R.drawable.ic_add_car_card_200px, "办理车卡", ""));
        smartParkAdapter.setNewData(listSmartPark);

        //物业服务卡片
        rvPropertyService.setLayoutManager(new GridLayoutManager(_mActivity, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPropertyService.setAdapter(propertyServiceAdapter = new StewardRVAdapter());
        List<IconBean> listPropertyService = new ArrayList<IconBean>();
        listPropertyService.add(new IconBean(R.drawable.ic_repair_orange_140px, "物业报修", ""));
        listPropertyService.add(new IconBean(R.drawable.ic_call_property_orange_140px, "联系物业", ""));
        listPropertyService.add(new IconBean(R.drawable.ic_property_complaints_orange_140px, "管理投诉", ""));
        propertyServiceAdapter.setNewData(listPropertyService);

    }

    private void setListener() {

        //设置我的房屋卡片的点击事件。
        myHouseAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position == adapter.getData().size() - 1) {
                //点击的最后一个item，此时应该跳转到添加房屋界面。
                go2House(Constants.ADD_HOUSE, listMyhouses.get(position).title, "");
            } else {
                go2House(Constants.HOUSE_RIGHTS, listMyhouses.get(position).title, listMyhouses.get(position).fid);
            }
        });

        //设置智能家居卡片点击事件。
        smartHomeAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
                    go2SmartDevice();
                    break;
                case 1:
                    go2DeviceStore();
                    break;
                case 2:
                    go2AddDevice();
                    break;
            }
        });

        //设置智能门禁卡片点击事件。
        smartDoorAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position == 3) {
                showLoadingDialog();
                mPresenter.requestDoors(params);
            } else {
                go2SmartDoor(position);
            }
        });

        //设置智慧停车卡片点击事件。
        smartParkAdapter.setOnItemClickListener((adapter, view, position) -> {
            go2Park(position);
        });

        //物业服务卡片点击事件。
        propertyServiceAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (!hasTokenAndCommunity()) {
                return;
            }
            if (position == 1) {
                params.cmnt_c = UserHelper.communityCode;
                showLoadingDialog();
                mPresenter.requestContact(params);
            } else {
                go2PropertyService(position);
            }
        });
    }


    private boolean hasTokenAndCommunity() {
        if (!UserHelper.isLogined()) {
            startActivity(new Intent(_mActivity, LoginActivity.class));
            return false;
        } else if (!UserHelper.hasCommunity()) {
            DialogHelper.warningSnackbar(getView(), "需要先选择社区！");
            Intent intent = new Intent(_mActivity, PickerActivity.class);
            startActivityForResult(intent, SELECT_COMMUNIT);
            return false;
        } else {
            return true;
        }
    }

    //跳转到智能门禁模块。
    private void go2SmartDoor(int position) {
        Intent intent = new Intent(_mActivity, DoorActivity.class);
        intent.putExtra(Constants.KEY_FROM_TO, position);
        startActivity(intent);
    }

    //跳转到物业模块。
    private void go2PropertyService(int position) {
        Intent intent = new Intent(_mActivity, PropertyActivity.class);
        intent.putExtra(Constants.KEY_FROM_TO, position);
        startActivity(intent);
    }

    //跳转到停车模块。
    private void go2Park(int position) {
        Intent intent = new Intent(_mActivity, ParkActivity.class);
        intent.putExtra(Constants.KEY_FROM_TO, position);
        startActivity(intent);
    }

    //跳转到添加房屋模块。
    private void go2House(int position, String address, String fid) {
        Intent intent = new Intent(_mActivity, HouseActivity.class);
        intent.putExtra(Constants.KEY_FROM_TO, position);
        intent.putExtra(Constants.KEY_FID, fid);
        intent.putExtra(Constants.KEY_ADDRESS, address);
        startActivity(intent);
    }

    //跳转到添加设备模块。
    private void go2AddDevice() {
        startActivity(new Intent(_mActivity, ScanQRCodeActivity.class));
    }


    private void go2DeviceStore() {
        _mActivity.start(GoodsCategoryFragment.newInstance());
    }

    private void go2SmartDevice() {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra(Constants.KEY_TITLE, "智能设备");
        intent.putExtra(Constants.KEY_LINK, ApiService.SMART_DEVICE);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        params.token = UserHelper.token;
        params.cmnt_c = UserHelper.communityCode;
        mPresenter.start(params);
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        dismissLoadingDialog();
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    protected void initToolbar(Toolbar toolbar) {
        initStateBar(toolbar);
        toolbarTitle.setText("智能管家");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        if (myHouseAdapter != null) {
            myHouseAdapter = null;
        }
        if (smartHomeAdapter != null) {
            smartHomeAdapter = null;
        }

        if (smartDoorAdapter != null) {
            smartDoorAdapter = null;
        }

        if (smartParkAdapter != null) {
            smartParkAdapter = null;
        }

        if (propertyServiceAdapter != null) {
            propertyServiceAdapter = null;
        }

        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void responseHouses(List<IconBean> listIcons) {
        ptrFrameLayout.refreshComplete();
        listMyhouses.clear();
        listMyhouses = listIcons;
        myHouseAdapter.setNewData(listIcons);
    }

    @Override
    public void responseContact(String[] arrayPhones) {
        dismissLoadingDialog();
        new AlertDialog.Builder(_mActivity)
                .setTitle("联系方式")
                .setItems(arrayPhones, (dialog, which) -> {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + arrayPhones[which].substring(3))));
                }).show();
    }

    @Override
    public void responseDoors(DoorListBean bean) {
        dismissLoadingDialog();
        if (bean == null || bean.getData() == null || bean.getData().isEmpty()) {
            DialogHelper.errorSnackbar(getView(), "没找到门禁");
            return;
        }

        String[] arrayDoors = new String[bean.getData().size()];

        for (int i = 0; i < bean.getData().size(); i++) {
            arrayDoors[i] = bean.getData().get(i).getName();
        }

        new AlertDialog.Builder(_mActivity)
                .setTitle("选择门禁")
                .setItems(arrayDoors, (dialog, which) -> {
                    params.dir = bean.getData().get(which).getDir();
//                    params.powerCode = "RemoteWatch";
                    params.powerCode = Constants.PERMISSION_REMOTEWATCH;
                    mPresenter.requestCheckPermission(params);
                    showLoadingDialog();
                })
                .show();
    }

    @Override
    public void responseCheckPermission(BaseBean mBaseBean) {
        dismissLoadingDialog();
        DoorManager
                .getInstance()
                .callOut(params.dir);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            if (requestCode == SELECT_COMMUNIT) {
//
//                ALog.e("11111111111111");
//                UserHelper.setCommunity(data.getStringExtra(Constants.COMMUNITY_NAME)
//                        , data.getStringExtra(Constants.COMMUNITY_CODE));
//            }
//        }
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        svSteward.fullScroll(ScrollView.FOCUS_UP);
        ptrFrameLayout.postDelayed(() -> ptrFrameLayout.autoRefresh(), 100);
    }
}


