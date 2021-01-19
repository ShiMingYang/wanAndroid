package com.example.dell.big_wanandroid.MainUi;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.dell.big_wanandroid.utils.CommonUtils;
import com.example.dell.big_wanandroid.utils.Constants;
import com.example.dell.big_wanandroid.utils.SpUtil;
import com.example.dell.big_wanandroid.utils.UIModeUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;


/**
 * Created by asus on 2019/3/5.
 */

public class MyApp extends MultiDexApplication {
    private static MyApp sMyApp;
    //默认不是夜间模式
    public static int mMode = AppCompatDelegate.MODE_NIGHT_NO;
    public static int mWidthPixels;
    public static int mHeightPixels;

    //修复内存泄漏
//    private RefWatcher refWatcher;


    @Override
    public void onCreate() {
        super.onCreate();
       //友盟统计
        UMConfigure.init(this,"5cd4d3893fc1951245001331", "", UMConfigure.DEVICE_TYPE_PHONE, null);

         //腾讯Bugly
        // 获取当前包名
        String packageName = getApplicationContext().getPackageName();
        // 获取当前进程名
        String processName = CommonUtils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_ID, true, strategy);


        //友盟打log
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("2982687150", "411b8ceea8673de2194f69caca2ea311",
                "http://sns.whalecloud.com");
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        //检测内存泄漏
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);



        sMyApp = this;
        getScreenWH();
        setDayNightMode();





    }



    //修复内存泄漏
//    public static RefWatcher getRefWatcher(Context context) {
//        MyApp applicationContext = (MyApp) context.getApplicationContext();
//        return applicationContext.refWatcher;
//    }

    public static MyApp getsMyApp() {
        return sMyApp;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void setDayNightMode() {
        //根据sp里面的模式设置对应的日夜间模式
        mMode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        UIModeUtil.setAppMode(mMode);
    }

    //屏幕宽高
    private void getScreenWH() {
        WindowManager manger = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manger.getDefaultDisplay();
        DisplayMetrics metics = new DisplayMetrics();
        defaultDisplay.getMetrics(metics);
        mWidthPixels = metics.widthPixels;
        mHeightPixels = metics.heightPixels;
    }

    public static MyApp getInstance(){
        return sMyApp;
    }

}
