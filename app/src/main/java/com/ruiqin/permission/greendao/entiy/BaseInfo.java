package com.ruiqin.permission.greendao.entiy;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ruiqin on 2017/6/26.
 */
@Entity
public class BaseInfo {
    @Id
    private Long id;

    @NotNull
    private String mobileNum;

    @Generated(hash = 186631975)
    public BaseInfo(Long id, @NotNull String mobileNum) {
        this.id = id;
        this.mobileNum = mobileNum;
    }

    @Generated(hash = 1463957903)
    public BaseInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNum() {
        return this.mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

}
