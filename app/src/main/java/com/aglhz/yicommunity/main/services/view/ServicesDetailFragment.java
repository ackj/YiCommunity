package com.aglhz.yicommunity.main.services.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.ServicesCommodityDetailBean;
import com.aglhz.yicommunity.main.services.contract.ServicesDetailContract;
import com.aglhz.yicommunity.main.services.presenter.ServicesDetailPresenter;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: leguang on 2017/4/21 9:14.
 * Email: langmanleguang@qq.com
 * <p>
 * [上门服务列表]的View层。
 * 打开方式：AppStart-->首页-->社区服务列表-->Item。
 */
public class ServicesDetailFragment extends BaseFragment<ServicesDetailContract.Presenter> implements ServicesDetailContract.View {
    private static final String TAG = ServicesDetailFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_cost_detail_services_fragment)
    TextView tvCost;

    Unbinder unbinder;
    @BindView(R.id.iv_firm_detail_services_fragment)
    ImageView ivFirm;
    @BindView(R.id.tv_name_detail_services_fragment)
    TextView tvName;
    @BindView(R.id.tv_age_detail_services_fragment)
    TextView tvAge;
    @BindView(R.id.tv_scope_detail_services_fragment)
    TextView tvScope;
    @BindView(R.id.tv_address_detail_services_fragment)
    TextView tvAddress;
    @BindView(R.id.tv_time_detail_services_fragment)
    TextView tvTime;
    @BindView(R.id.tv_contact_detail_services_fragment)
    TextView tvContact;
    @BindView(R.id.ll_firm_photo_detail_services_fragment)
    LinearLayout llFirmPhoto;
    @BindView(R.id.iv_commodity_detail_services_fragment)
    ImageView ivCommodity;
    @BindView(R.id.tv_title_detail_services_fragment)
    TextView tvTitle;
    @BindView(R.id.tv_subtitle_detail_services_fragment)
    TextView tvSubtitle;
//    @BindView(R.id.tv_info_detail_services_fragment)
//    TextView tvInfo;
    @BindView(R.id.iv_pic_1)
    ImageView ivPic1;
    @BindView(R.id.iv_pic_2)
    ImageView ivPic2;
    @BindView(R.id.iv_pic_3)
    ImageView ivPic3;

    private Params params = Params.getInstance();
    private String contactWay;

    public static ServicesDetailFragment newInstance(String fid) {
        Bundle args = new Bundle();
        args.putString(Constants.SERVICE_FID, fid);
        ServicesDetailFragment fragment = new ServicesDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            params.fid = args.getString(Constants.SERVICE_FID);
        }
    }

    @NonNull
    @Override
    protected ServicesDetailContract.Presenter createPresenter() {
        return new ServicesDetailPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_services, container, false);
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
        toolbarTitle.setText("商品详情");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> pop());
    }

    private void initData() {
        mPresenter.requestServiceDetail(params);
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void responseServiceDetail(ServicesCommodityDetailBean bean) {
        Glide.with(_mActivity)
                .load(bean.getData().getMerchantIconUrl())
                .into(ivFirm);
        Glide.with(_mActivity)
                .load(bean.getData().getCommodityUrl())
                .into(ivCommodity);

        contactWay = bean.getData().getContactWay();

        tvTitle.setText(bean.getData().getCommodityTitle());
        tvScope.setText("商家年龄：" + bean.getData().getMerchantAge() + "年");
        tvAge.setText("服务范畴：" + bean.getData().getServiceScopes());
        tvAddress.setText("商户地址：" + bean.getData().getAddress());
        tvTime.setText("经营时间：" + bean.getData().getBusinessHours());
        tvContact.setText("联系方式：" + bean.getData().getContactWay());
        tvSubtitle.setText("服务范畴：" + bean.getData().getServiceScopes());

        tvCost.setText(bean.getData().getCommodityPrice());

        List<ServicesCommodityDetailBean.DataBean.MerchantSceneBean> sceneBeans = bean.getData().getMerchantScene();
        for (int i = 0; i < sceneBeans.size(); i++) {
            String url = sceneBeans.get(i).getUrl();
            switch (i) {
                case 0:
                    ivPic1.setVisibility(View.VISIBLE);
                    Glide.with(_mActivity)
                            .load(url)
                            .into(ivPic1);
                    break;
                case 1:
                    ivPic2.setVisibility(View.VISIBLE);
                    Glide.with(_mActivity)
                            .load(url)
                            .into(ivPic2);
                    break;
                case 2:
                    ivPic3.setVisibility(View.VISIBLE);
                    Glide.with(_mActivity)
                            .load(url)
                            .into(ivPic3);
                    break;
            }
        }
    }

    @OnClick({R.id.bt_call,R.id.tv_business_license})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.bt_call:
                if (!TextUtils.isEmpty(contactWay)) {
                    //跳系统的拨打电话
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contactWay)));
                }
                break;
            case R.id.tv_business_license:
                //todo:跳WEB
                break;
        }
    }
}
