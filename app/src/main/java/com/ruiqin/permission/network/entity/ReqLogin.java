package com.ruiqin.permission.network.entity;

/**
 * Created by ruiqin.shen
 * 类说明：请求登录
 */

public class ReqLogin {
    String Mobile;
    String Password;

    public ReqLogin(String mobile, String password) {
        Mobile = mobile;
        Password = password;
    }
}
