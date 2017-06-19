package com.ruiqin.permissiondemo;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

/**
 * Created by Ruiqin on 2017/6/18.
 */

public class App extends Application {
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        Utils.init(mAppContext);
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
