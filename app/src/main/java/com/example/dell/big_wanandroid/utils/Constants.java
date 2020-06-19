package com.example.dell.big_wanandroid.utils;

import android.graphics.Color;
import android.os.Environment;

import com.example.dell.big_wanandroid.MainUi.MyApp;

import java.io.File;

/**
 * Created by asus on 2019/3/5.
 */

public interface Constants {
    boolean isDebug = true;

    static final String  BUGLY_ID = "8557f3fc75";


    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "codeest" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY="com.baidu.geek.fileprovider";

    //网络缓存的地址
    String PATH_DATA = MyApp.getInstance().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";
    String DATA = "data";
    //夜间模式
    String MODE = "mode";
    String NIGHT_CURRENT_FRAG_POS = "fragment_pos";
    //保存设置日夜间模式时碎片的position
    String DAY_NIGHT_FRAGMENT_POS = "day_night_fragment_pos";


    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };
}
