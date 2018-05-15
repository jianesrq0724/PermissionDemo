package com.carl.permission.module.home.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    private Button mButton2;

    private static final int CALL_PHONE_PERMISSION = 1;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void findView() {
        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
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
//            checkCallPhonePermission();
//            mPresenter.testLogin();
            permissions("电话权限", new String[]{Manifest.permission.CALL_PHONE}, new PermissionsResultListener() {
                @Override
                public void onPermissionGranted() {
                    showCallPhone();
                }

                @Override
                public void onPermissionDenied() {
                    showPermission();
                }
            });
        });

        mButton2.setOnClickListener(view -> {
            permissions("短信", new String[] {Manifest.permission.SEND_SMS}, new PermissionsResultListener(){

                @Override
                public void onPermissionGranted() {
                    ToastUtils.showShort("短信 授予");
                }

                @Override
                public void onPermissionDenied() {
                    ToastUtils.showShort("短信拒绝");
                }
            });
        });
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
