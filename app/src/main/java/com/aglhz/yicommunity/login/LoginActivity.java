package com.aglhz.yicommunity.login;

import android.Manifest;
import android.os.Bundle;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.abase.utils.ToastUtils;
import com.aglhz.yicommunity.BaseApplication;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.login.view.LoginFragment;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class LoginActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{
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

    @AfterPermissionGranted(SMS)
    private void sms() {
        String[] perms = {Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS};
        if (EasyPermissions.hasPermissions(this, perms)) {
            //有权限就直接进行定位操作
//            ToastUtils.showToast(BaseApplication.mContext, "正在定位……");
//            initLocate();最好还是不需要定位，只记录手动选择的城市和小区。定位只是辅助筛选。

            ALog.e(TAG,"111111");

        } else {
            ALog.e(TAG,"22222");
            EasyPermissions.requestPermissions(this, "应用需要接收短信验证", SMS, perms);
            ToastUtils.showToast(BaseApplication.mContext, "申请权限……");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ALog.e(TAG,"333333");

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
        ALog.e(TAG,"44444");

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
        ALog.e(TAG,"55555555");

    }
}
