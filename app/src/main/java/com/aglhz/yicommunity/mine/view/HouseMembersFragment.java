package com.aglhz.yicommunity.mine.view;

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
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.HouseRightsBean;
import com.aglhz.yicommunity.bean.MyHousesBean;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.house.contract.HouseRightsContract;
import com.aglhz.yicommunity.house.presenter.HouseRightsPresenter;
import com.aglhz.yicommunity.house.view.MemberRVAdapter;
import com.aglhz.yicommunity.house.view.PermissionRVAdapter;
import com.aglhz.yicommunity.mine.contract.MyHousesContract;
import com.aglhz.yicommunity.mine.presenter.MyHousesPresenter;
import com.kyleduo.switchbutton.SwitchButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Author: LiuJia on 2017/4/20 9:26.
 * Email: liujia95me@126.com
 */
public class HouseMembersFragment extends BaseFragment<MyHousesContract.Presenter> implements MyHousesContract.View {
    private static final String TAG = HouseMembersFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private MemberRVAdapter mAdapter;
    private Params params = Params.getInstance();
    private String title;
    private Unbinder unbinder;

    public static HouseMembersFragment newInstance(String fid, String address) {
        Bundle args = new Bundle();
        args.putString(Constants.HOUSE_FID, fid);
        args.putString(Constants.HOUSE_ADDRESS, address);
        HouseMembersFragment fragment = new HouseMembersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            params.fid = args.getString(Constants.HOUSE_FID);
            title = args.getString(Constants.HOUSE_ADDRESS);
        }
    }

    @NonNull
    @Override
    protected MyHousesContract.Presenter createPresenter() {
        return new MyHousesPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initPtrFrameLayout();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText(title);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initPtrFrameLayout() {
        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(BaseApplication.mContext, 15F), 0, DensityUtils.dp2px(BaseApplication.mContext, 10F));
        header.setPtrFrameLayout(ptrFrameLayout);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.postDelayed(() -> ptrFrameLayout.autoRefresh(true), 100);

        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //判断是否滑动到顶部。
                return ScrollingHelper.isRecyclerViewToTop(recyclerView);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了");
                mPresenter.requestRights(params);
            }
        });
    }

    private void initData() {
        //成员头像网格表
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 5));
        mAdapter = new MemberRVAdapter();
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void responseHouses(List<MyHousesBean.DataBean.AuthBuildingsBean> beas) {
        //公用Presenter多出来的
    }

    @Override
    public void responseRights(HouseRightsBean mHouseRights) {
        ptrFrameLayout.refreshComplete();
        mAdapter.setNewData(mHouseRights.getData());
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
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
