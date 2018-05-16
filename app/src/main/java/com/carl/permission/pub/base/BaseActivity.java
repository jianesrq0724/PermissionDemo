package com.carl.permission.pub.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.carl.permission.R;
import com.carl.permission.pub.loading.LoadingDialog;
import com.carl.permission.pub.loading.interfaces.ILoading;
import com.carl.permission.pub.utils.ActivityCollector;
import com.carl.permission.pub.utils.ToolbarManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements ILoading {

    public Context mContext;
    public T mPresenter;
    protected boolean isDestroy;
    /**
     * 防止重复点击设置的标志，涉及到点击打开其他Activity时，将该标志设置为false，在onResume事件中设置为true
     */
    private boolean clickable = true;

    private FrameLayout mContentView;
    private Toolbar mToolbar;
    public ToolbarManager mToolbarManager;

    @Override
    protected void onResume() {
        super.onResume();
        clickable = true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载main的布局
        setContentView(R.layout.pub_activity_base);
        //加载子类的布局
        setContentView(getLayoutId());
        mContext = this;
        ActivityCollector.addActivity(this);
        initLoading();
        initToolBar();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        findView();
        initData();
        initView();
        setOnInteractListener();
        isDestroy = false;
    }

    /**
     * 初始化toolBar
     */
    private void initToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mToolbarManager = new ToolbarManager(mContext, mToolbar, actionBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 返回
     */
    private void goBack() {
        finish();
    }

    @Override
    public void setContentView(int layoutResID) {
        if (R.layout.pub_activity_base == layoutResID) {
            super.setContentView(R.layout.pub_activity_base);
            mContentView = (FrameLayout) findViewById(R.id.content_view_fl);
            mContentView.removeAllViews();
        } else if (layoutResID != R.layout.pub_activity_base) {
            View addView = LayoutInflater.from(this).inflate(layoutResID, null);
            mContentView.addView(addView);
        }
    }

    protected abstract void findView();

    protected abstract void initData();

    protected abstract void initView();


    public abstract void setOnInteractListener();

    public abstract T createPresenter();

    public abstract int getLayoutId();

    LoadingDialog mLoadingDialog;

    /**
     * 初始化进度条
     */
    private void initLoading() {
        mLoadingDialog = new LoadingDialog(mContext);
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        ActivityCollector.removeActivity(this);
        isDestroy = true;
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (clickable) {
            lockClick();
            super.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 锁定点击
     */
    protected void lockClick() {
        clickable = false;
    }

    private PermissionsResultListener mPermissionListener;

    List<String> mPermissionList = new ArrayList<>();


    /**
     * 分装权限申请
     */
    public void permissions(String desc, String[] permissions, PermissionsResultListener permissionListener) {
        mPermissionList.clear();
        mPermissionListener = permissionListener;
        /**
         * 判断哪些权限未授予
         */
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(mContext, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        /**
         * 判断未授予的权限是否为空
         * 为空，表示都授予了
         */
        if (mPermissionList.isEmpty()) {
            mPermissionListener.onPermissionGranted();
        } else {//请求权限方法
            ActivityCompat.requestPermissions(this, mPermissionList.toArray(new String[mPermissionList.size()]), 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //部分手机上，可能会出现grantResult length为0的情况
        if (grantResults.length == 0) {
            mPermissionListener.onPermissionDenied();
            return;
        }

        int grantLength = grantResults.length;
        for (int i = 0; i < grantLength; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                mPermissionListener.onPermissionDenied();
                return;
            }
        }
        mPermissionListener.onPermissionGranted();
    }

    /**
     * 权限通信接口
     */
    public interface PermissionsResultListener {

        void onPermissionGranted();

        void onPermissionDenied();

    }

}
