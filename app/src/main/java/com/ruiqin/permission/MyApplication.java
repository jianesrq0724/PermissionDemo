package com.ruiqin.permission;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.ruiqin.permission.crash.CrashHandler;
import com.ruiqin.permission.greendao.gen.DaoMaster;
import com.ruiqin.permission.greendao.gen.DaoSession;
import com.ruiqin.permission.util.LogUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Ruiqin on 2017/6/26.
 */

public class MyApplication extends Application {
    private static Context mContext;
    private DaoSession daoSession;
    public static String token;//用户token
    public static String mobileNum;

    {
        PlatformConfig.setWeixin("wx4ce204c32f3c2181", "e5f37f10f34601a3e2d8516e8c09c834");
        PlatformConfig.setQQZone("1106116934", "dcPiNHxCFboZmfuj");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Utils.init(mContext);

        //友盟初始化
        UMShareAPI.get(this);

        /**
         * 初始化数据库
         */
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext, "test-db");
        Database writableDb = devOpenHelper.getWritableDb();
        daoSession = new DaoMaster(writableDb).newSession();

        initCrashHandler();
        initX5Environment();//x5内核初始化接口
    }

    /**
     * x5内核初始化接口
     */
    private void initX5Environment() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.e("myApplication", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    /**
     * 获得上下文
     */
    public static Context getAppContext() {
        return mContext;
    }

    private void initCrashHandler() {
        //设置该CrashHandler为程序的默认处理器
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

}
