package com.ruiqin.permission.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ruiqin.permission.R;
import com.ruiqin.permission.util.ActivityCollector;
import com.ruiqin.permission.util.InstanceUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ruiqin.shen.
 * 类说明：所有的Activity的基类
 * 在onCreate调用present的setVM方法，将View和Model关联起来
 */
public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {
    public P mPresenter;
    public Context mContext;
    private Unbinder mBind;
    private FrameLayout contentView;
    private Toolbar mToolbar;
    protected TextView mToolbarTitle;
    protected boolean isDestroy;
    //防止重复点击设置的标志，涉及到点击打开其他Activity时，将该标志设置为false，在onResume事件中设置为true
    private boolean clickable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        if (canAddCollector()) {
            ActivityCollector.addActivity(this);
        }
        isDestroy = false;
        mContext = this;
        if (this instanceof BaseView) {
            mPresenter = InstanceUtil.getInstance(this, 0);
            mPresenter.setVM(this, InstanceUtil.getInstance(this, 1));
        }
        initToolBar();
    }

    public boolean canAddCollector() {
        return true;
    }

    /**
     * 是否可以返回
     *
     * @return
     */
    public boolean canBack() {
        return true;
    }

    public void hideToolBar() {
        mToolbar.setVisibility(View.GONE);
    }

    /**
     * 初始化Toolbar
     */
    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && canBack()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (canBack()) {
                    finish();
                }
                break;
        }
        return true;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (R.layout.activity_base == layoutResID) {
            super.setContentView(R.layout.activity_base);
            contentView = (FrameLayout) findViewById(R.id.layout_center);
            contentView.removeAllViews();
        } else if (layoutResID != R.layout.activity_base) {
            View addView = LayoutInflater.from(this).inflate(layoutResID, null);
            contentView.addView(addView);
            mBind = ButterKnife.bind(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroy = true;
        ActivityCollector.removeActivity(this);
        mBind.unbind();
    }

    /**
     * 布局中Fragment的ID
     */
    protected abstract int getFragmentContentId();

    /**
     * 添加fragment
     *
     * @param fragment
     */
    protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    /**
     * 移除fragment
     */
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    //友盟统计
    @Override
    protected void onResume() {
        super.onResume();
        clickable = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 锁定点击
     */
    protected void lockClick() {
        clickable = false;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        if (clickable) {
            lockClick();
            super.startActivityForResult(intent, requestCode, options);
        }
    }
}
