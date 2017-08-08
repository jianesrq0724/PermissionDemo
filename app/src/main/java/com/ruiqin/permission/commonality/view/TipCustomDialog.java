package com.ruiqin.permission.commonality.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ruiqin.permission.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ruiqin.shen
 * 类说明：自定义提示Dialog
 */

public class TipCustomDialog extends Dialog {
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.btn_negative)
    Button mBtnNegative;
    @BindView(R.id.btn_position)
    Button mBtnPosition;
    private Context mContext;

    private OnClickListener mPositiveButtonListener;
    private OnClickListener mNegativeButtonListener;
    private String mPositiveButtonText;
    private String mNegativeButtonText;

    public TipCustomDialog(@NonNull Context context) {
        this(context, R.style.CustomDialogTheme);
    }

    public TipCustomDialog(@NonNull Context context, @StyleRes int themeResId) {
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

    /**
     * 设置取消按钮的点击事件
     *
     * @param text
     * @param listener
     */
    public void setNegativeButton(String text, final OnClickListener listener) {
        mNegativeButtonText = text;
        mNegativeButtonListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tip_custom);
        ButterKnife.bind(this);
        initDialog();
        initData();
    }

    /**
     * 初始化data
     */
    private void initData() {
        if (mNegativeButtonText != null) {
            mBtnNegative.setText(mNegativeButtonText);
        }

        if (mPositiveButtonText != null) {
            mBtnPosition.setText(mPositiveButtonText);
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
        if (mTvMessage != null) {
            mTvMessage.setText(message);
        }
    }

    @OnClick({R.id.btn_negative, R.id.btn_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_negative:
                if (mNegativeButtonListener != null) {
                    mNegativeButtonListener.onClick();
                    cancel();
                }
                break;
            case R.id.btn_position:
                if (mPositiveButtonListener != null) {
                    mPositiveButtonListener.onClick();
                    cancel();
                }
                break;
        }
    }

    public interface OnClickListener {
        void onClick();
    }

    /**
     * 屏蔽返回
     */
    @Override
    public void onBackPressed() {
    }
}
