package com.aglhz.yicommunity.main.house.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.abase.utils.RegexUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.BuildingBean;
import com.aglhz.yicommunity.entity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.entity.bean.FloorBean;
import com.aglhz.yicommunity.entity.bean.RoomBean;
import com.aglhz.yicommunity.entity.bean.UnitBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.main.house.contract.AddHouseContract;
import com.aglhz.yicommunity.main.house.presenter.AddHousePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import chihane.jdaddressselector.BottomDialog;

/**
 * Author：leguang on 2017/4/13 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 负责房屋模块的View层内容。
 */
public class AddHouseFragment extends BaseFragment<AddHouseContract.Presenter> implements AddHouseContract.View {
    private static final String TAG = AddHouseFragment.class.getSimpleName();
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
    @BindView(R.id.ll_area_add_house_fragment)
    LinearLayout llArea;
    @BindView(R.id.ll_community_add_house_fragment)
    LinearLayout llCommunity;
    @BindView(R.id.bt_submit_house_fragment)
    Button btSubmit;
    @BindView(R.id.ll_building_add_house_fragment)
    LinearLayout llBuilding;
    @BindView(R.id.ll_unit_add_house_fragment)
    LinearLayout llUnit;
    @BindView(R.id.ll_floor_add_house_fragment)
    LinearLayout llFloor;
    @BindView(R.id.ll_room_add_house_fragment)
    LinearLayout llRoom;
    @BindView(R.id.tv_area_add_house_fragment)
    TextView tvArea;
    @BindView(R.id.tv_community_add_house_fragment)
    TextView tvCommunity;
    @BindView(R.id.tv_building_add_house_fragment)
    TextView tvBuilding;
    @BindView(R.id.tv_unit_add_house_fragment)
    TextView tvUnit;
    @BindView(R.id.tv_floor_add_house_fragment)
    TextView tvFloor;
    @BindView(R.id.tv_room_add_house_fragment)
    TextView tvRoom;
    private boolean isProprietor = true;//用于区分申请时是业主还是成员。
    private BottomDialog addressSelector;//省、市、区选择。
    private Params params = Params.getInstance();//参数对象，每一个有网络请求的页面有都。
    private String title;//标题。
    private Unbinder unbinder;

