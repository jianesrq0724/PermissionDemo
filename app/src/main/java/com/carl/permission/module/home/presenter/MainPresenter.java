package com.carl.permission.module.home.presenter;

import com.carl.permission.pub.base.BasePresenter;
import com.carl.permission.module.home.interfaces.MainI;
import com.carl.permission.pub.utils.RxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class MainPresenter extends BasePresenter<MainI> {

    /**
     * 模拟网络请求
     */
    public void testLogin() {

        Disposable disposable = Flowable.timer(2 * 1000, TimeUnit.MILLISECONDS)
                .compose(RxUtils.<Long>getScheduler(true, getView()))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getView().loginSuccess("登录成功");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mRxManage.add(disposable);
    }
}
