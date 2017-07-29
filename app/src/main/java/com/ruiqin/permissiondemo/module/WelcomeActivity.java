package com.ruiqin.permissiondemo.module;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.ruiqin.permissiondemo.R;
import com.ruiqin.permissiondemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by ruiqin.shen on 2017/6/18.
 * 类说明：欢迎页面
 */
public class WelcomeActivity extends BaseActivity {

    String[] permissions = new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    List<String> mPermissionList = new ArrayList<>();
    @BindView(R.id.textView)
    TextView mTextView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        judgePermission();
    }


    private static final int REQUEST_CODE_PERMISISON = 1;

    /**
     * 判断权限申请
     */
    private void judgePermission() {
        /**
         * 判断哪些权限未授予
         */
        mPermissionList.clear();
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
            ActivityCompat.requestPermissions(WelcomeActivity.this, permissions, REQUEST_CODE_PERMISISON);
        }
    }

    /**
     * 延迟进入页面，为了增加用户体检效果
     * 一般在欢迎界面停留几秒
     * 使用RxJava的标识符来实现延迟效果
     */
    private void delayEntryPage() {
        Flowable.timer(1200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    startActivity(MainActivity.newIntent(mContext));//跳转到主界面
                    finish();
                }, Throwable::printStackTrace);
    }

    /**
     * 在首页不管用户，不管用户拒绝还是同意，都进入首页
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISISON:
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
