package com.aglhz.yicommunity.main.mine.view;

import android.os.Bundle;
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
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.entity.bean.MyHousesBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Author: LiuJia on 2017/4/20 9:26.
 * Email: liujia95me@126.com
 */
public class MyHousesMembersFragment extends BaseFragment {
    private static final String TAG = MyHousesMembersFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private MyHousesMembersRVAdapter mAdapter;
    private Unbinder unbinder;
    private MyHousesBean.DataBean.AuthBuildingsBean bean;

    public static MyHousesMembersFragment newInstance(MyHousesBean.DataBean.AuthBuildingsBean bean) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.KEY_MEMBER, bean);
        MyHousesMembersFragment fragment = new MyHousesMembersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            bean = (MyHousesBean.DataBean.AuthBuildingsBean) args.getSerializable(Constants.KEY_MEMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText(bean.getAddress());
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }


    private void initData() {
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return false;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
            }
        });
        //成员头像网格表
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 5));
        mAdapter = new MyHousesMembersRVAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(bean.getMembers());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
