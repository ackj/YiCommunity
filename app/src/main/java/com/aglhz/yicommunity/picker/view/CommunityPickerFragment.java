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
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.CommunitySelectBean;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.LbsManager;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunityChange;
import com.aglhz.yicommunity.picker.contract.CityPickerContract;
import com.aglhz.yicommunity.picker.presenter.CityPickerPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/4/29 0029.
 */
public class CommunityPickerFragment extends BaseFragment<CityPickerContract.Presenter> implements CityPickerContract.View {
    private static final String TAG = CommunityPickerFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<CommunitySelectBean.DataBean.CommunitiesBean> mDatas;
    private List<CommunitySelectBean.DataBean.CommunitiesBean> resultData;
    private EditText etSearchCommunity;
    private CommunityListAdapter adapter;
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
    protected CityPickerContract.Presenter createPresenter() {
        return new CityPickerPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picker_community, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_search_community);
        etSearchCommunity = (EditText) view.findViewById(R.id.et_search);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        tvTitle = (TextView) view.findViewById(R.id.toolbar_title);
        tvCity = (TextView) view.findViewById(R.id.toolbar_menu);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
        initListener();
        initLocate();
    }

    private void initLocate() {
        LbsManager.getInstance().startLocation(aMapLocation -> {
            String city = aMapLocation.getCity();
            if (!TextUtils.isEmpty(city)) {
                tvCity.setText(city);
                UserHelper.setCity(city);
                LbsManager.getInstance().stopLocation();
            }
        });
    }


    private void initToolbar() {
        initStateBar(toolbar);
        tvTitle.setText("小区名字");
        tvCity.setText("选择城市");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        params.city = UserHelper.city;
        mPresenter.requestCommunitys(params);

        etSearchCommunity.setHint("请输入城市名或小区名");
        //造假数据
        mDatas = new ArrayList<>();

        resultData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new CommunityListAdapter(resultData);
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

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            CommunitySelectBean.DataBean.CommunitiesBean communitiesBean = mDatas.get(position);
            EventBus.getDefault().post(new EventCommunityChange(communitiesBean));
            _mActivity.finish();
            return false;
        });
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CITY) {
            tvCity.setText(params.city = data.getString(Constants.CITY));
            mPresenter.requestCommunitys(params);
        }
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseCommunitys(List<CommunitySelectBean.DataBean.CommunitiesBean> beans) {
        ALog.d("CommunityList bpeans size:" + beans.size());
        mDatas = beans;
        adapter.setNewData(mDatas);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LbsManager.getInstance().stopLocation();
    }
}
