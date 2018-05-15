package com.carl.permission.pub.base;


import com.carl.permission.pub.utils.RxManage;

import java.lang.ref.WeakReference;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/9
 */
public class BasePresenter<T> {

    public RxManage mRxManage = new RxManage();

    protected WeakReference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
    }

    public T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }


    public void detachView() {
        if (mViewRef != null) {
            //解除关联
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
