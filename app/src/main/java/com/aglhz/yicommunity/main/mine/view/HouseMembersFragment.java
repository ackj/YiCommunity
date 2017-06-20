package com.aglhz.yicommunity.main.mine.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.HouseRightsBean;
import com.aglhz.yicommunity.entity.bean.MyHousesBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.main.house.view.MemberRVAdapter;
import com.aglhz.yicommunity.main.mine.contract.MyHousesContract;
import com.aglhz.yicommunity.main.mine.presenter.MyHousesPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

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
        args.putString(Constants.KEY_FID, fid);
        args.putString(Constants.KEY_ADDRESS, address);
        HouseMembersFragment fragment = new HouseMembersFragment();
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
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
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
