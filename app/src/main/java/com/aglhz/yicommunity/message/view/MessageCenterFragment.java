package com.aglhz.yicommunity.message.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.mvp.view.base.Decoration;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.MessageCenterBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.message.contract.MessageCenterContract;
import com.aglhz.yicommunity.message.presenter.MessageCenterPresenter;
import com.aglhz.yicommunity.propery.view.PropertyPayFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by leguang on 2017/5/7 0007.
 * Email：langmanleguang@qq.com
 */

public class MessageCenterFragment extends BaseFragment<MessageCenterContract.Presenter> implements MessageCenterContract.View {
    private static final String TAG = MessageCenterFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    Unbinder unbinder;
    private Params params = Params.getInstance();
    private LinearLayoutManager layoutManager;
    private MessageCenterRVAdapter adapter;

    private static final String SMARTDOOR_PUSHREC = "smartdoor_pushrec";
    private static final String HOUSE_OWNER_APPLY = "house_owner_apply";// 业主申请
    private static final String HOUSE_MEMBER_APPLY = "house_member_apply";// 成员申请
    private static final String HOUSE_RENTER_APPLY = "house_renter_apply";// 租客申请
    private static final String HOUSE_OWNER_APPROVE = "house_owner_approve";// 业主申请审核
    private static final String HOUSE_MEMBER_APPROVE = "house_member_approve";// 成员申请审核
    private static final String HOUSE_RENTER_APPROVE = "house_renter_approve";// 租客申请审核
    private static final String FEEDBACK_REPLY = "feedback_reply";// 信息反馈回复
    private static final String REPAIR_REPLY = "repair_reply";// 物业报修回复
    private static final String NOTICE_PUBLISH = "notice_publish";// 公告发布
    private static final String PROPERTY_BILL = "property_bill";// 物业账单

    public static MessageCenterFragment newInstance() {
        return new MessageCenterFragment();
    }

    @NonNull
    @Override
    protected MessageCenterContract.Presenter createPresenter() {
        return new MessageCenterPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initPtrFrameLayout();
        initListener();
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MessageCenterRVAdapter();

        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.start(params);

        }, recyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new Decoration(_mActivity, Decoration.VERTICAL_LIST));
    }


    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("消息中心");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            MessageCenterBean.DataBean.MemNewsBean bean = (MessageCenterBean.DataBean.MemNewsBean) adapter.getData().get(position);
            if (view.getId() == R.id.ll_layout_item_message_center_fragment) {
                ALog.e(TAG, "type:" + bean.getOpType());
                switch (bean.getOpType()) {
                    case NOTICE_PUBLISH://公告发布

                        break;
                    case HOUSE_OWNER_APPLY: //业主申请
                        //todo:可能要改
                        start(ApplyCheckFragment.newInstance(bean.getTitle(), bean.getDes()));
                        break;
                    case HOUSE_MEMBER_APPLY://成员申请
                        start(ApplyCheckFragment.newInstance(bean.getTitle(), bean.getDes()));
                        break;
                    case HOUSE_RENTER_APPLY://租客申请
                        //todo:可能要改
                        start(ApplyCheckFragment.newInstance(bean.getTitle(), bean.getDes()));
                        break;
                    case HOUSE_OWNER_APPROVE://业主申请审核结果
                        //todo:可能要改
                        start(ApplyResultFragment.newInstance());
                        break;
                    case HOUSE_MEMBER_APPROVE://成员申请审核结果
                        //todo:可能要改
                        start(ApplyResultFragment.newInstance());
                        break;
                    case HOUSE_RENTER_APPROVE://租客申请审核结果
                        break;
                    case FEEDBACK_REPLY://信息反馈回复
                        break;
                    case REPAIR_REPLY://物业报修回复
                        // start(RepairDetailFragment.newInstance(bean.getSfid()));
                        break;
                    case PROPERTY_BILL://物业账单
                        start(PropertyPayFragment.newInstance());
                        break;
                    case SMARTDOOR_PUSHREC://
                        start(CompainsReplyFragment.newInstance());
                        break;
                }
            }
        });
    }

    private void initPtrFrameLayout() {
        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(BaseApplication.mContext, 15F), 0, DensityUtils.dp2px(BaseApplication.mContext, 10F));
        header.setPtrFrameLayout(ptrFrameLayout);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.postDelayed(() -> ptrFrameLayout.autoRefresh(true), 100);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //判断是否滑动到顶部。
                return ScrollingHelper.isRecyclerViewToTop(recyclerView);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                ALog.e("开始刷新了…………");
                params.page = 1;
                params.pageSize = Constants.PAGE_SIZE;
                mPresenter.start(params);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        if (adapter != null) {
            adapter = null;
        }
    }

    @Override
    public void start(Object response) {
        ptrFrameLayout.refreshComplete();
        List<MessageCenterBean.DataBean.MemNewsBean> datas = (List<MessageCenterBean.DataBean.MemNewsBean>) response;

        if (datas == null || datas.isEmpty()) {
            adapter.loadMoreEnd();
            return;
        }

        if (params.page == 1) {
            adapter.setNewData(datas);
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(datas);
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void error(String errorMessage) {

        ptrFrameLayout.refreshComplete();
        if (params.page == 1) {
            //为后面的pageState做准备
        } else if (params.page > 1) {
            adapter.loadMoreFail();
            params.page--;
        }
        DialogHelper.warningSnackbar(getView(), errorMessage);//后面换成pagerstate的提示，不需要这种了
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        recyclerView.scrollToPosition(0);
        ptrFrameLayout.autoRefresh();
    }
}
