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
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.BaseBean;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventCommunityChange;
import com.aglhz.yicommunity.picker.PickerActivity;
import com.aglhz.yicommunity.picker.view.CityPickerFragment;
import com.aglhz.yicommunity.publish.contract.PublishContract;
import com.aglhz.yicommunity.publish.presenter.PublishCarpoolPresenter;
import com.bigkoo.pickerview.TimePickerView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: LiuJia on 2017/5/11 0011 21:32.
 * Email: liujia95me@126.com
 */

public class PublishCarpoolFragment extends BaseFragment<PublishCarpoolPresenter> implements PublishContract.View {
    private final String TAG = PublishCarpoolFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_select_start_point)
    TextView tvSelectStartPoint;
    @BindView(R.id.tv_select_end_point)
    TextView tvSelectEndPoint;
    @BindView(R.id.tv_select_go_time)
    TextView tvSelectGoTime;
    @BindView(R.id.et_input_content)
    EditText etInputContent;
    @BindView(R.id.tv_community_address)
    TextView tvCommunityAddress;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;

    private Unbinder unbinder;
    private PublishImageRVAdapter adapter;
    private Params params = Params.getInstance();
    private int REQUEST_START_POINT_CODE = 100;
    private int REQUEST_END_POINT_CODE = 101;
    private boolean requesting;

    public static PublishCarpoolFragment newInstance() {
        return new PublishCarpoolFragment();
    }

    @NonNull
    @Override
    protected PublishCarpoolPresenter createPresenter() {
        return new PublishCarpoolPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_carpool, container, false);
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
        requesting = false;
        DialogHelper.errorSnackbar(getView(), errorMessage);
    }

    @Override
    public void responseSuccess(BaseBean bean) {
        requesting = false;
        DialogHelper.successSnackbar(getView(), "提交成功!");
        pop();
    }

    @OnClick({R.id.btn_submit, R.id.ll_location, R.id.tv_select_start_point, R.id.tv_select_end_point, R.id.tv_select_go_time, R.id.rb_carpool_has_car, R.id.rb_carpool_hasnot_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                String content = etInputContent.getText().toString().trim();
                params.content = content;
                submit();
                break;
            case R.id.ll_location:
                _mActivity.startActivity(new Intent(_mActivity, PickerActivity.class));
                break;
            case R.id.tv_select_start_point:
                startForResult(CityPickerFragment.newInstance(), REQUEST_START_POINT_CODE);
                break;
            case R.id.tv_select_end_point:
                startForResult(CityPickerFragment.newInstance(), REQUEST_END_POINT_CODE);
                break;
            case R.id.tv_select_go_time:
                selectTogoTime();
                break;
            case R.id.rb_carpool_has_car:
                params.carpoolType = 1;
                break;
            case R.id.rb_carpool_hasnot_car:
                params.carpoolType = 2;
                break;
        }
    }

    private void selectTogoTime() {
        TimePickerView pvTime = new TimePickerView.Builder(_mActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                params.outTime = getTime(date);
                tvSelectGoTime.setText(params.outTime);
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_END_POINT_CODE) {
                ALog.d(TAG, "onFragmentResult end code:" + data.getString("city"));
                params.endPlace = data.getString("city");
                tvSelectEndPoint.setText(data.getString("city"));
            } else if (requestCode == REQUEST_START_POINT_CODE) {
                ALog.d(TAG, "onFragmentResult start code:" + data.getString("city"));
                params.startPlace = data.getString("city");
                tvSelectStartPoint.setText(data.getString("city"));
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventCommunityChange event) {
        ALog.e(TAG, "onEvent:::" + event.bean.getName());
        tvCommunityAddress.setText(event.bean.getName());
        params.cmnt_c = event.bean.getCode();
    }

    private void submit() {
        if (requesting) {
            ToastUtils.showToast(_mActivity, "正在提交当中，请勿重复操作");
            return;
        }
        if (TextUtils.isEmpty(params.startPlace)) {
            DialogHelper.errorSnackbar(getView(), "请选择起点城市!");
            return;
        }
        if (TextUtils.isEmpty(params.endPlace)) {
            DialogHelper.errorSnackbar(getView(), "请选择终点城市!");
            return;
        }
        if (TextUtils.isEmpty(params.outTime)) {
            DialogHelper.errorSnackbar(getView(), "请选择出发时间!");
            return;
        }
        if (params.carpoolType == 0) {
            DialogHelper.errorSnackbar(getView(), "请选择拼车类型!!");
            return;
        }
        if (TextUtils.isEmpty(params.content)) {
            DialogHelper.errorSnackbar(getView(), "请输入留言!");
            return;
        }
        params.positionType = 1;
        mPresenter.post(params);
        requesting = true;
    }

}
