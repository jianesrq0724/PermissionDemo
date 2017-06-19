package com.ruiqin.permissiondemo.until;

import android.widget.Toast;

import com.ruiqin.permissiondemo.App;

/**
 * Created by Ruiqin on 2017/6/18.
 */

public class ToastUtils {
    private static Toast mToast;

    public static void show(String content) {
        if (mToast == null) {
            mToast = mToast.makeText(App.getAppContext(), content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }
}
