package com.carl.permission.pub.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

import com.carl.permission.R;


/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class LoadingDialog extends Dialog {

    private LoadingHelper mLoadingHelper;

    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.CustomDialogTheme);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mLoadingHelper = new LoadingHelper();
    }

    @Override
    public void show() {
        mLoadingHelper.addCount();
        if (!isShowing()) {
            super.show();
        }
    }

    @Override
    public void dismiss() {
        mLoadingHelper.subCount();
        if (mLoadingHelper.isZero()) {
            super.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

}
