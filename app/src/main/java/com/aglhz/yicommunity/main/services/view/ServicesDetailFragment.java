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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.ServiceDetailBean;
import com.aglhz.yicommunity.event.EventRefreshRemarkList;
import com.aglhz.yicommunity.main.publish.CommentActivity;
import com.aglhz.yicommunity.main.services.contract.ServicesDetailContract;
import com.aglhz.yicommunity.main.services.presenter.ServicesDetailPresenter;
import com.aglhz.yicommunity.preview.PreviewActivity;
import com.aglhz.yicommunity.web.WebActivity;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    public static final String TAG = ServicesDetailFragment.class.getSimpleName();
    public static final int SERVICES_DETAIL_REQUESTCODE = 1101;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_cost_services_detail_fragment)
    TextView tvCost;
    Unbinder unbinder;
    @BindView(R.id.iv_firm_services_detail_fragment)
    ImageView ivFirm;
    @BindView(R.id.tv_name_services_detail_fragment)
    TextView tvName;
    @BindView(R.id.tv_age_services_detail_fragment)
    TextView tvAge;
    @BindView(R.id.tv_scope_services_detail_fragment)
    TextView tvScope;
    @BindView(R.id.tv_address_services_detail_fragment)
    TextView tvAddress;
    @BindView(R.id.tv_time_services_detail_fragment)
    TextView tvTime;
    @BindView(R.id.tv_contact_services_detail_fragment)
    TextView tvContact;
    @BindView(R.id.ll_firm_photo_services_detail_fragment)
    LinearLayout llFirmPhoto;
    @BindView(R.id.iv_commodity_services_detail_fragment)
    ImageView ivCommodity;
    @BindView(R.id.tv_title_services_detail_fragment)
    TextView tvTitle;
    @BindView(R.id.tv_subtitle_services_detail_fragment)
    TextView tvSubtitle;
    @BindView(R.id.tv_info_services_detail_fragment)
    TextView tvInfo;
    @BindView(R.id.rv_scene_services_detail_fragment)
    RecyclerView rvScene;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.rv_remark_services_detail_fragment)
    RecyclerView rvRemark;
    @BindView(R.id.bt_all_remark_services_detail_fragment)
    Button btAll;
    @BindView(R.id.tv_user_remark_services_detail_fragment)
    TextView tvUserRemark;
    @BindView(R.id.sv_services_detail_fragment)
    ScrollView sv;
    private Params params = Params.getInstance();
    private String contactWay, firmName;
    private ServiceDetailSceneRVAdapter adapterScene;
    private ServicesDetailRemarkRVAdapter adapterRemark;

    /**
     * 该页的入口
     *
     * @param fid 请求详情接口需要的fid
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
            ALog.e("params.fid-->" + params.fid);
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
        View view = inflater.inflate(R.layout.fragment_services_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
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
        toolbar.setOnClickListener(v -> go2Top());
        toolbar.inflateMenu(R.menu.menu_services_detail);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_remark:
                    startForResult(RemarkFragment.newInstance(params.fid, firmName), 100);
                    break;
                case R.id.action_report:
                    Intent introductionIntent = new Intent(_mActivity, WebActivity.class);
                    introductionIntent.putExtra(Constants.KEY_TITLE, "举报投诉");
                    String link = String.format(ApiService.REPORT_SERVICE_URL, UserHelper.token, params.fid);
                    introductionIntent.putExtra(Constants.KEY_LINK, link);
                    _mActivity.startActivity(introductionIntent);
                    break;
            }
            return true;
        });
    }

    private void initData() {
        mPresenter.requestServiceDetail(params);
        adapterScene = new ServiceDetailSceneRVAdapter();
        adapterRemark = new ServicesDetailRemarkRVAdapter();
        //————————————————————用户点评RecyclerView——————————————————
        rvRemark.setLayoutManager(new LinearLayoutManager(_mActivity) {

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapterRemark.setOnItemChildClickListener((adapter, view, position) -> {
            ServiceDetailBean.DataBean.CommorityCommentBean bean = adapterRemark.getData().get(position);
            switch (view.getId()) {
                case R.id.iv_head_item_rv_remark:
                    Intent preIntent = new Intent(_mActivity, PreviewActivity.class);
                    preIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle = new Bundle();
                    ArrayList<String> picsUrls = new ArrayList<>();
                    picsUrls.add(bean.getMember().getAvator());
                    bundle.putStringArrayList("picsList", picsUrls);
                    preIntent.putExtra("pics", bundle);
                    preIntent.putExtra("position", 0);
                    _mActivity.startActivity(preIntent);
                    break;
                case R.id.ll_comment_item_rv_remark:
                case R.id.tv_comment_count_item_rv_remark:
                    Intent intent = new Intent(_mActivity, CommentActivity.class);
                    intent.putExtra(Constants.KEY_FID, bean.getFid());
                    intent.putExtra(Constants.KEY_TYPE, Constants.TYPE_REMARK);
                    _mActivity.startActivityForResult(intent, SERVICES_DETAIL_REQUESTCODE);
                    break;
            }
        });
        rvRemark.setAdapter(adapterRemark);
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
        EventBus.getDefault().unregister(this);
    }

    /**
     * 响应请求详情数据
     *
     * @param bean
     */
    @Override
    public void responseServiceDetail(ServiceDetailBean bean) {
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
        firmName = bean.getData().getMerchantName();

        tvName.setText(firmName);
        tvTitle.setText(bean.getData().getCommodityTitle());
        tvAge.setText("商家年龄：" + bean.getData().getMerchantAge() + "年");
        tvScope.setText("服务范畴：" + bean.getData().getServiceScopes());
        tvAddress.setText("商户地址：" + bean.getData().getAddress());
        tvTime.setText("经营时间：" + bean.getData().getBusinessHours());
        tvContact.setText(Html.fromHtml("联系方式：<font color=red>" + contactWay.substring(0, 3) + "****" + contactWay.substring(7, 11) + "</font>"));

        tvSubtitle.setText(bean.getData().getCommodityDesc());

        tvCost.setText(bean.getData().getCommodityPrice());
        List<ServiceDetailBean.DataBean.MerchantSceneBean> sceneBeans = bean.getData().getMerchantScene();

        //——————————————商家实景——————————————————
        rvScene.setLayoutManager(new LinearLayoutManager(_mActivity, LinearLayoutManager.HORIZONTAL, false));
        rvScene.setAdapter(adapterScene);
        adapterScene.setNewData(sceneBeans);
        adapterScene.setOnItemClickListener((adapter1, view, position) -> {
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

        //————————————————服务介绍————————————————————
        StringBuilder sb = new StringBuilder()
                .append("【服务优点】\n")
                .append(bean.getData().getCommodityMerit())
                .append("\n")
                .append("【服务流程】\n")
                .append(bean.getData().getCommodityServiceFlow())
                .append("\n")
                .append("【服务时长参考】\n")
                .append(bean.getData().getDuration())
                .append("\n")
                .append("【覆盖区域】\n")
                .append(bean.getData().getCoverageArea())
                .append("\n");
        tvInfo.setText(sb.toString());

        //——————————————用户点评——————————————————
        if (!bean.getData().getCommorityComment().isEmpty()) {
            tvUserRemark.setVisibility(View.VISIBLE);
            rvRemark.setVisibility(View.VISIBLE);
            btAll.setVisibility(View.VISIBLE);
            tvUserRemark.setText("用户点评（" + bean.getData().getCommorityComment().size() + "）");
            adapterRemark.setNewData(bean.getData().getCommorityComment());
            btAll.setOnClickListener(v -> {
                start(RemarkListFragment.newInstance(params.fid, bean.getData().getMerchantName()));
            });
        }
    }

    @OnClick({R.id.bt_call_services_detail_fragment
            , R.id.tv_business_license_services_detail_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_call_services_detail_fragment:
                if (!TextUtils.isEmpty(contactWay)) {
                    //跳系统的拨打电话
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactWay)));
                }
                break;
            case R.id.tv_business_license_services_detail_fragment:
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

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RemarkFragment.RESULT_RECORD && data != null) {
            mPresenter.requestServiceDetail(params);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(EventRefreshRemarkList event) {
        if (mPresenter == null) {
            return;
        }
        mPresenter.requestServiceDetail(params);
    }

    public void go2Top() {
        if (sv == null) {
            return;
        }
        sv.post(() -> sv.fullScroll(ScrollView.FOCUS_UP));//滑动到顶部，提高用户体验，方便用户点击头像登录。
    }
}
