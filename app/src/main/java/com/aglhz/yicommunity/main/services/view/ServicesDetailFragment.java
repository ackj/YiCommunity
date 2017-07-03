package com.aglhz.yicommunity.main.services.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.ServicesCommodityDetailBean;
import com.aglhz.yicommunity.main.services.contract.ServicesDetailContract;
import com.aglhz.yicommunity.main.services.presenter.ServicesDetailPresenter;
import com.aglhz.yicommunity.preview.PreviewActivity;
import com.aglhz.yicommunity.web.WebActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
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
    @BindView(R.id.tv_info_detail_services_fragment)
    TextView tvInfo;
    @BindView(R.id.rv_pics)
    RecyclerView rvPics;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;

    private Params params = Params.getInstance();
    private String contactWay;

    /**
     * 该页的入口
     * @param fid  请求详情接口需要的fid
     * @return
     */
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
        toolbarMenu.setText("举报");
        toolbarMenu.setOnClickListener(v -> {
            Intent introductionIntent = new Intent(_mActivity, WebActivity.class);
            introductionIntent.putExtra(Constants.KEY_TITLE, "举报投诉");
            String link = String.format(ApiService.REPORT_URL, UserHelper.token, 1, params.fid);
            ALog.e(TAG, "report url:::" + link);
            introductionIntent.putExtra(Constants.KEY_LINK, link);
            _mActivity.startActivity(introductionIntent);
        });
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

    /**
     * 响应请求详情数据
     * @param bean
     */
    @Override
    public void responseServiceDetail(ServicesCommodityDetailBean bean) {
        Glide.with(_mActivity)
                .load(bean.getData().getMerchantIconUrl())
                .error(R.drawable.ic_default_img_120px)
                .placeholder(R.drawable.ic_default_img_120px)
                .into(ivFirm);
        Glide.with(_mActivity)
                .load(bean.getData().getCommodityUrl())
                .error(R.drawable.ic_default_img_120px)
                .placeholder(R.drawable.ic_default_img_120px)
                .into(ivCommodity);

        contactWay = bean.getData().getContactWay();

        tvTitle.setText(bean.getData().getCommodityTitle());
        tvScope.setText("商家年龄：" + bean.getData().getMerchantAge() + "年");
        tvAge.setText("服务范畴：" + bean.getData().getServiceScopes());
        tvAddress.setText("商户地址：" + bean.getData().getAddress());
        tvTime.setText("经营时间：" + bean.getData().getBusinessHours());
        tvContact.setText(Html.fromHtml("联系方式：<font color=red>" + contactWay.substring(0, 3) + "****" + contactWay.substring(7, 11) + "</font>"));

        tvSubtitle.setText("服务范畴：" + bean.getData().getServiceScopes());
        tvInfo.setText(bean.getData().getCommodityDesc());

        tvCost.setText(bean.getData().getCommodityPrice());
        List<ServicesCommodityDetailBean.DataBean.MerchantSceneBean> sceneBeans = bean.getData().getMerchantScene();
        rvPics.setLayoutManager(new LinearLayoutManager(_mActivity, LinearLayoutManager.HORIZONTAL, false));
        ServiceDetailPicsRVAdapter adapter = new ServiceDetailPicsRVAdapter();
        rvPics.setAdapter(adapter);
        adapter.setNewData(sceneBeans);

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent intent = new Intent(_mActivity, PreviewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle bundle = new Bundle();
            ArrayList<String> picsUrls = new ArrayList<>();
            for (int i = 0; i < sceneBeans.size(); i++) {
                picsUrls.add(sceneBeans.get(i).getUrl());
            }
            bundle.putStringArrayList("picsList", picsUrls);
            intent.putExtra("pics", bundle);
            intent.putExtra("position", position);
            _mActivity.startActivity(intent);
        });
    }

    @OnClick({R.id.bt_call, R.id.tv_business_license})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_call:
                if (!TextUtils.isEmpty(contactWay)) {
                    //跳系统的拨打电话
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactWay)));
                }
                break;
            case R.id.tv_business_license:
                go2Web("营业执照", ApiService.BUSINESS_LICENSE_URL + params.fid);
                break;
        }
    }

    public void go2Web(String title, String link) {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_LINK, link);
        _mActivity.startActivity(intent);
    }
}
