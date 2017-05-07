package com.aglhz.yicommunity.picker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.CommunityPickerBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/4/29 0029.
 */
public class CommunityPickerFragment extends BaseFragment {

    private static final String TAG = CommunityPickerFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private List<CommunityPickerBean> fakeData;
    private List<CommunityPickerBean> resultData;
    private EditText etSearchCommunity;
    private CommunityListAdapter adapter;
    private TextView tvCity;
    private TextView tvTitle;
    private Toolbar toolbar;

    public static CommunityPickerFragment newInstance() {
        return new CommunityPickerFragment();
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
    }

    private void initToolbar() {
        initStateBar(toolbar);
        tvTitle.setText("小区名字");
        tvCity.setText("选择城市");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        etSearchCommunity.setHint("请输入城市名或小区名");
        //造假数据
        fakeData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fakeData.add(new CommunityPickerBean("城市花样", "广东省惠州市惠城区城市花园"));
        }
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
                for (CommunityPickerBean bean : fakeData) {
                    if (bean.name.contains(s) || bean.address.contains(s)) {
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

        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startForResult(CityPickerFragment.newInstance(), 100);
            }
        });
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            String city = data.getString("city");
            tvCity.setText(city);
        }
    }
}
