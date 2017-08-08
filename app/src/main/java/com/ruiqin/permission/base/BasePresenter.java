package com.ruiqin.permission.base;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class BasePresenter<V, M> {
    public V mView;
    public M mModel;

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
    }
}
