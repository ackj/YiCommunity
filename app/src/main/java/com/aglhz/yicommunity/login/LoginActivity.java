package com.aglhz.yicommunity.login;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.login.view.LoginFragment;


public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int SMS = 122;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, LoginFragment.newInstance());
        }
    }

//    @AfterPermissionGranted(LOCATION)
//    private void location() {
//        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
//        if (EasyPermissions.hasPermissions(_mActivity, perms)) {
//            //有权限就直接进行定位操作
////            ToastUtils.showToast(BaseApplication.mContext, "正在定位……");
////            initLocate();最好还是不需要定位，只记录手动选择的城市和小区。定位只是辅助筛选。
//
//        } else {
//            EasyPermissions.requestPermissions(this, "亿社区需要定位权限", LOCATION, perms);
//            ToastUtils.showToast(BaseApplication.mContext, "申请权限……");
//        }
//    }
}
