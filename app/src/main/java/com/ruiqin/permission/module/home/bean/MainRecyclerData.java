package com.ruiqin.permission.module.home.bean;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MainRecyclerData {
    private String message;
    private Class<?> cls;

    public MainRecyclerData(String message, Class<?> cls) {
        this.message = message;
        this.cls = cls;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
