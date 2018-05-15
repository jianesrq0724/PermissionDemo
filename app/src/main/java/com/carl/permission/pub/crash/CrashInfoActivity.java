package com.carl.permission.pub.crash;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.carl.permission.R;
import com.carl.permission.pub.base.BaseActivity;
import com.carl.permission.pub.base.BasePresenter;


public class CrashInfoActivity extends BaseActivity {


    private static final String EXTRA_CONTENT = "content";

    TextView textView;

    public static void newInstance(Context context, String content) {
        Intent intent = new Intent(context.getApplicationContext(), CrashInfoActivity.class);
        intent.putExtra(EXTRA_CONTENT, content);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void findView() {
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void initData() {
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra(EXTRA_CONTENT);
            textView.setText(stringExtra);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    public void setOnInteractListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crash_info;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }


}
