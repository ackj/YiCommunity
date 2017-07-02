package com.aglhz.yicommunity.main.mine.view;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.network.http.LogInterceptor;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.DialogHelper;
import com.aglhz.yicommunity.common.DoorManager;
import com.aglhz.yicommunity.common.Params;
import com.aglhz.yicommunity.common.UserHelper;
import com.aglhz.yicommunity.entity.bean.UnreadMessageBean;
import com.aglhz.yicommunity.event.EventData;
import com.aglhz.yicommunity.event.EventUnread;
import com.aglhz.yicommunity.login.LoginActivity;
import com.aglhz.yicommunity.main.MainActivity;
import com.aglhz.yicommunity.main.about.AboutActivity;
import com.aglhz.yicommunity.main.door.DoorActivity;
import com.aglhz.yicommunity.main.message.view.MessageCenterFragment;
import com.aglhz.yicommunity.main.mine.contract.MineContract;
import com.aglhz.yicommunity.main.mine.presenter.MinePresenter;
import com.aglhz.yicommunity.main.mypublish.MyPublishActivity;
import com.aglhz.yicommunity.main.view.MainFragment;
import com.aglhz.yicommunity.web.WebActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.internal.FastBlur;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * Author: LiuJia on 2017/4/21 14:25.
 * Email: liujia95me@126.com
 */
