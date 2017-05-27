package com.aglhz.yicommunity.picker.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.LbsManager;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.ScrollingHelper;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.picker.contract.CommunityPickerContract;
import com.aglhz.yicommunity.picker.presenter.CommunityPickerPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

import static com.aglhz.yicommunity.common.UserHelper.city;


/**
 * Created by Administrator on 2017/4/29 0029.
 */
public class CommunityPickerFragment extends BaseFragment<CommunityPickerContract.Presenter> implements CommunityPickerContract.View {
    private static final String TAG = CommunityPickerFragment.class.getSimpleName();
    private PtrFrameLayout ptrFrameLayout;
    private RecyclerView recyclerView;
    private List<CommunitySelectBean.DataBean.CommunitiesBean> mDatas;
    private List<CommunitySelectBean.DataBean.CommunitiesBean> resultData;
    private EditText etSearchCommunity;
    private CommunityPickerRVAdapter adapter;
    private TextView tvCity;
    private TextView tvTitle;
    private Toolbar toolbar;
    private Params params = Params.getInstance();
    public static final int REQUEST_CODE_CITY = 100;

    public static CommunityPickerFragment newInstance() {
        return new CommunityPickerFragment();
    }

    @NonNull
    @Override
    protected CommunityPickerContract.Presenter createPresenter() {
        return new CommunityPickerPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picker_community, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptrFrameLayout);
        etSearchCommunity = (EditText) view.findViewById(R.id.et_search);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        tvTitle = (TextView) view.findViewById(R.id.toolbar_title);
        tvCity = (TextView) view.findViewById(R.id.toolbar_menu);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initLocate();
        initToolbar();
        initData();
        initListener();
        initPtrFrameLayout();
    }

//    private void initLocate() {
//        LbsManager.getInstance().startLocation(aMapLocation -> {
//            if (aMapLocation != null) {
//                if (aMapLocation.getErrorCode() == 0) {
//                    String city = aMapLocation.getCity();
//                    if (!TextUtils.isEmpty(city)) {
//                        tvCity.setText(city);
//                        UserHelper.setCity(city);
//                        LbsManager.getInstance().stopLocation();
//                    }
//                }
//            }
//        });
//    }

    private void initToolbar() {
        initStateBar(toolbar);
        tvTitle.setText("小区名称");
        tvCity.setText(TextUtils.isEmpty(UserHelper.city) ? "选择城市" : UserHelper.city);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }


    private void initData() {
        etSearchCommunity.setHint("请输入城市名或小区名");
        mDatas = new ArrayList<>();
        resultData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new CommunityPickerRVAdapter(resultData);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        etSearchCommunity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ALog.e(TAG, "beforeTextChanged:" + s + " start:" + start + " count:" + count + " after:" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ALog.e(TAG, "onTextChanged:" + s + " start:" + start + " count:" + count);
                resultData.clear();
                for (CommunitySelectBean.DataBean.CommunitiesBean bean : mDatas) {
                    if (bean.getName().contains(s) || bean.getPosition().getAddress().contains(s)) {
                        resultData.add(bean);
                    }
                }
                adapter.setSearchKey(s.toString());
                adapter.setNewData(resultData);
            }

            @Override
            public void afterTextChanged(Editable s) {
                ALog.e(TAG, "afterTextChanged:" + s);
            }
        });

        tvCity.setOnClickListener(v -> startForResult(CityPickerFragment.newInstance(), REQUEST_CODE_CITY));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            CommunitySelectBean.DataBean.CommunitiesBean community = mDatas.get(position);

            //设置省、市、区
            UserHelper.setPosition(community.getPosition().getProvince()
                    , community.getPosition().getCity()
                    , community.getPosition().getCounty()
                    , community.getPosition().getAddress());

            //设置小区
            UserHelper.setCommunity(community.getName(), community.getCode());
            EventBus.getDefault().post(new EventCommunity(community));
            _mActivity.finish();
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
                ALog.e("开始刷新了");
                params.city = UserHelper.city;
                mPresenter.requestCommunitys(params);
            }
        });
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CITY) {

            ALog.e("params.city-->" + params.city);
            tvCity.setText(UserHelper.city = data.getString(Constants.CITY));
            ptrFrameLayout.autoRefresh();
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        ptrFrameLayout.refreshComplete();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseCommunitys(List<CommunitySelectBean.DataBean.CommunitiesBean> beans) {
        ptrFrameLayout.refreshComplete();
        mDatas = beans;
        adapter.setNewData(mDatas);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LbsManager.getInstance().stopLocation();
    }
}
