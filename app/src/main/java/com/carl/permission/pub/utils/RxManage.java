package com.carl.permission.pub.utils;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class RxManage {
    private CompositeDisposable subscriptions = new CompositeDisposable();
    private HashMap<String, Disposable> mDisposableHashMap = new HashMap();

    public RxManage() {

    }

    public void add(Disposable m) {
        if (this.subscriptions == null) {
            this.subscriptions = new CompositeDisposable();
        }

        if (m != null && !this.subscriptions.isDisposed()) {
            this.subscriptions.add(m);
        }

    }

    public void add(String key, Disposable m) {
        if (m != null) {
            this.mDisposableHashMap.put(key, m);
            this.add(m);
        }

    }

    public void clear(String key) {
        Disposable disposable = (Disposable)this.mDisposableHashMap.get(key);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            this.subscriptions.delete(disposable);
        }

    }

    public void clear() {
        if (this.subscriptions != null) {
            this.subscriptions.dispose();
            this.subscriptions = null;
        }

    }

    public void addSubscription(Flowable observable, ResourceSubscriber subscriber) {
        if (observable != null && subscriber != null) {
            this.subscriptions.add((Disposable)observable.compose(RxUtils.rxScheduler()).subscribeWith(subscriber));
        }

    }
}
