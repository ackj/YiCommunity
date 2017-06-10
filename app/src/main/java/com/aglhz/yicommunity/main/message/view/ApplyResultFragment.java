package com.aglhz.yicommunity.main.message.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.Params;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/19 0019 17:54.
 * Email: liujia95me@126.com
 * 申请结果的View层。
 */

public class ApplyResultFragment extends BaseFragment {

    private static final String TAG = ApplyResultFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_image_apply_result_fargment)
    ImageView ivImage;
    @BindView(R.id.tv_desc_apply_result_fargment)
    TextView tvDesc;

    private Unbinder unbinder;
    private Params params = Params.getInstance();
    private String des;
    private String title;

    /**
     * ApplyResultFragment的创建入口
     * @param title toolbar的标题
     * @param des 结果的描述信息
     * @return
     */
    public static ApplyResultFragment newInstance(String title, String des) {
        ApplyResultFragment fragment = new ApplyResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_TITLE, title);
        bundle.putString(Constants.KEY_DES, des);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(Constants.KEY_TITLE);
        des = getArguments().getString(Constants.KEY_DES);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_result, container, false);
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
        if (des.contains("通过")) {
            ivImage.setImageResource(R.drawable.ic_apply_pass_200px);
        } else {
            ivImage.setImageResource(R.drawable.ic_apply_refuse_200px);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
