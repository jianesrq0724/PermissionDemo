package com.carl.permission.pub.utils;

import android.widget.Toast;

import com.carl.permission.BaseApplication;


/**
 * Created by ruiqin.shen
 * 类说明：
 */

public final class ToastUtils {

    /**
     * 显示短时吐司
     *
     * @param text 文本
     */
    public static void showShort(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示长时吐司
     *
     * @param text 文本
     */
    public static void showLong(CharSequence text) {
        show(text, Toast.LENGTH_LONG);
    }

    private static Toast sToast;


    /**
     * 显示吐司
     *
     * @param text     文本
     * @param duration 显示时长
     */
    private static void show(CharSequence text, int duration) {
        if (sToast == null) {
            sToast = Toast.makeText(BaseApplication.getContext(), text, duration);
        } else {
            sToast.setText(text);
        }
        sToast.show();
    }

    /**
     * 取消吐司显示
     */
    public static void cancel() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }
}
