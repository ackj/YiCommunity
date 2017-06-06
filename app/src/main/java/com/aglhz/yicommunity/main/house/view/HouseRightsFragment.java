package com.aglhz.yicommunity.main.house.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.HouseRightsBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.main.house.contract.HouseRightsContract;
import com.aglhz.yicommunity.main.house.presenter.HouseRightsPresenter;
import com.kyleduo.switchbutton.SwitchButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: LiuJia on 2017/4/20 9:26.
 * Email: liujia95me@126.com
 */
public class HouseRightsFragment extends BaseFragment<HouseRightsContract.Presenter> implements HouseRightsContract.View {
    private static final String TAG = HouseRightsFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_permission)
    RecyclerView rvPermission;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private int memberPosition = 0;
    private int permissionPosition;
    private MemberRVAdapter memberAdapter;
    private PermissionRVAdapter permissionAdapter;
    private View footerView;
    private RecyclerView rvMember;
    private HouseRightsBean mHouseRights;
    private Params params = Params.getInstance();
    private String title;
    private Unbinder unbinder;

    public static HouseRightsFragment newInstance(String fid, String address) {
        Bundle args = new Bundle();
        args.putString(Constants.KEY_FID, fid);
        args.putString(Constants.KEY_ADDRESS, address);
        HouseRightsFragment fragment = new HouseRightsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            params.fid = args.getString(Constants.KEY_FID);
            title = args.getString(Constants.KEY_ADDRESS);
        }
    }

    @NonNull
    @Override
    protected HouseRightsContract.Presenter createPresenter() {
        return new HouseRightsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_rights, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initPtrFrameLayout(ptrFrameLayout, rvPermission);
        initData();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText(title);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onRefresh() {
        mPresenter.requestRights(params);
    }

    private void initData() {
        //房屋成员
        TextView tvMember = new TextView(_mActivity);
        tvMember.setBackgroundColor(ContextCompat.getColor(BaseApplication.mContext, R.color.base_grey));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvMember.setPadding(30, 30, 0, 14);

        tvMember.setLayoutParams(lp);
        tvMember.setText("房屋成员");
        tvMember.setTextColor(Color.parseColor("#999999"));

        //成员头像网格表
        rvMember = new RecyclerView(_mActivity);
        rvMember.setBackgroundColor(ContextCompat.getColor(BaseApplication.mContext, R.color.base_grey));
        rvMember.setLayoutParams(new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rvMember.setLayoutManager(new GridLayoutManager(_mActivity, 5));
        memberAdapter = new MemberRVAdapter();
        rvMember.setAdapter(memberAdapter);

        //权限列表
        rvPermission.setLayoutManager(new LinearLayoutManager(_mActivity));
        permissionAdapter = new PermissionRVAdapter();
        permissionAdapter.addHeaderView(tvMember);
        permissionAdapter.addHeaderView(rvMember);

        //删除成员footer
        footerView = LayoutInflater.from(BaseApplication.mContext).inflate(R.layout.item_remove_member, null);

        rvPermission.setAdapter(permissionAdapter);
    }

    private void initListener() {
        //每选中一次，重新赋值选中的那个成员对象
        memberAdapter.setOnItemClickListener((adapter, view, position) -> {
            permissionAdapter.setNewData(mHouseRights.getData().get(position).getAuthority());
            adapter.getViewByPosition(rvMember, memberPosition, R.id.iv_avatar).setBackgroundResource(0);
            view.findViewById(R.id.iv_avatar).setBackgroundResource(R.drawable.shape_stroke_round_red_border);
            memberPosition = position;
            ALog.e("prePosition::" + memberPosition);

            if (position == 0) {
                permissionAdapter.removeFooterView(footerView);
            } else {
                permissionAdapter.removeAllFooterView();
                permissionAdapter.addFooterView(footerView);
            }
        });

        footerView.setOnClickListener(v -> {
            ALog.e("prePosition::" + memberPosition);
            params.mfid = memberAdapter.getData().get(memberPosition).getMember().getFid();
            mPresenter.requestDelete(params);
            showLoadingDialog();
        });

        permissionAdapter.setOnItemClickListener((adapter, view, position) -> {

            permissionPosition = position;
            SwitchButton switchButton = (SwitchButton) view.findViewById(R.id.switch_button);
//            switchButton.setChecked(!switchButton.isChecked());

            params.mfid = memberAdapter.getData().get(memberPosition).getMember().getFid();
            params.rfid = memberAdapter.getData().get(memberPosition).getMember().getRfid();
            params.picode = permissionAdapter.getData().get(position).getCode();

            ALog.e("switchButton.isChecked()::" + switchButton.isChecked());

            params.status = switchButton.isChecked() ? 0 : 1;

            if (memberAdapter.getData().size() == 1) {
                params.url = ApiService.UPDATE_RIGHTS_MYSELF;
            } else {
                if (memberAdapter.getData().get(memberPosition).getMember().getIsOwner() == 0) {
                    params.url = ApiService.UPDATE_RIGHTS_OTHER;
                } else {
                    params.url = ApiService.UPDATE_RIGHTS_MYSELF;
                }
            }

            mPresenter.requestUpdateRights(params);
            showLoadingDialog();

        });
    }

    @Override
    public void responseRights(HouseRightsBean mHouseRights) {
        ptrFrameLayout.refreshComplete();
        this.mHouseRights = mHouseRights;
        memberAdapter.setNewData(mHouseRights.getData());
        permissionAdapter.setNewData(mHouseRights.getData().get(memberPosition).getAuthority());
    }

    @Override
    public void responseUpdateRights(BaseBean mBaseBean) {
        dismissLoadingDialog();
        permissionAdapter.getData().get(permissionPosition).setStatus(params.status);
        permissionAdapter.notifyDataSetChanged();
        DialogHelper.successSnackbar(getView(), mBaseBean.getOther().getMessage());
    }

    @Override
    public void responseDelete(BaseBean mBaseBean) {
        dismissLoadingDialog();
        DialogHelper.successSnackbar(getView(), mBaseBean.getOther().getMessage());
        memberAdapter.remove(memberPosition);
        permissionAdapter.setNewData(null);
        if (memberAdapter.getData().size() == 1) {
            permissionAdapter.removeFooterView(footerView);
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        dismissLoadingDialog();
        permissionAdapter.notifyDataSetChanged();
        ptrFrameLayout.refreshComplete();
        DialogHelper.errorSnackbar(getView(), errorMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        ptrFrameLayout.autoRefresh();
    }
}
