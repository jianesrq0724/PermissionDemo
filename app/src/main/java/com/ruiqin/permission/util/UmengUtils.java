package com.ruiqin.permission.util;

import android.content.Context;

import com.ruiqin.permission.network.HttpClient;
import com.umeng.analytics.MobclickAgent;

import java.util.Map;


/**
 * Created by ruiqin.shen
 * 类说明：友盟统计工具类
 */

public class UmengUtils {
    /**
     * 自定义事件统计
     */
    public static void eventById(Context context, int id) {
        if (checkNetWorkEnvironment()) {
            MobclickAgent.onEvent(context, context.getString(id));
        }
    }

    /**
     * 自定义事件map统计
     *
     * @param context
     * @param id
     * @param map
     */
    public static void onEvent(Context context, int id, Map map) {
        if (checkNetWorkEnvironment()) {//正式环境才统计
            MobclickAgent.onEvent(context, context.getString(id), map);
        }
    }

    /**
     * 友盟统计onResume
     *
     * @param context
     */
    public static void onResume(Context context) {
        if (checkNetWorkEnvironment()) {
            MobclickAgent.onResume(context);
        }
    }

    /**
     * 友盟统计onPause
     *
     * @param context
     */
    public static void onPause(Context context) {
        if (checkNetWorkEnvironment()) {
            MobclickAgent.onPause(context);
        }
    }

    /**
     * 检出需要统计的网络环境
     *
     * @return
     */
    private static boolean checkNetWorkEnvironment() {
        if (HttpClient.ENVIRONMENT == 2) {
            return true;
        }
        return false;
    }
}
