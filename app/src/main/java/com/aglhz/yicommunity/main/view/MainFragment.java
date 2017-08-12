package com.aglhz.yicommunity.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.common.AudioPlayer;
import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.AppUtils;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.App;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.ApiService;
import com.aglhz.yicommunity.common.Constants;
import com.aglhz.yicommunity.common.appupdate.UpdateAppHttpUtils;
import com.aglhz.yicommunity.entity.bean.AppUpdateBean;
import com.aglhz.yicommunity.main.guide.GuideHelper;
import com.aglhz.yicommunity.main.home.view.HomeFragment;
import com.aglhz.yicommunity.main.mine.view.MineFragment;
import com.aglhz.yicommunity.main.sociality.view.NeighbourFragment;
import com.aglhz.yicommunity.main.sociality.view.SocialityListFragment;
import com.aglhz.yicommunity.main.steward.view.StewardFragment;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.gson.Gson;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class MainFragment extends BaseFragment {
    private static final String TAG = MainFragment.class.getSimpleName();
    private static final long WAIT_TIME = 2000L;// 再点一次退出程序时间设置
    private long TOUCH_TIME = 0;
    @BindView(R.id.ahbn_main_fragment)
    AHBottomNavigation ahbn;
    private ArrayList<AHBottomNavigationItem> bottomItems = new ArrayList<>();
    private int prePosition = 0;
    private SupportFragment[] mFragments = new SupportFragment[4];
    private Unbinder unbinder;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = StewardFragment.newInstance();
            mFragments[2] = NeighbourFragment.newInstance(SocialityListFragment.TYPE_NEIGHBOUR);
            mFragments[3] = MineFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_container_main_fragment, 0, mFragments[0], mFragments[1], mFragments[2], mFragments[3]);
        } else {
            mFragments[0] = findChildFragment(HomeFragment.class);
            mFragments[1] = findChildFragment(StewardFragment.class);
            mFragments[2] = findChildFragment(NeighbourFragment.class);
            mFragments[3] = findChildFragment(MineFragment.class);
            prePosition = savedInstanceState.getInt("prePosition");
        }
        initData();
    }

    private void initData() {
        //检测App的更新。
        updateApp();

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.community, R.drawable.ic_home_black_78px, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.steward, R.drawable.ic_guanjia_black_79px, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.neighbour, R.drawable.ic_neighbour_black_78px, R.color.white);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.mine, R.drawable.ic_mine_black_78px, R.color.white);
        bottomItems.add(item1);
        bottomItems.add(item2);
        bottomItems.add(item3);
        bottomItems.add(item4);
        ahbn.addItems(bottomItems);
        ahbn.setDefaultBackgroundColor(getResources().getColor(R.color.white));
        ahbn.setBehaviorTranslationEnabled(false);
        ahbn.setColored(true);
        ahbn.setForceTint(false);
        ahbn.setAccentColor(getResources().getColor(R.color.base_color));
        ahbn.setInactiveColor(getResources().getColor(R.color.black));
        ahbn.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        ahbn.setOnTabSelectedListener((position, wasSelected) -> {
            showHideFragment(mFragments[position], mFragments[prePosition]);
            prePosition = position;
            AudioPlayer.getInstance(_mActivity).play(2);

            if (wasSelected) {
                switch (position) {
                    case 0:
                        ((HomeFragment) mFragments[0]).go2TopAndRefresh();
                        break;
                    case 1:
                        ((StewardFragment) mFragments[1]).go2TopAndRefresh(null);
                        break;
                    case 2:
                        ((NeighbourFragment) mFragments[2]).go2TopAndRefresh();
                        break;
                    case 3:
                        break;
                }
            }
            return true;
        });
        ahbn.setCurrentItem(prePosition);
        GuideHelper.showHomeGuide(_mActivity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("prePosition", prePosition);
    }

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            //退到桌面，而不是退出应用，让用户以为退出应用，尽量保活。
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            ToastUtils.showToast(App.mContext, Constants.PRESS_AGAIN);
        }
        return true;
    }

    /**
     * 检测是否有新版本需要下载更新。
     */
    private void updateApp() {
        ALog.e("requestAppUpdatae-->" + ApiService.requestAppUpdatae);
        Map<String, String> params = new HashMap<String, String>();
        params.put("appType", "1");

        new UpdateAppManager
                .Builder()
                .setActivity(_mActivity)
                .setHttpManager(new UpdateAppHttpUtils())
                .setPost(true)
                .setParams(params)
                .setUpdateUrl(ApiService.requestAppUpdatae)
                .hideDialogOnDownloading(false)
                .build()
                .checkNewApp(new UpdateCallback() {
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        ALog.e("requestAppUpdatae-->" + json);
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        AppUpdateBean mAppUpdateBean = new Gson().fromJson(json, AppUpdateBean.class);

                        if (AppUtils.getVersionCode(_mActivity) < mAppUpdateBean.getData().getVersionCode()) {
                            updateAppBean.setUpdate("Yes");
                        } else {
                            updateAppBean.setUpdate("No");
                        }

                        updateAppBean
                                //（必须）是否更新Yes,No
                                .setUpdate(AppUtils.getVersionCode(_mActivity) < mAppUpdateBean.getData().getVersionCode() ? "Yes" : "No")
                                //（必须）新版本号，
                                .setNewVersion(mAppUpdateBean.getData().getVersionName())
                                //（必须）下载地址
                                .setApkFileUrl(mAppUpdateBean.getData().getUrl())
                                //（必须）更新内容
                                .setUpdateLog(mAppUpdateBean.getData().getDescription())
                                //大小，不设置不显示大小，可以不设置
                                .setTargetSize(mAppUpdateBean.getData().getSize())
                                //是否强制更新，可以不设置
                                .setConstraint(false);

                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
