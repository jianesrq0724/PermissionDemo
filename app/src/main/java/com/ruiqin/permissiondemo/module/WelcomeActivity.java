package com.ruiqin.permissiondemo.module;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.util.AppUtils;
import com.ruiqin.permissiondemo.R;
import com.ruiqin.permissiondemo.base.BaseActivity;
import com.ruiqin.permissiondemo.until.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiqin.shen on 2017/6/18.
 * 类说明：欢迎页面
 */
public class WelcomeActivity extends BaseActivity {

    String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    List<String> mPermissionList = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        judgePermission();
    }

    /**
     * 判断权限申请
     */
    private void judgePermission() {
        mPermissionList.clear();
        /**
         * 判断哪些权限未授予
         */
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(mContext, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }

        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            startActivity(MainActivity.newIntent(mContext));
            finish();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(WelcomeActivity.this, permissions, 1);
        }
    }


    boolean mIsBannedPermission = false;//用户是否禁止权限

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        //判断是否未禁止权限
                        boolean bannedPermission = ActivityCompat.shouldShowRequestPermissionRationale(WelcomeActivity.this, permissions[i]);
                        if (bannedPermission) {
                            judgePermission();//重新申请权限
                            return;
                        } else {
                            mIsBannedPermission = true;//已经禁止
                            break;
                        }
                    }
                }

                /**
                 * 循环结束
                 */
                if (mIsBannedPermission) {//禁止，跳转对话框
                    showPermissionDialog();
                } else {
                    judgePermission();
                }
                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    AlertDialog mPermissionDialog;

    /**
     * 展示对话框
     */
    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(mContext)
                    .setTitle("权限申请")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                            Uri packageURI = Uri.parse("package:" + AppUtils.getAppPackageName());
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent);
                            ToastUtils.show("设置");
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                            ToastUtils.show("取消");
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }

    /**
     * 取消权限对话框
     *
     * @return
     */
    private void cancelPermissionDialog() {
        if (mPermissionDialog != null) {
            mPermissionDialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelPermissionDialog();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }
}
