package com.aglhz.yicommunity.main.sociality.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.main.publish.view.PublishExchangeFragment;
import com.aglhz.yicommunity.main.publish.view.PublishNeighbourFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/11 0011 14:11.
 * Email: liujia95me@126.com
 */

public class NeighbourFragment extends BaseFragment {
    private static final String TAG = NeighbourFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;

    private Unbinder unbinder;
    private int type;

    public static NeighbourFragment newInstance(int type) {
        NeighbourFragment fragment = new NeighbourFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(Constants.KEY_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (type == SocialityListFragment.TYPE_EXCHANGE) {
            view = attachToSwipeBack(view);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadRootFragment(R.id.fl_container_fragment_fragment, SocialityListFragment.newInstance(type));
        initToolbar();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        switch (type) {
            case SocialityListFragment.TYPE_EXCHANGE:
                toolbarTitle.setText("闲置交换");
                break;
            case SocialityListFragment.TYPE_NEIGHBOUR:
                toolbarTitle.setText("左邻右里");
                break;
        }
        toolbarMenu.setText("发布");
        if (type == SocialityListFragment.TYPE_EXCHANGE) {
            toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
            toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_menu)
    public void onViewClicked() {
        switch (type) {
            case SocialityListFragment.TYPE_EXCHANGE:
                start(PublishExchangeFragment.newInstance());
                break;
            case SocialityListFragment.TYPE_NEIGHBOUR:
                String[] arr = {"发布照片", "发布视频"};
                new AlertDialog.Builder(_mActivity)
                        .setItems(arr, (dialog, which) -> {
                            //网络访问
                            dialog.dismiss();
                            _mActivity.start(PublishNeighbourFragment.newInstance(which));
                        })
                        .setTitle("请选择")
                        .setPositiveButton("取消", null)
                        .show();
                break;
        }
    }
}
