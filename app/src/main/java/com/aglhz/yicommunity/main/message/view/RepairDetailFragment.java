package com.aglhz.yicommunity.main.message.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.RepairDetailBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.main.message.contract.RepairDetailContract;
import com.aglhz.yicommunity.main.message.presenter.RepairDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/18 0018 17:10.
 * Email: liujia95me@126.com
 */

public class RepairDetailFragment extends BaseFragment<RepairDetailContract.Presenter> implements RepairDetailContract.View {
    private static final String TAG = RepairDetailFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_repair_type_repair_detail_fragment)
    TextView tvRepairType;
    @BindView(R.id.tv_repair_time_repair_detail_fragment)
    TextView tvRepairTime;
    @BindView(R.id.tv_name_repair_detail_fragment)
    TextView tvName;
    @BindView(R.id.tv_contact_repair_detail_fragment)
    TextView tvContact;
    @BindView(R.id.tv_desc_repair_detail_fragment)
    TextView tvDesc;
    @BindView(R.id.recyclerview_pics_repair_detail_fragment)
    RecyclerView recyclerviewPics;
    @BindView(R.id.recyclerview_reply_repair_detail_fragment)
    RecyclerView recyclerviewReply;
    @BindView(R.id.ll_pics_layout_repair_detail_fragment)
    LinearLayout llPicsLayout;
    @BindView(R.id.tv_community_name)
    TextView tvCommunityName;

    private Unbinder unbinder;
    private Params params = Params.getInstance();

    public static RepairDetailFragment newInstance(String fid) {
        RepairDetailFragment detailFragment = new RepairDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_FID, fid);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @NonNull
    @Override
    protected RepairDetailContract.Presenter createPresenter() {
        return new RepairDetailPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        params.fid = getArguments().getString(Constants.KEY_FID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repair_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("报修详情");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        mPresenter.requestRepairDetail(params);
    }

    private void initListener() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseRepairDetail(RepairDetailBean repairDetailBean) {
        RepairDetailBean.DataBean bean = repairDetailBean.getData();
        tvCommunityName.setText(bean.getHouse());
        tvName.setText(bean.getName());
        tvContact.setText(bean.getContact());
        tvDesc.setText(bean.getDes());
        tvRepairTime.setText(bean.getCtime());
        tvRepairType.setText(bean.getType());

        if (bean.getPics().size() > 0) {
            llPicsLayout.setVisibility(View.VISIBLE);
            recyclerviewPics.setLayoutManager(new GridLayoutManager(_mActivity, 3) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            recyclerviewPics.setAdapter(new RepairDetailPicsRVAdapter(bean.getPics()));
        } else {
            llPicsLayout.setVisibility(View.GONE);
        }

        if (bean.getReplys().size() > 0) {
            recyclerviewReply.setLayoutManager(new LinearLayoutManager(_mActivity) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            recyclerviewReply.setAdapter(new RepairDetailReplyRVAdapter(bean.getReplys()));
        }
    }
}
