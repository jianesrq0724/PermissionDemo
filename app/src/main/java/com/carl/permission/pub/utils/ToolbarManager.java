package com.carl.permission.pub.utils;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.carl.permission.R;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class ToolbarManager {

    private final Context mContext;
    private final Toolbar mToolbar;
    ActionBar mActionBar;

    protected TextView mToolbarTitle;

    public ToolbarManager(Context context, Toolbar toolbar, ActionBar actionBar) {
        mContext = context;
        mToolbar = toolbar;
        mActionBar = actionBar;
        mToolbarTitle = mToolbar.findViewById(R.id.toolbar_title);
    }

    public void hideToolBar() {
        mToolbar.setVisibility(View.GONE);
    }

    public void hideBackIcon() {
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    public void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }

}
