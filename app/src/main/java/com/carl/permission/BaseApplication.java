package com.carl.permission;

import android.app.Application;
import android.content.Context;

import com.carl.permission.pub.crash.CrashHandler;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        MyThread myThread = new MyThread();
        new Thread(myThread).start();

    }

    /**
     * 耗时操作启动分线程
     */
    public class MyThread implements Runnable {

        @Override
        public void run() {
            //全局异常捕捉
            if (BuildConfig.IS_CRASH) {
                CrashHandler crashHandler = CrashHandler.getInstance();
                crashHandler.init(getApplicationContext());
            }
        }
    }

    public static Context getContext() {
        return mContext;
    }
}
