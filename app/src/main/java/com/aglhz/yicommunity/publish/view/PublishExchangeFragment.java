package com.aglhz.yicommunity.publish.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.ImageUtils;
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunityChange;
import com.aglhz.yicommunity.picker.PickerActivity;
import com.aglhz.yicommunity.publish.contract.PublishContract;
import com.aglhz.yicommunity.publish.presenter.PublishExchangePresenter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/11 0011 21:13.
 * Email: liujia95me@126.com
 */

public class PublishExchangeFragment extends BaseFragment<PublishExchangePresenter> implements PublishContract.View {
    private final String TAG = PublishExchangeFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.et_input_money)
    EditText etInputMoney;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.et_input_content)
    EditText etInputContent;
    @BindView(R.id.tv_community_address)
    TextView tvCommunityAddress;

    private Unbinder unbinder;
    private PublishImageRVAdapter adapter;
    private Params params = Params.getInstance();

    public static PublishExchangeFragment newInstance() {
        return new PublishExchangeFragment();
    }

    @NonNull
    @Override
    protected PublishExchangePresenter createPresenter() {
        return new PublishExchangePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_exchange, container, false);
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
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("发布");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        tvCommunityAddress.setText(UserHelper.communityName);
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        List<Uri> datas = new ArrayList<>();
        datas.add(Uri.parse("android.resource://" + _mActivity.getPackageName() + "/" + R.drawable.ic_image_add_tian_80px));
        adapter = new PublishImageRVAdapter(datas);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (position == adapter.getData().size() - 1) {
                selectPhoto();
            }
            return false;
        });
    }

    private void selectPhoto() {
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(3)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .theme(R.style.Matisse_Dracula)
                .imageEngine(new GlideEngine())
                .forResult(100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ALog.d(TAG, "onActivityResult:" + requestCode + " --- :" + resultCode);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            List<Uri> uris = Matisse.obtainResult(data);
            params.files = new ArrayList<>();
            for (int i = 0; i < uris.size(); i++) {
                ALog.d(TAG, "getImageAbsolutePath:" + ImageUtils.getImageAbsolutePath(_mActivity, uris.get(i)));
                params.files.add(new File(ImageUtils.getImageAbsolutePath(BaseApplication.mContext, uris.get(i))));
            }
            if (params.files.size() > 0) {
                params.type = 1;
            }
            uris.add(Uri.parse("android.resource://" + _mActivity.getPackageName() + "/" + R.drawable.ic_image_add_tian_80px));
            adapter.setNewData(uris);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeyBoardUtils.hideKeybord(getView(), _mActivity);
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }


    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.errorSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseSuccess(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), "提交成功!");
    }

    @OnClick({R.id.ll_location, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_location:
                _mActivity.startActivity(new Intent(_mActivity, PickerActivity.class));
                break;
            case R.id.btn_submit:
                String money = etInputMoney.getText().toString().trim();
                if (TextUtils.isEmpty(money)) {
                    DialogHelper.errorSnackbar(getView(), "请输入金额");
                    return;
                }
                String content = etInputContent.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    DialogHelper.errorSnackbar(getView(), "请输入内容");
                    return;
                }
                submit(money, content);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunityChange event) {
        ALog.e(TAG, "onEvent:::" + event.bean.getName());
        tvCommunityAddress.setText(event.bean.getName());
        params.cmnt_c = event.bean.getCode();
    }

    private void submit(String money, String content) {
        params.cmnt_c = UserHelper.communityCode;
        params.content = content;
        params.price = money;
        mPresenter.post(params);
    }
}
