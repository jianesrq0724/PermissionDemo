package com.ruiqin.permission.commonality.view;

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

import com.blankj.utilcode.util.AppUtils;
import com.ruiqin.permission.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ruiqin.shen
 * 类说明：权限提示Dialog
 */

public class PermissionTipDialog extends Dialog {
    @BindView(R.id.tv_message)
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
        ButterKnife.bind(this);
        initDialog();
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

    @OnClick({R.id.btn_negative, R.id.btn_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_negative:
                cancel();
                break;
            case R.id.btn_setting:
                onClickSetting();
                break;
        }
    }

    /**
     * 点击去设置
     */
    public void onClickSetting() {
        Uri packageURI = Uri.parse("package:" + AppUtils.getAppPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        mContext.startActivity(intent);
        cancel();
    }

    public interface OnClickListener {
        void onClick();
    }

}
