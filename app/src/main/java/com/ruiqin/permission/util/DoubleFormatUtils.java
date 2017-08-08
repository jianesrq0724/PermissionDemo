package com.ruiqin.permission.util;

/**
 * Created by ruiqin.shen
 * 类说明：将小数转为小数点后两位的字符串
 */

public class DoubleFormatUtils {
    public static String format(double value) {
        String formatStr = String.format("%.2f", value);
        return formatStr;
    }
}
