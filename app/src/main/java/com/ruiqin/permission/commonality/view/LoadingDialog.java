package com.ruiqin.permission.commonality.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

import com.ruiqin.permission.R;

import butterknife.ButterKnife;



/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.CustomDialogTheme);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

}
