package com.aglhz.yicommunity.publish.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.bean.IconBean;
import com.aglhz.yicommunity.bean.RepairTypesBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.picker.PickerActivity;
import com.aglhz.yicommunity.publish.contract.PublishContract;
import com.aglhz.yicommunity.publish.presenter.RepairPresenter;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;

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
 * Created by Administrator on 2017/4/19 16:06.
 */
@SuppressLint("ValidFragment")
public class RepairFragment extends BaseFragment<PublishContract.Presenter> implements PublishContract.View {
    private final String TAG = RepairFragment.class.getSimpleName();
    @BindView(R.id.rl_house_name)
    RelativeLayout rlHouseName;
    @BindView(R.id.bt_submit_fragment_repair)
    Button btSubmit;
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
    @BindView(R.id.tv_house_name)
    TextView tvHouseName;
    @BindView(R.id.tv_repair_type)
    TextView tvRepairType;

    private Unbinder unbinder;
    private PublishImageRVAdapter adapter;
    private boolean isPrivate;//是否是私人报修
    private Params params = Params.getInstance();
    private Dialog loadingDialog;
    BaseMedia addMedia = new BaseMedia() {
        @Override
        public TYPE getType() {
            return TYPE.IMAGE;
        }
    };

    private RepairFragment(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public static RepairFragment newInstance(boolean isPrivate) {
        ALog.e("RepairFragment");
        return new RepairFragment(isPrivate);
    }

    @NonNull
    @Override
    protected PublishContract.Presenter createPresenter() {
        return new RepairPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_repair, container, false);
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

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (position == adapter.getData().size() - 1) {
                selectPhoto();
            }
        });
    }

    private void initData() {
        //因为params是单例，所以要将上次选择的清除
        params.files = new ArrayList<>();
        if (isPrivate) {
            rlHouseName.setVisibility(View.VISIBLE);
            params.cmnt_c = UserHelper.communityCode;
        } else {
            rlHouseName.setVisibility(View.GONE);
        }

        tvLocation.setText(TextUtils.isEmpty(UserHelper.communityName) ? "请选择小区" : UserHelper.communityName);
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        List<BaseMedia> datas = new ArrayList<>();
        addMedia.setPath("android.resource://" + _mActivity.getPackageName() + "/" + R.drawable.ic_image_add_tian_80px);
        datas.add(addMedia);
        adapter = new PublishImageRVAdapter(datas);
        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("我要报修");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeyBoardUtils.hideKeybord(getView(), _mActivity);
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    private void selectPhoto() {
        BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG); // Mode：Mode.SINGLE_IMG, Mode.MULTI_IMG, Mode.VIDEO
        config.needCamera(R.drawable.ic_boxing_camera_white).needGif().withMaxCount(3) // 支持gif，相机，设置最大选图数
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image); // 设置默认图片占位图，默认无
        Boxing.of(config).withIntent(_mActivity, BoxingActivity.class).start(this, 100);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        params.cmnt_c = event.bean.getCode();
        tvLocation.setText(event.bean.getName());
    }

    @OnClick({R.id.bt_submit_fragment_repair, R.id.tv_location_fragment_repair, R.id.rl_house_name, R.id.tl_repair_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_house_name:
                if (isPrivate) {
                    showLoadingDialog();
                    ((RepairPresenter) mPresenter).requestMyhouse(params);
                }
                break;
            case R.id.tl_repair_type:
                showLoadingDialog();
                if (isPrivate) {
                    params.type = 1;
                } else {
                    params.type = 2;
                }
                ((RepairPresenter) mPresenter).requestRepairTypes(params);

                break;
            case R.id.tv_location_fragment_repair:
                _mActivity.startActivity(new Intent(_mActivity, PickerActivity.class));
                break;
            case R.id.bt_submit_fragment_repair:
                params.name = etName.getText().toString().trim();
                params.des = etContent.getText().toString().trim();
                params.contact = etPhone.getText().toString().trim();
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
                break;
        }
    }

    private void submit(Params params) {
        showLoadingDialog();
        params.cmnt_c = UserHelper.communityCode;
        mPresenter.post(params);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ALog.d(TAG, "onActivityResult:" + requestCode + " --- :" + resultCode);
        if (resultCode == RESULT_OK && requestCode == 100) {
            ArrayList<BaseMedia> medias = Boxing.getResult(data);
            for (int i = 0; i < medias.size(); i++) {
                params.files.add(new File(medias.get(i).getPath()));
            }
            if (params.files.size() > 0) {
                params.type = 1;
            }
            medias.add(addMedia);
            adapter.setNewData(medias);
        }
    }

    @Override
    public void start(Object response) {
    }

    @Override
    public void error(String errorMessage) {
        dismissLoadingDialog();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    public void responseMyHouse(List<IconBean> iconBeans) {
        dismissLoadingDialog();
        String[] houseTitles = new String[iconBeans.size()];
        for (int i = 0; i < iconBeans.size(); i++) {
            houseTitles[i] = iconBeans.get(i).title;
        }

        new AlertDialog.Builder(_mActivity)
                .setTitle("请选择")
                .setItems(houseTitles, (dialog, which) -> {
                    //网络访问
                    tvHouseName.setText(iconBeans.get(which).title);
                    params.ofid = iconBeans.get(which).fid;
                })
                .setPositiveButton("取消", null)
                .show();
    }

    public void responseRepairTypes(List<RepairTypesBean.DataBean.TypesBean> datas) {
        dismissLoadingDialog();
        String[] arr = new String[datas.size()];
        for (int i = 0; i < datas.size(); i++) {
            arr[i] = datas.get(i).getName();
        }
        new AlertDialog.Builder(_mActivity).setItems(arr, (dialog, which) -> {
            //网络访问
            dialog.dismiss();
            ALog.e("AlertDialog which:::" + which);
            tvRepairType.setText(arr[which]);
            params.repairType = datas.get(which).getCode();
        }).setTitle("请选择").setPositiveButton("取消", null).show();
    }

    @Override
    public void responseSuccess(BaseBean bean) {
        dismissLoadingDialog();
        DialogHelper.successSnackbar(getView(), "提交成功!");
        setFragmentResult(RESULT_OK, null);
        pop();
    }
}
