package com.aglhz.yicommunity.message.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Params;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/19 0019 15:07.
 * Email: liujia95me@126.com
 */

public class ApplyCheckFragment extends BaseFragment {

    private static final String TAG = ApplyCheckFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_desc_apply_check_fragment)
    TextView tvDesc;

    private Unbinder unbinder;
    private String des;
    private String title;
    private Params params = Params.getInstance();

    public static ApplyCheckFragment newInstance(String title, String des) {
        ApplyCheckFragment checkFragment = new ApplyCheckFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("des", des);
        checkFragment.setArguments(bundle);
        return checkFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        des = getArguments().getString("des");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_check, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText(title);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        tvDesc.setText(des);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_pass_check_fragment, R.id.bt_refuse_check_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_pass_check_fragment:

                break;
            case R.id.bt_refuse_check_fragment:
                break;
        }
    }
}
