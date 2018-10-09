package com.test.yanxiu.common_base.base.ui.toolbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import java.lang.ref.SoftReference;

import static com.test.yanxiu.common_base.base.ui.toolbar.Utils.alphaColor;
import static com.test.yanxiu.common_base.base.ui.toolbar.Utils.isLollipop;

/**
 * 改变风格的具体实现
 *
 * @author Sharry <a href="frankchoochina@gmail.com">Contact me.</a>
 * @version 1.0
 * @since 2018/8/27 23:41
 */
public class AppBarHelper {

    private static final int DEFAULT_OPTIONS = 0;
    private int mOptions = DEFAULT_OPTIONS;
    private Window mWindow;
    private Activity mActivity;

    private AppBarHelper(Context context) {
        if (context instanceof Activity) {
            mActivity = new SoftReference<>((Activity) context).get();
            mWindow = mActivity.getWindow();
        } else {
            throw new IllegalArgumentException("AppBarHelper.Constructor -> AppBarHelper 只接收 Activity 类型的 Context");
        }
    }

    public static AppBarHelper with(Context context) {
        return new AppBarHelper(context);
    }

    /**
     * 设置StatusBar的风格
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AppBarHelper setStatusBarStyle(Style style) {
        if (!isLollipop()) return this;
        switch (style) {
            // 设置状态栏为全透明
            case TRANSPARENT: {
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                mOptions = mOptions | option;
                mWindow.setStatusBarColor(Color.TRANSPARENT);
                break;
            }
            // 设置状态栏为半透明
            case TRANSLUCENCE: {
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                mOptions = mOptions | option;
                mWindow.setStatusBarColor(alphaColor(Color.BLACK, 0.3f));
                break;
            }
            // 隐藏状态栏
            case HIDE: {
                int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
                mOptions = mOptions | option;
                break;
            }
            // 清除透明状态栏
            case DEFAULT: {
                mOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                //获取当前Application主题中的状态栏Color
                TypedValue typedValue = new TypedValue();
                mActivity.getTheme().resolveAttribute(android.R.attr.colorPrimaryDark, typedValue, true);
                int color = typedValue.data;
                mWindow.setStatusBarColor(color);
            }
        }
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AppBarHelper setStatusBarColor(int color) {
        if (!isLollipop()) return this;
        mOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        mWindow.setStatusBarColor(color);
        return this;
    }

    /**
     * 设置NavigationBar的风格
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AppBarHelper setNavigationBarStyle(Style style) {
        if (!isLollipop()) return this;
        switch (style) {
            // 设置导航栏为全透明
            case TRANSPARENT: {
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
                mOptions = mOptions | option;
                mWindow.setNavigationBarColor(Color.TRANSPARENT);
                break;
            }
            // 设置导航栏为半透明
            case TRANSLUCENCE: {
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
                mOptions = mOptions | option;
                mWindow.setNavigationBarColor(alphaColor(Color.BLACK, 0.3f));
                break;
            }
            //隐藏导航栏
            case HIDE: {
                int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                mOptions = mOptions | option;
                break;
            }
            case DEFAULT: {
                mOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                //获取当前Activity主题中的color
                TypedValue typedValue = new TypedValue();
                mActivity.getTheme().resolveAttribute(android.R.attr.colorPrimaryDark, typedValue, true);
                int color = typedValue.data;
                mWindow.setNavigationBarColor(color);
            }
        }
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AppBarHelper setNavigationBarColor(int color) {
        if (!isLollipop()) return this;
        mOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        mWindow.setNavigationBarColor(color);
        return this;
    }

    /**
     * 隐藏所有Bar(全屏模式)
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AppBarHelper setAllBarsHide() {
        if (!isLollipop()) return this;
        int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        mOptions = option;
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void apply() {
        if (!isLollipop()) return;
        if (mOptions != DEFAULT_OPTIONS) {
            View decorView = mWindow.getDecorView();
            decorView.setSystemUiVisibility(mOptions);
        }
    }

}
