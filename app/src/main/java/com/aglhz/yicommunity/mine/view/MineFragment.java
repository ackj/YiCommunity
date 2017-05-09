package com.aglhz.yicommunity.mine.view;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.about.AboutActivity;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.event.EventData;
import com.aglhz.yicommunity.login.LoginActivity;
import com.aglhz.yicommunity.main.view.MainFragment;
import com.aglhz.yicommunity.mine.contract.MineContract;
import com.aglhz.yicommunity.mine.presenter.MinePresenter;
import com.aglhz.yicommunity.mypublish.MyPublishActivity;
import com.aglhz.yicommunity.web.WebActivity;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * Author: LiuJia on 2017/4/21 14:25.
 * Email: liujia95me@126.com
 */
public class MineFragment extends BaseFragment<MineContract.Presenter> implements MineContract.View {
    private static final String TAG = MineFragment.class.getSimpleName();
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_user_data)
    TextView tvUserData;
    @BindView(R.id.ll_message_center)
    LinearLayout llMessageCenter;
    @BindView(R.id.ll_my_indent)
    LinearLayout llMyIndent;
    @BindView(R.id.ll_my_address)
    LinearLayout llMyAddress;
    @BindView(R.id.ll_make_shortcut)
    LinearLayout llMakeShortcut;
    @BindView(R.id.ll_my_publish)
    LinearLayout llMyPublish;
    @BindView(R.id.ll_clean_cache)
    LinearLayout llCleanCache;
    @BindView(R.id.ll_about_us)
    LinearLayout llAboutUs;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.tv_cache_sum)
    TextView tvCacheSum;
    private ViewGroup rootView;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @NonNull
    @Override
    protected MineContract.Presenter createPresenter() {
        return new MinePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = (ViewGroup) _mActivity.findViewById(android.R.id.content).getRootView();
        EventBus.getDefault().register(this);
        initData();
    }

    private void initData() {
        mPresenter.requestCache();
        //动态给“个人资料”TextView设置drawableLeft并改变其大小
        Drawable drawableLeft = ContextCompat.getDrawable(_mActivity, R.drawable.ic_oneself_info_80px);
        drawableLeft.setBounds(0, 0, DensityUtils.dp2px(BaseApplication.mContext, 16.f), DensityUtils.dp2px(BaseApplication.mContext, 16.f));
        tvUserData.setCompoundDrawables(drawableLeft, null, null, null);
        updataView();
    }

    @OnClick({R.id.iv_head, R.id.tv_user_data, R.id.ll_message_center,
            R.id.ll_my_indent, R.id.ll_my_address, R.id.ll_make_shortcut,
            R.id.ll_my_publish, R.id.ll_clean_cache, R.id.ll_about_us, R.id.tv_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                if (isLogined()) {
                    //后面再改成iOS那种
                    ALog.e("111111111");
                }
                break;
            case R.id.tv_user_data:
                if (isLogined()) {
                    ((MainFragment) getParentFragment()).start(UserDataFragment.newInstance(), SupportFragment.STANDARD);
                }
                break;
            case R.id.ll_message_center:
                if (isLogined()) {
                    ((MainFragment) getParentFragment()).start(MessageCenterFragment.newInstance(), SupportFragment.STANDARD);
                }

                break;
            case R.id.ll_my_indent:
                if (isLogined()) {
                    String url = ApiService.INDENT_CENTER + UserHelper.token;
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra("title", "订单中心");
                    intent.putExtra("link", url);
                    startActivity(intent);
                }

                break;
            case R.id.ll_my_address:
                break;
            case R.id.ll_make_shortcut:
                createShortCut();
                break;
            case R.id.ll_my_publish:
//                startActivity(new Intent(_mActivity, MyPublishActivity.class));
                break;
            case R.id.ll_clean_cache:
                mPresenter.requestClearCache();
                break;
            case R.id.ll_about_us:
                startActivity(new Intent(_mActivity, AboutActivity.class));
                break;
            case R.id.tv_logout:
                mPresenter.requestLogout(Params.getInstance());
                break;
        }
    }

    private boolean isLogined() {
        if (UserHelper.isLogined()) {
            return true;
        } else {
            startActivity(new Intent(_mActivity, LoginActivity.class));
            _mActivity.overridePendingTransition(0, 0);
            return false;
        }
    }


    @Override
    public void start(Object response) {
        if (response instanceof String) {
            tvCacheSum.setText((String) response);
        }
    }

    @Override
    public void error(String errorMessage) {
        DialogHelper.warningSnackbar(rootView, errorMessage);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void createShortCut() {
        Intent shortcutIntent = new Intent();
        //设置点击快捷方式时启动的Activity,因为是从Lanucher中启动，所以包名类名要写全。
        shortcutIntent.setComponent(new ComponentName("com.aglhz.yicommunity"
                , "com.aglhz.yicommunity.mine.QuickOpenActivity"));
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent resultIntent = new Intent();
        //设置快捷方式图标
        resultIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(_mActivity, R.mipmap.ic_shortcut_red_144px));
        // 不允许重建
        resultIntent.putExtra("duplicate", true);
        //启动的Intent
        resultIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        //设置快捷方式的名称
        resultIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "芝麻开门");
        resultIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        _mActivity.sendBroadcast(resultIntent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventData event) {
        if (event.position == Constants.login_success) {
            updataView();
        }
    }

    private void updataView() {
        if (UserHelper.isLogined()) {
            Glide.with(this)
                    .load(UserHelper.userInfo.getFace())
                    .bitmapTransform(new CropCircleTransformation(_mActivity))
                    .into(ivHead);

            tvName.setText(UserHelper.userInfo.getNickName());
            tvPhoneNumber.setText(UserHelper.userInfo.getMobile());
            tvLogout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void responseLogout() {
        //清理信息
        UserHelper.clear();
        DialogHelper.successSnackbar(rootView, "退出登录！");
        ivHead.setImageResource(R.drawable.ic_mine_avatar_normal_320px);
        tvName.setText("访客");
        tvPhoneNumber.setText("");
        tvLogout.setVisibility(View.GONE);
    }

    @Override
    public void responseCache(String str) {
        tvCacheSum.setText(str);
    }
}
