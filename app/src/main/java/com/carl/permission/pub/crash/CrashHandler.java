package com.carl.permission.pub.crash;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.carl.permission.pub.utils.FileUtils;
import com.carl.permission.pub.utils.TimeUtils;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * Created by ruiqin.shen
 * 类说明：
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/com.baidaibao/log/";
    private static final String FILE_NAME = "crash";

    //log文件的后缀名
    private static final String FILE_NAME_SUFFIX = ".txt";

    private static CrashHandler sInstance = new CrashHandler();

    //系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private Context mContext;

    //构造方法私有，防止外部构造多个实例，即采用单例模式
    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        //获取系统默认的异常处理器
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        //获取Context，方便内部使用
        mContext = context.getApplicationContext();
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //打印出当前调用栈信息
        ex.printStackTrace();
        //保存日志文件到本地
        saveCrashInfo2File(ex);
        //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
        uploadExceptionToServer();
        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, ex);
        } else {
            System.exit(0);
        }
    }


    /**
     * 保存错误信息到文件中
     */
    private void saveCrashInfo2File(Throwable ex) {
        StringBuffer crashInfo = new StringBuffer();
        //时间
        crashInfo.append(TimeUtils.TimeFormat2Date(System.currentTimeMillis())).append("\n");
        //手机信息
        crashInfo.append(getPhoneInfo()).append("\n");
        //奔溃信息
        crashInfo.append(throwable2String(ex));
        FileUtils.writeCrashToSDCard(crashInfo.toString());
    }

    private String throwable2String(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        ex.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    /**
     * 获取手机信息和应用版本信息
     */
    private String getPhoneInfo() {
        //手机信息
        StringBuffer phoneInfo = new StringBuffer();
        //应用的版本名称和版本号
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //App版本
        phoneInfo.append("App Version: ").append(pi.versionName).append("_").append(pi.versionCode).append("\n");
        //android版本号
        phoneInfo.append("OS Version: ").append(Build.VERSION.RELEASE).append("_").append(Build.VERSION.SDK_INT).append("\n");
        //手机制造商
        phoneInfo.append("Vendor: ").append(Build.MANUFACTURER).append("\n");
        //手机型号
        phoneInfo.append("Model: ").append(Build.MODEL).append("\n");
        //cpu架构
        phoneInfo.append("CPU ABI: ").append(Build.CPU_ABI).append("\n");
        return phoneInfo.toString();
    }

    /**
     * 上传到服务器
     */
    private void uploadExceptionToServer() {

    }

}