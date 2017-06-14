package com.aglhz.yicommunity.main.publish.view;

import android.app.Dialog;
import android.content.Intent;
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
import com.aglhz.abase.utils.KeyBoardUtils;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunity;
import com.aglhz.yicommunity.main.publish.presenter.ComplainPresenter;
import com.aglhz.yicommunity.main.picker.PickerActivity;
import com.aglhz.yicommunity.main.publish.contract.PublishContract;
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
 * Created by Administrator on 2017/4/19 16:09.
 * [我要投诉]的View层
 * 打开方式-->StartApp-->管家-->管理投诉
 */
public class ComplainFragment extends BaseFragment<PublishContract.Presenter> implements PublishContract.View {
    private final String TAG = ComplainFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_community_fragment_complain)
    TextView tvCommunity;
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
    private Dialog loadingDialog;
    BaseMedia addMedia = new BaseMedia() {
        @Override
        public TYPE getType() {
            return TYPE.IMAGE;
        }
    };
    private ArrayList<BaseMedia> selectedMedia;

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
        EventBus.getDefault().register(this);
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
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }

    private void initData() {
        tvCommunity.setText(TextUtils.isEmpty(UserHelper.communityName) ? "请选择小区" : UserHelper.communityName);

        //因为params是单例，所以要将上次选择的清除
        params.files = new ArrayList<>();
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

    private void initListener() {
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            selectPhoto();
        });
    }

    @Override
    public void start(Object response) {

    }

    @Override
    public void error(String errorMessage) {
        dismissLoadingDialog();
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    /**
     * 响应请求投诉成功
     *
     * @param baseBean
     */
    @Override
    public void responseSuccess(BaseBean baseBean) {
        dismissLoadingDialog();
        DialogHelper.successSnackbar(getView(), "提交成功!");
        getView().postDelayed(() -> _mActivity.finish(), 300);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeyBoardUtils.hideKeybord(getView(), _mActivity);
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.tv_community_fragment_complain, R.id.btn_submit_fragment_complain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_community_fragment_complain:
                _mActivity.startActivity(new Intent(_mActivity, PickerActivity.class));
                break;

            case R.id.btn_submit_fragment_complain:
                params.name = etName.getText().toString().trim();
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
                break;
        }
    }

    private void selectPhoto() {
        BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG); // Mode：Mode.SINGLE_IMG, Mode.MULTI_IMG, Mode.VIDEO
        config.needCamera(R.drawable.ic_boxing_camera_white).needGif().withMaxCount(3) // 支持gif，相机，设置最大选图数
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image); // 设置默认图片占位图，默认无
        Boxing.of(config).withIntent(_mActivity, BoxingActivity.class, selectedMedia).start(this, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ALog.d(TAG, "onActivityResult:" + requestCode + " --- :" + resultCode);
        if (resultCode == RESULT_OK && requestCode == 100) {
            ArrayList<BaseMedia> medias = new ArrayList<>(Boxing.getResult(data));
            selectedMedia = Boxing.getResult(data);
            params.files.clear();
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

    private void submit(Params params) {
        params.cmnt_c = UserHelper.communityCode;
        mPresenter.requestSubmit(params);//上传
        showLoadingDialog();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunity event) {
        tvCommunity.setText(UserHelper.communityName);
    }
}