    /**
     * AddHouseFragment的创建入口
     *
     * @param address 从管家页面点击进来时，传房间的具体详细地址，用于显示在toolbar上的。
     * @return
     */
    public static AddHouseFragment newInstance(String address) {
        Bundle args = new Bundle();
        args.putString(Constants.KEY_ADDRESS, address);
        AddHouseFragment fragment = new AddHouseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            title = args.getString(Constants.KEY_ADDRESS);
        }
    }

    @NonNull
    @Override
    protected AddHouseContract.Presenter createPresenter() {
        return new AddHousePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_house, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar(toolbar);
    }

    /**
     * 初始化Toolbar,包括设置顶部的状态栏，标题，返回图标和事件等。
     *
     * @param toolbar
     */
    protected void initToolbar(Toolbar toolbar) {
        initStateBar(toolbar);//为了达到透明状态栏效果，给toolbar添加一定的padding值。
        toolbarTitle.setText(title);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.finish());
    }

    @OnClick({R.id.tv_proprietor_house_fragment,
            R.id.tv_member_house_fragment,
            R.id.ll_area_add_house_fragment,
            R.id.ll_community_add_house_fragment,
            R.id.ll_building_add_house_fragment,
            R.id.ll_unit_add_house_fragment,
            R.id.ll_floor_add_house_fragment,
            R.id.ll_room_add_house_fragment,
            R.id.bt_submit_house_fragment,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_proprietor_house_fragment:
                resetIdentity(tvProprietor, tvMember, true);//当点击时，刷新背景。
                break;
            case R.id.tv_member_house_fragment:
                resetIdentity(tvMember, tvProprietor, false);//当点击时，刷新背景。
                break;
            case R.id.ll_area_add_house_fragment:
                showAddressSelector();//选择省市县。
                break;
            case R.id.ll_community_add_house_fragment:
                if (TextUtils.isEmpty(tvArea.getText().toString())) {
                    DialogHelper.warningSnackbar(getView(), "请先选择区域！");
                } else {
                    showLoadingDialog();
                    mPresenter.requestCommunitys(params);//根据城市请求网络获取城市的社区列表信息并展示在dialog里，让用户选择。
                }
                break;
            case R.id.ll_building_add_house_fragment:
                if (TextUtils.isEmpty(tvCommunity.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(getView(), "请先选择小区！");
                } else {
                    showLoadingDialog();
                    mPresenter.requestBuildings(params);//根据社区请求网络获取社区中的楼栋列表信息，
                }
                break;
            case R.id.ll_unit_add_house_fragment:
                if (TextUtils.isEmpty(tvBuilding.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(getView(), "请先选择楼栋！");
                } else {
                    showLoadingDialog();
                    mPresenter.requestUnits(params);//根据楼栋获取单元信息列表。
                }
                break;
            case R.id.ll_floor_add_house_fragment:
                if (TextUtils.isEmpty(tvUnit.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(getView(), "请先选择单元！");
                } else {
                    showLoadingDialog();
                    mPresenter.requestFloors(params);//根据单元获取楼层信息列表。
                }
                break;
            case R.id.ll_room_add_house_fragment:
                if (TextUtils.isEmpty(tvFloor.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(getView(), "请先选择楼层！");
                } else {
                    showLoadingDialog();
                    mPresenter.requestRooms(params);//根据楼层请求网络获取房屋号信息列表。
                }
                break;
            case R.id.bt_submit_house_fragment:
                if (TextUtils.isEmpty(tvRoom.getText().toString().trim())) {
                    DialogHelper.warningSnackbar(getView(), "请先选择房间！");
                } else {
                    params.isProprietor = isProprietor;
                    params.name = etName.getText().toString().trim();
                    String idCard = etIdcard.getText().toString().trim();
                    if (!(RegexUtils.isIDCard15(idCard) || RegexUtils.isIDCard18(idCard))) {
                        DialogHelper.warningSnackbar(getView(), "请输入正确的身份证号码!");
                        return;
                    }
                    params.idCard = idCard;
                    showLoadingDialog();
                    mPresenter.requestApply(params);//拿到用户填表的参数，请求网络，申请房屋。
                }
                break;
        }
    }

    /**
     * 刷新一下身份选择的空间，如：改变背景和字体颜色等。
     *
     * @param tvChecked   选中状态控件。
     * @param tvUnChecked 未选中状态的控件。
     * @param b           是否是业主。
     */
    private void resetIdentity(TextView tvChecked, TextView tvUnChecked, boolean b) {
        tvChecked.setBackgroundResource(R.drawable.bg_checked_red_340px_180px);
        tvChecked.setTextColor(ContextCompat.getColor(BaseApplication.mContext, R.color.base_color));
        tvUnChecked.setBackgroundResource(R.drawable.bg_unchecked_gray_340px_180px);
        tvUnChecked.setTextColor(ContextCompat.getColor(BaseApplication.mContext, R.color.default_text));
        isProprietor = b;
    }

    //选择省市县
    private void showAddressSelector() {
        if (addressSelector == null) {
            addressSelector = new BottomDialog(_mActivity);
            addressSelector.setOnAddressSelectedListener((province, city, county, street) -> {
                params.province = province.name;
                params.city = city.name;
                params.county = county.name;

                ALog.e(params.province);
                ALog.e(params.city);
                ALog.e(params.county);

                String s = province.name + "　" + city.name + "　" + county.name + "　" + (street == null ? "" : street.name);
                tvArea.setText(s);
                addressSelector.dismiss();
            });
        }
        addressSelector.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeyBoardUtils.hideKeybord(getView(), _mActivity);//退出页面时，要隐藏键盘。
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        if (addressSelector != null) {
            addressSelector = null;
        }

        super.onDestroy();
    }

    /**
     * 响应请求社区的列表的结果，使用AlertDialog展示，当用户选择后设置到参数对象中，同时要置空社区后面的选择项。
     *
     * @param communities
     */
    @Override
    public void responseCommunitys(final List<CommunitySelectBean.DataBean.CommunitiesBean> communities) {
        dismissLoadingDialog();
        String[] array = new String[communities.size()];
        for (int i = 0; i < communities.size(); i++) {
            array[i] = communities.get(i).getName();
        }

        new AlertDialog.Builder(_mActivity)
                .setTitle("请选择小区")
                .setItems(array, (dialog, which) -> {
                    params.cmnt_c = communities.get(which).getCode();
                    tvCommunity.setText(array[which]);

                    tvBuilding.setText("");//这一部分操作是为了置控，
                    tvUnit.setText("");    //因为当用户之前选择了小区楼栋单元等信息后，
                    tvFloor.setText("");   //又更换了小区，那后面的楼栋单元等信息应该清空
                    tvRoom.setText("");

                    params.bdg_c = "";
                    params.bdg_u_c = "";
                    params.bdg_f_c = "";
                    params.bdg_f_h_c = "";
                })
                .show();
    }

    /**
     * 响应请求楼栋的列表的结果，使用AlertDialog展示，当用户选择后设置到参数对象中，同时要置空楼栋后面的选择项。
     *
     * @param buildings
     */
    @Override
    public void responseBuildings(List<BuildingBean.DataBean.BuildingsBean> buildings) {
        dismissLoadingDialog();

        String[] array = new String[buildings.size()];
        for (int i = 0; i < buildings.size(); i++) {
            array[i] = buildings.get(i).getName();
        }

        new AlertDialog.Builder(_mActivity)
                .setTitle("请选择楼栋")
                .setItems(array, (dialog, which) -> {
                    params.bdg_c = buildings.get(which).getCode();
                    tvBuilding.setText(array[which]);

                    tvUnit.setText("");
                    tvFloor.setText("");
                    tvRoom.setText("");

                    params.bdg_u_c = "";
                    params.bdg_f_c = "";
                    params.bdg_f_h_c = "";
                })
                .show();
    }

    /**
     * 响应请求单元的列表的结果，使用AlertDialog展示，当用户选择后设置到参数对象中，同时要置空单元后面的选择项。
     *
     * @param units
     */
    @Override
    public void responseUnits(List<UnitBean.DataBean.BuildingUnitsBean> units) {
        dismissLoadingDialog();

        String[] array = new String[units.size()];
        for (int i = 0; i < units.size(); i++) {
            array[i] = units.get(i).getName();
        }

        new AlertDialog.Builder(_mActivity)
                .setTitle("请选择单元")
                .setItems(array, (dialog, which) -> {
                    params.bdg_u_c = units.get(which).getCode();
                    tvUnit.setText(array[which]);

                    tvFloor.setText("");
                    tvRoom.setText("");

                    params.bdg_f_c = "";
                    params.bdg_f_h_c = "";
                })
                .show();
    }

    /**
     * 响应请求楼层的列表的结果，使用AlertDialog展示，当用户选择后设置到参数对象中，同时要置空楼层后面的选择项。
     *
     * @param floors
     */
    @Override
    public void responseFloors(List<FloorBean.DataBean.FloorsBean> floors) {
        dismissLoadingDialog();

        String[] array = new String[floors.size()];
        for (int i = 0; i < floors.size(); i++) {
            array[i] = floors.get(i).getName();
        }

        new AlertDialog.Builder(_mActivity)
                .setTitle("请选择楼层")
                .setItems(array, (dialog, which) -> {
                    params.bdg_f_c = floors.get(which).getCode();
                    tvFloor.setText(array[which]);
                    tvRoom.setText("");
                    params.bdg_f_h_c = "";
                })
                .show();
    }

    /**
     * 响应请求房屋的列表的结果，使用AlertDialog展示，当用户选择后设置到参数对象中。
     *
     * @param rooms
     */
    @Override
    public void responseRooms(List<RoomBean.DataBean.HousesBean> rooms) {
        dismissLoadingDialog();

        String[] array = new String[rooms.size()];
        for (int i = 0; i < rooms.size(); i++) {
            array[i] = rooms.get(i).getName();
        }

        new AlertDialog.Builder(_mActivity)
                .setTitle("请选择楼层")
                .setItems(array, (dialog, which) -> {
                    params.bdg_f_h_c = rooms.get(which).getCode();
                    tvRoom.setText(array[which]);
                })
                .show();
    }


    /**
     * 响应申请的结果，同时结束掉本页面。
     *
     * @param message 由服务器返回。
     */
    @Override
    public void responseApply(String message) {
        dismissLoadingDialog();
        if (TextUtils.isEmpty(UserHelper.communityName)
                || TextUtils.isEmpty(UserHelper.communityCode)) {
            UserHelper.setCommunity(tvCommunity.getText().toString(), params.cmnt_c);
            UserHelper.setPosition(params.province, params.city, params.county, "");
            EventBus.getDefault().post(new EventCommunity(null));
        }
        DialogHelper.successSnackbar(getView(), message);
        if (getView() != null) {
            getView().postDelayed(() -> _mActivity.finish(), 1000);
        }
    }

    @Override
    public void start(Object response) {

    }

    /**
     * 所有的报错和异常信息都会归结到这个函数中。
     *
     * @param errorMessage
     */
    @Override
    public void error(String errorMessage) {
        dismissLoadingDialog();
        DialogHelper.errorSnackbar(getView(), errorMessage);
    }
}


