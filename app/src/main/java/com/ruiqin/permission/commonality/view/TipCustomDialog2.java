package com.ruiqin.permission.commonality.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiqin.permission.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ruiqin.shen
 * 类说明：自定义提示Dialog
 */

public class TipCustomDialog2 extends Dialog {
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.btn_position)
    Button mBtnPosition;
    private Context mContext;
    @BindView(R.id.iv_close)
    ImageView mIvClose;

    private OnClickListener mPositiveButtonListener;
    private String mPositiveButtonText;
    private String mMessage;

    public TipCustomDialog2(@NonNull Context context) {
        this(context, R.style.CustomDialogTheme);
    }

    public TipCustomDialog2(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    /**
     * 设置确定按钮的点击事件
     *
     * @param text
     * @param listener
     */
    public void setPositiveButton(String text, final OnClickListener listener) {
        mPositiveButtonText = text;
        mPositiveButtonListener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tip_custom2);
        ButterKnife.bind(this);
        initDialog();
        initData();
    }

    /**
     * 初始化data
     */
    private void initData() {

        if (mPositiveButtonText != null) {
            mBtnPosition.setText(mPositiveButtonText);
        }

        if (mMessage != null) {
            mTvMessage.setText(mMessage);
        }

        if (canBack) {//可以返回的时候，显示x
            mIvClose.setVisibility(View.VISIBLE);
        } else {
            mIvClose.setVisibility(View.GONE);
        }
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
        mMessage = message;
    }

    public void setCanBack(boolean canBack) {
        this.canBack = canBack;
    }

    @OnClick({R.id.iv_close, R.id.btn_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                cancel();
                break;
            case R.id.btn_position:
                cancel();
                if (mPositiveButtonListener != null) {
                    mPositiveButtonListener.onClick();
                }
                break;
        }
    }

    public interface OnClickListener {
        void onClick();
    }

    private boolean canBack = true;

    @Override
    public void onBackPressed() {
        if (canBack) {
            super.onBackPressed();
        }
    }
}
