package com.carl.permission.pub.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class InstanceUtil {
    public static <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) o.getClass().getGenericSuperclass()).getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
