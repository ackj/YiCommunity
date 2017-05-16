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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.ImageUtils;
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.publish.contract.PublishContract;
import com.aglhz.yicommunity.publish.presenter.ComplainPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/19 16:09.
 */
public class ComplainFragment extends BaseFragment<PublishContract.Presenter> implements PublishContract.View {
    private final String TAG = ComplainFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_name_fragment_complain)
    EditText etName;
    @BindView(R.id.et_phone_fragment_complain)
    EditText etPhone;
    @BindView(R.id.et_content_fragment_complain)
    EditText etContent;
    @BindView(R.id.recyclerView_fragment_complain)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private PublishImageRVAdapter adapter;
    Params params = Params.getInstance();
    private boolean requesting;

    public static ComplainFragment newInstance() {
        return new ComplainFragment();
    }

    @NonNull
    @Override
    protected PublishContract.Presenter createPresenter() {
        return new ComplainPresenter(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complain, container, false);
        unbinder = ButterKnife.bind(this, view);
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
        toolbarTitle.setText("我要投诉");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
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
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == adapter.getData().size() - 1) {
                    selectPhoto();
                }
                return false;
            }
        });
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        requesting = false;
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseSuccess(BaseBean baseBean) {
        requesting = false;
        DialogHelper.successSnackbar(getView(), "提交成功!");
        pop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeyBoardUtils.hideKeybord(getView(), _mActivity);
        unbinder.unbind();
    }

    @OnClick(R.id.btn_submit_fragment_complain)
    public void onViewClicked() {
        try {
            params.name = new String(etName.getText().toString().trim().getBytes("GBK"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.phoneNo = etPhone.getText().toString().trim();
        params.content = etContent.getText().toString().trim();
        if (params.name.isEmpty()) {
            DialogHelper.errorSnackbar(getView(), "请输入联系人");
            return;
        }

        if (params.phoneNo.isEmpty()) {
            DialogHelper.errorSnackbar(getView(), "请输入您的联系方式");
            return;
        }

        if (params.content.isEmpty()) {
            DialogHelper.errorSnackbar(getView(), "请输入详情");
            return;
        }
        submit(params);
    }

    public static final int REQUEST_CODE_CHOOSE = 100;

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
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ALog.d(TAG, "onActivityResult:" + requestCode + " --- :" + resultCode);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
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

    private void submit(Params params) {
        if (requesting) {
            ToastUtils.showToast(_mActivity, "正在提交当中，请勿重复操作");
            return;
        }
        params.cmnt_c = UserHelper.communityCode;
        mPresenter.post(params);//上传
        requesting = true;
    }
}