public class MineFragment extends BaseFragment<MineContract.Presenter> implements MineContract.View {
    private static final String TAG = MineFragment.class.getSimpleName();
    @BindView(R.id.iv_head_item_comment)
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
    @BindView(R.id.iv_header_background)
    ImageView ivHeaderBackground;
    @BindView(R.id.view_unread_mark_mine_fragment)
    View viewUnreadMark;
    @BindView(R.id.sv_mine_fragment)
    ScrollView sv;
    private Unbinder unbinder;
    private Params params = Params.getInstance();

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
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        initData();
    }

    private void initData() {
        mPresenter.requestCache();
        mPresenter.requestUnreadMark(params);
        //动态给“个人资料”TextView设置drawableLeft并改变其大小
        Drawable drawableLeft = ContextCompat.getDrawable(_mActivity, R.drawable.ic_oneself_info_80px);
        drawableLeft.setBounds(0, 0, DensityUtils.dp2px(BaseApplication.mContext, 16.f), DensityUtils.dp2px(BaseApplication.mContext, 16.f));
        tvUserData.setCompoundDrawables(drawableLeft, null, null, null);
        updataView();
    }

    @OnClick({R.id.iv_head_item_comment, R.id.tv_user_data,
            R.id.ll_message_center, R.id.ll_my_indent,
            R.id.ll_my_address, R.id.ll_make_shortcut,
            R.id.ll_my_publish, R.id.ll_clean_cache,
            R.id.ll_about_us, R.id.tv_logout, R.id.ll_my_house})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head_item_comment:
                if (isLogined()) {
                    //后面再改成iOS那种
                    ALog.e("111111111");
                }
                break;
            case R.id.ll_my_house:
                if (isLogined()) {
                    ((MainFragment) getParentFragment()).start(MyHousesFragment.newInstance(), SupportFragment.STANDARD);
                }
                break;
            case R.id.tv_user_data:
                if (isLogined()) {
                    ((MainFragment) getParentFragment()).start(UserDataFragment.newInstance(), SupportFragment.STANDARD);
                }
                break;
            case R.id.ll_message_center:
                if (isLogined()) {
//                    _mActivity.startActivity(new Intent(_mActivity, MessageActivity.class));
                    _mActivity.start(MessageCenterFragment.newInstance());
                }
                break;
            case R.id.ll_my_indent:
                if (isLogined()) {
                    String url = ApiService.INDENT_CENTER + UserHelper.token;
                    go2Web("我的订单", url);
                }
                break;
            case R.id.ll_my_address:
                if (isLogined()) {
                    String url = ApiService.MY_ADDRESS + UserHelper.token;
                    go2Web("我的地址", url);
                }
                break;
            case R.id.ll_make_shortcut:
                if (TextUtils.isEmpty(UserHelper.dir)) {
                    new AlertDialog.Builder(_mActivity)
                            .setTitle("提醒")
                            .setMessage("检测到您尚未设置一键开门，如需设置，请点击确定。")
                            .setPositiveButton("确定", (dialog, which) -> go2SmartDoor(Constants.SET_OPEN_DOOR))
                            .setNegativeButton("取消", null)
                            .show();
                } else {
                    createShortCut();
                }
                break;
            case R.id.ll_my_publish:
                startActivity(new Intent(_mActivity, MyPublishActivity.class));
                break;
            case R.id.ll_clean_cache:
                mPresenter.requestClearCache();
                break;
            case R.id.ll_about_us:
                startActivity(new Intent(_mActivity, AboutActivity.class));
                break;
            case R.id.tv_logout:
                mPresenter.requestLogout(params);//请求服务器注销。
                onLoginoutEvent(null);
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
        DialogHelper.warningSnackbar(getView(), errorMessage);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void createShortCut() {
        Intent shortcutIntent = new Intent();
        //设置点击快捷方式时启动的Activity,因为是从Lanucher中启动，所以包名类名要写全。
        shortcutIntent.setComponent(new ComponentName("com.aglhz.yicommunity", "com.aglhz.yicommunity.main.mine.QuickOpenActivity"));
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
    public void onLoginEvent(EventData event) {
        ALog.e("11111onLoginEvent");
        if (event.code == Constants.login) {
            if (_mActivity instanceof MainActivity) {
                ALog.e("11111setCallListener");
                ((MainActivity) _mActivity).setCallListener();
            }
            updataView();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnreadEvent(EventUnread event) {
        ALog.e("111111111onUnreadEventonUnreadEventonUnreadEvent");
        mPresenter.requestUnreadMark(params);
    }

    private void updataView() {
        if (UserHelper.isLogined()) {
            Glide.with(this)
                    .load(UserHelper.userInfo.getFace())
                    .bitmapTransform(new CropCircleTransformation(_mActivity))
                    .into(ivHead);

            updateHeaderBackground();

            tvName.setText(UserHelper.userInfo.getNickName());
            tvPhoneNumber.setText(UserHelper.userInfo.getMobile());
            tvLogout.setVisibility(View.VISIBLE);
        }
    }

    private void updateHeaderBackground() {
        Glide.with(this)
                .load(UserHelper.userInfo.getFace())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(DensityUtils.dp2px(_mActivity, 80), DensityUtils.dp2px(_mActivity, 80)) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        Bitmap blurBitmap = FastBlur.blur(bitmap, 8, true);
                        ivHeaderBackground.setImageBitmap(blurBitmap);
                    }
                });
    }

    @Override
    public void responseLogout(String message) {
        DialogHelper.successSnackbar(getView(), message);
    }

    @Override
    public void responseCache(String str) {
        tvCacheSum.setText(str);
    }

    @Override
    public void responseUnreadMark(UnreadMessageBean bean) {
        if (bean != null) {
            viewUnreadMark.setVisibility(bean.getData() > 0 ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginoutEvent(LogInterceptor event) {
        tvName.setText("访客");
        tvPhoneNumber.setText("");
        ivHeaderBackground.setImageResource(R.drawable.bg_mine_1920px_1080px);
        ivHead.setImageResource(R.drawable.ic_mine_avatar_normal_320px);
        tvLogout.setVisibility(View.GONE);
        sv.post(() -> sv.fullScroll(ScrollView.FOCUS_UP));//滑动到顶部，提高用户体验，方便用户点击头像登录。
        DoorManager.getInstance().exit();// 停止SipService，用户明确的退出。
        PushAgent.getInstance(BaseApplication.mContext)
                .removeAlias(UserHelper.account, "userType", new UTrack.ICallBack() {
                    @Override
                    public void onMessage(boolean b, String s) {
                        ALog.e("b-->" + b);
                        ALog.e("s-->" + s);
                    }
                });
        UserHelper.clear();//要放在最后清除，不然上面用到UserHelper.account也为空了
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void go2Web(String title, String link) {
        Intent intent = new Intent(getContext(), WebActivity.class);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_LINK, link);
        startActivity(intent);
    }

    //跳转到智能门禁模块。
    private void go2SmartDoor(int position) {
        Intent intent = new Intent(_mActivity, DoorActivity.class);
        intent.putExtra(Constants.KEY_FROM_TO, position);
        startActivity(intent);
    }
}
