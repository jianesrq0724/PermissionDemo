package com.carl.permission.pub.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：对activity进行管理
 */
public class ActivityCollector {
    private static final List<Activity> activities = new ArrayList<Activity>();

    /**
     * 添加Activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 关闭所有的添加的Activity
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }


}
