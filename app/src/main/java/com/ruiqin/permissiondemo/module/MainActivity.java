package com.ruiqin.permissiondemo.module;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.util.AppUtils;
import com.ruiqin.permissiondemo.R;
import com.ruiqin.permissiondemo.base.BaseActivity;
import com.ruiqin.permissiondemo.until.ToastUtils;

import butterknife.OnClick;

/**
 * Created by ruiqin.shen on 2017/6/18.
 * 类说明：
 */
public class MainActivity extends BaseActivity {

    /**
     * 获取自身的实列
     *
     * @param context
     * @return
     */
    protected static Intent newIntent(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        return intent;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    /**
     * 拨打电话
     */
    @OnClick(R.id.call_phone)
    public void onClick() {
        showCallPhoneDialog();//展示拨打电话对话框
    }

    AlertDialog mPermissionDialog;

    /**
     * 不再提示权限 时的展示对话框
     */
    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(mContext)
                    .setMessage("已禁用权限，请手动授予")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                            Uri packageURI = Uri.parse("package:" + AppUtils.getAppPackageName());
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
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

    AlertDialog mAlertDialog;

    /**
     * 拨打电话对话框
     */
    private void showCallPhoneDialog() {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(mContext)
                    .setMessage("拨打电话")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtils.show("取消");
                            cancelCallPhoneDialog();
                        }
                    })
                    .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startCallPhone();
                        }
                    })
                    .create();
        }
        mAlertDialog.show();
    }

    private static final int CALL_PHONE_REQUESTCODE = 1;//权限申请响应码

    /**
     * 拨打电话
     */
    private void startCallPhone() {
        /**
         * 判断权限
         */
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_REQUESTCODE);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel://10086"));
            startActivity(intent);
        }
    }


    boolean mShowRequestPermission = true;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CALL_PHONE_REQUESTCODE://电话权限
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {//未授权
                        boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[i]);//是否显示权限弹窗
                        if (!showRequestPermission) {//禁用请求权限弹窗(用户勾选进入后不再询问)
                            mShowRequestPermission = false;
                            break;
                        }
                    }
                }
                if (!mShowRequestPermission) {
                    showPermissionDialog();
                }
                break;
            default:
                break;
        }
    }


    private void cancelCallPhoneDialog() {
        if (mAlertDialog != null) {
            mAlertDialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelPermissionDialog();
    }
}
