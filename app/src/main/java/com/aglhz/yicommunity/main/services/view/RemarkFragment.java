package com.aglhz.yicommunity.main.services.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.network.http.HttpHelper;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.abase.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: leguang on 2017/4/21 9:14.
 * Email: langmanleguang@qq.com
 * <p>
 * [点评]的View层。
 * 打开方式：AppStart-->首页-->社区服务列表-->服务详情-->点评。
 */
public class RemarkFragment extends BaseFragment {
    public static final String TAG = RemarkFragment.class.getSimpleName();
    public static final int RESULT_RECORD = 110;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rb_remark_services_fragment)
    RatingBar rbRemark;
    @BindView(R.id.et_describe_remark_services_fragment)
    EditText etDescribe;
    @BindView(R.id.bt_submit_remark_services_fragment)
    Button btSubmit;
    private Params params = Params.getInstance();
    private Unbinder unbinder;
    private String title;

    /**
     * 该页的入口
     *
     * @param commodityFid 请求详情接口需要的fid
     * @return
     */
    public static RemarkFragment newInstance(String commodityFid, String firmName) {
        Bundle args = new Bundle();
        args.putString(Constants.COMMODITY_FID, commodityFid);
        args.putString(Constants.FIRM_NAME, firmName);
        RemarkFragment fragment = new RemarkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            params.commodityFid = args.getString(Constants.COMMODITY_FID);
            title = args.getString(Constants.FIRM_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remark_services, container, false);
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
        toolbarTitle.setText(TextUtils.isEmpty(title) ? "点评" : title);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> pop());
    }

    private void initData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_submit_remark_services_fragment)
    public void onViewClicked() {
        ALog.e("NumStars-->" + rbRemark.getNumStars());
        ALog.e("getRating-->" + rbRemark.getRating());
        ALog.e("getStepSize-->" + rbRemark.getStepSize());

        if (TextUtils.isEmpty(etDescribe.getText().toString())) {
            DialogHelper.warningSnackbar(getView(), "评论不能为空");
            return;
        }

        if (etDescribe.getText().toString().length() < 10) {
            DialogHelper.warningSnackbar(getView(), "评论字数不能小于10");
            return;
        }

        showLoading();
        params.startLevel = (int) rbRemark.getRating();
        params.content = etDescribe.getText().toString();
        HttpHelper.getService(ApiService.class)
                .requestRemarkService(ApiService.requestRemarkService,
                        params.token,
                        params.commodityFid,
                        params.startLevel,
                        params.content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    dismissLoading();
                    if (bean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        DialogHelper.successSnackbar(getView(), bean.getOther().getMessage());
                        setFragmentResult(RESULT_RECORD, new Bundle());
                        pop();
                    } else {
                        DialogHelper.errorSnackbar(getView(), bean.getOther().getMessage());
                    }
                }, throwable -> {
                    dismissLoading();
                    DialogHelper.errorSnackbar(getView(), "网络异常");
                });
    }
}
