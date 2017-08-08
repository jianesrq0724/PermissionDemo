package com.ruiqin.permission.util;


import com.blankj.utilcode.util.StringUtils;
import com.ruiqin.permission.MyApplication;

import static com.ruiqin.permission.constant.Constant.SP_TOKEN;

/**
 * Created by ruiqin.shen
 * 类说明：数据仓库
 */

public class DataWareHouse {

    /**
     * 设置手机号码
     *
     * @param mobileNum
     */
    public static void setMobileNum(String mobileNum) {
        MyApplication.mobileNum = mobileNum;
    }

    /**
     * 获取手机号码
     */
    public static String getMobileNum() {
        return MyApplication.mobileNum;
    }


    /**
     * 保存用户token ，登陆成功之后保存
     */
    public static void setToken(String token) {
        MyApplication.token = token;
        SPUtils.getInstance().put(SP_TOKEN, token);
    }

    /**
     * 获取用户token
     */
    public static String getToken() {
        if (!StringUtils.isEmpty(MyApplication.token)) {
            return MyApplication.token;
        } else {
            return SPUtils.getInstance().getString(SP_TOKEN);
        }
    }

}
