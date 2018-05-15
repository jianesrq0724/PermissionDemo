package com.carl.permission.pub.utils;

import org.joda.time.DateTime;

import java.util.Locale;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/23
 */

public class TimeUtils {


    /**
     * 毫秒转化成日时分
     */
    public static String formatTime(long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        return sb.toString();
    }


    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2DateWithChinese(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy年MM月dd日 HH:mm", Locale.CHINESE);
    }

    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2DateWithChinese2(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("MM/dd HH:mm", Locale.CHINESE);
    }

    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2DateWithChinese3(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("MM-dd HH:mm", Locale.CHINESE);
    }

    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2Date(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy/MM/dd HH:mm:ss", Locale.CHINESE);
    }

    public static String TimeFormatDate(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("MM/dd HH:mm:ss", Locale.CHINESE);
    }

    public static String TimeFormat3Date(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy/MM/dd HH:mm", Locale.CHINESE);
    }

    public static String TimeFormat2DataV2(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy-MM-dd");
    }

    public static String TimeFormat2DataV3(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
    }

    public static String TimeFormat2DataV4(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy-MM-dd HH:mm", Locale.CHINESE);
    }

    public static String TimeFormat4SH(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("MM/dd HH:mm", Locale.CHINESE);
    }

    public static String TimeFormat4SHInHM(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("HH:mm", Locale.CHINESE);
    }

    /**
     * 转换成小时分钟
     */
    public static String TimeFormat2HM(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("HH:mm");
    }

    public static String TimeFormat2MS(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("mm:ss");
    }
}
