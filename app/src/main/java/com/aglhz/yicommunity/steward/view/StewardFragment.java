package com.aglhz.yicommunity.steward.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseLazyFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.ServiceApi;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.bean.IconBean;
import com.aglhz.yicommunity.bean.SipBean;
import com.aglhz.yicommunity.door.DoorActivity;
import com.aglhz.yicommunity.house.HouseActivity;
import com.aglhz.yicommunity.login.LoginActivity;
import com.aglhz.yicommunity.park.ParkActivity;
import com.aglhz.yicommunity.picker.PickerActivity;
import com.aglhz.yicommunity.publish.PropertyActivity;
import com.aglhz.yicommunity.qrcode.ScanQRCodeActivity;
import com.aglhz.yicommunity.steward.contract.StewardContract;
import com.aglhz.yicommunity.steward.presenter.StewardPresenter;
import com.aglhz.yicommunity.web.WebActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Author：leguang on 2017/4/13 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 负责管家模块的View层内容。
 */
public class StewardFragment extends BaseLazyFragment<StewardContract.Presenter> implements StewardContract.View {
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
    private ViewGroup rootView;
    private StewardRVAdapter myHouseAdapter;
    private StewardRVAdapter smartHomeAdapter;
    private StewardRVAdapter smartDoorAdapter;
    private StewardRVAdapter smartParkAdapter;
    private StewardRVAdapter propertyServiceAdapter;
    private List<IconBean> listMyhouses;
    private DialogPlus contactDialog;
    private boolean isShow;
    private final static int SELECT_COMMUNIT = 100;   //选择社区

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
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = (ViewGroup) _mActivity.findViewById(android.R.id.content).getRootView();
        initToolbar(toolbar);
    }

    /**
     * 懒加载
     */
    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            initData();
        }
    }

    private void initData() {
        initPtrFrameLayout(ptrFrameLayout);
        initRecyclerView();
        setListener();
    }

    private void initRecyclerView() {

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
        listSmartDoor.add(new IconBean(R.drawable.ic_call_door_green_140px, "呼叫门禁", ""));
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
        myHouseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ALog.e("adapter.getData().size()::" + adapter.getData().size());
                ALog.e("listMyhouses::" + listMyhouses);
                ALog.e("position::" + position);

                if (position == adapter.getData().size() - 1) {
                    //点击的最后一个item，此时应该跳转到添加房屋界面。
                    go2House(Constants.ADD_HOUSE, "");
                    ALog.e("1111111111");

                } else {
                    ALog.e("000000");
                    go2House(Constants.HOUSE_RIGHTS, listMyhouses.get(position).fid);
                    ALog.e("listMyhouses.get(position).fid::" + listMyhouses.get(position).fid);

                }
            }
        });

        //设置智能家居卡片点击事件。
        smartHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
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
            }
        });

        //设置智能门禁卡片点击事件。
        smartDoorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 3) {
                    mPresenter.requestGetSip("");
                } else {
                    go2SmartDoor(position);
                }
            }
        });

        //设置智慧停车卡片点击事件。
        smartParkAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                go2Park(position);
            }
        });

        //物业服务卡片点击事件。
        propertyServiceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 1) {
                    if (contactDialog == null) {
                        isShow = true;
                        mPresenter.requestContact(UserHelper.communityCode);
                    } else {
                        contactDialog.show();
                    }

                } else if (position == 0) {
                    if (checkToken()) {
//                        if (checkHasCommunity()) {
//                            startActivity(new Intent(getContext(), PropertyRepairActivity.class));

//                        }
                        go2PropertyService(position);
                    }

                } else if (position == 2) {
                    go2PropertyService(position);
                }
            }
        });
    }


    private boolean checkToken() {
        if (!UserHelper.isLogined()) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return false;
        }
        return true;
    }

    //是否已经选择社区
    private boolean checkHasCommunity() {
        if (!UserHelper.hasCommunity()) {
            Toast.makeText(getContext(), "需要先选择社区", Toast.LENGTH_SHORT).show();
            onSelectCommunity(null);
            return false;
        }
        return true;
    }

    public void onSelectCommunity(View view) {
        Intent intent = new Intent(getContext(), PickerActivity.class);
        startActivityForResult(intent, SELECT_COMMUNIT);
    }

    //跳转到智能门禁模块。
    private void go2SmartDoor(int position) {
        Intent intent = new Intent(_mActivity, DoorActivity.class);
        intent.putExtra(Constants.FROM_TO, position);
        startActivity(intent);
    }

    //跳转到物业模块。
    private void go2PropertyService(int position) {
        Intent intent = new Intent(_mActivity, PropertyActivity.class);
        intent.putExtra(Constants.FROM_TO, position);
        startActivity(intent);
    }

    //跳转到停车模块。
    private void go2Park(int position) {
        Intent intent = new Intent(_mActivity, ParkActivity.class);
        intent.putExtra(Constants.FROM_TO, position);
        startActivity(intent);
    }

    //跳转到添加房屋模块。
    private void go2House(int position, String fid) {
        Intent intent = new Intent(_mActivity, HouseActivity.class);
        intent.putExtra(Constants.FROM_TO, position);
        intent.putExtra(Constants.HOUSE_FID, fid);
        startActivity(intent);
    }

    //跳转到添加设备模块。
    private void go2AddDevice() {
        startActivity(new Intent(_mActivity, ScanQRCodeActivity.class));
    }


    private void go2DeviceStore() {

    }

    private void go2SmartDevice() {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra("title", "智能设备");
        intent.putExtra("link", ServiceApi.SMART_DEVICE);
        startActivity(intent);
    }

    private void initPtrFrameLayout(final PtrFrameLayout ptrFrameLayout) {
        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(BaseApplication.mContext, 15F), 0, DensityUtils.dp2px(BaseApplication.mContext, 10F));
        header.setPtrFrameLayout(ptrFrameLayout);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.autoRefresh(true);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //判断是否滑动到顶部。
                return svSteward != null && svSteward.getScrollY() == 0;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
