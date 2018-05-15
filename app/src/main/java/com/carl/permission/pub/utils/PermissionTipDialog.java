package com.carl.permission.pub.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import com.carl.permission.R;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/23
 * 类说明：权限提示Dialog
 */

public class PermissionTipDialog extends Dialog {
    TextView mTvMessage;
    private Context mContext;

    public PermissionTipDialog(@NonNull Context context) {
        this(context, R.style.CustomDialogTheme);
    }

    public PermissionTipDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_permission_tip);
        mTvMessage = findViewById(R.id.tv_message);
        initDialog();
        setOnclickListener();
    }

    private void setOnclickListener() {
        findViewById(R.id.btn_negative).setOnClickListener(v -> cancel());

        findViewById(R.id.btn_setting).setOnClickListener(v -> onClickSetting());
    }


    private void initDialog() {
        setCanceledOnTouchOutside(false);
    }

    /**
     * 更改提示信息
     *
     * @param message
     */
    public void setMesage(String message) {
        if (mTvMessage != null) {
            mTvMessage.setText("请您授予" + message + "权限，您可在“应用信息>权限”中打开权限");
        }
    }


    /**
     * 点击去设置
     */
    public void onClickSetting() {
        Uri packageURI = Uri.parse("package:" + mContext.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        mContext.startActivity(intent);
        cancel();
    }

    public interface OnClickListener {
        void onClick();
    }

}
