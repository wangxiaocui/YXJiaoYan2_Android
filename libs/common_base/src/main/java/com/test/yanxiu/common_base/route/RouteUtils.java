package com.test.yanxiu.common_base.route;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Hu Chao on 18/9/28.
 */
public class RouteUtils {

    public static void startActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }


    public static void startActivityForResult(Activity activity, String path, int code) {
        ARouter.getInstance().build(path).navigation(activity, code);
    }


    public static Fragment getFramentByPath(String path){
        Fragment fragment = (Fragment) ARouter.getInstance().build(path).navigation();
        return fragment;
    }
}
