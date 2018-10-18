package com.test.yanxiu.common_base.route;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.test.yanxiu.common_base.utils.checkLogin.AvoidOnResult;

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

    public static void startActivityForResult(Activity activity, String path, AvoidOnResult.Callback callback) {
        Postcard postcard = ARouter.getInstance().build(path);
        LogisticsCenter.completion(postcard);
        Class<?> destination = postcard.getDestination();
        Bundle bundle = postcard.getExtras();
        Intent intent = new Intent(activity, destination);
        intent.putExtras(bundle);
        new AvoidOnResult(activity).startForResult(intent, callback);
    }

    public static Fragment getFragmentByPath(String path) {
        Fragment fragment = (Fragment) ARouter.getInstance().build(path).navigation();
        return fragment;
    }
}
