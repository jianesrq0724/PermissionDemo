package com.ruiqin.permission.crash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ruiqin.permission.R;
import com.ruiqin.permission.base.BaseActivity;

import butterknife.BindView;

public class CrashInfoActivity extends BaseActivity {


    private static final String EXTRA_CONTENT = "content";
    @BindView(R.id.textView)
    TextView textView;

    public static void newInstance(Context context, String content) {
        Intent intent = new Intent(context.getApplicationContext(), CrashInfoActivity.class);
        intent.putExtra(EXTRA_CONTENT, content);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_info);
        getIntentData();
    }

    private void getIntentData() {
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra(EXTRA_CONTENT);
            textView.setText(stringExtra);
        }
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }
}
