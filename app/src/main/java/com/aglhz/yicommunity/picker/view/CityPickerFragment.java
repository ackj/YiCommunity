package com.aglhz.yicommunity.picker.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aglhz.abase.cache.SPCache;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.LbsManager;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.picker.PickerActivity;
import com.aglhz.yicommunity.picker.model.City;
import com.aglhz.yicommunity.common.DBManager;
import com.aglhz.yicommunity.picker.model.LocateState;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/29 0029.
 */
public class CityPickerFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = CityPickerFragment.class.getSimpleName();
    public static final String CITY = "city";
    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private EditText searchBox;
    private ImageView clearBtn;
    private ViewGroup emptyView;

    private CityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private List<City> mAllCities;
    private DBManager dbManager;
    private TextView floatTitle;
    private LinearLayout llFloatTitle;
    private TextView tvTitle;
    private Toolbar toolbar;

    public static CityPickerFragment newInstance() {
        return new CityPickerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_picker, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        initToolbar();
        initListener();
        initLocate();
    }

    private void initLocate() {
        LbsManager.getInstance().startLocation(aMapLocation -> {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {

                    String city = aMapLocation.getCity();
                    if (!TextUtils.isEmpty(city)) {
                        if (mCityAdapter != null) {
                            mCityAdapter.updateLocateState(LocateState.SUCCESS, city);
                        }
                        UserHelper.setCity(city);
                        LbsManager.getInstance().stopLocation();
                    }
                } else {
                    mCityAdapter.updateLocateState(LocateState.FAILED, null);
                }
            }
        });

    }

    private void initToolbar() {
        initStateBar(toolbar);
        tvTitle.setText("选择城市");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        dbManager = new DBManager(_mActivity);
        dbManager.copyDBFile();
        mAllCities = dbManager.getAllCities();
        mCityAdapter = new CityListAdapter(_mActivity, mAllCities);
        mResultAdapter = new ResultListAdapter(_mActivity, null);
    }

    private void initListener() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                ALog.d(TAG, " first:" + firstVisibleItem + "   visible:" + visibleItemCount + " total:" + totalItemCount);
                char title = mAllCities.get(firstVisibleItem).getPinyin().toUpperCase().charAt(0);
                ALog.d(TAG, "ciry:" + title);
                if (title >= 'A') {
                    llFloatTitle.setVisibility(View.VISIBLE);
                    floatTitle.setText(title + "");
                } else {
                    llFloatTitle.setVisibility(View.GONE);
                }

            }
        });

        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                LbsManager.getInstance().startLocation();
            }
        });
    }

    private void saveUsedCity2Local(String name) {
        String citysStr = (String) SPCache.get(_mActivity, Constants.SP_KEY_USED_CITYS, "");
        if (TextUtils.isEmpty(citysStr)) {
            citysStr = name;
        } else {
            if (citysStr.contains(name)) {
                if (citysStr.startsWith(name)) {
                    return;
                } else {
                    String[] split = citysStr.split("," + name);
                    citysStr = split.length == 2 ? split[0] + split[1] : split[0];
                    ALog.d(TAG, "contains substring --> :" + citysStr);
                }
            } else {
                String[] split = citysStr.split(",");
                //最多存6个
                if (split.length >= 6) {
                    ALog.d(TAG, "indexof:" + citysStr.lastIndexOf(","));
                    citysStr = citysStr.substring(0, citysStr.lastIndexOf(","));
                }
            }
            citysStr = name + "," + citysStr;
        }
        ALog.d(TAG, "save citysStr:" + citysStr);
        SPCache.put(_mActivity, Constants.SP_KEY_USED_CITYS, citysStr);
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.listview_all_city);
        mListView.setAdapter(mCityAdapter);

        floatTitle = (TextView) view.findViewById(R.id.tv_float_title);
        llFloatTitle = (LinearLayout) view.findViewById(R.id.ll_float_title);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        tvTitle = (TextView) view.findViewById(R.id.toolbar_title);

        TextView overlay = (TextView) view.findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) view.findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(letter -> {
            int position = mCityAdapter.getLetterPosition(letter);
            mListView.setSelection(position);
        });

        searchBox = (EditText) view.findViewById(R.id.et_search);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    clearBtn.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    mResultListView.setVisibility(View.GONE);
                } else {
                    clearBtn.setVisibility(View.VISIBLE);
                    mResultListView.setVisibility(View.VISIBLE);
                    List<City> result = dbManager.searchCity(keyword);
                    if (result == null || result.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(result);
                    }
                }
            }
        });

        emptyView = (ViewGroup) view.findViewById(R.id.empty_view);
        mResultListView = (ListView) view.findViewById(R.id.listview_search_result);
        mResultListView.setAdapter(mResultAdapter);
        mResultListView.setOnItemClickListener((parent, view1, position, id) -> back(mResultAdapter.getItem(position).getName()));

        clearBtn = (ImageView) view.findViewById(R.id.iv_search_clear);

        clearBtn.setOnClickListener(this);
    }

    private void back(String city) {
        saveUsedCity2Local(city);
        UserHelper.setCity(city);
        Bundle bundle = new Bundle();
        bundle.putString(CITY, city);
        setFragmentResult(RESULT_OK, bundle);
        pop();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_search_clear) {
            searchBox.setText("");
            clearBtn.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            mResultListView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LbsManager.getInstance().stopLocation();
    }

    @OnClick(R.id.ll_location)
    public void onViewClicked() {
        _mActivity.startActivity(new Intent(_mActivity, PickerActivity.class));
    }
}
