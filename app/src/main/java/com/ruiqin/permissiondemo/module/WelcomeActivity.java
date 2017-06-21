package com.ruiqin.permissiondemo.module;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.ruiqin.permissiondemo.R;
import com.ruiqin.permissiondemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by ruiqin.shen on 2017/6/18.
 * 类说明：欢迎页面
 */
public class WelcomeActivity extends BaseActivity {

    String[] permissions = new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
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

        /**
         * 判断是否为空
         */
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            delayEntryPage();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(WelcomeActivity.this, permissions, 1);
        }
    }

    /**
     * 延迟进入页面，为了增加用户体检效果
     * 一般在欢迎界面停留几秒
     * 使用RxJava的标识符来实现延迟效果
     */
    private void delayEntryPage() {
        Flowable.timer(1200, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Long aLong) throws Exception {
                startActivity(MainActivity.newIntent(mContext));
                finish();
            }
        }, Throwable::printStackTrace);
    }


    boolean mShowRequestPermission = true;//用户是否禁止权限

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        //判断是否勾选禁止后不再询问
                        boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(WelcomeActivity.this, permissions[i]);
                        if (showRequestPermission) {//
                            judgePermission();//重新申请权限
                            return;
                        } else {
                            mShowRequestPermission = false;//已经禁止
                        }
                    }
                }
                delayEntryPage();
                break;
            default:
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }
}
