package com.carl.permission.pub.utils;

import java.util.ArrayList;

/**
 * 类描述:
 *
 * @author : ruiqin.shen
 * @date : 2017/11/28
 */
public class OptionDataUtils {

    public static void initTimeData(int startHour, int startMinute, int endHour, int endMinute, ArrayList<String> options1Items, ArrayList<ArrayList<String>> options2Items) {

        /**
         * 限制数据范围
         */
        if (startHour > endHour) {
            startHour = 0;
            startMinute = 0;
        }

        if (options1Items == null) {
            throw new RuntimeException("data not init");
        }

        if (options2Items == null) {
            throw new RuntimeException("data not init");
        }


        //开始时间等于结束时间
        if (startHour == endHour) {
            options1Items.add(startHour + "");
            ArrayList<String> options2Items_01 = new ArrayList<>();
            for (int i = startMinute; i < endMinute; i++) {
                options2Items_01.add(i + "");
            }
            options2Items.add(options2Items_01);
        } else if (startHour == endHour - 1) {
            //小时不超过1
            options1Items.add(startHour + "");
            options1Items.add(endHour + "");
            ArrayList<String> options2Items_01 = new ArrayList<>();
            for (int i = startMinute; i < 60; i++) {
                options2Items_01.add(i + "");
            }

            options2Items.add(options2Items_01);
            ArrayList<String> options2Items_02 = new ArrayList<>();
            for (int i = 0; i < endMinute; i++) {
                options2Items_02.add(i + "");
            }
            options2Items.add(options2Items_02);
        } else if (startHour < endHour - 1) {
            options1Items.add(startHour + "");
            ArrayList<String> options2Items_01 = new ArrayList<>();
            for (int i = startMinute; i < 60; i++) {
                options2Items_01.add(i + "");
            }
            options2Items.add(options2Items_01);

            for (int i = startHour + 1; i < endHour; i++) {
                options1Items.add(i + "");
                ArrayList<String> options2Item = new ArrayList<>();
                for (int j = 0; j < 60; j++) {
                    options2Item.add(j + "");
                }
                options2Items.add(options2Item);
            }
            options1Items.add(endHour + "");

            ArrayList<String> options2Items_02 = new ArrayList<>();
            for (int i = 0; i < endMinute; i++) {
                options2Items_02.add(i + "");
            }
            options2Items.add(options2Items_02);
        }
    }
}
