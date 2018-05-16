package com.carl.permission.module.welcome;

import android.Manifest;
import android.annotation.SuppressLint;

import com.carl.permission.R;
import com.carl.permission.module.home.view.MainActivity;
import com.carl.permission.pub.base.BaseActivity;
import com.carl.permission.pub.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/15
 */
public class WelcomeActivity extends BaseActivity {

    String[] permissions = new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void findView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //隐藏ToolBar
        mToolbarManager.hideToolBar();

        permissions("多个权限", permissions, new PermissionsResultListener() {
            @Override
            public void onPermissionGranted() {
                delayEntryPage();
            }

            @Override
            public void onPermissionDenied() {
                delayEntryPage();
            }
        });
    }

    @Override
    public void setOnInteractListener() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }


    /**
     * 延迟进入页面，为了增加用户体检效果
     * 一般在欢迎界面停留几秒
     * 使用RxJava的标识符来实现延迟效果
     */
    @SuppressLint("CheckResult")
    private void delayEntryPage() {
        Flowable.timer(1200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    //跳转到主界面
                    MainActivity.startActivity(mContext);
                    finish();
                }, Throwable::printStackTrace);
    }


}