//                mPresenter.start();

            }
        });
    }

//    @Override
//    public void end() {
//        ALog.e("end()…………………………………………");
//        ptrFrameLayout.refreshComplete();
//    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(rootView, "网络异常，请刷新！");
    }


    protected void initToolbar(Toolbar toolbar) {
        initStateBar(toolbar);
        toolbarTitle.setText("智能管家");

    }

    @Override
    public void onDestroy() {
        myHouseAdapter = null;
        smartHomeAdapter = null;
        smartDoorAdapter = null;
        smartParkAdapter = null;
        propertyServiceAdapter = null;
        super.onDestroy();
    }

    @Override
    public void responseHouses(List<IconBean> listIcons) {
        ptrFrameLayout.refreshComplete();
        listIcons.add(new IconBean(R.drawable.ic_add_house_red_140px, "添加房屋", ""));
        listMyhouses.clear();
        listMyhouses = listIcons;
        myHouseAdapter.setNewData(listIcons);
        for (IconBean listIcon : listIcons) {
            ALog.e("fid**" + listIcon.fid);
        }
    }

    @Override
    public void responseContact(final List<String> listPhone) {
        if (contactDialog == null) {
            contactDialog = DialogPlus.newDialog(_mActivity)
                    .setHeader(R.layout.dialog_header)
                    .setFooter(R.layout.dialog_footer)
                    .setContentHolder(new ListHolder())
                    .setGravity(Gravity.BOTTOM)
                    .setAdapter(new ArrayAdapter<>(_mActivity, android.R.layout.simple_list_item_1, listPhone))
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                            ALog.e("listPhone.get(position).substring(2)::" + listPhone.get(position).substring(3));
                            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + listPhone.get(position).substring(3))));
                        }
                    })
                    .setCancelable(true)
                    .create();
//            ((TextView) ((LinearLayout) contactDialog.getHeaderView()).getChildAt(0)).setText("电话：");
        }

        if (isShow) {
            contactDialog.show();
        }
    }

    @Override
    public void responseGetSip(SipBean mSipBean) {
        DialogPlus.newDialog(_mActivity)
                .setHeader(R.layout.dialog_header)
                .setFooter(R.layout.dialog_footer)
                .setContentHolder(new ListHolder())
                .setGravity(Gravity.BOTTOM)
                .setAdapter(new ArrayAdapter<>(_mActivity, android.R.layout.simple_list_item_1, mSipBean.getData().getPowers()))
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

                    }
                })
                .setCancelable(true)
                .create()
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == SELECT_COMMUNIT) {
                UserHelper.setCommunity(data.getStringExtra(Constants.COMMUNITY_NAME)
                        , data.getStringExtra(Constants.COMMUNITY_CODE));
            }
        }
    }
}


