package com.aglhz.yicommunity.main.services.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.widget.statemanager.StateManager;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.abase.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.entity.bean.RemarkListBean;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.event.EventRefreshRemarkList;
import com.aglhz.yicommunity.main.publish.CommentActivity;
import com.aglhz.yicommunity.main.services.contract.RemarkContract;
import com.aglhz.yicommunity.main.services.presenter.RemarkPresenter;
import com.aglhz.yicommunity.preview.PreviewActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: leguang on 2017/4/21 9:14.
 * Email: langmanleguang@qq.com
 * <p>
 * [评论列表]的View层。
 */
public class RemarkListFragment extends BaseFragment<RemarkContract.Presenter> implements RemarkContract.View {
    private static final String TAG = RemarkListFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.bt_remark_list_fragment)
    Button btRemark;
    private RemarkListRVAdapter adapter;
    private Unbinder unbinder;
    private Params params = Params.getInstance();
    private String firmName;
    private StateManager mStateManager;

    /**
     * 启动该View的入口
     *
     * @param commodityFid 要根据此唯一标识来请求接口
     * @return
     */
    public static RemarkListFragment newInstance(String commodityFid, String firmName) {
        Bundle args = new Bundle();
        args.putString(Constants.COMMODITY_FID, commodityFid);
        args.putString(Constants.FIRM_NAME, firmName);
        RemarkListFragment fragment = new RemarkListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            params.commodityFid = args.getString(Constants.COMMODITY_FID);
            firmName = args.getString(Constants.FIRM_NAME);
        }
    }

    @NonNull
    @Override
    protected RemarkContract.Presenter createPresenter() {
        return new RemarkPresenter(this) {
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remark_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initStateManager();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    @Override
    public void onRefresh() {
        mPresenter.start(params);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText(TextUtils.isEmpty(firmName) ? "点评" : firmName);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
        toolbar.setOnClickListener(v -> go2Top());
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new RemarkListRVAdapter();
        adapter.setEnableLoadMore(true);
        recyclerView.setAdapter(adapter);
        //设置Item动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.isFirstOnly(true);
    }

    private void initListener() {
        //设置允许加载更多
        adapter.setOnLoadMoreListener(() -> {
            params.page++;
            mPresenter.start(params);//请求获取开门列表
        }, recyclerView);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            RemarkListBean.DataBean.CommentListBean bean = adapter.getData().get(position);
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
                    _mActivity.startActivity(intent);
                    break;
            }
        });
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_empty)
                .setEmptyImage(R.drawable.ic_open_record_empty_state_gray_200px)
                .setEmptyText("暂无点评！请点击刷新")
                .setErrorOnClickListener(v -> ptrFrameLayout.autoRefresh())
                .setEmptyOnClickListener(v -> onViewClicked())
                .build();
    }

    @Override
    public void start(Object response) {
        RemarkListBean data = (RemarkListBean) response;
        ptrFrameLayout.refreshComplete();
        if (data == null || data.getData().getCommentList().isEmpty()) {
            if (params.page == 1) {
                mStateManager.showEmpty();
            }
            adapter.loadMoreEnd();
            return;
        }

        if (params.page == 1) {
            mStateManager.showContent();
            adapter.setNewData(data.getData().getCommentList());
            adapter.disableLoadMoreIfNotFullPage(recyclerView);
        } else {
            adapter.addData(data.getData().getCommentList());
            adapter.setEnableLoadMore(true);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(getView(), errorMessage);//后面换成pagerstate的提示，不需要这种了
        if (params.page == 1) {
            //为后面的pageState做准备
            mStateManager.showError();
        } else if (params.page > 1) {
            adapter.loadMoreFail();
            params.page--;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 选择社区后要刷新服务列表
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        recyclerView.scrollToPosition(0);
        ptrFrameLayout.autoRefresh();
    }

    @OnClick(R.id.bt_remark_list_fragment)
    public void onViewClicked() {
        startForResult(RemarkFragment.newInstance(params.commodityFid, firmName), 100);
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RemarkFragment.RESULT_RECORD && data != null) {
            ptrFrameLayout.autoRefresh();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void go2TopAndRefresh(EventRefreshRemarkList event) {
        if (recyclerView == null || ptrFrameLayout == null) {
            return;
        }
        recyclerView.scrollToPosition(0);
        ptrFrameLayout.autoRefresh();
    }

    public void go2Top() {
        if (recyclerView == null) {
            return;
        }
        recyclerView.scrollToPosition(0);
    }
}
