package com.carl.permission.module.home.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

import com.carl.permission.R;
import com.carl.permission.module.home.interfaces.MainI;
import com.carl.permission.module.home.presenter.MainPresenter;
import com.carl.permission.pub.base.BaseActivity;
import com.carl.permission.pub.utils.PermissionTipDialog;
import com.carl.permission.pub.utils.TipCustomDialog;
import com.carl.permission.pub.utils.ToastUtils;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class MainActivity extends BaseActivity<MainI, MainPresenter> implements MainI {

    private Button mButton;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void findView() {
        mButton = findViewById(R.id.button);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mToolbarManager.hideBackIcon();
        mToolbarManager.setToolbarTitle("Permission");
    }

    @Override
    public void setOnInteractListener() {
        mButton.setOnClickListener(v -> {
            //检查电话权限，并申请
            checkCallPhonePermission();
//            mPresenter.testLogin();
        });
    }

    private static final int CALL_PHONE_PERMISSION = 1;

    /**
     * 检查电话权限
     */
    private void checkCallPhonePermission() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
        } else {
            showCallPhone();
        }
    }


    /**
     * 响应权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //部分手机上，可能会出现grantResult length为0的情况
        if (grantResults.length == 0) {
            showPermission();
            return;
        }
        switch (requestCode) {
            case CALL_PHONE_PERMISSION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showPermission();
                    return;
                }
                showCallPhone();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }


    PermissionTipDialog mPermissionTipDialog;

    /**
     * 展示权限
     */
    private void showPermission() {
        if (mPermissionTipDialog == null) {
            mPermissionTipDialog = new PermissionTipDialog(mContext);
        }
        mPermissionTipDialog.show();
    }


    TipCustomDialog mTipCustomDialog;

    /**
     * showCallPhone
     */
    private void showCallPhone() {
        if (mTipCustomDialog == null) {
            mTipCustomDialog = new TipCustomDialog(mContext);
            mTipCustomDialog.setNegativeButton("取消", () -> {

            });
            mTipCustomDialog.setPositiveButton("拨打", () -> {
                try {
                    call("10086");
                } catch (Exception e) {
                    showPermission();
                    e.printStackTrace();
                }
            });
        }
        mTipCustomDialog.show();
        mTipCustomDialog.setMesage("是否拨打电话");
    }


    /**
     * 拨打电话
     *
     * @param phoneNumber
     */
    public void call(String phoneNumber) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void loginSuccess(String msg) {
        ToastUtils.showShort(msg);
    }

    private long lastClickTime;

    @Override
    public void onBackPressed() {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - lastClickTime > 2000) {
            ToastUtils.showShort("再按一次退出");
            lastClickTime = currentClickTime;
        } else {
            super.onBackPressed();
        }
    }
}
