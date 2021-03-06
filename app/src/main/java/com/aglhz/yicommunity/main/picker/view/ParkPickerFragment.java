package com.aglhz.yicommunity.main.picker.view;

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
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.abase.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.ParkSelectBean;
import com.aglhz.yicommunity.event.EventPark;
import com.aglhz.yicommunity.main.picker.contract.ParkPickerContract;
import com.aglhz.yicommunity.main.picker.presenter.ParkPickerPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.aglhz.yicommunity.common.UserHelper.city;

/**
 * Author: LiuJia on 2017/5/23 0023 14:51.
 * Email: liujia95me@126.com
 * [选择停车场]的View层
 */

public class ParkPickerFragment extends BaseFragment<ParkPickerContract.Presenter> implements ParkPickerContract.View {
    private static final String TAG = ParkPickerFragment.class.getSimpleName();
    private PtrFrameLayout ptrFrameLayout;
    private RecyclerView recyclerView;
    private List<ParkSelectBean.DataBean.ParkPlaceListBean> mDatas;
    private List<ParkSelectBean.DataBean.ParkPlaceListBean> resultData;
    private EditText etSearchCommunity;
    private ParkPickerRVAdapter adapter;
    private TextView tvCity;
    private TextView tvTitle;
    private Toolbar toolbar;
    private Params params = Params.getInstance();
    public static final int REQUEST_CODE_CITY = 100;

    public static ParkPickerFragment newInstance() {
        return new ParkPickerFragment();
    }

    @NonNull
    @Override
    protected ParkPickerContract.Presenter createPresenter() {
        return new ParkPickerPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picker_park, container, false);
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
        initToolbar();
        initData();
        initListener();
        initPtrFrameLayout(ptrFrameLayout, recyclerView);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        tvTitle.setText("选择停车场");
        tvCity.setText(TextUtils.isEmpty(UserHelper.city) ? "选择城市" : UserHelper.city);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onRefresh() {
        mPresenter.requestParkList(params);
    }

    private void initData() {
        params.city = city;
        mPresenter.requestParkList(params);//请求停车场列表
        etSearchCommunity.setHint("请输入停车场关键字");
        mDatas = new ArrayList<>();
        resultData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new ParkPickerRVAdapter(resultData);
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
                for (ParkSelectBean.DataBean.ParkPlaceListBean bean : mDatas) {
                    if (bean.getName().contains(s)) {
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

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            ParkSelectBean.DataBean.ParkPlaceListBean listBean = mDatas.get(position);
            EventBus.getDefault().post(new EventPark(listBean));
            _mActivity.finish();
        });
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CITY) {
            tvCity.setText(params.city = data.getString(Constants.CITY));
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

    /**
     * 响应请求停车场列表
     *
     * @param beans
     */
    @Override
    public void responsePark(List<ParkSelectBean.DataBean.ParkPlaceListBean> beans) {
        ptrFrameLayout.refreshComplete();
        mDatas = beans;
        adapter.setNewData(mDatas);
    }
}
