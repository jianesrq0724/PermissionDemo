package com.carl.permission.pub.utils;

import android.util.Log;


public class LogUtils {
    public static final String TAG = "TAG";   //自定义TAG

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int LEVEL = VERBOSE;

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            if (checkNetWorkEnvironment()) {
                Log.v(tag, msg);
            }
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            if (checkNetWorkEnvironment()) {
                Log.d(tag, msg);
            }
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            if (checkNetWorkEnvironment()) {
                Log.i(tag, msg);
            }
        }
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            if (checkNetWorkEnvironment()) {
                Log.w(tag, msg);
            }
        }
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            if (checkNetWorkEnvironment()) {
                Log.e(tag, msg);
            }
        }
    }

    /**
     * 非正式环境打印日志
     *
     * @return
     */
    private static boolean checkNetWorkEnvironment() {
        return false;
    }

}
