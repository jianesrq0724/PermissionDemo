package com.ruiqin.permissiondemo.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ruiqin.permissiondemo.R;
import com.ruiqin.permissiondemo.base.BaseActivity;

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
}
