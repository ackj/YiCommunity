package com.aglhz.yicommunity.publish.view;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.aglhz.yicommunity.picker.PickerActivity;
import com.aglhz.yicommunity.publish.contract.RepairContract;
import com.aglhz.yicommunity.publish.presenter.RepairPresenter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.aglhz.yicommunity.publish.view.ComplainFragment.REQUEST_CODE_CHOOSE;

/**
 * Created by Administrator on 2017/4/19 16:06.
 */
@SuppressLint("ValidFragment")
public class RepairFragment extends BaseFragment<RepairContract.Presenter> implements RepairContract.View {
    private final String TAG = RepairFragment.class.getSimpleName();
    @BindView(R.id.rl_house_name_fragment_repair)
    RelativeLayout rlHouseName;
    @BindView(R.id.btn_submit_fragment_repair)
    Button btnSubmitFragmentRepair;

    private boolean isPrivate;//是否是私人报修

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_location_fragment_repair)
    TextView tvLocation;
    @BindView(R.id.et_name_fragment_repair)
    EditText etName;
    @BindView(R.id.et_phone_fragment_repair)
    EditText etPhone;
    @BindView(R.id.et_content_fragment_repair)
    EditText etContent;
    @BindView(R.id.recyclerView_fragment_repair)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private PublishImageRVAdapter adapter;
    Params params = Params.getInstance();

    private RepairFragment(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public static RepairFragment newInstance(boolean isPrivate) {
        ALog.e("RepairFragment");
        return new RepairFragment(isPrivate);
    }

    @NonNull
    @Override
    protected RepairContract.Presenter createPresenter() {
        return new RepairPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_repair, container, false);
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

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (position == adapter.getData().size() - 1) {
                selectPhoto();
            }
            return false;
        });
    }

    private void initData() {
        if (isPrivate) {
            rlHouseName.setVisibility(View.VISIBLE);
        } else {
            rlHouseName.setVisibility(View.GONE);
        }

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

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("我要报修");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeyBoardUtils.hideKeybord(getView(), _mActivity);
        unbinder.unbind();
    }

    @OnClick({R.id.tv_location_fragment_repair, R.id.btn_submit_fragment_repair})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_location_fragment_repair:
                _mActivity.startActivity(new Intent(_mActivity, PickerActivity.class));
                break;
            case R.id.btn_submit_fragment_repair:
                break;
        }
    }

    private void selectPhoto() {
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(100)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .theme(R.style.Matisse_Dracula)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @OnClick(R.id.btn_submit_fragment_repair)
    public void onViewClicked() {
        params.name = etName.getText().toString().trim();
        params.des = etContent.getText().toString().trim();
        params.contact = etPhone.getText().toString().trim();
        params.type = 1;
        params.single = isPrivate;
        if (params.name.isEmpty()) {
            DialogHelper.errorSnackbar(getView(), "请输入联系人");
            return;
        }

        if (params.contact.isEmpty()) {
            DialogHelper.errorSnackbar(getView(), "请输入您的联系方式");
            return;
        }

        if (params.des.isEmpty()) {
            DialogHelper.errorSnackbar(getView(), "请输入详情");
            return;
        }
        submit(params);
    }

    private void submit(Params params) {
        mPresenter.postRepair(params);
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
            uris.add(Uri.parse("android.resource://" + _mActivity.getPackageName() + "/" + R.drawable.ic_image_add_tian_80px));
            adapter.setNewData(uris);
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
    public void responseRepair(BaseBean bean) {
        DialogHelper.successSnackbar(getView(), "提交成功!");
        setFragmentResult(RESULT_OK, null);
        pop();
    }
}
