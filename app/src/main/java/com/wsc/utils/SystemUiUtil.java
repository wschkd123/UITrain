package com.wsc.utils;

import android.app.Activity;
import android.view.View;

/**
 * @author shichao5
 * @date 2018/11/5
 * @describ
 */
public class SystemUiUtil {

    public void hideSystemUI(Activity context){
        context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //隐藏导航栏
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //视图延伸至导航栏区域，导航栏上浮于视图之上
                |View.SYSTEM_UI_FLAG_FULLSCREEN //状态栏隐藏，效果同设置WindowManager.LayoutParams.FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //视图延伸至状态栏区域，状态栏上浮于视图之上
                |View.SYSTEM_UI_FLAG_IMMERSIVE); //沉浸模式, 隐藏状态栏和导航栏, 并且在第一次会弹泡提醒,
        // 并且在状态栏区域滑动可以呼出状态栏（这样会系统会清楚之前设置的View.SYSTEM_UI_FLAG_FULLSCREEN或View.SYSTEM_UI_FLAG_HIDE_NAVIGATION标志）。
        // 使之生效，需要和View.SYSTEM_UI_FLAG_FULLSCREEN，View.SYSTEM_UI_FLAG_HIDE_NAVIGATION中的一个或两个同时设置。
    }

    public void showSystemUI(Activity context){
        context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    public static void yzbHideSystemUI(Activity context) {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        context.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        /*getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        *//*| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION*//*
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        *//*| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar*//*
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);*/
    }
}
