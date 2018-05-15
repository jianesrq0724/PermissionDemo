package com.carl.permission.pub.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.carl.permission.BaseApplication;


/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class ClipboardUtils {
    public static void copyText(CharSequence text) {
        ClipboardManager clipboardManager = (ClipboardManager) BaseApplication.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("text", text));
    }

    /**
     * 获取剪贴板的文本
     *
     * @return 剪贴板的文本
     */
    public static CharSequence getText() {
        ClipboardManager clipboard = (ClipboardManager) BaseApplication.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).coerceToText(BaseApplication.getContext());
        }
        return null;
    }
}
