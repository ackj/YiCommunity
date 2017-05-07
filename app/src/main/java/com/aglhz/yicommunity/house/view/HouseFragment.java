package com.aglhz.yicommunity.house.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.abase.widget.selector.DataProvider;
import com.aglhz.abase.widget.selector.ISelectAble;
import com.aglhz.abase.widget.selector.SelectedListener;
import com.aglhz.abase.widget.selector.Selector;
import com.aglhz.abase.widget.selector.SelectorDialog;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.house.contract.HouseContract;
import com.aglhz.yicommunity.house.presenter.HousePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

/**
 * Author：leguang on 2017/4/13 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块的View层内容。
 */
public class HouseFragment extends BaseFragment<HouseContract.Presenter> implements HouseContract.View {
    private static final String TAG = HouseFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_proprietor_house_fragment)
    TextView tvProprietor;
    @BindView(R.id.tv_member_house_fragment)
    TextView tvMember;
    @BindView(R.id.et_name_house_fragment)
    EditText etName;
    @BindView(R.id.et_idcard_house_fragment)
    EditText etIdcard;
    @BindView(R.id.tv_area_house_fragment)
    TextView tvArea;
    @BindView(R.id.tv_community_house_fragment)
    TextView tvCommunity;
    @BindView(R.id.bt_submit_house_fragment)
    Button btSubmit;
    @BindView(R.id.tv_building_house_fragment)
    TextView tvBuilding;
    @BindView(R.id.tv_unit_house_fragment)
    TextView tvUnit;
    @BindView(R.id.tv_floor_house_fragment)
    TextView tvFloor;
    @BindView(R.id.tv_room_number_house_fragment)
    TextView tvRoomNumber;
    private boolean isProprietor = true;
    private ViewGroup rootView;
    private BottomDialog addressSelector;
    private SelectorDialog selector;
    private String province;
    private String city;
    private String county;
    private String communityCode;
    private String buildingCode;
    private String unitCode;
    private String floorCode;
    private String roomCode;


    public static HouseFragment newInstance() {
        return new HouseFragment();
    }

    @NonNull
    @Override
    protected HouseContract.Presenter createPresenter() {
        return new HousePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = (ViewGroup) _mActivity.findViewById(android.R.id.content).getRootView();
        initToolbar(toolbar);
    }

    protected void initToolbar(Toolbar toolbar) {
        initStateBar(toolbar);
        toolbarTitle.setText("智能管家");

    }


    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ALog.e("77777777");
        DialogHelper.warningSnackbar(rootView, errorMessage);
    }

    @OnClick({R.id.tv_proprietor_house_fragment,
            R.id.tv_member_house_fragment,
            R.id.tv_area_house_fragment,
            R.id.tv_community_house_fragment,
            R.id.tv_building_house_fragment,
            R.id.tv_unit_house_fragment,
            R.id.tv_floor_house_fragment,
            R.id.tv_room_number_house_fragment,
            R.id.bt_submit_house_fragment,})
    public void onClick(View view) {
        ToastUtils.showToast(BaseApplication.mContext, "view.getId()::" + view.getId());
        switch (view.getId()) {
            case R.id.tv_proprietor_house_fragment:
                resetIdentity(tvProprietor, tvMember, true);
                break;
            case R.id.tv_member_house_fragment:
                resetIdentity(tvMember, tvProprietor, false);
                break;
            case R.id.tv_area_house_fragment:
                showAddressSelector();//选择省市县
                break;
            case R.id.tv_community_house_fragment:
                if (TextUtils.isEmpty(tvArea.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(rootView, "请先选择区域！");
                } else {
                    mPresenter.requestCommunity(province, city, county);
                }
                break;
            case R.id.tv_building_house_fragment:
                if (TextUtils.isEmpty(tvCommunity.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(rootView, "请先选择小区！");
                } else {
                    mPresenter.requestBuilding(communityCode);
                }
                break;
            case R.id.tv_unit_house_fragment:
                if (TextUtils.isEmpty(tvBuilding.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(rootView, "请先选择楼栋！");
                } else {
                    mPresenter.requestUnit(communityCode, buildingCode);
                }
                break;
            case R.id.tv_floor_house_fragment:
                if (TextUtils.isEmpty(tvUnit.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(rootView, "请先选择单元！");
                } else {
                    mPresenter.requestFloor(communityCode, buildingCode, unitCode);
                }
                break;
            case R.id.tv_room_number_house_fragment:
                if (TextUtils.isEmpty(tvFloor.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(rootView, "请先选择楼层！");
                } else {
                    mPresenter.requestRoom(communityCode, buildingCode, unitCode, floorCode);
                }
                break;
            case R.id.bt_submit_house_fragment:
                if (TextUtils.isEmpty(tvRoomNumber.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(rootView, "请先选择房间！");
                } else {
                    mPresenter.requestApply(isProprietor, communityCode, buildingCode, unitCode, floorCode,
                            roomCode, etName.getText().toString().trim(), etIdcard.getText().toString().trim());
                }
                break;
        }
        ALog.e(isProprietor);

    }


    private void resetIdentity(TextView tvChecked, TextView tvUnChecked, boolean b) {
        tvChecked.setBackgroundResource(R.drawable.bg_checked_red_340px_180px);
        tvChecked.setTextColor(ContextCompat.getColor(BaseApplication.mContext, R.color.base_color));
        tvUnChecked.setBackgroundResource(R.drawable.bg_unchecked_gray_340px_180px);
        tvUnChecked.setTextColor(ContextCompat.getColor(BaseApplication.mContext, R.color.default_text));
        isProprietor = b;
    }

    //选择省市县
    private void showAddressSelector() {
        ALog.e("1111111111111");
        if (addressSelector == null) {
            addressSelector = new BottomDialog(_mActivity);
            addressSelector.setOnAddressSelectedListener(new OnAddressSelectedListener() {
                @Override
                public void onAddressSelected(Province province, City city, County county, Street street) {
                    HouseFragment.this.province = province.name;
                    HouseFragment.this.city = city.name;
                    HouseFragment.this.county = county.name;

                    String s = (province == null ? "" : province.name + "　") + (city == null ? "" : city.name + "　") +
                            (county == null ? "" : county.name + "　") + (street == null ? "" : street.name);
                    ToastUtils.showToast(BaseApplication.mContext, s);
                    tvArea.setText(s);
                    addressSelector.dismiss();
                }
            });
        }
        addressSelector.show();
    }

    @Override
    public void onDestroy() {
        if (selector != null) {
            selector = null;
        }
        if (addressSelector != null) {
            addressSelector = null;
        }

        super.onDestroy();
    }

    @Override
    public void responseCommunity(final List<ISelectAble> communities) {
        ALog.e("communities.size()::" + communities.size());

        if (selector == null) {
            selector = new SelectorDialog(_mActivity);
            selector.init(_mActivity, new Selector(_mActivity, 1));
        }
        selector.setSelectedListener(new SelectedListener() {
            @Override
            public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {

                tvCommunity.setText(selectAbles.get(0).getName());
                communityCode = ((String) selectAbles.get(0).getArg());

                ALog.e("communityCode::" + communityCode);
                selector.dismiss();
            }
        });
        showSelector(communities);
    }

    @Override
    public void responseBuilding(List<ISelectAble> buildings) {
        ALog.e("buildings.size()::" + buildings.size());

        selector.setSelectedListener(new SelectedListener() {
            @Override
            public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {
                tvBuilding.setText(selectAbles.get(0).getName());
                buildingCode = ((String) selectAbles.get(0).getArg());

                ALog.e("buildingCode::" + buildingCode);
                selector.dismiss();
            }
        });
        showSelector(buildings);
    }

    @Override
    public void responseUnit(List<ISelectAble> units) {
        ALog.e("units.size()::" + units.size());

        selector.setSelectedListener(new SelectedListener() {
            @Override
            public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {
                tvUnit.setText(selectAbles.get(0).getName());
                unitCode = ((String) selectAbles.get(0).getArg());

                ALog.e("units::" + unitCode);
                selector.dismiss();
            }
        });
        showSelector(units);
    }

    @Override
    public void responseFloor(List<ISelectAble> floors) {
        ALog.e("floors.size()::" + floors.size());

        selector.setSelectedListener(new SelectedListener() {
            @Override
            public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {
                tvFloor.setText(selectAbles.get(0).getName());
                floorCode = ((String) selectAbles.get(0).getArg());

                ALog.e("floors::" + floorCode);
                selector.dismiss();
            }
        });
        showSelector(floors);
    }

    @Override
    public void responseRoom(List<ISelectAble> rooms) {
        ALog.e("rooms.size()::" + rooms.size());

        selector.setSelectedListener(new SelectedListener() {
            @Override
            public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {
                tvRoomNumber.setText(selectAbles.get(0).getName());
                roomCode = ((String) selectAbles.get(0).getArg());

                ALog.e("rooms::" + roomCode);
                selector.dismiss();
            }
        });
        showSelector(rooms);
    }

    @Override
    public void responseApply(BaseBean mBaseBean) {
        DialogHelper.successSnackbar(rootView, "恭喜，申请成功！");
    }


    private void showSelector(final List list) {
        if (selector == null) {
            selector = new SelectorDialog(_mActivity);
            selector.init(_mActivity, new Selector(_mActivity, 1));
        }
        selector.reset();
        selector.setDataProvider(new DataProvider() {
            @Override
            public void provideData(int currentDeep, int preId, DataReceiver receiver) {
                receiver.send(list);
            }
        });
        selector.show();
    }
}


