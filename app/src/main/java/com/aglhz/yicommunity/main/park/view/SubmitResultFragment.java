package com.aglhz.yicommunity.main.park.view;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/19 9:43.
 */
public class SubmitResultFragment extends BaseFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_apply_card_result_submit_result_fragment)
    TextView tvApplyCardResult;
    @BindView(R.id.iv_image_submit_result_fragment)
    ImageView ivImage;
    @BindView(R.id.tv_apply_message_submit_result_fragment)
    TextView tvApplyMessage;

    private Unbinder unbinder;
    private String cardType;
    private boolean isPass;

    public static SubmitResultFragment newInstance(String cardType, boolean isPass) {
        SubmitResultFragment fragment = new SubmitResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cardType", cardType);
        bundle.putBoolean("isPass", isPass);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardType = getArguments().getString("cardType");
        isPass = getArguments().getBoolean("isPass");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_result, container, false);
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
        toolbarTitle.setText("提交成功");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        if (!isPass) {
            tvApplyCardResult.setVisibility(View.GONE);
            ivImage.setImageResource(R.drawable.ic_apply_refuse_200px);
            tvApplyMessage.setText("抱歉，审核不通过，请联系物业！");
        } else {
            if ("月租卡".equals(cardType)) {
                tvApplyCardResult.setText(String.format(_mActivity.getResources().getString(R.string.apply_card_result), "月卡"));
            } else {
                tvApplyCardResult.setText(String.format(_mActivity.getResources().getString(R.string.apply_card_result), "车库"));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_go_back_home)
    public void onViewClicked() {
        setFragmentResult(RESULT_OK, null);
        _mActivity.finish();
    }
}
