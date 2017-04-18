package com.aglhz.yicommunity.steward.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseLazyFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.ServiceApi;
import com.aglhz.yicommunity.common.bean.IconBean;
import com.aglhz.yicommunity.steward.contract.StewardContract;
import com.aglhz.yicommunity.steward.presenter.StewardPresenter;
import com.aglhz.yicommunity.web.WebActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

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
    private ArrayList<IconBean> listMyhouses;

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
        rvMyHouse.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        rvMyHouse.setAdapter(myHouseAdapter = new StewardRVAdapter());
        listMyhouses = new ArrayList<IconBean>();
        listMyhouses.add(new IconBean(R.drawable.ic_add_house_red_140px, "添加主机"));
        myHouseAdapter.setNewData(listMyhouses);

        //智能家居卡片
        rvSmartHome.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        rvSmartHome.setAdapter(smartHomeAdapter = new StewardRVAdapter());
        List<IconBean> listSmartHome = new ArrayList<IconBean>();
        listSmartHome.add(new IconBean(R.drawable.ic_smart_device_blue_140px, "智能设备"));
        listSmartHome.add(new IconBean(R.drawable.ic_smart_store_blue_140px, "智能设备商城"));
        listSmartHome.add(new IconBean(R.drawable.ic_add_smart_blue_140px, "添加主机"));

        smartHomeAdapter.setNewData(listSmartHome);

        //智慧门禁卡片
        rvSmartDoor.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        rvSmartDoor.setAdapter(smartDoorAdapter = new StewardRVAdapter());
        List<IconBean> listSmartDoor = new ArrayList<IconBean>();
        listSmartDoor.add(new IconBean(R.drawable.ic_bluetooth_green_140px, "蓝牙开门"));
        listSmartDoor.add(new IconBean(R.drawable.ic_open_door_green_140px, "点击开门"));
        listSmartDoor.add(new IconBean(R.drawable.ic_password_open_door_green_140px, "密码开门"));
        listSmartDoor.add(new IconBean(R.drawable.ic_call_door_green_140px, "呼叫门禁"));
        listSmartDoor.add(new IconBean(R.drawable.ic_open_recording_green_140px, "开门记录"));
        smartDoorAdapter.setNewData(listSmartDoor);


        //智慧停车卡片
        rvSmartPark.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        rvSmartPark.setAdapter(smartParkAdapter = new StewardRVAdapter());
        List<IconBean> listSmartPark = new ArrayList<IconBean>();
        listSmartPark.add(new IconBean(R.drawable.ic_car_card_200px, "我的车卡"));
        listSmartPark.add(new IconBean(R.drawable.ic_stop_record_140px, "停车记录"));
        listSmartPark.add(new IconBean(R.drawable.ic_add_car_card_200px, "办理车卡"));
        smartParkAdapter.setNewData(listSmartPark);

        //物业服务卡片
        rvPropertyService.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        rvPropertyService.setAdapter(propertyServiceAdapter = new StewardRVAdapter());
        List<IconBean> listPropertyService = new ArrayList<IconBean>();
        listPropertyService.add(new IconBean(R.drawable.ic_repair_orange_140px, "物业报修"));
        listPropertyService.add(new IconBean(R.drawable.ic_call_property_orange_140px, "联系物业"));
        listPropertyService.add(new IconBean(R.drawable.ic_property_complaints_orange_140px, "管理投诉"));
        propertyServiceAdapter.setNewData(listPropertyService);

    }

    private void setListener() {
        smartHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(BaseApplication.mContext, "position::" + position);


                String token = "";
                Intent intent = new Intent(_mActivity, WebActivity.class);
                intent.putExtra("title", "生活超市");
                intent.putExtra("link", ServiceApi.SMART_DEVICE + token);
                startActivity(intent);
            }
        });

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
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //判断是否滑动到顶部。
                return svSteward != null && svSteward.getScrollY() == 0;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
                mPresenter.start();

            }
        });
    }

    @Override
    public void end() {
        ALog.e("end()…………………………………………");
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void error(Throwable t) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(rootView, "网络异常，请刷新！");
    }


    protected void initToolbar(Toolbar toolbar) {
        initStateBar(toolbar);
        toolbarTitle.setText("智能管家");

    }

    @Override
    public void onDestroy() {
        /**
         * 记得清理adapter
         */
        super.onDestroy();
    }

    @Override
    public void responseHouses(List<IconBean> listIcons) {
        ptrFrameLayout.refreshComplete();
        listMyhouses.addAll(0, listIcons);
        myHouseAdapter.setNewData(listMyhouses);


    }
}


