package com.test.yanxiu.common_base.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import com.yanxiu.lib.yx_basic_library.util.YXSystemUtil;

import java.util.List;

/**
 * 该类对YXSystemUtil进行业务扩展，替代原先的DeviceUtil类
 * 比如增加方法，对底层方法装饰或者添加逻辑处理
 * Created by 杨小明 on 2018/8/14.
 */

public class SystemUtil extends YXSystemUtil {


    /**
     * 获取厂商品牌
     * 将空格换成"_"
     */
    public static String getBrandName() {
        String brand = Build.BRAND;
        if (brand == null || brand.length() <= 0) {
            return "";
        } else {
            return brand.replace(" ", "_");
        }
    }

    /**
     * 获取进程名称
     * @param context
     * @param pid
     * @return
     */
    public static String getProcessName(Context context,int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
