package com.ruiqin.permission.module.home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.PhoneUtils;
import com.ruiqin.permission.R;
import com.ruiqin.permission.base.BaseActivity;
import com.ruiqin.permission.commonality.view.PermissionTipDialog;
import com.ruiqin.permission.commonality.view.TipCustomDialog;
import com.ruiqin.permission.module.home.adapter.MainRecyclerAdapter;
import com.ruiqin.permission.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    /**
     * 启动自身的实列
     *
     * @param context
     */
    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        return intent;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mPresenter.setAdapter();
    }

    @Override
    public boolean canBack() {
        mToolbarTitle.setText("BaseProject");
        return false;
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

    @Override
    public void setRecyclerAdapterSuccess(MainRecyclerAdapter mainRecyclerAdapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        mRecyclerView.setAdapter(mainRecyclerAdapter);
    }

    @OnClick(R.id.btn_call_phone)
    public void onViewClicked() {
        checkCallPhonePermission();//检查权限
    }

    private static final int CALL_PHONE_PERMISSION = 1;

    /**
     * 检查权限
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
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults == null || grantResults.length == 0) {//部分手机上，可能会出现grantResult length为0的情况
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
            mTipCustomDialog.setNegativeButton("取消", new TipCustomDialog.OnClickListener() {
                @Override
                public void onClick() {

                }
            });
            mTipCustomDialog.setPositiveButton("拨打", new TipCustomDialog.OnClickListener() {
                @Override
                public void onClick() {
                    PhoneUtils.call("10086");
                }
            });
        }
        mTipCustomDialog.show();
        mTipCustomDialog.setMesage("是否拨打电话");
    }

}
